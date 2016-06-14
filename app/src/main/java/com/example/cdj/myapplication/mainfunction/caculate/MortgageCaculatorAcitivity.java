package com.example.cdj.myapplication.mainfunction.caculate;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;

import com.example.cdj.myapplication.R;
import com.example.cdj.myapplication.mainfunction.caculate.impl.OnHeadlineSelectedListener;
import com.orhanobut.logger.Logger;

/**
 * 房贷计算器
 * Created by cdj onCallBackData 2016/5/18.
 */
public class MortgageCaculatorAcitivity extends FragmentActivity implements OnHeadlineSelectedListener {


    public final static String HOUSE_STYLE = "houseStyle";
    public final static String TOTAL_PRICE = "totalPrice";

    // 这里的参数只是一个举例可以根据需求更改
    private String mParam1;
    private String mParam2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.frame);
        Bundle bundle = getIntent().getExtras();
        addFragment(CaculateMainFragment.class.getName(), bundle);
    }

    /**
     * 添加fragment到  frame_container ;
     * @param fragmentClassName
     * @param args
     */
    public void addFragment(String fragmentClassName, Bundle args) {
        Fragment fragment = Fragment.instantiate(this, fragmentClassName, args);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.setCustomAnimations(R.anim.push_right_in, R.anim.push_right_out,R.anim.push_right_in, R.anim.push_right_out);
        transaction.add(R.id.frame_container, fragment,fragmentClassName);
        transaction.addToBackStack(fragmentClassName);
        transaction.commit();
        Logger.d("addFragment   "+ fragmentClassName+"\n  stackCount   "+getSupportFragmentManager().getBackStackEntryCount());
    }

    @Override
    public void onCallBackData(String num) {
    }

    @Override
    public void onCallBackData(float percent, int price) {
    }


    @Override
    public void onAddFragment(String fragmentName, Bundle bundle) {
        addFragment(fragmentName,bundle);
    }



    /**
     * 处理返回键
     */
    @Override
    public void onBackPressed() {
        if (!BackHandlerHelper.handleBackPress(this)) {
            super.onBackPressed();
        }
        if (1==getSupportFragmentManager().getBackStackEntryCount()){
            finish();
        }
    }
}
