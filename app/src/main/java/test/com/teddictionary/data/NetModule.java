package test.com.teddictionary.data;

import javax.inject.Inject;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import test.com.teddictionary.Constants;

/**
 * Created by teddylin on 12/12/2017.
 */

@Module
public class NetModule {
    @Inject
    public NetModule(){}

    @Provides
    @Singleton
    public static Retrofit provideRetrofit() {
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(Constants.QUIZLET_API_BASE)
                .build();
        return retrofit;
    }

}