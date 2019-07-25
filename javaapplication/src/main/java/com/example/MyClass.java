package com.example;

import com.example.lambda.LambdaTest;
import com.example.pojo.Car;
import com.example.pojo.FilterMoreEnum;
import com.example.pojo.Student;
import com.example.rxjava.RxjavaTest;

import java.lang.ref.WeakReference;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MyClass {

    public static void main(String[] args) {
//        rxjavaTest.mapTest();
//        rxjavaTest.mapTest2();
//        RxjavaTest.flatMapTest();
//        RxjavaTest.createTest1();
//        RxjavaTest.interval();
//        RxjavaTest.rangeTest();
//        RxjavaTest.filterTest();
//        RxjavaTest.flowableTest();
//        blockSubscribe();

//        Observable.create(new ObservableOnSubscribe<String>() {
//
//            @Override
//            public void subscribe(ObservableEmitter<String> e) throws Exception {
//                e.onNext("hello");
//            }
//
//        }).subscribe(new Consumer<String>() {
//
//            @Override
//            public void accept(String s) {
//                System.out.println(s);
//            }
//        });
//        System.out.println("枚举名字::"+ EntrustTitle.UNDER_COMMISSIONING.getName());
//        int currentIndex = 6;

//        int count = 1;
//        for (int i = 0; i < 10; i++) {
//            int currentIndex = i % count + 1;
//            System.out.println("currentIndex  = " + currentIndex);
//        }
//        String value = "-0.222";
//        double v = Double.parseDouble(value);
//        if (v>0){
//            System.out.println(" 哒哒哒哒  = " + v);
//        }else {
//            System.out.println(" 小小小  = " + v);
//        }
        ArrayList<String> mData = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            if (i < 10) {
                mData.add("0" + i);
            } else {
                mData.add(i + "");
            }
        }
//        RowAndColumm.printDataToend(mData, 5);
        System.out.println("------------------------");
//        List<String> sortRowAndColumnData = RowAndColumm.sortRowAndColumnData(mData, 2, 5);
//        RowAndColumm.printDataToend(sortRowAndColumnData, 5);


//        RowAndColumm.sortRowAndColumnData1(mData, 2, 5);

//        BigDecimal bigDecimal = BigDecimal.valueOf(20);
//        double intValue = bigDecimal.divide(BigDecimal.valueOf(6), RoundingMode.UP).doubleValue();
//        System.out.println(" 除数10 = "+intValue);

//        for (int i = 0; i < 6; i++) {
//            System.out.println(i + " % " + 5 + " = " + i % 5);
//        }

        String ss = "22.549625";
        System.out.println(ss);
        int div = div(1, 2);
        System.out.println("除法结果" + div);
//        BigDecimal bigDecimal = new BigDecimal(1.5);
//        System.out.println("reuslt="+bigDecimal.setScale(0,RoundingMode.UP));
    }

    private static int div(double v1, double v2) {
        BigDecimal b1 = new BigDecimal(v1);
        BigDecimal b2 = new BigDecimal(v2);
        BigDecimal divide = b1.divide(b2, 0, RoundingMode.UP);
//        BigDecimal bigDecimal = divide.setScale(0, BigDecimal.ROUND_UP);
//        System.out.println("bigDecimal " + bigDecimal);
        return divide.intValue();
    }

    private static void 枚举测试类() {
        Car car = new Car(123, "红色");
        LambdaTest.test1(car, (Car c) -> c.getColour());

        HashMap<FilterMoreEnum, String> hashMap = new HashMap<>();
        hashMap.put(FilterMoreEnum.FILTER_HOUSE_POINT, "1");
        hashMap.put(FilterMoreEnum.FILTER_NEWHOUSE_DECORATION, "2");
        hashMap.put(FilterMoreEnum.FILTER_HOUSE_TYPE, "3");
        hashMap.put(FilterMoreEnum.FILTER_HOUSE_POINT, "4");
//        System.out.println("枚举" + FilterMoreEnum.FILTER_HOUSE_POINT);
//        Set<Map.Entry<FilterMoreEnum, String>> entrySet = hashMap.entrySet();
        for (Map.Entry<FilterMoreEnum, String> entry : hashMap.entrySet()) {
            System.out.println("k =" + entry.getKey() + " v = " + entry.getValue());
        }
        System.out.println("equas   =  " + FilterMoreEnum.FILTER_HOUSE_POINT.equals("FILTER_HOUSE_POINT"));
        String strings = "sssss,aaa,bbb,ccc,ddd";
        StringBuilder stringBuilder = arraysDelete(strings.split(","), "sssss");
        StringBuilder stringBuilder1 = arraysDelete(strings.split(","), "aaa");
        StringBuilder stringBuilder2 = arraysDelete(strings.split(","), "ddd");
        System.out.println(stringBuilder.toString());
        System.out.println(stringBuilder1.toString());
        System.out.println(stringBuilder2.toString());
    }

    /**
     * 删除某个元素,结果拼接成aaa,bbb,ccc
     *
     * @param split
     * @param target
     * @return
     */
    private static StringBuilder arraysDelete(String[] split, String target) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < split.length; i++) {
            String s = split[i];
            if (s.equals(target)) {
                continue;
            }
            if (i > 0 && stringBuilder.length() != 0) {
                stringBuilder.append(",");
            }
            stringBuilder.append(s);
        }
        return stringBuilder;
    }

    private static void blockSubscribe() {
        //创建一个上游 Observable：
        Observable<Integer> observable = Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                System.out.println("subscribe...");
                RxjavaTest.threadName();
                emitter.onNext(1);
                emitter.onNext(2);
                emitter.onNext(3);
                emitter.onComplete();
            }
        }).subscribeOn(Schedulers.io());
        //创建一个下游 Observer
        Observer<Integer> observer = new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {
                System.out.println("subscribe");
                RxjavaTest.threadName();
            }

            @Override
            public void onNext(Integer value) {
//                System.out.println("on next ");
//                RxjavaTest.threadName();
            }

            @Override
            public void onError(Throwable e) {
            }

            @Override
            public void onComplete() {
                System.out.println("on complete ");
                RxjavaTest.threadName();
            }
        };
        //建立连接
        observable.blockingSubscribe(observer);
    }

    public static void greeting() {
        System.out.println("Hello");
    }

    public static int flatMapTest1(int a) {
        if (a == 0 || a == 1) {
            System.out.println(1);
            return 1;
        } else {

            int i = flatMapTest1(a - 2) + flatMapTest1(a - 1);
            System.out.println(i);
            return i;
        }
    }

    public static void flatMapTest() {
//
        Observable.range(0, 5)
                .doAfterNext(i ->
                        System.out.println(i)
                )
                .flatMap(integer -> Observable.range(0, 5))
                .doOnNext(j -> System.out.print(j))
                .subscribe();
    }

    private static void weakReferenceTest() {
        Car car = new Car(22000, "silver");
        WeakReference<Car> weakCar = new WeakReference<Car>(car);

        int i = 0;

        while (true) {
            if (weakCar.get() != null) {
                i++;
                System.out.println("Object is alive for " + i + " loops - " + weakCar);
            } else {
                System.out.println("Object has been collected.");
                break;
            }
        }
    }

    private void testSoftRefrence() {


    }

    /**
     * 弱引用
     */
    private static void testWeakRefrence() {
        String abc = new String("abc");
        WeakReference<String> abcWeakRef = new WeakReference<String>(abc);
        abc = null;
        System.out.println("before gc: " + abcWeakRef.get());
        System.gc();
        System.out.println("after gc: " + abcWeakRef.get());
    }

    /**
     * 弱引用
     */
    private static void testWeakRefrence2() {
        Student obj = new Student();
        WeakReference wr = new WeakReference(obj);
        obj = null;
        if (wr.get() == null) {
            System.out.println("obj 已经被清除了 ");
        } else {
            System.out.println("obj 尚未被清除，其信息是 " + wr.get().toString());
        }

    }

}
