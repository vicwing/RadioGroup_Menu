package com.example.cdj.myapplication.mainfunction.taxcaculator;

/**
 * 税率计算器用到的辅助方法
 * Created by cdj on 2016/5/30.
 */
public class TaxUitls {



    /**
     * 是否唯一一套
     * @param onlyOne
     * @return
     */
    public static boolean isOnlyHouse(String onlyOne){
        if (onlyOne.equals(TaxMainFragment.ONLYONE)){
            return  true;
        }
        return false;
    }

    /**
     * 是否北上广深
     * @param currentCity
     * @return
     */
    public static boolean isSpcialCcity(String currentCity) {
        if (currentCity.equalsIgnoreCase(EnumCity.SHENZHEN.name) || currentCity.equalsIgnoreCase(EnumCity.GUANGZHOU.name) ||
                currentCity.equalsIgnoreCase(EnumCity.BEIJING.name) || currentCity.equalsIgnoreCase(EnumCity.SHANGHAI.name)) {
            return true;
        }
        return false;
    }

    /**
     *  获取房屋年限
     * @param latestSale
     * @return
     */
    public static int  getBuyHouseTime(String latestSale){
        if (latestSale.equals(TaxMainFragment.OVER_5_YEARS)){
            return 5;
        }else if(latestSale.equals(TaxMainFragment.OVER_2_5_YEARS)){
            return 3;
        }else  {
            return 1;
        }
    }
}
