package com.sunfusheng.StickyHeaderListView.model;

import java.util.List;

/**
 * 热门团购
 */
public  class HotGroupBuyListBean {
    private String applicantsNumber;
    private int avgPrice;
    private GardenBean garden;
    private String homePictureUrl;
    private String saleStatus;
    private String status;
    private List<GroupBuyListBean> groupBuyList;

    public String getApplicantsNumber() {
        return applicantsNumber;
    }

    public void setApplicantsNumber(String applicantsNumber) {
        this.applicantsNumber = applicantsNumber;
    }

    public int getAvgPrice() {
        return avgPrice;
    }

    public void setAvgPrice(int avgPrice) {
        this.avgPrice = avgPrice;
    }

    public GardenBean getGarden() {
        return garden;
    }

    public void setGarden(GardenBean garden) {
        this.garden = garden;
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

    public List<GroupBuyListBean> getGroupBuyList() {
        return groupBuyList;
    }

    public void setGroupBuyList(List<GroupBuyListBean> groupBuyList) {
        this.groupBuyList = groupBuyList;
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
            private String id;
            private String name;
            private ParentBean parent;

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

            public ParentBean getParent() {
                return parent;
            }

            public void setParent(ParentBean parent) {
                this.parent = parent;
            }

            public static class ParentBean {
                private String id;
                private String name;
                private Object parent;

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

                public Object getParent() {
                    return parent;
                }

                public void setParent(Object parent) {
                    this.parent = parent;
                }
            }
        }
    }

    public static class GroupBuyListBean {
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
}