package com.sunfusheng.StickyHeaderListView.model;


/**
 * 新房首页广告的banaer
 */
public class NewHouseHomeAdvTopBanner {

    //这两个参数不确定类型
    //            private Object params;
    //            private Object adJumpEnum;
    private String pictureUrl;
    private String effectTime;
    private String expiredTime;
    private String id;
    private String title;
    private String url;


    public String getEffectTime() {
        return effectTime;
    }

    public void setEffectTime(String effectTime) {
        this.effectTime = effectTime;
    }

    public String getExpiredTime() {
        return expiredTime;
    }

    public void setExpiredTime(String expiredTime) {
        this.expiredTime = expiredTime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}