package com.sunfusheng.StickyHeaderListView.model;

import java.io.Serializable;
import java.util.List;

/**
 * 筛选: 区域类
 * Created by vic on 2016/7/6.
 */
public class FilterAreaBean  extends  QFJSONResult<List<FilterAreaBean.ResultBean>>  implements Serializable{

    public static class ResultBean {
        private String fullPinyin;
        private String id;
        private double latitude;
        private double longitude;
        private String name;
        private List<SubregionsBean> subregions; //商圈区域名称
        private List<SubregionsBean> stations; //地铁线

        public List<SubregionsBean> getStations() {
            return stations;
        }

        public void setStations(List<SubregionsBean> stations) {
            this.stations = stations;
        }

        private List<ResultBean> midList;

        public List<ResultBean> getMidList() {
            return midList;
        }

        public void setMidList(List<ResultBean> midList) {
            this.midList = midList;
        }

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

        public List<SubregionsBean> getSubregions() {
            return subregions;
        }

        public void setSubregions(List<SubregionsBean> subregions) {
            this.subregions = subregions;
        }


    }
}
