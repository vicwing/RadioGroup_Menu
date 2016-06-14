package com.example.cdj.myapplication.mainfunction.caculate;

import com.orhanobut.logger.Logger;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * 房贷计算器工具
 * Created by cdj on 2016/6/1.
 */
public class MortCaculatorUtils {


    /**
     * 等额本金
     * 本息总额: 还款月数×(总贷款额×月利率-月利率×(总贷款额÷还款月数)*(还款月数-1)÷2+总贷款额÷还款月数
     *
     * @param amoun      本金
     * @param monthRate  月利率
     * @param totalMonth 总月数
     * @return
     */
    public static int getEqualityCorpusTotalAmount(int amoun, double monthRate, int totalMonth) {
//        double totalAmount = totalMonth * (amoun * monthRate - monthRate * (amoun / totalMonth) * (totalMonth - 1) / 2 +  amoun / totalMonth);
        double v = monthRate * (amoun / totalMonth) * (totalMonth - 1) / 2;
        int i = amoun / totalMonth;
        double v1 = amoun * monthRate;
        double totalAmount = totalMonth * (v1 - v + i);
        return BigDecimal.valueOf(totalAmount).setScale(2, RoundingMode.HALF_UP).intValue();
    }

    /**
     * 等额本金
     * 总利息 : 本息总额-本金
     *
     * @param totalAmount 本息总额
     * @param amount
     * @return
     */
    public static int getEqualityCorpusTotalInterest(int totalAmount, int amount) {
        return totalAmount - amount;
    }

    /**
     * 等额本金
     * 首月月供金额：(贷款本金÷还款月数)+(贷款本金)×月利率
     *
     * @param amount
     * @param monthRate
     * @param totalMonth
     * @return
     */
    public static int getFirstMonthPay(int amount, double monthRate, int totalMonth) {
        double firstMonthPay = (amount / totalMonth) + amount * monthRate;
        return BigDecimal.valueOf(firstMonthPay).setScale(2, RoundingMode.HALF_UP).intValue();
    }

    /**
     * 等额本金
     * 月供递减金额：贷款本金÷还款月数×月利率
     *
     * @param amount     本金
     * @param monthRate  月利率
     * @param totalMonth 还款总月数
     * @return
     */
    public static float getDecreasePay(int amount, double monthRate, int totalMonth) {
        double v = amount / totalMonth * monthRate;
        return BigDecimal.valueOf(v).setScale(2, RoundingMode.HALF_UP).floatValue();
    }

    /**
     * 等额本息  月供计算结果.
     *
     * @param amount     贷款本金
     * @param monthRate  月利率
     * @param totalMonth 贷款月数
     * @return
     */
    public static double getEqualityInterestMonthPay(int amount, double monthRate, int totalMonth) {
//        double pow = BigDecimal.valueOf(Math.pow(1 + monthRate, totalMonth)).setScale(6, RoundingMode.HALF_UP).doubleValue();
//        double pow = Math.pow(1 + monthRate, totalMonth);
        BigDecimal bigDecimal = new BigDecimal(1 + monthRate);
        double pow = bigDecimal.pow(totalMonth).doubleValue();
        double monthPay = amount * (monthRate * pow) / (pow - 1);
        Logger.d("amount  "+amount+"  montRate "+monthRate+"   monthPay "+monthPay   +"\n pow    "+pow);
//        return BigDecimal.valueOf(monthPay).setScale(4, RoundingMode.HALF_UP).doubleValue();
        return monthPay;
    }

    /**
     * 等额本息   总利息
     *
     * @param amount     本金
     * @param monthRate  月利率
     * @param totalMonth 总月数
     * @return
     */
    public static double getEqualityTotalInterest(int amount, double monthRate, int totalMonth) {
        double equalityInterestMonthPay = getEqualityInterestMonthPay(amount, monthRate, totalMonth);
        return equalityInterestMonthPay * totalMonth - amount;
    }

    /**
     * 等额本息  本息总额
     *
     * @param amount     本金
     * @param monthRate  月利率
     * @param totalMonth 总月数
     * @return
     */
    public static double getEqualitTotalAmount(int amount, double monthRate, int totalMonth) {
        return getEqualityTotalInterest(amount, monthRate, totalMonth) + amount;
    }

    /**
     *  组合贷款
     * @param amount
     * @param totalInterest
     * @return
     */
    public static double getCombinedTotalAmount(int amount , double totalInterest) {
        return amount + totalInterest;
    }

    /**
     * 获取月利率
     *
     * @param rate
     * @return
     */
    public static double getMonthRate(float rate) {
        return BigDecimal.valueOf(rate / 100 / 12).setScale(10, BigDecimal.ROUND_HALF_UP).floatValue();
    }
}
