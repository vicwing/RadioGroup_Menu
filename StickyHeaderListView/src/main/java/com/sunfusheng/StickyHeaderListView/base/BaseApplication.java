package com.sunfusheng.StickyHeaderListView.base;

import android.app.Application;

import com.apkfuns.logutils.LogLevel;
import com.apkfuns.logutils.LogUtils;
import com.orhanobut.logger.Logger;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.log.LoggerInterceptor;

import okhttp3.OkHttpClient;

/**
 * Created by vic on 2016/7/5.
 */
public class BaseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        LogUtils.getLogConfig()
                .configAllowLog(true)
                .configTagPrefix("123")
                .configShowBorders(false)
                .configLevel(LogLevel.TYPE_VERBOSE);

//        Logger
//                .init("logger")                 // default PRETTYLOGGER or use just init()
//                .methodCount(0)                 // default 2
//                .hideThreadInfo()               // default shown
//                .logLevel(com.orhanobut.logger.LogLevel.FULL)        // default LogLevel.FULL
//                .methodOffset(0)          ;      // default 0
//                .logAdapter(new AndroidLogAdapter()); //default AndroidLogAdapter

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(new LoggerInterceptor("TAG"))
                //其他配置
                .build();
        OkHttpUtils.initClient(okHttpClient);
        Logger.init("vicwing").methodCount(0).logLevel(com.orhanobut.logger.LogLevel.FULL).hideThreadInfo().methodOffset(2);
    }
}
