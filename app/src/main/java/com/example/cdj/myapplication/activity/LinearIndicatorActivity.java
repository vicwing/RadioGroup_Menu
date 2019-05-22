package com.example.cdj.myapplication.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.blankj.utilcode.util.ScreenUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.cdj.myapplication.R;
import com.example.cdj.myapplication.widget.pageindicator.LinePageIndicator;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * 类描述：
 * 创建人：vicwing
 * 创建时间：2019-05-13 17:26
 * 最后修改人：vicwing
 */
public class LinearIndicatorActivity extends AppCompatActivity {
    @BindView(R.id.recycleview)
    RecyclerView mRecyclerView;

    @BindView(R.id.line_indicator)
    LinePageIndicator pageIndicator;
    ArrayList<String> mData;
    private int column = 5;
    private int row = 2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_linear_indicator);
        ButterKnife.bind(this);
        initData();
//        orignalRecycleview();
        secondRecycleView();
    }

    private void secondRecycleView() {
        GridLayoutManager layoutManager = new GridLayoutManager(this, row, LinearLayoutManager.HORIZONTAL, false);
        int screenWidth = ScreenUtils.getScreenWidth() - mRecyclerView.getPaddingLeft() - mRecyclerView.getPaddingRight();
        int itemWidth = screenWidth / column;
        Logger.d("secondRecycleView:  left =  " + mRecyclerView.getPaddingLeft());

        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(new IndicatorAdapter(mData, itemWidth));
        pageIndicator.setRecyclerView(mRecyclerView);
        pageIndicator.setPageColumn(column);
        pageIndicator.setCurrentItem(1);
    }


    private void initData() {
        mData = new ArrayList<>();
        for (int i = 0; i < 60; i++) {
            mData.add("i=" + i);
        }
        ArrayList<String> objects = new ArrayList<>();
        int rowCount = mData.size() / column;
        for (int i = 0; i < mData.size(); i++) {
            String s = mData.get(i);

//            objects.add(i)
        }

    }

    static class IndicatorAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

        private final int itemWidth;

        public IndicatorAdapter(@Nullable List<String> data, int itemWidth) {
            super(R.layout.item_list_simple_text, data);
            this.itemWidth = itemWidth;
        }

        @Override
        protected void convert(BaseViewHolder helper, String item) {
            ViewGroup.LayoutParams layoutParams = helper.itemView.getLayoutParams();
            layoutParams.width = itemWidth;
            helper.setText(R.id.tv_avg_price, item);
        }
    }
}
