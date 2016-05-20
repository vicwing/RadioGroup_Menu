package com.example.cdj.myapplication.mainfunction.function4;

import android.os.Bundle;

/**
 *
 * 子fragment和activity通讯接口 .
 *
 * Created by cdj onCallBackData 2016/5/19.
 */
public interface OnHeadlineSelectedListener {
    void onCallBackData(String  num);
    void onReplaceFragment(String fragmentName, Bundle bundle);
    void onCallBackData(float percent,int price);
}
