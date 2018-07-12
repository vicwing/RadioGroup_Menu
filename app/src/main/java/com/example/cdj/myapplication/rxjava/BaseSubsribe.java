package com.example.cdj.myapplication.rxjava;

import com.orhanobut.logger.Logger;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

/**
 * Description :
 * Created by vicwing
 * Created Time 2018/5/31
 */
public class BaseSubsribe<T> implements Subscriber<T> {
    private static final String TAG = "BaseSubsribe";

    @Override
    public void onSubscribe(Subscription s) {
        Logger.d(TAG + "  onCompleted");
    }

    @Override
    public void onNext(Object o) {
        Logger.d(TAG + "  onNext..");
    }

    @Override
    public void onComplete() {
        Logger.d(TAG + "  onCompleted");
    }

    @Override
    public void onError(Throwable e) {
        Logger.d(TAG + "  onError");
    }
}
