package com.sunfusheng.StickyHeaderListView.newDropDownMenu.view.betterDoubleGrid;

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
import com.sunfusheng.StickyHeaderListView.model.FilterBean;
import com.sunfusheng.StickyHeaderListView.newDropDownMenu.FilterUrl;
import com.sunfusheng.StickyHeaderListView.newDropDownMenu.view.betterDoubleGrid.holder.ItemViewHolder;

import java.util.HashMap;
import java.util.List;

/**
 * auther: baiiu
 * time: 16/6/5 05 23:03
 * description:
 */
public class BetterDoubleGridView extends LinearLayout implements View.OnClickListener, ItemViewHolder.ClickListener {
    RecyclerView recyclerView;

    private List<String> mTopGridData;
    private List<String> mBottomGridList;
    private OnFilterDoneListener mOnFilterDoneListener;

    //    private ItemViewHolder.ClickListener mCallback;
    private HashMap<String, List<FilterBean>> hashMap;
    private DoubleGridAdapter2 doubleGridAdapter2;

    public BetterDoubleGridView(Context mContext) {
        super(mContext, null);
//        mCallback = callback;
        init(mContext);
    }

    public BetterDoubleGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public BetterDoubleGridView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public BetterDoubleGridView(Context context, AttributeSet attrs, int defStyleAttr,
                                int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }


    private void init(Context context) {
        setBackgroundColor(Color.WHITE);
        inflate(context, R.layout.merge_filter_double_grid, this);
        findViewById(R.id.btn_clear).setOnClickListener(this);
        findViewById(R.id.bt_confirm).setOnClickListener(this);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);

    }


    public BetterDoubleGridView setmTopGridData(List<String> mTopGridData) {
        this.mTopGridData = mTopGridData;
        return this;
    }

    public BetterDoubleGridView setmBottomGridList(List<String> mBottomGridList) {
        this.mBottomGridList = mBottomGridList;
        return this;
    }

    public BetterDoubleGridView setGidMap(HashMap<String, List<FilterBean>> gridList) {
        this.hashMap = gridList;
        return this;
    }

    private List<FilterBean> label;
    private List<FilterBean> age;
    private List<FilterBean> decoration;
    private List<FilterBean> area;
    private int firstSection;
    private int secondSection;
    private int thirdSection;

    public BetterDoubleGridView build() {

        area = hashMap.get("面积");
        label = hashMap.get("标签");
        age = hashMap.get("房龄");
        decoration = hashMap.get("装修");

        firstSection = area.size() + 1;
        secondSection = area.size() + label.size() + 2;
        thirdSection = area.size() + label.size() + age.size() + 3;

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this.getContext(), 4);
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
//                if (position == 0 || position == mTopGridData.size() + 1) {
//                    return 4;
//                }
//                return 1;

                if (position == 0 || position == firstSection ||
                        position == secondSection || position == thirdSection) {
                    return 4;
                }
                return 1;
            }
        });
        recyclerView.setLayoutManager(gridLayoutManager);
//        recyclerView.setAdapter(new DoubleGridAdapter(getContext(), mTopGridData, mBottomGridList, this));
//        recyclerView.setAdapter(new DoubleGridAdapter2(getContext(), mTopGridData, mBottomGridList, this, mCallback));
        doubleGridAdapter2 = new DoubleGridAdapter2(getContext(), hashMap, this);
        recyclerView.setAdapter(doubleGridAdapter2);
        return this;
    }

    private TextView mTopSelectedTextView;
    private TextView mBottomSelectedTextView;

    @Override
    public void onClick(View v) {

//        TextView textView = (TextView) v;
//        String text = (String) textView.getTag();
//
//        if (textView == mTopSelectedTextView) {
//            mTopSelectedTextView = null;
//            textView.setSelected(false);
//        } else if (textView == mBottomSelectedTextView) {
//            mBottomSelectedTextView = null;
//            textView.setSelected(false);
//        } else if (mTopGridData.contains(text)) {
//            if (mTopSelectedTextView != null) {
//                mTopSelectedTextView.setSelected(false);
//            }
//            mTopSelectedTextView = textView;
//            textView.setSelected(true);
//        } else {
//            if (mBottomSelectedTextView != null) {
//                mBottomSelectedTextView.setSelected(false);
//            }
//            mBottomSelectedTextView = textView;
//            textView.setSelected(true);
//        }
        LogUtils.d("onClick   position " + v);
        int id = v.getId();
//        DoubleGridAdapter2 doubleGridAdapter2 = (DoubleGridAdapter2) recyclerView.getAdapter();
        if (id == R.id.bt_confirm) {
//            List<Integer> selectedItems = doubleGridAdapter2.getSelectedItems();
            if (mOnFilterDoneListener != null) {
                mOnFilterDoneListener.onFilterDone(3, null);
            }
        } else if (id == R.id.btn_clear) {
            doubleGridAdapter2.clearSelectedState();
        }
    }


    public BetterDoubleGridView setOnFilterDoneListener(OnFilterDoneListener listener) {
        mOnFilterDoneListener = listener;
        return this;
    }

    public void clickDone() {

        FilterUrl.instance().doubleGridTop = mTopSelectedTextView == null ? "" : (String) mTopSelectedTextView.getTag();
        FilterUrl.instance().doubleGridBottom = mBottomSelectedTextView == null ? "" : (String) mBottomSelectedTextView.getTag();

        DoubleGridAdapter2 doubleGridAdapter2 = (DoubleGridAdapter2) recyclerView.getAdapter();
        List<Integer> selectedItems = doubleGridAdapter2.getSelectedItems();
        LogUtils.d("getSelectedItems   " + selectedItems);
        if (mOnFilterDoneListener != null) {
            mOnFilterDoneListener.onFilterDone(3, FilterUrl.instance().doubleGridTop, FilterUrl.instance().doubleGridBottom);
        }
    }

    public RecyclerView getRecyclerView() {
        return recyclerView;
    }


    @Override
    public void onItemClicked(int position) {
        DoubleGridAdapter2 adapter = (DoubleGridAdapter2) recyclerView.getAdapter();
        adapter.switchSelectedState(position);
        List<Integer> selectedItems = adapter.getSelectedItems();

        LogUtils.d("DropMenuAdapter   position" + position);
        LogUtils.d("DropMenuAdapter   selectItems" + selectedItems);
        LogUtils.d("setVerticalScrollBarEnabled  " + recyclerView.isVerticalScrollBarEnabled() + "  H   " + recyclerView.isHorizontalScrollBarEnabled());
    }
}
