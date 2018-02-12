package com.example;

import com.example.pojo.Car;
import com.example.pojo.Student;

import java.lang.ref.WeakReference;

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


        Car car = new Car(22000,"silver");
        WeakReference<Car> weakCar = new WeakReference<Car>(car);

        int i=0;

        while(true){
            if(weakCar.get()!=null){
                i++;
                System.out.println("Object is alive for "+i+" loops - "+weakCar);
            }else{
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
