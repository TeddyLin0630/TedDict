package test.com.teddictionary;

import android.app.Application;

import test.com.teddictionary.data.AppModule;
import test.com.teddictionary.data.NetModule;

/**
 * Created by teddylin on 01/01/2018.
 */

public class TedApp extends Application {
    AppComponent mAppComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        mAppComponent = DaggerAppComponent
                .builder()
                .appModule(new AppModule(this))
                .netModule(new NetModule())
                .build();
    }

    public AppComponent getAppcomponent() {
        return mAppComponent;
    }
}
