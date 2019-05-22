package com.example.lambda;

/**
 * Description :
 * Created by vicwing
 * Created Time 2018/11/15
 */
public interface CusFunction<T> {
    String apply(T bean);
}
