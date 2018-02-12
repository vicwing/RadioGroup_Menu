package com.sunfusheng.StickyHeaderListView.model;

import java.io.Serializable;

/**
 * 筛选栏菜单基础类.
 * Created by vic on 2016/7/6.
 */
public class FilterBean implements Serializable{
    private String desc;
    private String value;

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
