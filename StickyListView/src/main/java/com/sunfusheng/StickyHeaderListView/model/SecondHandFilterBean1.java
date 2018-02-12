package com.sunfusheng.StickyHeaderListView.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by vic on 2016/7/1.
 */
public class SecondHandFilterBean1 extends QFJSONResult implements Serializable{
    private String message;
    private ResultBean result;
    private String status;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public static class ResultBean {

        private List<DirectionsBean> directions;

        private List<AreaBean> area;

        private List<PriceBean> price;

        private List<OrderByBean> orderBy;

        private List<DecorationBean> decoration;//装修

        private List<AgeBean> age;

        private List<LayoutBean> layout;

        private List<LableBean> lable;

        private List<FacilitiesBean> facilities;

        public List<DirectionsBean> getDirections() {
            return directions;
        }

        public void setDirections(List<DirectionsBean> directions) {
            this.directions = directions;
        }

        public List<AreaBean> getArea() {
            return area;
        }

        public void setArea(List<AreaBean> area) {
            this.area = area;
        }

        public List<PriceBean> getPrice() {
            return price;
        }

        public void setPrice(List<PriceBean> price) {
            this.price = price;
        }

        public List<OrderByBean> getOrderBy() {
            return orderBy;
        }

        public void setOrderBy(List<OrderByBean> orderBy) {
            this.orderBy = orderBy;
        }

        public List<DecorationBean> getDecoration() {
            return decoration;
        }

        public void setDecoration(List<DecorationBean> decoration) {
            this.decoration = decoration;
        }

        public List<AgeBean> getAge() {
            return age;
        }

        public void setAge(List<AgeBean> age) {
            this.age = age;
        }

        public List<LayoutBean> getLayout() {
            return layout;
        }

        public void setLayout(List<LayoutBean> layout) {
            this.layout = layout;
        }

        public List<LableBean> getLable() {
            return lable;
        }

        public void setLable(List<LableBean> lable) {
            this.lable = lable;
        }

        public List<FacilitiesBean> getFacilities() {
            return facilities;
        }

        public void setFacilities(List<FacilitiesBean> facilities) {
            this.facilities = facilities;
        }

        public static class DirectionsBean {
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

        public static class AreaBean {
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

        public static class PriceBean {
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

        public static class OrderByBean {
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

        public static class DecorationBean {
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

        public static class AgeBean {
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

        public static class LayoutBean {
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

        public static class LableBean {
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

        public static class FacilitiesBean {
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
}
