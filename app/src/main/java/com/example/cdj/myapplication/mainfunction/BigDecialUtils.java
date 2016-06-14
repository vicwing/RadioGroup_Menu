package com.example.cdj.myapplication.mainfunction;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Created by cdj on 2016/6/3.
 */
public class BigDecialUtils {
    /**
     * 四舍五入,整数.
     * @param value
     * @return
     */
    public static int getBigDecimalInt(double value){
        return  BigDecimal.valueOf(value).setScale(0, RoundingMode.HALF_UP).intValue();
    }
}
