package com.sunfusheng.StickyHeaderListView.model;

import java.io.Serializable;

/**
 * Created by vic on 2016/6/28.
 */
public class CityItem   implements Serializable {
    private String cityName;
    private String cityCode;
    private String imageUrl;

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }
}
