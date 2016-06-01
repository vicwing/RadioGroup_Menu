package com.example.cdj.myapplication.mainfunction.caculate;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * 房贷计算器工具
 * Created by cdj on 2016/6/1.
 */
public class MortgageCaculatorUtils {


    /**
     * 等额本金
     * 本息总额: 还款月数×(总贷款额×月利率-月利率×(总贷款额÷还款月数)*(还款月数-1)÷2+总贷款额÷还款月数
     *
     * @param amoun      本金
     * @param monthRate  月利率
     * @param totalMonth 总月数
     * @return
     */
    public static  int getEqualityCorpusTotalAmount(int amoun, float monthRate, int totalMonth) {
        float totalAmount = totalMonth * (amoun * monthRate - monthRate * (amoun / totalMonth) * (totalMonth - 1) / 2 + amoun / totalMonth);
        return BigDecimal.valueOf(totalAmount).setScale(2, RoundingMode.HALF_UP).intValue();
    }

    /**
     * 等额本金
     * 总利息 :本息总额-本金
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
    public static  int getFirstMonthPay(int amount, float monthRate, int totalMonth) {
        float firstMonthPay = (amount / totalMonth) + amount * monthRate;
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
    public static float getDecreasePay(int amount, float monthRate, int totalMonth) {
        float v = amount / totalMonth * monthRate;
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
    public static int getEqualityInterestMonthPay(int amount, float monthRate, int totalMonth) {
        double pow = Math.pow(1 + monthRate, totalMonth);
        double monthPay = amount * (monthRate * pow) / (pow - 1);
        return BigDecimal.valueOf(monthPay).setScale(2, RoundingMode.HALF_UP).intValue();
    }

    /**
     * 等额本息 总利息
     *
     * @param amount     本金
     * @param monthPay   月供
     * @param totalMonth 还款总月数
     * @return
     */
    public static int getEqualityTotalInterest(int amount, int monthPay, int totalMonth) {
        return monthPay * totalMonth - amount;
    }

    /**
     * 等额本息  本息总额
     *
     * @param amount      本金
     * @param totalInrest 总利息
     * @return
     */
    public static int getEqualitTotalAmount(int amount, int totalInrest) {
        return totalInrest + amount;
    }


    /**
     * 获取月利率
     *
     * @param rate
     * @return
     */
    public static  float getMonthRate(float rate) {
        return BigDecimal.valueOf(rate / 100 / 12).setScale(6, BigDecimal.ROUND_HALF_UP).floatValue();
    }
}
