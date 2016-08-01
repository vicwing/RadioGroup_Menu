package com.example.cdj.myapplication.base;

import android.support.v7.app.AppCompatActivity;

/**
 * Created by vic on 2016/7/27.
 */
public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        RefWatcher refWatcher = BaseApplication.getRefWatcher(this);
//        refWatcher.watch(this);
    }
}
