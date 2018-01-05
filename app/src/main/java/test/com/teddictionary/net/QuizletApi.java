package test.com.teddictionary.net;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import test.com.teddictionary.Model.QuizletAddSetBody;
import test.com.teddictionary.Model.QuizletAddSetResponse;
import test.com.teddictionary.Model.QuizletAddTermResponse;
import test.com.teddictionary.Model.QuizletSearchSets;
import test.com.teddictionary.Model.QuizletSet;
import test.com.teddictionary.Model.QuizletTerm;
import test.com.teddictionary.Model.QuizletUserSetResponse;
import test.com.teddictionary.data.QuizletToken;

/**
 * Created by teddylin on 13/12/2017.
 */

public interface QuizletApi {
    @GET("2.0/users/{username}/sets?client_id=ZuyJXZaNnv&whitespace=1")
    Call<List<QuizletSet>> listSets(@Path("username") String username);

    @GET("2.0/users/{username}/sets?client_id=ZuyJXZaNnv&whitespace=1")
    Call<List<QuizletUserSetResponse>> searchUserSets(@Path("username") String username,
                                                @Query("q") String keyword,
                                                @Query("modified_since") String timestamp);

    @GET("2.0/search/sets?client_id=ZuyJXZaNnv&whitespace=1")
    Call<QuizletSearchSets> searchSets(@Query("q") String keyword, @Query("creator") String username);

    @POST("2.0/sets")
    Call<QuizletAddSetResponse> addSet(@Header("Authorization") String accessToken,
                                       @Body QuizletAddSetBody quizletAddSetBody);

    @POST("2.0/sets/{setId}/terms")
    Call<QuizletAddTermResponse> addTerm(@Path("setId") int setId,
                                         @Header("Authorization") String accessToken,
                                         @Body QuizletTerm quizletAddSetBody);

    @Headers("Accept: application/json")
    @POST("/oauth/token")
    @FormUrlEncoded
    Call<QuizletToken> getAccessToken (@Header("Authorization") String basic_auth,
                                       @Field("grant_type") String grant_type,
                                       @Field("code") String code,
                                       @Field("redirect_uri") String redirect_uri);
}
