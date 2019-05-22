package com.example.lambda;

import com.example.pojo.Car;

/**
 * Description :
 * Created by vicwing
 * Created Time 2018/11/15
 */
public class LambdaTest {
    public static String test1(Car car, CusFunction<Car> function) {
        return function.apply(car);
    }
}
