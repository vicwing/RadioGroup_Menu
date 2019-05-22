package com.example.pojo;

/**
 * 类描述：
 * 创建人：vicwing
 * 创建时间：2019/4/18 9:32 PM
 * 最后修改人：vicwing
 */
public class Son<T, E extends T> extends Father<T> {

    E otherData;

    public Son(T data, E otherData) {
        super(data);
        this.otherData = otherData;
    }

    @Override
    public String toString() {
        return "Son6 [otherData=" + otherData + ", data=" + data + "]";
    }
}
