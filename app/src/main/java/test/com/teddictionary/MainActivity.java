package test.com.teddictionary;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Credentials;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import test.com.teddictionary.Model.Definition;
import test.com.teddictionary.Model.QuizletAddSetBody;
import test.com.teddictionary.Model.QuizletAddSetResponse;
import test.com.teddictionary.Model.QuizletSearchSets;
import test.com.teddictionary.data.Key;
import test.com.teddictionary.data.QuizletToken;
import test.com.teddictionary.event.MessageEvent;
import test.com.teddictionary.net.QuizletApi;
import test.com.teddictionary.net.SearchAsycTask;

@SuppressWarnings("squid:MaximumInheritanceDepth")
public class MainActivity extends AppCompatActivity {
    @BindView(R.id.search_keyword)
    AutoCompleteTextView mSearchKeyword;
    NetComponent mNetComponent;
    QuizletApi mQuizletApi;
    QuizletToken mQuizletToken;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mNetComponent = DaggerNetComponent.create();
        Retrofit retrofit = mNetComponent.netMaker().provideRetrofit();
        mQuizletApi = retrofit.create(QuizletApi.class);
        Key key = new Key();
        Uri callbackUri = getIntent().getData();
        if (callbackUri == null) {
            String authLink = String.format("https://quizlet.com/authorize?response_type=%s&client_id=%s&scope=%s&state=%s&redirect_uri=%s"
                    , key.getResponse_type()
                    , key.getClient_id()
                    , key.getScope()
                    , key.getState()
                    , key.getRedirect_uri());
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(authLink));
            startActivity(intent);
        } else {
            String code = callbackUri.getQueryParameter("code");
            mQuizletApi.getAccessToken(Credentials.basic(key.getClient_id(), key.getSecret()),
                    "authorization_code",
                    code,
                    key.getRedirect_uri()).enqueue(new Callback<QuizletToken>() {
                @Override
                public void onResponse(Call<QuizletToken> call, Response<QuizletToken> response) {
                    mQuizletToken = response.body();
                }

                @Override
                public void onFailure(Call<QuizletToken> call, Throwable t) {
                    Log.d("test", "error : " + t.getMessage());
                }
            });
        }
    }

    private void hideKeyboard() {
        View view = this.getCurrentFocus();
        if (isFinishing()) return;
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    public void addVoc2Quizlet(final Definition definition) {
        new Thread() {
            @Override
            public void run() {
                try {
                    int setId = getSetId();
                    if (setId <= 0) {
                        addSet(definition);
                    } else {

                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }


    public void addSet(Definition definition) throws IOException {
        QuizletAddSetBody quizletAddSetBody = new QuizletAddSetBody();
        quizletAddSetBody.setDefinitions(new String[]{"Ignore", definition.getDefinicion() + "\n" + definition.getExample()});
        quizletAddSetBody.setTerms(new String[]{"Ignore", mSearchKeyword.getText().toString()});
        quizletAddSetBody.setTitle(getTodayTitle());
        quizletAddSetBody.setLang_definitions("en");
        quizletAddSetBody.setLang_terms("en");
        Call<QuizletAddSetResponse> response = mQuizletApi.addSet( "Bearer " + mQuizletToken.getAccess_token(),
                quizletAddSetBody);
        response.enqueue(new Callback<QuizletAddSetResponse>() {
            @Override
            public void onResponse(Call<QuizletAddSetResponse> call, Response<QuizletAddSetResponse> response) {
                    Log.d("test", "result : " + call.request().headers().toString());
                    Log.d("test", "result : " + response.body().getSet_id());
            }

            @Override
            public void onFailure(Call<QuizletAddSetResponse> call, Throwable t) {
                Log.d("test", "result fail : " + t.getMessage());
            }
        });
    }

    private String getTodayTitle() {
        return new SimpleDateFormat("yyyy.MM.dd").format(new Date()) + Constants.NEW_SET_TITLE;
    }

    public int getSetId() throws IOException {
        String timeStamp = getTodayTitle();
        Call<QuizletSearchSets> repos = mQuizletApi.searchSets(timeStamp, Constants.QUIELET_USER_NAME);
        QuizletSearchSets result = repos.execute().body();
        if (result.getSets().isEmpty()) {
            return 0;
        }
        return result.getSets().get(0).getId();
    }

    @OnClick(R.id.search_btn)
    public void search() {
        String keyword = mSearchKeyword.getText().toString();

        if (keyword == null || keyword.length() < 1) {
            Toast.makeText(this, "Please give word to search", Toast.LENGTH_SHORT);
            return;
        }
        hideKeyboard();
        new SearchAsycTask().execute(keyword);
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(MessageEvent.SaveDefinition2Quizlet definition) {
        addVoc2Quizlet(definition.getDefinition());
    }
}
