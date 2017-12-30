package test.com.teddictionary;

import javax.inject.Singleton;

import dagger.Component;
import test.com.teddictionary.data.NetModule;

/**
 * Created by teddylin on 12/12/2017.
 */

@Singleton
@Component(modules = NetModule.class)
interface NetComponent {
    NetModule netMaker();
}
