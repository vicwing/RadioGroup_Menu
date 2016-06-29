package com.sunfusheng.StickyHeaderListView.view.SmoothListView;

import android.view.View;
import android.widget.AbsListView;

import com.apkfuns.logutils.LogUtils;
import com.sunfusheng.StickyHeaderListView.ui.MainActivity;
import com.sunfusheng.StickyHeaderListView.util.DensityUtil;
import com.sunfusheng.StickyHeaderListView.view.FilterView;

/**
 * Created by vic on 2016/6/29.
 */
public class SmoothScrollListener implements SmoothListView.OnSmoothScrollListener {



    private boolean isSmooth = false; // 没有吸附的前提下，是否在滑动
    private int titleViewHeight = 50; // 标题栏的高度
    private int filterPosition = -1; // 点击FilterView的位置：分类(0)、排序(1)、筛选(2)
    private int adViewHeight = 180; // 广告视图的高度
    private int adViewTopSpace; // 广告视图距离顶部的距离
    private int filterViewPosition = 4; // 筛选视图的位置

    private int filterViewTopSpace; // 筛选视图距离顶部的距离

    private View itemHeaderAdView; // 从ListView获取的广告子View
    private View itemHeaderFilterView; // 从ListView获取的筛选子View



    private boolean isStickyTop = false; // 是否吸附在顶部
    private boolean isScrollIdle = true; // ListView是否在滑动
    private MainActivity mContext;
    private SmoothListView smoothListView;
    FilterView fvTopFilter;

    public void setTitleViewHeightPx(int titleViewHeightPx) {
        this.titleViewHeightPx = titleViewHeightPx;
    }

    public SmoothScrollListener(MainActivity mainActivity, SmoothListView smoothListView,FilterView fvTopFilter) {
        this.mContext = mainActivity;
        this.smoothListView = smoothListView;
        this.fvTopFilter = fvTopFilter;
        filterViewPosition = smoothListView.getHeaderViewsCount() - 1;
    }

    @Override
    public void onSmoothScrolling(View view) {

    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        isScrollIdle = (scrollState == AbsListView.OnScrollListener.SCROLL_STATE_IDLE);
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        if (isScrollIdle && adViewTopSpace < 0) return;

        // 获取广告头部View、自身的高度、距离顶部的高度
        if (itemHeaderAdView == null) {
            itemHeaderAdView = smoothListView.getChildAt(1 - firstVisibleItem);
        }
        if (itemHeaderAdView != null) {
            adViewTopSpace = DensityUtil.px2dip(mContext, itemHeaderAdView.getTop());
            adViewHeight = DensityUtil.px2dip(mContext, itemHeaderAdView.getHeight());
        }

        // 获取筛选View、距离顶部的高度
        if (itemHeaderFilterView == null) {
            itemHeaderFilterView = smoothListView.getChildAt(filterViewPosition - firstVisibleItem);
        }
        if (itemHeaderFilterView != null) {
            filterViewTopSpace = DensityUtil.px2dip(mContext, itemHeaderFilterView.getTop() + titleViewHeightPx);
            if (onDataChangeListener!=null){
                onDataChangeListener.filterViewTopSpace(filterViewTopSpace);
            }

        }

         LogUtils.d("filterViewTopSpace "+filterViewTopSpace +"  titleViewHeight   "+ titleViewHeight);
        // 处理筛选是否吸附在顶部
        if (filterViewTopSpace > titleViewHeight) {
            isStickyTop = false; // 没有吸附在顶部
            onSickyChange();
            fvTopFilter.setVisibility(View.INVISIBLE);
        } else {
            isStickyTop = true; // 吸附在顶部
            onSickyChange();
            fvTopFilter.setVisibility(View.VISIBLE);
        }

        if (firstVisibleItem > filterViewPosition) {
            isStickyTop = true;
            onSickyChange();
            fvTopFilter.setVisibility(View.VISIBLE);
        }

        if (isSmooth && isStickyTop) {
            isSmooth = false;
            if (onDataChangeListener!=null)
                onDataChangeListener.isSmooth(false);
            fvTopFilter.showFilterLayout(filterPosition);
        }

        fvTopFilter.setStickyTop(isStickyTop);

        // 处理标题栏颜色渐变
//                handleTitleBarColorEvaluate();
    }

    private void onSickyChange() {
        if (onDataChangeListener!=null)
            onDataChangeListener.isSitcky(isStickyTop);
    }

    private int titleViewHeightPx;

    public int getTitleViewHeightPx() {
        return titleViewHeightPx;
    }

    public boolean isSmooth() {
        return isSmooth;
    }

    public void setSmooth(boolean smooth) {
        isSmooth = smooth;
    }
    OnDataChangeListener onDataChangeListener;
    public void setOnDataChangeListener(OnDataChangeListener listener){
        this.onDataChangeListener = listener;
    }
    public interface OnDataChangeListener  {
        void isSmooth(boolean isSmooth );
        void isSitcky(boolean isSicky);
        void filterViewTopSpace(int filterViewTopSpace);
    }

    public int getFilterPosition() {
        return filterPosition;
    }

    public void setFilterPosition(int filterPosition) {
        this.filterPosition = filterPosition;
    }

    public boolean isStickyTop() {
        return isStickyTop;
    }

    public void setStickyTop(boolean stickyTop) {
        isStickyTop = stickyTop;
    }

    public int getTitleViewHeight() {
        return titleViewHeight;
    }

    public void setTitleViewHeight(int titleViewHeight) {
        this.titleViewHeight = titleViewHeight;
    }
}
