package com.example.cdj.myapplication.Bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * Description : 根据百度定位当前位置的城市信息,保存当前的经纬度.(默认值是) 和qfcity 有区别
 * Created by vic
 * Created Time 2017/11/21
 * modity: 2018/06/28
 */
public class CityInfoBean implements Serializable, Parcelable {
    private static final long serialVersionUID = -3158654834117391852L;

    private String nameZh = "深圳";

    private String dataSource = "SHENZHEN";

    public String lat = "22.549625";

    public String lng = "114.066003";

    /**
     * 街道名称
     */
    private String street;
    public CityInfoBean(){

    }

    protected CityInfoBean(Parcel in) {
        nameZh = in.readString();
        dataSource = in.readString();
        lat = in.readString();
        lng = in.readString();
        street = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(nameZh);
        dest.writeString(dataSource);
        dest.writeString(lat);
        dest.writeString(lng);
        dest.writeString(street);
    }

    public static final Creator<CityInfoBean> CREATOR = new Creator<CityInfoBean>() {
        @Override
        public CityInfoBean createFromParcel(Parcel in) {
            return new CityInfoBean(in);
        }

        @Override
        public CityInfoBean[] newArray(int size) {
            return new CityInfoBean[size];
        }
    };

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getNameZh() {
        return nameZh;
    }

    public void setNameZh(String nameZh) {
        this.nameZh = nameZh;
    }

    public String getDataSource() {
        return dataSource;
    }

    public void setDataSource(String dataSource) {
        this.dataSource = dataSource;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    @Override
    public String toString() {
        return "CityInfoBean{" +
                "nameZh='" + nameZh + '\'' +
                ", dataSource='" + dataSource + '\'' +
                ", lat='" + lat + '\'' +
                ", lng='" + lng + '\'' +
                '}';
    }


}
