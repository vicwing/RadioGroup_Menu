package com.sunfusheng.StickyHeaderListView.ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.apkfuns.logutils.LogLevel;
import com.apkfuns.logutils.LogUtils;
import com.sunfusheng.StickyHeaderListView.R;
import com.sunfusheng.StickyHeaderListView.adapter.TravelingAdapter;
import com.sunfusheng.StickyHeaderListView.model.ChannelEntity;
import com.sunfusheng.StickyHeaderListView.model.CityItem;
import com.sunfusheng.StickyHeaderListView.model.FilterData;
import com.sunfusheng.StickyHeaderListView.model.FilterEntity;
import com.sunfusheng.StickyHeaderListView.model.FilterTwoEntity;
import com.sunfusheng.StickyHeaderListView.model.OperationEntity;
import com.sunfusheng.StickyHeaderListView.model.TravelingEntity;
import com.sunfusheng.StickyHeaderListView.util.ColorUtil;
import com.sunfusheng.StickyHeaderListView.util.DensityUtil;
import com.sunfusheng.StickyHeaderListView.util.ModelUtil;
import com.sunfusheng.StickyHeaderListView.view.FilterView;
import com.sunfusheng.StickyHeaderListView.view.HeaderAdViewView;
import com.sunfusheng.StickyHeaderListView.view.HeaderChannelViewView;
import com.sunfusheng.StickyHeaderListView.view.HeaderDividerViewView;
import com.sunfusheng.StickyHeaderListView.view.HeaderFilterViewView;
import com.sunfusheng.StickyHeaderListView.view.HeaderHorzontalListBannerView;
import com.sunfusheng.StickyHeaderListView.view.HeaderOperationViewView;
import com.sunfusheng.StickyHeaderListView.view.SmoothListView.SmoothListView;
import com.sunfusheng.StickyHeaderListView.view.SmoothListView.SmoothScrollListener;

import java.util.ArrayList;
import java.util.List;




/**
 * 作者：孙福生
 * <p>
 * 个人博客：sunfusheng.com
 */
public class MainActivity extends BasePtrPullToResfrshActivity implements SmoothListView.ISmoothListViewListener {

    SmoothListView smoothListView;
    FilterView fvTopFilter;
    RelativeLayout rlBar;
    TextView tvTitle;
    View viewTitleBg;
    View viewActionMoreBg;
    FrameLayout flActionMore;

    private Context mContext;
    private Activity mActivity;
    private int mScreenHeight; // 屏幕高度

    private List<String> adList = new ArrayList<>(); // 广告数据
    private List<ChannelEntity> channelList = new ArrayList<>(); // 频道数据
    private List<OperationEntity> operationList = new ArrayList<>(); // 运营数据
    private List<CityItem> horizontalList = new ArrayList<>(); // 运营数据
    private List<TravelingEntity> travelingList = new ArrayList<>(); // ListView数据

    private HeaderAdViewView listViewAdHeaderView; // 广告视图
    private HeaderChannelViewView headerChannelView; // 频道视图
    private HeaderOperationViewView headerOperationViewView; // 运营视图
    private HeaderHorzontalListBannerView headerHorzontalListBannerView; // 设置横向滑动banner
    private HeaderDividerViewView headerDividerViewView; // 分割线占位图
    private HeaderFilterViewView headerFilterViewView; // 分类筛选视图
    private FilterData filterData; // 筛选数据
    private TravelingAdapter mAdapter; // 主页数据

    private View itemHeaderAdView; // 从ListView获取的广告子View
    private View itemHeaderFilterView; // 从ListView获取的筛选子View
    private boolean isScrollIdle = true; // ListView是否在滑动
    private boolean isStickyTop = false; // 是否吸附在顶部
    private boolean isSmooth = false; // 没有吸附的前提下，是否在滑动
    private int titleViewHeight = 50; // 标题栏的高度
    private int filterPosition = -1; // 点击FilterView的位置：分类(0)、排序(1)、筛选(2)

    private int adViewHeight = 180; // 广告视图的高度
    private int adViewTopSpace; // 广告视图距离顶部的距离

    private int filterViewPosition = 4; // 筛选视图的位置
    private int filterViewTopSpace; // 筛选视图距离顶部的距离

    private int titleViewHeightPx;

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
        }
    };
    private SmoothScrollListener smoothScrollListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_stickylistview_main);

        tvTitle = (TextView) findViewById(R.id.tv_title);
        viewTitleBg = findViewById(R.id.view_title_bg);
        flActionMore = (FrameLayout) findViewById(R.id.fl_action_more);
        fvTopFilter = (FilterView) findViewById(R.id.fv_top_filter);
        rlBar = (RelativeLayout) findViewById(R.id.rl_bar);
        smoothListView = (SmoothListView) findViewById(R.id.listView);
        viewActionMoreBg = findViewById(R.id.view_action_more_bg);

        LogUtils.getLogConfig()
                .configAllowLog(true)
                .configTagPrefix("StickyHeadListview")
                .configShowBorders(false)
                .configLevel(LogLevel.TYPE_VERBOSE);
        initData();
        initView();
