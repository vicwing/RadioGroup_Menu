package com.example.cdj.myapplication.rxjava;


import android.arch.lifecycle.LifecycleOwner;
import android.os.SystemClock;

import com.orhanobut.logger.Logger;
import com.uber.autodispose.AutoDispose;
import com.uber.autodispose.android.lifecycle.AndroidLifecycleScopeProvider;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Description :
 * Created by vicwing
 * Created Time 2018/8/22
 */
public class RxjavaTest {
    public static void threadName() {
        String name = Thread.currentThread().getName();
        System.out.println("thread name  " + name);
    }


    public static void interval(LifecycleOwner lifecycleOwner) {
        Observable.interval(2, TimeUnit.SECONDS)
                .doOnDispose(() -> Logger.i("Disposing subscription from onCreate()"))
                .as(AutoDispose.autoDisposable(AndroidLifecycleScopeProvider.from(lifecycleOwner)))
                .subscribe(num -> Logger.i("Started in onCreate(), running until onDestroy(): " + num));
    }

    /**
     * 普通的
     */
    public static Disposable rangeTest(LifecycleOwner lifecycleOwner) {
        return Observable.create(new ObservableOnSubscribe<Object>() {
            @Override
            public void subscribe(ObservableEmitter<Object> emitter) throws Exception {
                System.out.println("disposed 1= " + emitter.isDisposed());
                threadName();
                emitter.onNext(1);
                SystemClock.sleep(5000);
                System.out.println("disposed 2= " + emitter.isDisposed());
            }
        })
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.computation())
                .as(AutoDispose.autoDisposable(AndroidLifecycleScopeProvider.from(lifecycleOwner)))
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(Object o) throws Exception {
                        System.out.println("consumer " + o);
                    }
                });
    }

    public static void flatMapTest() {
//
        Observable.range(0, 10)
                .doAfterNext(i -> System.out.println("i"))
                .flatMap(integer -> Observable.range(0, 10))
                .doOnNext(i -> System.out.print("j "))
                .subscribe();
    }
}
