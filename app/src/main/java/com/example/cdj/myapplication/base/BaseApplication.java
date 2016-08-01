package com.example.cdj.myapplication.base;

import android.app.Application;
import android.content.Context;

import com.orhanobut.logger.LogLevel;
import com.orhanobut.logger.Logger;
import com.socks.library.KLog;
import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

import java.util.Timer;
import java.util.TimerTask;

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

        Logger.init("vicwing").methodCount(1).logLevel(LogLevel.FULL).hideThreadInfo().methodOffset(2);
//        Logger.init().setMethodCount(1).hideThreadInfo().logLevel(LogLevel.NONE);
//        Logger.clear();
        KLog.init(true);
//        String androidId = System.getString(getContentResolver(), System.ANDROID_ID);
//        LogUtils.getLogConfig()
//                .configAllowLog(GloabalConstant.isDebug)
//                .configTagPrefix("MyAppName")
//                .configShowBorders(false);
//                .configFormatTag("%d{HH:mm:ss:SSS} %t %c{-5}")
//                .configLevel(com.apkfuns.logutils.LogLevel.TYPE_ERROR);
        //内存泄漏检测
        refWatcher = LeakCanary.install(this);
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
    private   Timer timer ;
    private MyTimerTask timerTask;
    class  MyTimerTask extends TimerTask{

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
