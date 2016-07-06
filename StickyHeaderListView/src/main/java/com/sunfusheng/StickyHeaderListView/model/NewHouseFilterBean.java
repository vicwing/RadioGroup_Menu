package com.sunfusheng.StickyHeaderListView.model;

import java.io.Serializable;
import java.util.List;

/**
 * 二手房,更多筛选框
 * Created by vic on 2016/7/1.
 */
public class NewHouseFilterBean extends QFJSONResult<NewHouseFilterBean.ResultBean>  implements Serializable{

    public static class ResultBean {
        /**
         * 是否有地铁
         */
        private boolean bMetroRoom;
        private List<FilterBean> price; //价格
        private List<FilterBean> orderBy; //排序
        private List<FilterBean> saleStatus;//销售状态

        private List<FilterBean> features;
        /**
         * 房屋类型 :住宅,写字楼,别墅,商住,商铺
         */
        private List<FilterBean> property;


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

        public boolean isbMetroRoom() {
            return bMetroRoom;
        }

        public void setbMetroRoom(boolean bMetroRoom) {
            this.bMetroRoom = bMetroRoom;
        }

        public List<FilterBean> getSaleStatus() {
            return saleStatus;
        }

        public void setSaleStatus(List<FilterBean> saleStatus) {
            this.saleStatus = saleStatus;
        }

        public List<FilterBean> getFeatures() {
            return features;
        }

        public void setFeatures(List<FilterBean> features) {
            this.features = features;
        }

        public List<FilterBean> getProperty() {
            return property;
        }

        public void setProperty(List<FilterBean> property) {
            this.property = property;
        }
    }
}
