package com.example;

import com.example.pojo.Car;
import com.example.pojo.Student;

import java.lang.ref.WeakReference;
import java.util.UUID;

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

        String uuid = UUID.randomUUID().toString();
        System.out.println("uuid  "+uuid);
//        String iid = InstanceID.getInstance(context).getId()
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
