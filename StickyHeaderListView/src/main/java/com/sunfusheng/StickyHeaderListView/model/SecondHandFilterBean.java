package com.sunfusheng.StickyHeaderListView.model;

import java.io.Serializable;
import java.util.List;

/**
 * 二手房,更多筛选框
 * Created by vic on 2016/7/1.
 */
public class SecondHandFilterBean extends QFJSONResult<SecondHandFilterBean.ResultBean>  implements Serializable{

    public static class ResultBean {

        private List<FilterBean> directions;

        private List<FilterBean> area;

        private List<FilterBean> price;

        private List<FilterBean> orderBy;

        private List<FilterBean> decoration;//装修

        private List<FilterBean> age;

        private List<FilterBean> layout;

        private List<FilterBean> lable;

        private List<FilterBean> facilities;

        public List<FilterBean> getDirections() {
            return directions;
        }

        public void setDirections(List<FilterBean> directions) {
            this.directions = directions;
        }

        public List<FilterBean> getArea() {
            return area;
        }

        public void setArea(List<FilterBean> area) {
            this.area = area;
        }

        public List<FilterBean> getPrice() {
            return price;
        }

        public void setPrice(List<FilterBean> price) {
            this.price = price;
        }

        public List<FilterBean> getOrderBy() {
            return orderBy;
        }

        public void setOrderBy(List<FilterBean> orderBy) {
            this.orderBy = orderBy;
        }

        public List<FilterBean> getDecoration() {
            return decoration;
        }

        public void setDecoration(List<FilterBean> decoration) {
            this.decoration = decoration;
        }

        public List<FilterBean> getAge() {
            return age;
        }

        public void setAge(List<FilterBean> age) {
            this.age = age;
        }

        public List<FilterBean> getLayout() {
            return layout;
        }

        public void setLayout(List<FilterBean> layout) {
            this.layout = layout;
        }

        public List<FilterBean> getLable() {
            return lable;
        }

        public void setLable(List<FilterBean> lable) {
            this.lable = lable;
        }

        public List<FilterBean> getFacilities() {
            return facilities;
        }

        public void setFacilities(List<FilterBean> facilities) {
            this.facilities = facilities;
        }

    }
}
