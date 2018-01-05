package test.com.teddictionary.data;

import android.content.Context;
import android.content.SharedPreferences;

import dagger.Module;
import dagger.Provides;

/**
 * Created by teddylin on 12/12/2017.
 */

@Module
public class AppModule {
    public static String ACCESS_TOKEN = "access-token";
    public static String GEN_ACCESS_TOKEN_TIME = "access-token-time";
    public static String TED_DICT_PREF = "ted_dict_pref";

    private Context mContext;

    public AppModule(Context context) {
        this.mContext = context;
    }

    @Provides
    public SharedPreferences.Editor provideSharedPreferencesEditor() {
        SharedPreferences.Editor editor = mContext.getSharedPreferences(TED_DICT_PREF, Context.MODE_PRIVATE).edit();
        return editor;
    }

    @Provides
    public SharedPreferences provideSharedPreferences() {
        return mContext.getSharedPreferences(TED_DICT_PREF, Context.MODE_PRIVATE);
    }
}
