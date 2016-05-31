package com.example;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

public class MyClass {

    public static void  main(String[] args){

        // 得到德国的格式
        NumberFormat nf2 =
                NumberFormat.getInstance(Locale.CHINESE);
        System.out.println(nf2.format(15644546541l));

        int i = BigDecimal.valueOf(5000000 / 1.05f * 0.056f).setScale(0, RoundingMode.HALF_UP).intValue();
        System.out.println("结果"+i );

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
