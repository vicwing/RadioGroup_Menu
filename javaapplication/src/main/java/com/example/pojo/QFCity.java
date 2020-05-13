package com.example.pojo;


import java.io.Serializable;

/**
 * 项目名称：Qfang Wang App Git
 * 类描述：将内部类抽取单独的实体类
 * 创建人：aben
 * 创建时间：2018/4/24 9:19
 */
public class QFCity implements Serializable, Comparable<QFCity> {

    private static final long serialVersionUID = -4214095821189679393L;
    //城市dataSource首字母
    private String firstName;
    private String groupName;
    private String name = "深圳";
    private String dataSource = "SHENZHEN";
    private double lat = 22.549625;
    private double lng = 114.066003;
    /**
     * 是否显示新房首页
     */
    private boolean isNewHouseCity;

    public QFCity() {

    }

    public QFCity(String dataSource, String name, double lat, double lng) {
        this.dataSource = dataSource;
        this.name = name;
        this.lat = lat;
        this.lng = lng;
    }

    public boolean isNewHouseCity() {
        return isNewHouseCity;
    }

    public void setNewHouseCity(boolean newHouseCity) {
        isNewHouseCity = newHouseCity;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDataSource() {
        return dataSource;
    }

    public void setDataSource(String dataSource) {
        this.dataSource = dataSource;
    }

    public double getLat() {
        return lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    @Override
    public int compareTo(QFCity another) {
        return 0;
    }

    @Override
    public String toString() {
        return "QFCity{" +
                "firstName='" + firstName + '\'' +
                ", name='" + name + '\'' +
                ", dataSource='" + dataSource + '\'' +
                ", lat='" + lat + '\'' +
                ", lng='" + lng + '\'' +
                '}';
    }

}
