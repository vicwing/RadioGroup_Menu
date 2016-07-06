package com.sunfusheng.StickyHeaderListView.newDropDownMenu.view.NewDoubleSelectedGrid;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.baiiu.filter.interfaces.OnFilterDoneListener;
import com.sunfusheng.StickyHeaderListView.R;
import com.sunfusheng.StickyHeaderListView.model.FilterBean;
import com.sunfusheng.StickyHeaderListView.newDropDownMenu.view.betterDoubleGrid.DoubleGridAdapter2;
import com.sunfusheng.StickyHeaderListView.ui.GridViewCompat;

import java.util.HashMap;
import java.util.List;

/**
 * auther: baiiu
 * time: 16/6/5 05 23:03
 * description:
 */
public class FilterMoreGridView extends LinearLayout implements View.OnClickListener {
//    RecyclerView recyclerView;

    private OnFilterDoneListener mOnFilterDoneListener;

    private HashMap<String, List<FilterBean>> hashMap;
    private DoubleGridAdapter2 doubleGridAdapter2;

    private LinearLayout ll_filtermore_container;
    private TextView textTitle;
    private GridViewCompat gv_gridview;

    public FilterMoreGridView(Context mContext) {
        super(mContext, null);
//        mCallback = callback;
        init(mContext);
    }

    public FilterMoreGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public FilterMoreGridView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public FilterMoreGridView(Context context, AttributeSet attrs, int defStyleAttr,
                              int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }


    private void init(Context context) {
        setBackgroundColor(Color.WHITE);
        inflate(context, R.layout.newhouse_filter_more_grid, this);
        textTitle = (TextView) findViewById(R.id.tv_title);
        gv_gridview= (GridViewCompat) findViewById(R.id.gv_gridview);

    }

    public FilterMoreGridView setGidMap(HashMap<String, List<FilterBean>> gridList) {
        this.hashMap = gridList;
        return this;
    }


    public FilterMoreGridView build() {



//        doubleGridAdapter2 = new DoubleGridAdapter2(getContext(), hashMap, this);
//        recyclerView.setAdapter(doubleGridAdapter2);
        return this;
    }

    @Override
    public void onClick(View v) {

        int id = v.getId();
        if (id == R.id.bt_confirm) {
            List<Integer> selectedItems = doubleGridAdapter2.getSelectedItems();
            if (mOnFilterDoneListener != null) {
                mOnFilterDoneListener.onFilterDone(3, null);
            }
        } else if (id == R.id.btn_clear) {
            doubleGridAdapter2.clearSelectedState();
        }
    }


    public FilterMoreGridView setOnFilterDoneListener(OnFilterDoneListener listener) {
        mOnFilterDoneListener = listener;
        return this;
    }

}
