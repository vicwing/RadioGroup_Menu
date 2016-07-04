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

import com.baiiu.filter.interfaces.OnFilterDoneListener;
import com.sunfusheng.StickyHeaderListView.R;
import com.sunfusheng.StickyHeaderListView.newDropDownMenu.FilterUrl;
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
public class BetterDoubleGridView extends LinearLayout implements View.OnClickListener
//        , ItemViewHolder.ClickListener
{

    @Bind(R.id.recyclerView)
    RecyclerView recyclerView;

    private List<String> mTopGridData;
    private List<String> mBottomGridList;
    private OnFilterDoneListener mOnFilterDoneListener;


    private ItemViewHolder.ClickListener mCallback;

    public BetterDoubleGridView(Context mContext, ItemViewHolder.ClickListener callback) {
        super(mContext, null);
        mCallback = callback;
        init(mContext);
    }

//    public BetterDoubleGridView(Context context) {
//        this(context, null);
//    }

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
        ButterKnife.bind(this, this);
    }


    public BetterDoubleGridView setmTopGridData(List<String> mTopGridData) {
        this.mTopGridData = mTopGridData;
        return this;
    }

    public BetterDoubleGridView setmBottomGridList(List<String> mBottomGridList) {
        this.mBottomGridList = mBottomGridList;
        return this;
    }

    public BetterDoubleGridView build() {

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
        recyclerView.setAdapter(new DoubleGridAdapter2(getContext(), mTopGridData, mBottomGridList, this, mCallback));
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


        if (mCallback != null) {
//            recyclerView.findContainingViewHolder().getAdapterPosition();
            int adapterPosition = recyclerView.findContainingViewHolder(v).getAdapterPosition();
//            LogUtils.d("mCallback   position "+adapterPosition);
            mCallback.onItemClicked(adapterPosition);
        }
    }


    public BetterDoubleGridView setOnFilterDoneListener(OnFilterDoneListener listener) {
        mOnFilterDoneListener = listener;
        return this;
    }

    @OnClick(R.id.bt_confirm)
    public void clickDone() {

        FilterUrl.instance().doubleGridTop = mTopSelectedTextView == null ? "" : (String) mTopSelectedTextView.getTag();
        FilterUrl.instance().doubleGridBottom = mBottomSelectedTextView == null ? "" : (String) mBottomSelectedTextView.getTag();

        if (mOnFilterDoneListener != null) {
            mOnFilterDoneListener.onFilterDone(3, FilterUrl.instance().doubleGridTop, FilterUrl.instance().doubleGridBottom);
        }
    }


//    @Override
//    public void onItemClicked(int position) {
//        LogUtils.d("22222222222222222222222222222222222222");
//    }

    public RecyclerView getRecyclerView(){
        return recyclerView;
    }
}
