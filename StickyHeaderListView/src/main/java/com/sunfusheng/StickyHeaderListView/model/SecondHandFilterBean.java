package com.sunfusheng.StickyHeaderListView.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by vic on 2016/7/1.
 */
public class SecondHandFilterBean extends QFJSONResult<SecondHandFilterBean.ResultBean>  implements Serializable{

    public static class ResultBean {

        private List<FilterDescBean> directions;

        private List<FilterDescBean> area;

        private List<FilterDescBean> price;

        private List<FilterDescBean> orderBy;

        private List<FilterDescBean> decoration;//装修

        private List<FilterDescBean> age;

        private List<FilterDescBean> layout;

        private List<FilterDescBean> lable;

        private List<FilterDescBean> facilities;

        public List<FilterDescBean> getDirections() {
            return directions;
        }

        public void setDirections(List<FilterDescBean> directions) {
            this.directions = directions;
        }

        public List<FilterDescBean> getArea() {
            return area;
        }

        public void setArea(List<FilterDescBean> area) {
            this.area = area;
        }

        public List<FilterDescBean> getPrice() {
            return price;
        }

        public void setPrice(List<FilterDescBean> price) {
            this.price = price;
        }

        public List<FilterDescBean> getOrderBy() {
            return orderBy;
        }

        public void setOrderBy(List<FilterDescBean> orderBy) {
            this.orderBy = orderBy;
        }

        public List<FilterDescBean> getDecoration() {
            return decoration;
        }

        public void setDecoration(List<FilterDescBean> decoration) {
            this.decoration = decoration;
        }

        public List<FilterDescBean> getAge() {
            return age;
        }

        public void setAge(List<FilterDescBean> age) {
            this.age = age;
        }

        public List<FilterDescBean> getLayout() {
            return layout;
        }

        public void setLayout(List<FilterDescBean> layout) {
            this.layout = layout;
        }

        public List<FilterDescBean> getLable() {
            return lable;
        }

        public void setLable(List<FilterDescBean> lable) {
            this.lable = lable;
        }

        public List<FilterDescBean> getFacilities() {
            return facilities;
        }

        public void setFacilities(List<FilterDescBean> facilities) {
            this.facilities = facilities;
        }

    }
    public static class FilterDescBean {
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
}
