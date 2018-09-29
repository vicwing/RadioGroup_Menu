package com.example.cdj.myapplication.rxjava;


import com.orhanobut.logger.Logger;

import io.reactivex.Observable;

/**
 * Description :
 * Created by vicwing
 * Created Time 2018/8/22
 */
public class RxjavaTest {
    public static void rangeTest() {
//        Observable.range(0, 10).forEach(
//                s -> Logger.d("rangeTest:   " + s);
//        );
        Observable.range(0, 10).forEach(integer -> {
            Logger.d("rangeTest:   " + integer);
        });
    }
    public static void flatMapTest() {
//
        Observable.range(0, 10)
                .doAfterNext(i-> System.out.println("i"))
                .flatMap(integer -> Observable.range(0, 10))
                .doOnNext(i -> System.out.print("j "))
                .subscribe();
    }
}
