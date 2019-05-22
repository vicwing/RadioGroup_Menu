package com.example.cdj.myapplication.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.OnScrollListener;
import android.widget.TextView;

import com.example.cdj.myapplication.R;
import com.example.cdj.myapplication.activity.adapter.GallerySnaperAdapter;
import com.example.cdj.myapplication.rxjava.RxLifecycleUtils;
import com.jakewharton.rxbinding2.view.RxView;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.functions.Consumer;


/**
 * 类描述：
 * 创建人：vicwing
 * 创建时间：2019-05-13 17:26
 * 最后修改人：vicwing
 */
public class SnapHelperActivity extends AppCompatActivity {
    @BindView(R.id.recycleview)
    RecyclerView mRecyclerView;
    ArrayList<String> mData;
    LinearLayoutManager mLayoutManager;

    LinearSnapHelper linearSnapHelper;
    GallerySnapHelper gallerySnapHelper;
    @BindView(R.id.tv_rxbinding)
    TextView tvRxbinding;
    @BindView(R.id.tv_result)
    TextView tvResult;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_snapheler);
        ButterKnife.bind(this);
        initData();
//        orignalRecycleview();
        secondRecycleView();
        rxBindingTest();
    }

    private void secondRecycleView() {

        GridLayoutManager layoutManager = new GridLayoutManager(this, 4, LinearLayoutManager.HORIZONTAL, false);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(new GallerySnaperAdapter(mData));
//        mRecyclerView.addOnScrollListener(new OnScrollListener() {
//            @Override
//            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
//                super.onScrollStateChanged(recyclerView, newState);
//            }
//
//            @Override
//            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
//                super.onScrolled(recyclerView, dx, dy);
//                Logger.d("onScrolled:   " + " dx = [" + dx + "], dy = [" + dy + "]");
//            }
//        });
    }

    private void orignalRecycleview() {
        mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(new GallerySnaperAdapter(mData));
        mRecyclerView.addOnScrollListener(new OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                Logger.d("onScrolled:   " + " dx = [" + dx + "], dy = [" + dy + "]");
            }
        });
        linearSnapHelper = new LinearSnapHelper();

//        gallerySnapHelper = new GallerySnapHelper();
//        gallerySnapHelper.attachToRecyclerView(mRecyclerView);
    }

    private void initData() {
        mData = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            mData.add("i=" + i);
        }
    }

    int count = 0;

    private void rxBindingTest() {
        RxView.clicks(tvRxbinding)
                .throttleFirst(3, TimeUnit.SECONDS) //两秒钟之内只取一个0点击事件，防抖操作
                .as(RxLifecycleUtils.bindLifecycle(this))
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(Object o) {
//                        Toast.makeText(mContext, "点击了", Toast.LENGTH_SHORT).show();
                        Logger.d("点击了" + count);
                        tvResult.setText("我被点击了 " + count);
                        count++;
                    }

                });
    }

}
