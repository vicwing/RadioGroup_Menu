package com.example.cdj.myapplication.mainfunction.caculate.impl;

import android.os.Bundle;

/**
 *
 * 子fragment 和 activity 通讯接口 .
 * Created by cdj onCallBackData 2016/5/19.
 */
public interface OnHeadlineSelectedListener {
    void onCallBackData(String  num);
    void onAddFragment(String fragmentName, Bundle bundle);
    void onCallBackData(float percent,int price);
}
