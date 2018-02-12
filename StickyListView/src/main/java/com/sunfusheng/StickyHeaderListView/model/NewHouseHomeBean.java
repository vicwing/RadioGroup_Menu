package com.sunfusheng.StickyHeaderListView.model;

import java.util.List;

/**
 * Created by vic on 2016/7/12.
 */
public class NewHouseHomeBean extends QFJSONResult<NewHouseHomeBean.ResultBean> {

    public static class ResultBean{
        private int onSaleCount;
        private HotGroupBuyLMBean hotGroupBuyLM;

        private List<NewHouseHomeAdvTopBanner> indexTopBannerAdList;//广告

        private List<BrickListBean> brickList;//     模式：全部楼盘 ＋ 多个特色标签（多个一般为5个）,   此处需要在运营平台新房特色处增加移动端的相应图片配置。

        private List<HotGroupBuyListBean> hotGroupBuyList;//热门团购

        private List<HotNewhouselistBean> hotNewhouselist;//热门新盘

        private List<GuideListBean> guideList;//楼盘导购

        public int getOnSaleCount() {
            return onSaleCount;
        }

        public void setOnSaleCount(int onSaleCount) {
            this.onSaleCount = onSaleCount;
        }

        public HotGroupBuyLMBean getHotGroupBuyLM() {
            return hotGroupBuyLM;
        }

        public void setHotGroupBuyLM(HotGroupBuyLMBean hotGroupBuyLM) {
            this.hotGroupBuyLM = hotGroupBuyLM;
        }

        public List<NewHouseHomeAdvTopBanner> getIndexTopBannerAdList() {
            return indexTopBannerAdList;
        }

        public void setIndexTopBannerAdList(List<NewHouseHomeAdvTopBanner> indexTopBannerAdList) {
            this.indexTopBannerAdList = indexTopBannerAdList;
        }

        public List<GuideListBean> getGuideList() {
            return guideList;
        }

        public void setGuideList(List<GuideListBean> guideList) {
            this.guideList = guideList;
        }

        public List<HotNewhouselistBean> getHotNewhouselist() {
            return hotNewhouselist;
        }

        public void setHotNewhouselist(List<HotNewhouselistBean> hotNewhouselist) {
            this.hotNewhouselist = hotNewhouselist;
        }

        public List<BrickListBean> getBrickList() {
            return brickList;
        }

        public void setBrickList(List<BrickListBean> brickList) {
            this.brickList = brickList;
        }

        public List<HotGroupBuyListBean> getHotGroupBuyList() {
            return hotGroupBuyList;
        }

        public void setHotGroupBuyList(List<HotGroupBuyListBean> hotGroupBuyList) {
            this.hotGroupBuyList = hotGroupBuyList;
        }

        /**
         *
         */
        public static class HotGroupBuyLMBean {
            private String desc;
            private String param;

            public String getDesc() {
                return desc;
            }

            public void setDesc(String desc) {
                this.desc = desc;
            }

            public String getParam() {
                return param;
            }

            public void setParam(String param) {
                this.param = param;
            }
        }


        /**
         *  楼盘导购
         */
        public static class GuideListBean {
            private int id;
            private String infoPicture;
            private String publishTime;
            private String title;

            public int getId() {
                return id;
            }

            public String getInfoPicture() {
                return infoPicture;
            }

            public void setInfoPicture(String infoPicture) {
                this.infoPicture = infoPicture;
            }

            public void setId(int id) {
                this.id = id;
            }
            public String getPublishTime() {
                return publishTime;
            }

            public void setPublishTime(String publishTime) {
                this.publishTime = publishTime;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }
        }
        /**
         * 热门新盘
         */
        public static class HotNewhouselistBean {
            private int applicantsNumber;
            private String avgPrice;
            private GardenBean garden;
            private List<GroupBuyList> groupBuyList;
            private String homePictureUrl;
            private String saleStatus;
            private String status;

            public  static class GroupBuyList{

                private String groupBuyId;
                private boolean isGroupBuy;
                private int applicantsNumber;
                private String favorableTitle;

                public String getGroupBuyId() {
                    return groupBuyId;
                }

                public void setGroupBuyId(String groupBuyId) {
                    this.groupBuyId = groupBuyId;
                }

                public boolean isIsGroupBuy() {
                    return isGroupBuy;
                }

                public void setIsGroupBuy(boolean isGroupBuy) {
                    this.isGroupBuy = isGroupBuy;
                }

                public int getApplicantsNumber() {
                    return applicantsNumber;
                }

                public void setApplicantsNumber(int applicantsNumber) {
                    this.applicantsNumber = applicantsNumber;
                }

                public String getFavorableTitle() {
                    return favorableTitle;
                }

                public void setFavorableTitle(String favorableTitle) {
                    this.favorableTitle = favorableTitle;
                }
            }

            public int getApplicantsNumber() {
                return applicantsNumber;
            }

            public void setApplicantsNumber(int applicantsNumber) {
                this.applicantsNumber = applicantsNumber;
            }

            public String getAvgPrice() {
                return avgPrice;
            }

            public void setAvgPrice(String avgPrice) {
                this.avgPrice = avgPrice;
            }

            public GardenBean getGarden() {
                return garden;
            }

            public void setGarden(GardenBean garden) {
                this.garden = garden;
            }

            public  List<GroupBuyList>  getGroupBuyList() {
                return groupBuyList;
            }

            public void setGroupBuyList( List<GroupBuyList>  groupBuyList) {
                this.groupBuyList = groupBuyList;
            }

            public String getHomePictureUrl() {
                return homePictureUrl;
            }

            public void setHomePictureUrl(String homePictureUrl) {
                this.homePictureUrl = homePictureUrl;
            }

            public String getSaleStatus() {
                return saleStatus;
            }

            public void setSaleStatus(String saleStatus) {
                this.saleStatus = saleStatus;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public static class GardenBean {
                private String id;
                private String name;
                private RegionBean region;

                public String getId() {
                    return id;
                }

                public void setId(String id) {
                    this.id = id;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public RegionBean getRegion() {
                    return region;
                }

                public void setRegion(RegionBean region) {
                    this.region = region;
                }

                public static class RegionBean {
                    private Object id;
                    private String name;

                    private ParentBean parent;

                    public Object getId() {
                        return id;
                    }

                    public void setId(Object id) {
                        this.id = id;
                    }

                    public String getName() {
                        return name;
                    }

                    public void setName(String name) {
                        this.name = name;
                    }

                    public ParentBean getParent() {
                        return parent;
                    }

                    public void setParent(ParentBean parent) {
                        this.parent = parent;
                    }

                    public static class ParentBean {
                        private Object id;
                        private String name;
                        private Object parent;

                        public Object getId() {
                            return id;
                        }

                        public void setId(Object id) {
                            this.id = id;
                        }

                        public String getName() {
                            return name;
                        }

                        public void setName(String name) {
                            this.name = name;
                        }

                        public Object getParent() {
                            return parent;
                        }

                        public void setParent(Object parent) {
                            this.parent = parent;
                        }
                    }
                }
            }
        }

        public static class BrickListBean {
            private String id;
            private String name;
            private String nameTemp;
            private String picture;
            private String searchStr;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getNameTemp() {
                return nameTemp;
            }

            public void setNameTemp(String nameTemp) {
                this.nameTemp = nameTemp;
            }

            public String getPicture() {
                return picture;
            }

            public void setPicture(String picture) {
                this.picture = picture;
            }

            public String getSearchStr() {
                return searchStr;
            }

            public void setSearchStr(String searchStr) {
                this.searchStr = searchStr;
            }
        }

    }
}
