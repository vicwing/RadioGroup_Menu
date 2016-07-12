package com.sunfusheng.StickyHeaderListView.model;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by vic on 2016/7/11.
 */
public class NewHouseListItemBean extends QFJSONResult<NewHouseListItemBean.ResultBean> {

    public static class ResultBean {
        private int recordCount;
        private int pageCount;
        private boolean bCityJudgeGroupBuy;
        private int pageSize;
        private int currentPage;
        private List<NewHouseListItem> list;

        public int getRecordCount() {
            return recordCount;
        }

        public void setRecordCount(int recordCount) {
            this.recordCount = recordCount;
        }

        public int getPageCount() {
            return pageCount;
        }

        public void setPageCount(int pageCount) {
            this.pageCount = pageCount;
        }

        public boolean isbCityJudgeGroupBuy() {
            return bCityJudgeGroupBuy;
        }

        public void setbCityJudgeGroupBuy(boolean bCityJudgeGroupBuy) {
            this.bCityJudgeGroupBuy = bCityJudgeGroupBuy;
        }

        public int getPageSize() {
            return pageSize;
        }

        public void setPageSize(int pageSize) {
            this.pageSize = pageSize;
        }

        public int getCurrentPage() {
            return currentPage;
        }

        public void setCurrentPage(int currentPage) {
            this.currentPage = currentPage;
        }

        public List<NewHouseListItem> getList() {
            return list;
        }

        public void setList(List<NewHouseListItem> list) {
            this.list = list;
        }
    }
    public static class NewHouseListItem {
        private String applicantsNumber;
        private BigDecimal avgPrice;
        private String decoration;
        private String favorableTitle;
        private GardenBean garden;
        private String homePictureUrl;
        private boolean isGroupBuy;
        private String latitude;
        private String longitude;
        private String price;
        private String propertyType;
        private String saleStatus;
        private String spreadType;
        private List<String> features;
        private List<GroupBuyList> groupBuyList;


        public String getApplicantsNumber() {
            return applicantsNumber;
        }

        public void setApplicantsNumber(String applicantsNumber) {
            this.applicantsNumber = applicantsNumber;
        }

        public BigDecimal getAvgPrice() {
            return avgPrice;
        }

        public void setAvgPrice(BigDecimal avgPrice) {
            this.avgPrice = avgPrice;
        }

        public String getDecoration() {
            return decoration;
        }

        public void setDecoration(String decoration) {
            this.decoration = decoration;
        }

        public String getFavorableTitle() {
            return favorableTitle;
        }

        public void setFavorableTitle(String favorableTitle) {
            this.favorableTitle = favorableTitle;
        }

        public GardenBean getGarden() {
            return garden;
        }

        public void setGarden(GardenBean garden) {
            this.garden = garden;
        }

        public List<GroupBuyList> getGroupBuyList() {
            return groupBuyList;
        }

        public void setGroupBuyList(List<GroupBuyList> groupBuyList) {
            this.groupBuyList = groupBuyList;
        }

        public String getHomePictureUrl() {
            return homePictureUrl;
        }

        public void setHomePictureUrl(String homePictureUrl) {
            this.homePictureUrl = homePictureUrl;
        }

        public boolean isGroupBuy() {
            return isGroupBuy;
        }

        public void setGroupBuy(boolean groupBuy) {
            isGroupBuy = groupBuy;
        }

        public String getLatitude() {
            return latitude;
        }

        public void setLatitude(String latitude) {
            this.latitude = latitude;
        }

        public String getLongitude() {
            return longitude;
        }

        public void setLongitude(String longitude) {
            this.longitude = longitude;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getPropertyType() {
            return propertyType;
        }

        public void setPropertyType(String propertyType) {
            this.propertyType = propertyType;
        }

        public String getSaleStatus() {
            return saleStatus;
        }

        public void setSaleStatus(String saleStatus) {
            this.saleStatus = saleStatus;
        }

        public String getSpreadType() {
            return spreadType;
        }

        public void setSpreadType(String spreadType) {
            this.spreadType = spreadType;
        }

        public List<String> getFeatures() {
            return features;
        }

        public void setFeatures(List<String> features) {
            this.features = features;
        }

        public static class GroupBuyList {

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

        public static class GardenBean {
            private String address;
            private String area;
            private String id;
            private String indexPictureUrl;
            private double latitude;
            private double longitude;
            private String name;
            private BigDecimal price;

            private RegionBean region;

            public String getAddress() {
                return address;
            }

            public void setAddress(String address) {
                this.address = address;
            }

            public String getArea() {
                return area;
            }

            public void setArea(String area) {
                this.area = area;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getIndexPictureUrl() {
                return indexPictureUrl;
            }

            public void setIndexPictureUrl(String indexPictureUrl) {
                this.indexPictureUrl = indexPictureUrl;
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

            public BigDecimal getPrice() {
                return price;
            }

            public void setPrice(BigDecimal price) {
                this.price = price;
            }

            public RegionBean getRegion() {
                return region;
            }

            public void setRegion(RegionBean region) {
                this.region = region;
            }

            public static class RegionBean {
                private String name;

                private ParentBean parent;

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
                    private String name;
                    private String parent;

                    public String getName() {
                        return name;
                    }

                    public void setName(String name) {
                        this.name = name;
                    }

                    public String getParent() {
                        return parent;
                    }

                    public void setParent(String parent) {
                        this.parent = parent;
                    }
                }
            }
        }
    }
}
