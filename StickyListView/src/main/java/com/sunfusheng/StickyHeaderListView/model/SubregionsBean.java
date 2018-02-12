package com.sunfusheng.StickyHeaderListView.model;

/**
 * 筛选菜单.区域.子项
 * Created by vic on 2016/7/7.
 */
public class SubregionsBean {
    private String fullPinyin;
    private String id;
    private double latitude;
    private double longitude;
    private String name;
    private Object subregions;

    public String getFullPinyin() {
        return fullPinyin;
    }

    public void setFullPinyin(String fullPinyin) {
        this.fullPinyin = fullPinyin;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Object getSubregions() {
        return subregions;
    }

    public void setSubregions(Object subregions) {
        this.subregions = subregions;
    }
}