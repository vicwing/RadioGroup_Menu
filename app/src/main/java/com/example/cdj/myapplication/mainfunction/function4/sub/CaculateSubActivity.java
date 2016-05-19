package com.example.cdj.myapplication.mainfunction.function4.sub;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;

import com.example.cdj.myapplication.R;

/**
 * 所有计算器的二级界面,共用一个activity,通过fragment切换界面
 * Created by cdj on 2016/5/18.
 */
public class CaculateSubActivity extends FragmentActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.frame);
        replaceFragment(CommercialInputFragment.class.getName(),null);
    }

    /**
     * 添加Fragment到容器
     *
     * @param fragmentClassName
     * @param args
     */
    public void replaceFragment(String fragmentClassName, Bundle args) {
        Fragment fragment = Fragment.instantiate(this, fragmentClassName, args);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container, fragment).commit();
    }

}
