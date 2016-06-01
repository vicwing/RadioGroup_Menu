package com.example.cdj.myapplication.mainfunction.taxcaculator;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * 税率计算器用到的辅助方法
 * Created by cdj on 2016/5/30.
 */
public class TaxCaculatorUitls {


    /**
     * 获取增值税
     *
     * @param totalPrice      房屋总价
     * @param buyHouseYear    购买房屋年限
     * @param houseType       普通住房/非普通住房
     * @param currentCity     当前城市
     * @param differencePrice 房屋差价
     * @return
     */
    public static  int getValueAddTax(int totalPrice, int buyHouseYear, String houseType, String currentCity, int differencePrice) {
        if (buyHouseYear < 2) {//未满2年
//            Logger.d("增值税价格"+BigDecimal.valueOf(totalPrice / 1.05f * 0.056f).setScale(0, RoundingMode.HALF_UP));
            return BigDecimal.valueOf(totalPrice / 1.05f * 0.056f).setScale(0, RoundingMode.HALF_UP).intValue();
        } else {
            if (TaxMainFragment.HOUSE_NORMAL.equals(houseType)) {
                return 0;
            } else {//非普通住房
                if (currentCity.equalsIgnoreCase(EnumCity.SHENZHEN.name) || currentCity.equalsIgnoreCase(EnumCity.GUANGZHOU.name) ||
                        currentCity.equalsIgnoreCase(EnumCity.BEIJING.name) || currentCity.equalsIgnoreCase(EnumCity.SHANGHAI.name)) { //北上广深
                    return BigDecimal.valueOf(differencePrice/1.05f * 0.056f).setScale(0, RoundingMode.HALF_UP).intValue();
                } else {
                    return 0;
                }
            }
        }
    }


    /**
     * 个人所得税
     *
     * @param totalPrice   房屋总价 (减去增值税后的价格)
     * @param buyHouseYear 房屋年限
     * @param houseOnly    卖方 是否家庭唯一一套
     * @return
     */
    public static int getPersonaIncomeTax(int totalPrice, int buyHouseYear, boolean houseOnly) {

        if (buyHouseYear < 5 || houseOnly) {//满5年免征
            return BigDecimal.valueOf(totalPrice * 0.01).setScale(0, RoundingMode.HALF_UP).intValue();
        } else {
            return 0;
        }
    }

    /**
     * 契税
     *
     * @param price         交易的价格(减去增值税后的价格)
     * @param area          房子面积
     * @param buyerFirstOne 买方 是否首套,
     * @param currentCity   当前城市
     * @return
     */
    public static int getContractTax(int price, int area, String buyerFirstOne, String currentCity) {

        int baseArea = 90;
        if (TaxMainFragment.Buy_First.equals(buyerFirstOne)) {
            if (area > baseArea) {
                return BigDecimal.valueOf(price * 0.015f).setScale(0, RoundingMode.HALF_UP).intValue();
            } else {
                return BigDecimal.valueOf(price * 0.01f).setScale(0, RoundingMode.HALF_UP).intValue();
            }
        } else {
            if (currentCity.equalsIgnoreCase(EnumCity.SHENZHEN.name) || currentCity.equalsIgnoreCase(EnumCity.GUANGZHOU.name) ||
                    currentCity.equalsIgnoreCase(EnumCity.BEIJING.name) || currentCity.equalsIgnoreCase(EnumCity.SHANGHAI.name)) {
                return BigDecimal.valueOf(price * 0.03f).setScale(0, RoundingMode.HALF_UP).intValue();
            } else {
                if (area < baseArea) {
                    return BigDecimal.valueOf(price * 0.01f).setScale(0, RoundingMode.HALF_UP).intValue();
                } else {
                    return BigDecimal.valueOf(price * 0.02f).setScale(0, RoundingMode.HALF_UP).intValue();
                }
            }
        }
    }


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
