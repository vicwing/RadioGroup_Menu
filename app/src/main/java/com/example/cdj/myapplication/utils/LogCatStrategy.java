package com.example.cdj.myapplication.utils;

import android.util.Log;

import com.orhanobut.logger.LogStrategy;

/**
 * Description : 解决logger在AndroidStudio 3.1.2上打印日志不规范的问题.
 * Created by vicwing
 * Created Time 2018/6/8
 */
public class LogCatStrategy implements LogStrategy {

    @Override
    public void log(int priority, String tag, String message) {
        Log.println(priority, randomKey() + tag, message);
    }

    private int last;

    private String randomKey() {
        int random = (int) (10 * Math.random());
        if (random == last) {
            random = (random + 1) % 10;
        }
        last = random;
        return String.valueOf(random);
    }
}
