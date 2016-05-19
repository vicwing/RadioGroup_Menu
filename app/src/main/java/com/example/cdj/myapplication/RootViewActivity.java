package com.example.cdj.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;

/**
 * 动态外层添加一个view
 * Created by cdj on 2016/5/18.
 */
public class RootViewActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_rootview_test);
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT);
        FrameLayout rootLayout = new FrameLayout(this);
        View view = View.inflate(this, R.layout.activity_rootview_test, null);
        rootLayout.addView(view);
        addContentView(rootLayout,params);
    }
}
