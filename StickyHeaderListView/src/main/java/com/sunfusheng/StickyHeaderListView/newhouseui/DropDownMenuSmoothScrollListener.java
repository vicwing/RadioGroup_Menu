package com.sunfusheng.StickyHeaderListView.newhouseui;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.AbsListView;
import android.widget.RelativeLayout;

import com.baiiu.filter.DropDownMenu;
import com.sunfusheng.StickyHeaderListView.R;
import com.sunfusheng.StickyHeaderListView.util.ColorUtil;
import com.sunfusheng.StickyHeaderListView.util.DensityUtil;
import com.sunfusheng.StickyHeaderListView.view.SmoothListView.SmoothListView;

/**
 * 新房首页:listview 滚动监听
 * 监听筛选视图的位置.实现滚动显示筛选视图
 * Created by vic on 2016/6/29.
 */
public class DropDownMenuSmoothScrollListener implements SmoothListView.OnSmoothScrollListener {


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
    private Activity mContext;
    private SmoothListView smoothListView;
    DropDownMenu dropDownMenu;
    RelativeLayout rlBar;
    View viewActionMoreBg;
    View viewTitleBg;

    private OnDataChangeListener onDataChangeListener;

    public DropDownMenuSmoothScrollListener(Context mainActivity, SmoothListView smoothListView, OnDataChangeListener onDataChangeListener) {
        this.mContext = (Activity) mainActivity;
        this.smoothListView = smoothListView;
        this.onDataChangeListener = onDataChangeListener;
        init(smoothListView);
    }

    public void setTitleViewHeightPx(int titleViewHeightPx) {
        this.titleViewHeightPx = titleViewHeightPx;
    }

    public DropDownMenuSmoothScrollListener(Activity mainActivity, SmoothListView smoothListView) {
        this.mContext = mainActivity;
        this.smoothListView = smoothListView;
        init(smoothListView);
    }

    public DropDownMenuSmoothScrollListener(Activity mainActivity, SmoothListView smoothListView, DropDownMenu dropDownMenu) {
        this.mContext = mainActivity;
        this.smoothListView = smoothListView;
        this.dropDownMenu = dropDownMenu;
        init(smoothListView);
    }

    private void init(SmoothListView smoothListView) {
        filterViewPosition = smoothListView.getHeaderViewsCount() - 1;
        rlBar = (RelativeLayout) mContext.findViewById(R.id.rl_bar);
        viewActionMoreBg = mContext.findViewById(R.id.view_action_more_bg);
        viewTitleBg = mContext.findViewById(R.id.view_title_bg);
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
            filterViewTopSpace = DensityUtil.px2dip(mContext, itemHeaderFilterView.getTop());
            if (onDataChangeListener != null) {
                onDataChangeListener.filterViewTopSpace(filterViewTopSpace);
            }
//            Logger.d("filterViewTopSpace " + filterViewTopSpace + "  titleViewHeight   "
//                    + titleViewHeight+"   itemHeaderFilterView.getTop() "+ itemHeaderFilterView.getTop());
        }

        // 处理筛选是否吸附在顶部
        if (filterViewTopSpace > 0) {
            isStickyTop = false; // 没有吸附在顶部
            onSickyChange();
            dropDownMenu.setVisibility(View.INVISIBLE);
        } else {
            if (itemHeaderFilterView!=null){ //当itemHeaderFilterView 可见时才能吸附在顶部.不可见时filterViewTopSpace 无法计算
                isStickyTop = true; // 吸附在顶部
                onSickyChange();
                dropDownMenu.setVisibility(View.VISIBLE);
            }
        }

        if (firstVisibleItem > filterViewPosition) {
            isStickyTop = true;
            onSickyChange();
            dropDownMenu.setVisibility(View.VISIBLE);
        }

        if (isSmooth && isStickyTop) {
            isSmooth = false;
//            if (onDataChangeListener != null)
//                onDataChangeListener.isSmooth(false);
//            fvTopFilter.showFilterLayout(filterPosition);
        }

//        fvTopFilter.setStickyTop(isStickyTop);

        // 处理标题栏颜色渐变
        handleTitleBarColorEvaluate();
    }

    // 处理标题栏颜色渐变
    private void handleTitleBarColorEvaluate() {
        float fraction;
        if (adViewTopSpace > 0) {
            fraction = 1f - adViewTopSpace * 1f / 60;
            if (fraction < 0f) fraction = 0f;
            rlBar.setAlpha(fraction);
            return;
        }

        float space = Math.abs(adViewTopSpace) * 1f;
        fraction = space / (adViewHeight - titleViewHeight);
        if (fraction < 0f) fraction = 0f;
        if (fraction > 1f) fraction = 1f;
        rlBar.setAlpha(1f);

        if (fraction >= 1f || isStickyTop) {
            isStickyTop = true;
            viewTitleBg.setAlpha(0f);
            viewActionMoreBg.setAlpha(0f);
            rlBar.setBackgroundColor(mContext.getResources().getColor(R.color.orange));
        } else {
            viewTitleBg.setAlpha(1f - fraction);
            viewActionMoreBg.setAlpha(1f - fraction);
            rlBar.setBackgroundColor(ColorUtil.getNewColorByStartEndColor(mContext, fraction, R.color.transparent, R.color.orange));
        }
    }

    private void onSickyChange() {
        if (onDataChangeListener != null)
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



    public void setOnDataChangeListener(OnDataChangeListener listener) {
        this.onDataChangeListener = listener;
    }

    public interface OnDataChangeListener {

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
