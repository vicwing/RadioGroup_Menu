package com.example;

import com.example.pojo.Car;
import com.example.pojo.Student;
import com.example.rxjava.RxjavaTest;

import java.lang.ref.WeakReference;

import io.reactivex.Observable;

public class MyClass {

    public static void main(String[] args) {
//        LocalDateTime arrivalDate = LocalDateTime.now();
//        try {
//            DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy/MM/dd/hh");
//            String landing = arrivalDate.format(format);
//            System.out.printf("Arriving at :  %s %n", landing);
//        } catch (DateTimeException ex) {
//            System.out.printf("%s can't be formatted!%n", arrivalDate);
//            ex.printStackTrace();
//        }
//        double lat = 22.547076;
//        double lng = 113.96026;
//        System.out.println("lat " + String.valueOf(lat) + " " + String.valueOf(lng));

//        String uuid = UUID.randomUUID().toString();
//        System.out.println("uuid  " + uuid);

//        String iid = InstanceID.getInstance(context).getId()
//        List<String> list = new ArrayList<>();
//        list.add("H");
//        list.add("e");
//        list.add("l");
//        list.add("l");
//        list.add("o");
//        list.add(" ");
//        list.add("W");
//        list.add("o");
//        list.add("r");
//        list.add("l");
//        list.add("d");
//        list.stream().forEach(s -> System.out.println(s)); // Java8

//        flatMapTest();

//        int mapTest1 = flatMapTest1(10);


//        List<String> names = Arrays.asList("Hafiz", "Waleed", "Hussain", "Steve");
//        for (int i = 0; i < names.size(); i++) {
//            if (i % 2 == 0) continue;
//            System.out.println(names.get(i));
//        }
//
//        Observable.range(0, names.size())
//                .filter(index -> index % 2 == 1)
//                .subscribe(index -> System.out.println("rxjava   " + names.get(index)));


//        new Thread(() -> {
//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            greeting();
//        }).start();

//        Observable.interval(0, 1, TimeUnit.SECONDS)
//                .subscribe(aLong -> greeting());

//        Observable.create(new ObservableOnSubscribe<Integer>() {
//            @Override
//            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
//
//            }
//        }).subscribe(new Observer<Integer>() {
//            @Override
//            public void onSubscribe(Disposable d) {
//
//            }
//
//            @Override
//            public void onNext(Integer integer) {
//
//            }
//
//            @Override
//            public void onError(Throwable e) {
//
//            }
//
//            @Override
//            public void onComplete() {
//
//            }
//        });

//        rxjavaTest.mapTest();
//        rxjavaTest.mapTest2();
//        RxjavaTest.flatMapTest();
//        RxjavaTest.createTest();
//        RxjavaTest.interval();
//        RxjavaTest.rangeTest();
//        RxjavaTest.filterTest();
        RxjavaTest.flowableTest();
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
