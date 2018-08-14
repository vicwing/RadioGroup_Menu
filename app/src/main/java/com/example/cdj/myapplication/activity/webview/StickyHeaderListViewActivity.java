package com.example.cdj.myapplication.activity.webview;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;

import com.example.cdj.myapplication.R;
import com.example.cdj.myapplication.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Description : 有固定顶部的 RecycleView
 * Created by vicwing
 * Created Time 2018/7/25
 */
public class StickyHeaderListViewActivity extends BaseActivity {
    @BindView(R.id.recycleview)
    RecyclerView recycleview;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sticky_header_list);
        ButterKnife.bind(this);

    }
}