//        initPtrFrame();
        initListener();
    }

    @Override
    protected int getlayoutId() {
        return R.layout.activity_stickylistview_main;
    }

    @Override
    protected View getListView() {
        return smoothListView;
    }

    private void initData() {
        mContext = this;
        mActivity = this;
        mScreenHeight = DensityUtil.getWindowHeight(this);

        // 筛选数据
        filterData = new FilterData();
        filterData.setCategory(ModelUtil.getCategoryData());
        filterData.setSorts(ModelUtil.getSortData());
        filterData.setFilters(ModelUtil.getFilterData());
        filterData.setMores(ModelUtil.getSortData());

        // 广告数据
        adList = ModelUtil.getAdData();

        // 频道数据
        channelList = ModelUtil.getChannelData();

        // 运营数据
        operationList = ModelUtil.getOperationData();


        // 横向banner
        horizontalList = ModelUtil.getHorizontalData();

        // ListView数据
        travelingList = ModelUtil.getTravelingData();
    }

    private void initView() {
        fvTopFilter.setVisibility(View.INVISIBLE);

        // 设置筛选数据
        fvTopFilter.setFilterData(mActivity, filterData);

        // 设置广告数据
        listViewAdHeaderView = new HeaderAdViewView(this);
        listViewAdHeaderView.fillView(adList, smoothListView);

        // 设置频道数据
        headerChannelView = new HeaderChannelViewView(this);
        headerChannelView.fillView(channelList, smoothListView);

        // 设置运营数据
        headerOperationViewView = new HeaderOperationViewView(this);
        headerOperationViewView.fillView(operationList, smoothListView);


        //设置横向滑动banner
        headerHorzontalListBannerView = new HeaderHorzontalListBannerView(this);
        headerHorzontalListBannerView.fillView(horizontalList, smoothListView);


        // 设置分割线
        headerDividerViewView = new HeaderDividerViewView(this);
        headerDividerViewView.fillView("", smoothListView);

        // 设置筛选数据
        headerFilterViewView = new HeaderFilterViewView(this);
        headerFilterViewView.fillView(new Object(), smoothListView);

        // 设置ListView数据
        mAdapter = new TravelingAdapter(this, travelingList);
        smoothListView.setAdapter(mAdapter);

        filterViewPosition = smoothListView.getHeaderViewsCount() - 1;

//


    }


    private void initListener() {
        // 关于
        flActionMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(mActivity, AboutActivity.class));
            }
        });

        // (假的ListView头部展示的)筛选视图点击
        headerFilterViewView.setOnFilterClickListener(new HeaderFilterViewView.OnFilterClickListener() {
            @Override
            public void onFilterClick(int position) {
                filterPosition = position;
                isSmooth = true;

                if (smoothScrollListener!=null){
                    smoothScrollListener.setSmooth(isSmooth);
                    smoothScrollListener.setFilterPosition(position);
                }

                LogUtils.d("假的筛选menu  " + "filterViewPosition  " + filterViewPosition + "  titleViewHeight  " + titleViewHeight);
                smoothListView.smoothScrollToPositionFromTop(filterViewPosition, DensityUtil.dip2px(mContext, 0));
            }
        });

        // (真正的)筛选视图点击
        fvTopFilter.setOnFilterClickListener(new FilterView.OnFilterClickListener() {
            @Override
            public void onFilterClick(int position) {
                if (isStickyTop) {
                    filterPosition = position;

                    if (smoothScrollListener!=null){
                        smoothScrollListener.setFilterPosition(position);
                    }
                    fvTopFilter.showFilterLayout(position);
                    if (titleViewHeight - 3 > filterViewTopSpace || filterViewTopSpace > titleViewHeight + 3) {
                        smoothListView.smoothScrollToPositionFromTop(filterViewPosition, DensityUtil.dip2px(mContext, titleViewHeight));
                    }
                }
            }
        });
        // 分类Item点击
        fvTopFilter.setOnItemCategoryClickListener(new FilterView.OnItemCategoryClickListener() {
            @Override
            public void onItemCategoryClick(FilterTwoEntity entity) {
                fillAdapter(ModelUtil.getCategoryTravelingData(entity));
            }
        });

        // 排序Item点击
        fvTopFilter.setOnItemSortClickListener(new FilterView.OnItemSortClickListener() {
            @Override
            public void onItemSortClick(FilterEntity entity) {
                fillAdapter(ModelUtil.getSortTravelingData(entity));
            }
        });

        // 筛选Item点击
        fvTopFilter.setOnItemFilterClickListener(new FilterView.OnItemFilterClickListener() {
            @Override
            public void onItemFilterClick(FilterEntity entity) {
                fillAdapter(ModelUtil.getFilterTravelingData(entity));
            }
        });

        // 更多Item点击
        fvTopFilter.setOnItemFilterClickListener(new FilterView.OnItemFilterClickListener() {
            @Override
            public void onItemFilterClick(FilterEntity entity) {
                fillAdapter(ModelUtil.getFilterTravelingData(entity));
            }
        });

        smoothListView.setRefreshEnable(false);
        smoothListView.setLoadMoreEnable(true);
        smoothListView.setSmoothListViewListener(this);

        smoothScrollListener = new SmoothScrollListener(this, smoothListView,fvTopFilter);
        smoothScrollListener.setOnDataChangeListener(new SmoothScrollListener.OnDataChangeListener() {
            @Override
            public void isSmooth(boolean isSmooth) {

            }

            @Override
            public void isSitcky(boolean isSicky) {
                isStickyTop = isSicky;
            }

            @Override
            public void filterViewTopSpace(int topspace) {
                filterViewTopSpace = topspace;
            }
        });
        smoothListView.setOnScrollListener(smoothScrollListener);
