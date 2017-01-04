package com.example.cdj.myapplication.base;

import android.app.Application;
import android.content.Context;
import android.os.Environment;

import com.orhanobut.logger.LogLevel;
import com.orhanobut.logger.Logger;
import com.socks.library.KLog;
import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.cookie.CacheInterceptor;
import com.zhy.http.okhttp.log.LoggerInterceptor;

import java.io.File;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.OkHttpClient;

/**
 * Created by cdj onCallBackData 2016/3/7.
 */
public class BaseApplication extends Application {

    //内存泄漏观察者
    private RefWatcher refWatcher;

    @Override
    public void onCreate() {
        super.onCreate();
//        initOldCrashHandler();
        CrashHandlerNew crashHandlerNew = CrashHandlerNew.getInstance();
        crashHandlerNew.init(getApplicationContext());
        Logger.init("vicwing").logLevel(LogLevel.FULL).methodCount(1).hideThreadInfo();
//        Logger.init().setMethodCount(1).hideThreadInfo().logLevel(LogLevel.NONE);
//        Logger.clear();
        KLog.init(false);
//        String androidId = System.getString(getContentResolver(), System.ANDROID_ID);
//        LogUtils.getLogConfig()
//                .configAllowLog(GloabalConstant.isDebug)
//                .configTagPrefix("MyAppName")
//                .configShowBorders(false);
//                .configFormatTag("%d{HH:mm:ss:SSS} %t %c{-5}")
//                .configLevel(com.apkfuns.logutils.LogLevel.TYPE_ERROR);
        //内存泄漏检测
        refWatcher = LeakCanary.install(this);

//        OkHttpClient okHttpClient = new OkHttpClient.Builder()
//                .addInterceptor(new LoggerInterceptor("okhttp_request"))
//                //其他配置
//                .build();


        OkHttpClient okHttpClient = new OkHttpClient();
        OkHttpClient newClient = okHttpClient.newBuilder()
                .addNetworkInterceptor(new CacheInterceptor())
                .addInterceptor(new LoggerInterceptor("okhttp_request"))
                .cache(provideCache())
                .connectTimeout(20, TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS)
                .build();
        OkHttpUtils.initClient(newClient);
    }

    public Cache provideCache() {
        String sdPath = getSDPath();
        File cacheDir = getCacheDir();
        File file = new File(sdPath+"/vicwing");
        Logger.d("cacheDir  " + cacheDir+" sdPath "+sdPath);
        Logger.d("cacheDir  " + file.getAbsolutePath());
        Cache cache = new Cache(file, 1024 * 1024);
        return cache;
    }

    /**
     * 取SD卡路径
     **/
    private String getSDPath() {
        File sdDir = null;
        boolean sdCardExist = Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED); // 判断sd卡是否存在
        if (sdCardExist) {
            sdDir = Environment.getExternalStorageDirectory(); // 获取根目录
        }
        if (sdDir != null) {
            return sdDir.toString();
        } else {
            return "";
        }
    }

    private void initOldCrashHandler() {
        CrashHandler crashHandler = CrashHandler.getInstance();
        crashHandler.init(getApplicationContext());
        //获取到当前线程，设置未捕获异常的处理
//        Thread.currentThread().setUncaughtExceptionHandler(crashHandler);
    }


    public static RefWatcher getRefWatcher(Context context) {
        BaseApplication application = (BaseApplication) context.getApplicationContext();
        return application.refWatcher;
    }

    /**
     * 获取到的服务器当前时间
     */
    public static long mServerTime;
    private Timer timer;
    private MyTimerTask timerTask;

    class MyTimerTask extends TimerTask {

        @Override
        public void run() {
            BaseApplication.mServerTime++;
        }
    }

    /**
     * 开启计时
     */
    public void startTimerTask(long serverTime) {
        mServerTime = serverTime;
        timer = new Timer(true);
        if (timer != null) {
            if (timerTask != null) {
                timerTask.cancel();  //将原任务从队列中移除
            }
            timerTask = new MyTimerTask();  // 新建一个任务
            timer.schedule(timerTask, 0, 1);
        }
    }

    /**
     * 停止时钟计时
     */
    public void stopTimerTask() {
        if (timerTask != null) {
            timerTask.cancel();
        }
    }

}
