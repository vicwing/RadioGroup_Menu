package com.example.cdj.myapplication.base;

import android.app.Application;

import com.orhanobut.logger.LogLevel;
import com.orhanobut.logger.Logger;
import com.socks.library.KLog;

/**
 * Created by cdj onCallBackData 2016/3/7.
 */
public class BaseApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Logger.init("vicwing").methodCount(1).logLevel(LogLevel.FULL).hideThreadInfo().methodOffset(2);
//        Logger.init().setMethodCount(1).hideThreadInfo().logLevel(LogLevel.NONE);
//        Logger.clear();

//        KLog.init(BuildConfig.LOG_DEBUG);
        KLog.init(true);

//        String androidId = System.getString(getContentResolver(), System.ANDROID_ID);
    }
}
