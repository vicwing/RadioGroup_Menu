package com.example;

import com.example.utils.SortUtils;

public class MyClass {

    public static void main(String[] args) {

        long l = Double.doubleToRawLongBits(8.5d);
        System.out.println("Value = " + l);

        Double value = new Double("8.5");
        //returns the bits that represent the floating-point number
//        System.out.println("Value = " + Double.doubleToRawLongBits(8.5d));
//        System.out.println("Value = " + Double.doubleToRawLongBits(value));

        SortUtils.arrangementSelect(new String[]{
                "1", "2", "3", "4"
        }, 2);
        SortUtils.combinationSelect(new String[]{
                "1", "2", "3", "4", "5"
        }, 3);
    }

}
