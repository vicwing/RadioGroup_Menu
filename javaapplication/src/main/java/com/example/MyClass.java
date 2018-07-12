package com.example;

import com.example.pojo.Car;
import com.example.pojo.Student;

import java.lang.ref.WeakReference;
import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class MyClass {

    public static void main(String[] args) {

//        long l = Double.doubleToRawLongBits(8.5d);
//        System.out.println("Value = " + l);

//        Double value = new Double("8.5");
        //returns the bits that represent the floating-point number
//        System.out.println("Value = " + Double.doubleToRawLongBits(8.5d));
//        System.out.println("Value = " + Double.doubleToRawLongBits(value));

//        SortUtils.arrangementSelect(new String[]{
//                "1", "2", "3", "4"
//        }, 2);
//        SortUtils.combinationSelect(new String[]{
//                "1", "2", "3", "4", "5"
//        }, 3);

//        testWeakRefrence();
//
//        testWeakRefrence2();


//        weakReferenceTest();
//        LocalTime time = LocalTime.now();
//        System.out.println("local time now : " + time);
//        LocalDate localDate = LocalDate.now();
//        System.out.println("local date now : " + localDate);
//        LocalDateTime localDateTime = LocalDateTime.now();
//        System.out.println("local dateTime now : " + localDateTime);
//
//        String dayAfterTommorrow = "20140116";
//        LocalDate formattedLocalDateTime = LocalDate.parse(dayAfterTommorrow, DateTimeFormatter.BASIC_ISO_DATE);
//        System.out.println("local dateTime now : " + formattedLocalDateTime);

        LocalDateTime arrivalDate = LocalDateTime.now();
        try {
//            DateTimeFormatter format = DateTimeFormatter.ofPattern("MMM dd yyyy  hh:mm a");
            DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy/MM/dd/hh");
            String landing = arrivalDate.format(format);
            System.out.printf("Arriving at :  %s %n", landing);
        } catch (DateTimeException ex) {
            System.out.printf("%s can't be formatted!%n", arrivalDate);
            ex.printStackTrace();
        }

        double lat = 22.547076;
        double lng = 113.96026;
        System.out.println("lat " + String.valueOf(lat) + " " + String.valueOf(lng));
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
