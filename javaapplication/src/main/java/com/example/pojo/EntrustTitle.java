package com.example.pojo;

/**
 * Description :
 * Created by vicwing
 * Created Time 2018/10/17
 */
public enum EntrustTitle {
    UNDER_COMMISSIONING("正在委托中"), DEALDONE("已成交"), EXPIRED("已失效");

    private String name;

    EntrustTitle(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }
}
