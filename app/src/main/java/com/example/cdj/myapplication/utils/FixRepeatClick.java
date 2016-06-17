package com.example.cdj.myapplication.utils;

import com.orhanobut.logger.Logger;

/**
 * 防止重复点击
 */
public class FixRepeatClick {
    private static long lastClickTime;

    /**
     * 两次点击时间相隔小于800ms,便不会触发事件
     * @return
     */
    public static boolean isFastDoubleClick() {
        long time = System.currentTimeMillis();
        long timeD = time - lastClickTime;
        Logger.d("lastClickTime  "+lastClickTime  +"   timeD   "+timeD);
        if ( 0 < timeD && timeD < 1000) {
            return true;
        }
        lastClickTime = time;
        return false;
    }
    public synchronized static boolean isFastClick() {
        long time = System.currentTimeMillis();
        if ( time - lastClickTime < 500) {
            return true;
        }
        lastClickTime = time;
        return false;
    }
}