package test.com.teddictionary.data;

import android.app.Application;

import dagger.Module;
import dagger.Provides;

/**
 * Created by teddylin on 12/12/2017.
 */

@Module
public class AppModule {
    Application mApplication;

    public AppModule(Application application) {
        mApplication = application;
    }

    @Provides
    Application providesApplication() {
        return mApplication;
    }
}
