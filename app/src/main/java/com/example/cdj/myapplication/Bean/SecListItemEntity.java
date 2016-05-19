package com.example.cdj.myapplication.Bean;

/**
 * Created by cdj onCallBackData 2016/5/12.
 * 二手房详情页列表item 实体
 */
public  class SecListItemEntity {
    private double area;
    private int bathRoom;
    private int bedRoom;
    private String decoration;
    private String direction;
    private int floor;
    private GardenEntity garden;
    private boolean hasCollection;
    private String id;
    private int livingRoom;
    private String livingRoomPictrue;
    private int price;
    private String roomSourceEnum;
    private String roomType;
    private String title;
    private int totalFloor;

    public double getArea() {
        return area;
    }

    public void setArea(double area) {
        this.area = area;
    }

    public int getBathRoom() {
        return bathRoom;
    }

    public void setBathRoom(int bathRoom) {
        this.bathRoom = bathRoom;
    }

    public int getBedRoom() {
        return bedRoom;
    }

    public void setBedRoom(int bedRoom) {
        this.bedRoom = bedRoom;
    }

    public String getDecoration() {
        return decoration;
    }

    public void setDecoration(String decoration) {
        this.decoration = decoration;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public GardenEntity getGarden() {
        return garden;
    }

    public void setGarden(GardenEntity garden) {
        this.garden = garden;
    }

    public boolean isHasCollection() {
        return hasCollection;
    }

    public void setHasCollection(boolean hasCollection) {
        this.hasCollection = hasCollection;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getLivingRoom() {
        return livingRoom;
    }

    public void setLivingRoom(int livingRoom) {
        this.livingRoom = livingRoom;
    }

    public String getLivingRoomPictrue() {
        return livingRoomPictrue;
    }

    public void setLivingRoomPictrue(String livingRoomPictrue) {
        this.livingRoomPictrue = livingRoomPictrue;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getRoomSourceEnum() {
        return roomSourceEnum;
    }

    public void setRoomSourceEnum(String roomSourceEnum) {
        this.roomSourceEnum = roomSourceEnum;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getTotalFloor() {
        return totalFloor;
    }

    public void setTotalFloor(int totalFloor) {
        this.totalFloor = totalFloor;
    }

    public static class GardenEntity {
        private String id;
        private double latitude;
        private double longitude;
        private String name;
        /**
         * name : 布吉
         * parent : {"name":"龙岗"}
         */

        private RegionEntity region;
        private int rentRoomCount;

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

        public RegionEntity getRegion() {
            return region;
        }

        public void setRegion(RegionEntity region) {
            this.region = region;
        }

        public int getRentRoomCount() {
            return rentRoomCount;
        }

        public void setRentRoomCount(int rentRoomCount) {
            this.rentRoomCount = rentRoomCount;
        }

        public static class RegionEntity {
            private String name;
            /**
             * name : 龙岗
             */

            private ParentEntity parent;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public ParentEntity getParent() {
                return parent;
            }

            public void setParent(ParentEntity parent) {
                this.parent = parent;
            }

            public static class ParentEntity {
                private String name;

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }
            }
        }
    }
}