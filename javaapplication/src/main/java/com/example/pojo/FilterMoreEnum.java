package com.example.pojo;

/**
 * 类描述：筛选更多枚举类.
 * 创建人：vicwing
 * 创建时间：2018/12/6 4:04 PM
 * 最后修改人：vicwing
 */
public enum FilterMoreEnum {
    //新房列表
    FILTER_HOUSE_SALE_STATUS("saleStatus", "销售状态"),
    FILTER_HOUSE_SALE_PROPERTY("t", "物业用途"),
    FILTER_NEWHOUSE_DECORATION("r", "装修情况"),
    FILTER_HOUSE_POINT("feature", "项目特色"),

    FILTER_HOUSE_TYPE("b", "户型"),
    FILTER_MORE_HEATING("h", "供暖方式"),

    //二手房
    FILTER_MORE_AGE("g", "楼龄"),
    FILTER_MORE_AREA("a", "面积"),
    FILTER_MORE_DECORATION("r", "装修"),
    FILTER_MORE_FEATURES("t", "特色"),
    FILTER_MORE_LOUCENG("floorCondition", "楼层"),
    FILTER_MORE_ORIENTATION("direction", "朝向"),
    FILTER_MORE_RENT_TYPE("rentType", "方式"),

    //公寓
    FILTER_ROOM_ORIENTATION("direction", "房间朝向"),
    FILTER_TOTAL_AREA("totalArea", "整套面积"),
    FILTER_TOTAL_DIRECTION("totalDirection", "整套朝向");

    FilterMoreEnum(String paramkey, String desc) {
        this.paramkey = paramkey;
        this.desc = desc;
    }

    /**
     * 筛选描述 唯一
     */
    private String desc;
    /**
     * 筛选参数名
     */
    private String paramkey;

    public String getDesc() {
        return desc;
    }

    public String getParamkey() {
        return paramkey;
    }
}
