package com.example.cdj.myapplication.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.view.ViewPager;
import android.view.MenuItem;

import com.example.cdj.myapplication.R;
import com.example.cdj.myapplication.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 类描述： https://juejin.im/post/5bf92436e51d4561a86c04f3
 * 创建人：vicwing
 * 创建时间：2019-08-16 15:46
 * 最后修改人：vicwing
 */
public class SecondMainBottomNavigationActivity extends BaseActivity implements BottomNavigationView.OnNavigationItemSelectedListener {
    @BindView(R.id.viewpager)
    ViewPager viewpager;
    @BindView(R.id.bottomnavigationview)
    BottomNavigationView bottomnavigationview;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_bottom_navigation);
        ButterKnife.bind(this);
        bottomnavigationview.setOnNavigationItemSelectedListener(this);
//        BottomNavigationViewHelper.disableShiftMode(bottomnavigationview);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.tab_one:
                return true;
            case R.id.tab_two:
                return true;
            case R.id.tab_three:
                return true;
            case R.id.tab_four:
                return true;
        }
        return false;
    }
}
