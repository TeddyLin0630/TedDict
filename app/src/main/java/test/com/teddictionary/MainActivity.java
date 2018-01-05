package test.com.teddictionary;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.AutoCompleteTextView;
import android.widget.TextView;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnEditorAction;
import okhttp3.Credentials;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import test.com.teddictionary.Model.Definition;
import test.com.teddictionary.Model.QuizletAddSetBody;
import test.com.teddictionary.Model.QuizletAddSetResponse;
import test.com.teddictionary.Model.QuizletAddTermResponse;
import test.com.teddictionary.Model.QuizletSearchSets;
import test.com.teddictionary.Model.QuizletTerm;
import test.com.teddictionary.Model.QuizletUserSetResponse;
import test.com.teddictionary.data.Key;
import test.com.teddictionary.data.QuizletToken;
import test.com.teddictionary.event.MessageEvent;
import test.com.teddictionary.net.QuizletApi;
import test.com.teddictionary.net.SearchAsycTask;

import static test.com.teddictionary.data.AppModule.ACCESS_TOKEN;
import static test.com.teddictionary.data.AppModule.GEN_ACCESS_TOKEN_TIME;

@SuppressWarnings("squid:MaximumInheritanceDepth")
public class MainActivity extends AppCompatActivity {
    @BindView(R.id.search_keyword)
    AutoCompleteTextView mSearchKeyword;
    QuizletApi mQuizletApi;

    QuizletToken mQuizletToken = new QuizletToken();

    @Inject SharedPreferences.Editor mSharePreferenceEditor;

    @Inject SharedPreferences mSharePreference;

    @Inject Retrofit mRetrofit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ((TedApp)getApplication()).getAppcomponent().inject(this);

        ButterKnife.bind(this);

        mQuizletApi = mRetrofit.create(QuizletApi.class);

        Uri callbackUri = getIntent().getData();


        if (callbackUri != null && callbackUri.getScheme().equalsIgnoreCase("teddictionary")) {
            requestAccessToken(callbackUri);
        } else {
            startLoginPage();
        }
    }

    private void startLoginPage() {
        long lastGenAccessTokenTime = mSharePreference.getLong(GEN_ACCESS_TOKEN_TIME, 0L);
        String accessToken = mSharePreference.getString(ACCESS_TOKEN, "");
        if (SystemClock.elapsedRealtime() - lastGenAccessTokenTime < Constants.ACCESS_TOKEN_EXPIRE_TIME &&
                !accessToken.isEmpty()) {
            mQuizletToken.setAccess_token(accessToken);
            return;
        }

        Key key = new Key();
        String authLink = String.format("https://quizlet.com/authorize?response_type=%s&client_id=%s&scope=%s&state=%s&redirect_uri=%s"
                , key.getResponse_type()
                , key.getClient_id()
                , key.getScope()
                , key.getState()
                , key.getRedirect_uri());
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(authLink));
        startActivity(intent);
    }

    private void requestAccessToken(Uri callbackUri) {
        if (callbackUri != null) {
            Key key = new Key();
            String code = callbackUri.getQueryParameter("code");
            mQuizletApi.getAccessToken(Credentials.basic(key.getClient_id(), key.getSecret()),
                    "authorization_code",
                    code,
                    key.getRedirect_uri()).enqueue(new Callback<QuizletToken>() {
                @Override
                public void onResponse(Call<QuizletToken> call, Response<QuizletToken> response) {
                    mQuizletToken = response.body();
                    mSharePreferenceEditor.putLong(GEN_ACCESS_TOKEN_TIME, SystemClock.elapsedRealtime()).commit();
                    mSharePreferenceEditor.putString(ACCESS_TOKEN, mQuizletToken.getAccess_token()).commit();
                }

                @Override
                public void onFailure(Call<QuizletToken> call, Throwable t) {
                }
            });
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
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
                    int setId = getUserSetId();
                    Log.d("test", "set id:"+setId);
                    if (setId <= 0) {
                        Log.d("test", "add set");
                        addSet(definition);
                    } else {
                        Log.d("test", "add term");
                        addTerm(setId, definition);
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
        Call<QuizletAddSetResponse> response = mQuizletApi.addSet(mQuizletToken.getAccessToken(),
                quizletAddSetBody);
        response.enqueue(new Callback<QuizletAddSetResponse>() {
            @Override
            public void onResponse(Call<QuizletAddSetResponse> call, Response<QuizletAddSetResponse> response) {
                    Toast.makeText(getBaseContext(), "Done", Toast.LENGTH_SHORT);
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

    private String getTodayTimestamp() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        Date date = calendar.getTime();
        return String.valueOf((date.getTime() / 1000));
    }


    public void addTerm(int setId, Definition definition) throws IOException {
        QuizletTerm term = new QuizletTerm();
        term.setDefinition(definition.getDefinicion() + definition.example);
        term.setTerm(mSearchKeyword.getText().toString());
        Call<QuizletAddTermResponse> repos = mQuizletApi.addTerm(setId, mQuizletToken.getAccessToken(), term);
        repos.execute();
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

    public int getUserSetId() throws IOException {
        final String setTitle = getTodayTitle();
        Call<List<QuizletUserSetResponse>> repos = mQuizletApi.searchUserSets(Constants.QUIELET_USER_NAME,
                setTitle, getTodayTimestamp());
        List<QuizletUserSetResponse> result = repos.execute().body();
        for (QuizletUserSetResponse qus : result) {
            if (qus.getTitle().equalsIgnoreCase(setTitle)) {
                return qus.getId();
            }
        }
        return -1;
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

    @OnEditorAction(R.id.search_keyword)
    public boolean searchKeyword(TextView v, int actionId, KeyEvent event) {
        boolean handled = false;
        if (actionId == EditorInfo.IME_ACTION_SEARCH) {
            search();
            handled = true;
        }
        return handled;
    }
}
