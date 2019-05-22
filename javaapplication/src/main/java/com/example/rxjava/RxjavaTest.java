package com.example.rxjava;

import java.util.concurrent.TimeUnit;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;

/**
 * Description :操作符测试
 * Created by vicwing
 * Created Time 2018/8/28
 */
public class RxjavaTest {

    private static final String TAG = RxjavaTest.class.getSimpleName();

    /**
     * 最简单的异步操作
     */
    public static void createTest1() {
        Observable.create(new ObservableOnSubscribe<Object>() {
            @Override
            public void subscribe(ObservableEmitter<Object> emitter) throws Exception {
                System.out.println("disposed 1= " + emitter.isDisposed());
                threadName();
                emitter.onComplete();
                System.out.println("disposed 2= " + emitter.isDisposed());
            }
        })
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.computation())
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(Object o) throws Exception {
                        System.out.println("consumer " + o);
                    }
                });
    }

    public static void createTest() {
        Observable.create(new ObservableOnSubscribe<Object>() {
            @Override
            public void subscribe(ObservableEmitter<Object> emitter) throws Exception {
                System.out.println("disposed 1= " + emitter.isDisposed());
                threadName();
//                emitter.onNext(1);
                //                emitter.onComplete();
                System.out.println("disposed 2= " + emitter.isDisposed());
            }
        })
                .subscribeOn(Schedulers.io())
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(Object o) throws Exception {
                        System.out.println("consumer " + o);
                    }
                });
    }

    public static void threadName() {
        String name = Thread.currentThread().getName();
        System.out.println("thread name  " + name);
    }

    public static void mapTest() {
        Observable<Integer> observable = Observable.just(10, 21, 33);
        observable.map(new Function<Integer, Object>() {
            @Override
            public Object apply(Integer integer) throws Exception {
                return integer * 10 + "";
            }
        });
        observable.subscribe(new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {
                System.out.println("d");
            }

            @Override
            public void onNext(Integer integer) {
                System.out.println(integer);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
//
    }

    public static void mapTest2() {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                e.onNext(11);
                e.onNext(22);
                e.onNext(33);
            }
        }).map(new Function<Integer, String>() {
            @Override
            public String apply(Integer integer) throws Exception {
                return "我是变换过后的" + integer;
            }
        }).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                System.out.println("XYK" + s);
            }
        });
    }

    /**
     * 将Observable发射的数据变换为Observables集合，然后将这些Observable发射的数据平坦化的放进一个单独的Observable，内部采用merge合并。
     */
    public static void flatMapTest() {
        Observable.just(2, 3, 5)
                .flatMap(new Function<Integer, ObservableSource<?>>() {
                    @Override
                    public ObservableSource<?> apply(Integer integer) throws Exception {
                        System.out.println("flatmap  integer = " + integer);
                        return Observable.create(subseriber -> {
                            subseriber.onNext(integer * 10);
                            subseriber.onNext(integer * 100);
                            subseriber.onComplete();
                        });
                    }
                })
                .subscribe(o -> System.out.println("修改后的flatmap" + o));

    }

    public static void interval() {
//        Integer[] items = {1, 2, 3, 4, 5};
//        Observable.fromArray(items)
//                .interval(1, 1, TimeUnit.SECONDS)
//                .subscribe(s -> System.out.println(s));
        Observable.interval(0, 5, TimeUnit.SECONDS)
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        System.out.println("interval");
                    }
                });
    }

    public static void rangeTest() {
        Observable.range(0, 19)
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Integer integer) {
                        System.out.println(" range = " + integer);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public static void filterTest() {
        Observable.just(1, 2, 3, 4)
                .filter(new Predicate<Integer>() {
                    @Override
                    public boolean test(Integer integer) throws Exception {
//                        System.out.println("filter ingeger = " + integer);
                        return integer % 2 == 0;
                    }
                }).subscribe(new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Integer integer) {
                System.out.println("filter ingeger = " + integer);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }

    public static void flowableTest() {
        Flowable.just("Hello,I am China!", "B2")
                //将1.x中的Func1,2改为Function和BiFunction，Func3-9改为Function3-9
                //多参数FuncN改为Function<Object[],R>

                //这个第一个泛型为接收参数的数据类型，第二个泛型为转换后要发射的数据类型
                .map(new Function<String, String>() {
                    @Override
                    public String apply(String s) throws Exception {
                        return s + "    ::::  apply Mars";
                    }
                })
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        System.out.println("consumer" + s);
                    }
                });
    }
}
