package com.example;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;

public class MyClass {

    public static void  main(String[] args){

        // 得到德国的格式
//        NumberFormat nf2 = NumberFormat.getInstance(Locale.CHINESE);
//        System.out.println(nf2.format(15644546541l));
//        int i = BigDecimal.valueOf(5000000 / 1.05f * 0.056f).setScale(0, RoundingMode.HALF_UP).intValue();
//        System.out.println("结果"+i );

        BigDecimal bigDecimal = BigDecimal.valueOf(Math.pow(1 + 1, 360)).setScale(4, RoundingMode.HALF_UP);
        String s = bigDecimal.toString();
//        double v = BigDecimal.valueOf(Math.pow(1 + 99, 360)).setScale(4, RoundingMode.HALF_UP).doubleValue();
        System.out.println("result  "+s);

        java.text.DecimalFormat decimalFormat = new java.text.DecimalFormat("0.00");
//        decimalFormat.setRoundingMode(RoundingMode.HALF_UP);

        System.out.println("利率  "+decimalFormat.format(5.665));
    }
    // 等额本息 月供;
    public static int equalityInterestResult(int commercialAmount, float monthRate, int loanTerm) {

        System.out.println();
        int amount = commercialAmount * 10000;
        double pow = Math.pow(1 + monthRate, loanTerm);
        double monthPay = amount *(monthRate * pow)/ (pow - 1);
        System.out.println(BigDecimal.valueOf(monthPay).setScale(2, RoundingMode.HALF_UP).doubleValue());
        return  BigDecimal.valueOf(monthPay).setScale(2, RoundingMode.HALF_UP).intValue();
    }

    /**
     * DecimalFormat转换最简便
     */
    public static void m2() {
        DecimalFormat df = new DecimalFormat("#.00");
//        System.out.println(df.format(f));
    }
}
