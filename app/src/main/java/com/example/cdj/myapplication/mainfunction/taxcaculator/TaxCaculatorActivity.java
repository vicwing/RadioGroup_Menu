package com.example.cdj.myapplication.mainfunction.taxcaculator;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;

import com.example.cdj.myapplication.R;
import com.example.cdj.myapplication.mainfunction.caculate.BackHandlerHelper;
import com.example.cdj.myapplication.mainfunction.caculate.impl.OnHeadlineSelectedListener;
import com.orhanobut.logger.Logger;

/**
 * Created by cdj on 2016/5/26.
 */
public class TaxCaculatorActivity extends FragmentActivity implements OnHeadlineSelectedListener {

    public static final String HOUSE_AREA = "house_area";
    public static final String HOUSE_PRICE = "house_price";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.frame);
        overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);

        int area = getIntent().getIntExtra(HOUSE_AREA, 0);
        int price = getIntent().getIntExtra(HOUSE_PRICE, 0);

        Bundle bundle = new Bundle();
        bundle.putInt(HOUSE_AREA,area);
        bundle.putInt(HOUSE_PRICE,price);

        addFragment(TaxMainFragment.class.getName(), bundle);
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
        transaction.replace(R.id.frame_container, fragment).addToBackStack(null).commit();
    }

    /**
     * 添加fragment到  frame_container ;
     *
     * @param fragmentClassName
     * @param args
     */
    public void addFragment(String fragmentClassName, Bundle args) {
        Fragment fragment = Fragment.instantiate(this, fragmentClassName, args);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.setCustomAnimations(R.anim.push_right_in, R.anim.push_right_out, R.anim.push_right_in, R.anim.push_right_out);
        transaction.add(R.id.frame_container, fragment, fragmentClassName);
        transaction.addToBackStack(fragmentClassName);
        transaction.commit();
        Logger.d("addFragment   " + fragmentClassName + "\n  stackCount   " + getSupportFragmentManager().getBackStackEntryCount());
    }

    @Override
    public void onCallBackData(String num) {

    }


    @Override
    public void onAddFragment(String fragmentName, Bundle bundle) {
        addFragment(fragmentName, bundle);
    }

    @Override
    public void onCallBackData(float percent, int price) {

    }

    /**
     * 处理返回键
     */
    @Override
    public void onBackPressed() {
        if (!BackHandlerHelper.handleBackPress(this)) {
            super.onBackPressed();
        }
        if (1 == getSupportFragmentManager().getBackStackEntryCount()) {
            finish();
        }
    }
}
