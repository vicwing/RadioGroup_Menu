package com.example;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;

public class MyClass {

    public static void  main(String[] args){
        int commercialAmount = 70;
        float commercialRate =4.9f;
        int loanTerm =30;

//        float  monthRate = BigDecimal.valueOf(commercialRate/100/12).setScale(3,BigDecimal.ROUND_HALF_UP).floatValue();
        float monthRate = commercialRate/100/12;

        System.out.println("月利率  " +monthRate);

        double pow = Math.pow(commercialRate, loanTerm);
        System.out.println("pow  "+pow);

        int monthPay = equalityInterestResult(commercialAmount, monthRate, loanTerm*12);

        System.out.println("等额本息 月供   "+monthPay);

        if (true){
            System.out.println("111111111111111111111");
        }
        if (true){
            System.out.println("22222222222222222222");
        }else if (true){
            System.out.println("33333333333333333333333333");
        }

    }
    // 等额本息 月供;
    public static int equalityInterestResult(int commercialAmount, float monthRate, int loanTerm) {

        System.out.println();
        int amount = commercialAmount * 10000;
        double pow = Math.pow(1 + monthRate, loanTerm);
//        BigDecimal bigDecimal = BigDecimal.valueOf(pow).setScale(3, RoundingMode.HALF_UP);

//        System.out.println("bigDecimal  "+bigDecimal);
        double monthPay = amount *(monthRate * pow)/ (pow - 1);
//        double monthPay = amount *(monthRate *bigDecimal.doubleValue())/ (bigDecimal.doubleValue() - 1);
//        double monthPay = amount * (rate * Math.pow(1 + rate, loanTerm)) / (Math.pow(1+rate, loanTerm) - 1);
//        double monthPay = amount * (rate * (1 + rate)*loanTerm)  / ((1 + rate)*loanTerm - 1);
//        double monthPay = amount * (rate * (1 + rate)*loanTerm)  / ((1 + rate)*loanTerm - 1);

//         BigDecimal.valueOf(monthPay).setScale(2, RoundingMode.HALF_UP).intValue();
        System.out.println(BigDecimal.valueOf(monthPay).setScale(2, RoundingMode.HALF_UP).doubleValue());
        return  BigDecimal.valueOf(monthPay).setScale(2, RoundingMode.HALF_UP).intValue();
//        return (int) monthPay;
    }

    /**
     * DecimalFormat转换最简便
     */
    public static void m2() {
        DecimalFormat df = new DecimalFormat("#.00");
//        System.out.println(df.format(f));
    }
}
