package com.example.pojo;

/**
 * 类描述：https://blog.csdn.net/ShierJun/article/details/51253870
 * 泛型继承很简单，一句话就是，所有的泛型参数在使用时都能被指定为特定的类型，要么开发者指定要么编译器可以推断出来
 * 创建人：vicwing
 * 创建时间：2019/4/18 9:31 PM
 * 最后修改人：vicwing
 */
public class Father<T> {
    T data;

    public Father(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Father [data=" + data + "]";
    }
}
