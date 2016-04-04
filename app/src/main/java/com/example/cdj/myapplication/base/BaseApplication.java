package com.example.cdj.myapplication.base;

import android.app.Application;

import com.orhanobut.logger.Logger;

/**
 * Created by cdj on 2016/3/7.
 */
public class BaseApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Logger.init("vicwing").setMethodCount(1).hideThreadInfo();
    }
}
