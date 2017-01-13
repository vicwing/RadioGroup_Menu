package com.example;

public class MyClass {

    public static void  main(String[] args){

        long l = Double.doubleToRawLongBits(8.5d);
        System.out.println("Value = "+l);

        Double value = new Double("8.5");
        //returns the bits that represent the floating-point number
        System.out.println("Value = " + Double.doubleToRawLongBits(8.5d));
        System.out.println("Value = " + Double.doubleToRawLongBits(value));
    }

}
