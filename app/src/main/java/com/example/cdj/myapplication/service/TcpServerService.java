package com.example.cdj.myapplication.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

/**
 * Description : 模拟 sock 服务
 * Created by vic
 * Created Time 2018/2/11
 */
public class TcpServerService extends Service {

    private boolean mIsServiceDestroyed = false;


    @Override
    public void onCreate() {
        super.onCreate();
        initData();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mIsServiceDestroyed = true;
    }

    private void initData() {
        new Thread(new ServiceWork()).start();
    }

    /**
     * 每隔五秒添加一本书.
     */
    private class ServiceWork implements Runnable {
        @Override
        public void run() {

        }
    }
}
