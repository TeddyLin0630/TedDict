package test.com.teddictionary;

import javax.inject.Singleton;

import dagger.Component;
import test.com.teddictionary.data.AppModule;
import test.com.teddictionary.data.NetModule;

/**
 * Created by teddylin on 01/01/2018.
 */

@Singleton
@Component(modules = {AppModule.class, NetModule.class})
public interface AppComponent {
        void inject(MainActivity activity);
}