//        smoothListView.setOnScrollListener(new SmoothListView.OnSmoothScrollListener() {
//            @Override
//            public void onSmoothScrolling(View view) {
//            }
//
//            @Override
//            public void onScrollStateChanged(AbsListView view, int scrollState) {
//                isScrollIdle = (scrollState == AbsListView.OnScrollListener.SCROLL_STATE_IDLE);
//            }
//
//            @Override
//            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
//                if (isScrollIdle && adViewTopSpace < 0) return;
//
//                // 获取广告头部View、自身的高度、距离顶部的高度
//                if (itemHeaderAdView == null) {
//                    itemHeaderAdView = smoothListView.getChildAt(1 - firstVisibleItem);
//                }
//                if (itemHeaderAdView != null) {
//                    adViewTopSpace = DensityUtil.px2dip(mContext, itemHeaderAdView.getTop());
//                    adViewHeight = DensityUtil.px2dip(mContext, itemHeaderAdView.getHeight());
//                }
//
//                // 获取筛选View、距离顶部的高度
//                if (itemHeaderFilterView == null) {
//                    itemHeaderFilterView = smoothListView.getChildAt(filterViewPosition - firstVisibleItem);
//                }
//                if (itemHeaderFilterView != null) {
//                    filterViewTopSpace = DensityUtil.px2dip(mContext, itemHeaderFilterView.getTop() + titleViewHeightPx);
//                }
//
////                LogUtils.d("filterViewTopSpace "+filterViewTopSpace +"  titleViewHeight   "+ titleViewHeight);
//                // 处理筛选是否吸附在顶部
//                if (filterViewTopSpace > titleViewHeight) {
//                    isStickyTop = false; // 没有吸附在顶部
//                    fvTopFilter.setVisibility(View.INVISIBLE);
//                } else {
//                    isStickyTop = true; // 吸附在顶部
//                    fvTopFilter.setVisibility(View.VISIBLE);
//                }
//
//                if (firstVisibleItem > filterViewPosition) {
//                    isStickyTop = true;
//                    fvTopFilter.setVisibility(View.VISIBLE);
//                }
//
//                if (isSmooth && isStickyTop) {
//                    isSmooth = false;
//                    fvTopFilter.showFilterLayout(filterPosition);
//                }
//
//                fvTopFilter.setStickyTop(isStickyTop);
//
//                // 处理标题栏颜色渐变
////                handleTitleBarColorEvaluate();
//            }
//        });
    }

    // 填充数据
    private void fillAdapter(List<TravelingEntity> list) {
        if (list == null || list.size() == 0) {
            smoothListView.setLoadMoreEnable(false);
            int height = mScreenHeight - DensityUtil.dip2px(mContext, 95); // 95 = 标题栏高度 ＋ FilterView的高度
            mAdapter.setData(ModelUtil.getNoDataEntity(height));
        } else {
            smoothListView.setLoadMoreEnable(list.size() > TravelingAdapter.ONE_REQUEST_COUNT);
            mAdapter.setData(list);
        }
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (listViewAdHeaderView != null) {
            listViewAdHeaderView.stopADRotate();
        }
    }

    @Override
    public void onBackPressed() {
        if (!fvTopFilter.isShowing()) {
            super.onBackPressed();
        } else {
            fvTopFilter.resetAllStatus();
        }
    }

    @Override
    public void onRefresh() {
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                smoothListView.stopRefresh();
                smoothListView.setRefreshTime("刚刚");
            }
        }, 2000);
    }

    @Override
    public void onLoadMore() {
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                smoothListView.stopLoadMore();
            }
        }, 2000);
    }



    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);

        titleViewHeightPx = rlBar.getHeight();
        titleViewHeight = DensityUtil.px2dip(this, rlBar.getHeight());
        if (smoothScrollListener!=null){
            smoothScrollListener.setTitleViewHeightPx(titleViewHeightPx);
            smoothScrollListener.setTitleViewHeight(titleViewHeight);
        }
        qfangframelayout.cancelAll();
    }

}
