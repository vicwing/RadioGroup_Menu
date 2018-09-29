package com.example.cdj.myapplication.widget;

import android.content.Context;
import android.support.v4.widget.NestedScrollView;
import android.util.AttributeSet;

/**
 * Description : 添加滚动事件的 ScrollView  和 到达顶部的接口.用于首页
 * Created by vic
 * Created Time 2017/7/11
 */
public class CusNestedScrollView extends NestedScrollView {

    private OnScrollListener onScrollListener;

    public CusNestedScrollView(Context context) {
        super(context, null);
    }

    public CusNestedScrollView(Context context, AttributeSet attrs) {
        super(context, attrs, 0);
    }

    public CusNestedScrollView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    /**
     * 监听ScroView的滑动情况
     *
     * @param x    变化后的X轴位置
     * @param y    变化后的Y轴的位置
     * @param oldx 原先的X轴的位置
     * @param oldy 原先的Y轴的位置
     */
    @Override
    protected void onScrollChanged(int x, int y, int oldx, int oldy) {
        super.onScrollChanged(x, y, oldx, oldy);
        if (onScrollListener != null) {
            onScrollListener.onScrollChanged(y, oldy);
        }
    }

    /**
     * 设置滚动接口
     *
     * @param onScrollListener
     */
    public void setOnScrollListener(OnScrollListener onScrollListener) {
        this.onScrollListener = onScrollListener;
    }

    /**
     * 滚动的回调接口
     */
    public interface OnScrollListener {
        /**
         * 回调方法， 返回MyScrollView滑动的Y方向距离
         */
        void onScrollChanged(int y, int oldy);

    }
}
