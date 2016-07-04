package com.sunfusheng.StickyHeaderListView.newDropDownMenu.view.NewDoubleSelectedGrid;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.apkfuns.logutils.LogUtils;
import com.baiiu.filter.interfaces.OnFilterDoneListener;
import com.sunfusheng.StickyHeaderListView.R;
import com.sunfusheng.StickyHeaderListView.newDropDownMenu.FilterUrl;
import com.sunfusheng.StickyHeaderListView.newDropDownMenu.view.betterDoubleGrid.DoubleGridAdapter2;
import com.sunfusheng.StickyHeaderListView.newDropDownMenu.view.betterDoubleGrid.holder.ItemViewHolder;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * auther: baiiu
 * time: 16/6/5 05 23:03
 * description:
 */
public class MultSelctBetterDoubleGridView extends LinearLayout implements View.OnClickListener  , ItemViewHolder.ClickListener {

    @Bind(R.id.recyclerView)
    RecyclerView recyclerView;

    private List<String> mTopGridData;
    private List<String> mBottomGridList;
    private OnFilterDoneListener mOnFilterDoneListener;


    public MultSelctBetterDoubleGridView(Context context) {
        this(context, null);
    }

    public MultSelctBetterDoubleGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public MultSelctBetterDoubleGridView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public MultSelctBetterDoubleGridView(Context context, AttributeSet attrs, int defStyleAttr,
                                         int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }

    private void init(Context context) {
        setBackgroundColor(Color.WHITE);
        inflate(context, R.layout.merge_filter_double_grid, this);
        ButterKnife.bind(this, this);
    }


    public MultSelctBetterDoubleGridView setmTopGridData(List<String> mTopGridData) {
        this.mTopGridData = mTopGridData;
        return this;
    }

    public MultSelctBetterDoubleGridView setmBottomGridList(List<String> mBottomGridList) {
        this.mBottomGridList = mBottomGridList;
        return this;
    }

    public MultSelctBetterDoubleGridView build() {

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this.getContext(), 4);
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                if (position == 0 || position == mTopGridData.size() + 1) {
                    return 4;
                }
                return 1;
            }
        });
        recyclerView.setLayoutManager(gridLayoutManager);
//        recyclerView.setAdapter(new DoubleGridAdapter(getContext(), mTopGridData, mBottomGridList, this));
        recyclerView.setAdapter(new DoubleGridAdapter2(getContext(), mTopGridData, mBottomGridList, this,this));
        return this;
    }

    private TextView mTopSelectedTextView;
    private TextView mBottomSelectedTextView;

    @Override
    public void onClick(View v) {

        TextView textView = (TextView) v;
        String text = (String) textView.getTag();

        if (textView == mTopSelectedTextView) {
            mTopSelectedTextView = null;
            textView.setSelected(false);
        } else if (textView == mBottomSelectedTextView) {
            mBottomSelectedTextView = null;
            textView.setSelected(false);
        } else if (mTopGridData.contains(text)) {
            if (mTopSelectedTextView != null) {
                mTopSelectedTextView.setSelected(false);
            }
            mTopSelectedTextView = textView;
            textView.setSelected(true);
        } else {
            if (mBottomSelectedTextView != null) {
                mBottomSelectedTextView.setSelected(false);
            }
            mBottomSelectedTextView = textView;
            textView.setSelected(true);
        }
    }


    public MultSelctBetterDoubleGridView setOnFilterDoneListener(OnFilterDoneListener listener) {
        mOnFilterDoneListener = listener;
        return this;
    }

    @OnClick(R.id.bt_confirm)
    public void clickDone() {

        FilterUrl.instance().doubleGridTop = mTopSelectedTextView == null ? "" : (String) mTopSelectedTextView.getTag();
        FilterUrl.instance().doubleGridBottom = mBottomSelectedTextView == null ? "" : (String) mBottomSelectedTextView.getTag();

        if (mOnFilterDoneListener != null) {
            mOnFilterDoneListener.onFilterDone(3,  FilterUrl.instance().doubleGridTop ,   FilterUrl.instance().doubleGridBottom );
        }
    }


    @Override
    public void onItemClicked(int position) {
        LogUtils.d("22222222222222222222222222222222222222");
    }

}
