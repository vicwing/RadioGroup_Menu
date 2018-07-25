package com.example.cdj.myapplication;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.OnLifecycleEvent;

import com.orhanobut.logger.Logger;

/**
 * Description :
 * Created by vicwing
 * Created Time 2018/7/12
 */
public class LifeListenter implements LifecycleObserver {
    private Lifecycle lifecycle;

    public LifeListenter(Lifecycle lifecycle) {
        this.lifecycle = lifecycle;
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    public void onResume() {
        Logger.d("onResume:   " + lifecycle.getCurrentState());
    }
    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    public void onCreate() {
        Logger.d("create:   " + lifecycle.getCurrentState());
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    public void onPause() {
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    public void onStop() {
        Logger.d("onStop:   ");

    }
}
