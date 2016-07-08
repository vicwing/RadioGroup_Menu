package com.sunfusheng.StickyHeaderListView.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by vic on 2016/7/7.
 */
public class FilterNewAreaBean implements Serializable {
    private String message;
    private String status;

    private List<ResultBean> result;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public static class ResultBean {
        private String fullPinyin;
        private String id;
        private double latitude;
        private double longitude;
        private String name;
        private List<SubregionsBean> subregions;

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
