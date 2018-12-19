package jp.bizloco.kotlinexample;

import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;
import android.util.Log;

import jp.bizloco.kotlinexample.service.core.ApiClient;

public class BaseApp extends MultiDexApplication {
    private static final String TAG = BaseApp.class.getSimpleName();
    private static BaseApp sInstance = null;

    /**
     * Get instance of app
     *
     * @return app
     */
    public static synchronized BaseApp getInstance() {
        if (sInstance == null) {
            sInstance = new BaseApp();
        }
        return sInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate: ");
        MultiDex.install(getApplicationContext());
        ApiClient.getInstance().init();
    }
}
