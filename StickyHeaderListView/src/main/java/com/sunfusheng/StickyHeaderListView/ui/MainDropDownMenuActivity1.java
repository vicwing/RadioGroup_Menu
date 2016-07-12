//package com.sunfusheng.StickyHeaderListView.ui;
//
//import android.app.Activity;
//import android.content.Context;
//import android.content.Intent;
//import android.os.Bundle;
//import android.os.Handler;
//import android.os.Message;
//import android.view.View;
//import android.widget.FrameLayout;
//import android.widget.RelativeLayout;
//import android.widget.TextView;
//
//import com.apkfuns.logutils.LogUtils;
//import com.baiiu.filter.interfaces.OnFilterDoneListener;
//import com.sunfusheng.StickyHeaderListView.R;
//import com.sunfusheng.StickyHeaderListView.adapter.TravelingAdapter;
//import com.sunfusheng.StickyHeaderListView.model.ChannelEntity;
//import com.sunfusheng.StickyHeaderListView.model.CityItem;
//import com.sunfusheng.StickyHeaderListView.model.FilterBean;
//import com.sunfusheng.StickyHeaderListView.model.FilterData;
//import com.sunfusheng.StickyHeaderListView.model.OperationEntity;
//import com.sunfusheng.StickyHeaderListView.model.SecondHandFilterListCallback;
//import com.sunfusheng.StickyHeaderListView.model.TravelingEntity;
//import com.sunfusheng.StickyHeaderListView.newDropDownMenu.DropMenuAdapter;
//import com.sunfusheng.StickyHeaderListView.util.DensityUtil;
//import com.sunfusheng.StickyHeaderListView.util.ModelUtil;
//import com.sunfusheng.StickyHeaderListView.view.HeaderAdvTopBannerView;
//import com.sunfusheng.StickyHeaderListView.view.HeaderHotGroupBuyView;
//import com.sunfusheng.StickyHeaderListView.view.HeaderDividerViewView;
//import com.sunfusheng.StickyHeaderListView.view.HeaderFilterViewView;
//import com.sunfusheng.StickyHeaderListView.view.HeaderHorzontalListBannerView;
//import com.sunfusheng.StickyHeaderListView.view.HeaderOperationViewView;
//import com.sunfusheng.StickyHeaderListView.view.SmoothListView.DropDownMenuSmoothScrollListener;
//import com.sunfusheng.StickyHeaderListView.view.SmoothListView.SmoothListView;
//import com.zhy.http.okhttp.OkHttpUtils;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//
///**
// *
// */
//public class MainDropDownMenuActivity1 extends BasePtrPullToResfrshActivity implements OnFilterDoneListener<FilterBean>, SmoothListView.ISmoothListViewListener, View.OnClickListener {
//    public static final String houseArea = "面积";
//    public static final String houseLabel = "标签";
//    public static final String houseAge = "房龄";
//    public static final String houseDecorate = "装修";
//
//
//    SmoothListView smoothListView;
//    com.baiiu.filter.DropDownMenu mDropDownMenu;
//    RelativeLayout rlBar;
//    TextView tvTitle;
//    View viewTitleBg;
//    View viewActionMoreBg;
//    FrameLayout flActionMore;
//
//    private Context mContext;
//    private Activity mActivity;
//    private int mScreenHeight; // 屏幕高度
//
//    private List<String> adList = new ArrayList<>(); // 广告数据
//    private List<ChannelEntity> channelList = new ArrayList<>(); // 频道数据
//    private List<OperationEntity> operationList = new ArrayList<>(); // 运营数据
//    private List<CityItem> horizontalList = new ArrayList<>(); // 运营数据
//    private List<TravelingEntity> travelingList = new ArrayList<>(); // ListView数据
//
//    private HeaderAdvTopBannerView listViewAdHeaderView; // 广告视图
//    private HeaderHotGroupBuyView headerChannelView; // 频道视图
//    private HeaderOperationViewView headerOperationViewView; // 运营视图
//    private HeaderHorzontalListBannerView headerHorzontalListBannerView; // 设置横向滑动banner
//    private HeaderDividerViewView headerDividerViewView; // 分割线占位图
//    private HeaderFilterViewView headerFilterViewView; // 分类筛选视图
//    private FilterData filterData; // 筛选数据
//    private TravelingAdapter mAdapter; // 主页数据
//
//    private boolean isStickyTop = false; // 是否吸附在顶部
//    private boolean isSmooth = false; // 没有吸附的前提下，是否在滑动
//    private int titleViewHeight = 50; // 标题栏的高度
//    private int filterPosition = -1; // 点击FilterView的位置：分类(0)、排序(1)、筛选(2)
//
//
//    private int filterViewPosition = 4; // 筛选视图的位置
//    private int filterViewTopSpace; // 筛选视图距离顶部的距离
//    private int titleViewHeightPx;
//
//    private Handler mHandler = new Handler() {
//        @Override
//        public void handleMessage(Message msg) {
//            super.handleMessage(msg);
//        }
//    };
//    private DropDownMenuSmoothScrollListener smoothScrollListener;
//    private DropMenuAdapter dropMenuAdapter;
//
//    private HashMap<String, List<FilterBean>> hashMap;
//    private List<FilterBean> label;
//    private List<FilterBean> age;
//    private List<FilterBean> decoration;
//    private List<FilterBean> area;
//    private int firstSection;
//    private int secondSection;
//    private int thirdSection;
//
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
////        setContentView(R.layout.activity_stickylistview_main);
//
//        tvTitle = (TextView) findViewById(R.id.tv_title);
//        viewTitleBg = findViewById(R.id.view_title_bg);
//        flActionMore = (FrameLayout) findViewById(R.id.fl_action_more);
//        mDropDownMenu = (com.baiiu.filter.DropDownMenu) findViewById(R.id.dropDownMenu);
//        rlBar = (RelativeLayout) findViewById(R.id.rl_bar);
//        smoothListView = (SmoothListView) findViewById(R.id.listview);
//        viewActionMoreBg = findViewById(R.id.view_action_more_bg);
//
//
//        initData();
//        initView();
//        initListener();
////        initFilterDropDownView();
//        requestFilterData();
//    }
//
//    @Override
//    protected int getlayoutId() {
//        return R.layout.activity_stickylistview_dropdown_main;
//    }
//
//    @Override
//    protected View getListView() {
//        return smoothListView;
//    }
//
//    private void initData() {
//        mContext = this;
//        mActivity = this;
//        mScreenHeight = DensityUtil.getWindowHeight(this);
//
//        // 筛选数据
//        filterData = new FilterData();
//        filterData.setCategory(ModelUtil.getCategoryData());
//        filterData.setSorts(ModelUtil.getSortData());
//        filterData.setFilters(ModelUtil.getFilterData());
//        filterData.setMores(ModelUtil.getSortData());
//
//        // 广告数据
//        adList = ModelUtil.getAdData();
//
//        // 频道数据
//        channelList = ModelUtil.getChannelData();
//
//        // 运营数据
//        operationList = ModelUtil.getOperationData();
//
//        // 横向banner
//        horizontalList = ModelUtil.getHorizontalData();
//
//        // ListView数据
//        travelingList = ModelUtil.getTravelingData();
//    }
//
//    private void initView() {
//        mDropDownMenu.setVisibility(View.INVISIBLE);
//
//        // 设置筛选数据
////        mDropDownMenu.setFilterData(mActivity, filterData);
//
//        // 设置广告数据
//        listViewAdHeaderView = new HeaderAdvTopBannerView(this);
//        listViewAdHeaderView.fillView(adList, smoothListView);
//
//        // 设置频道数据
//        headerChannelView = new HeaderHotGroupBuyView(this);
//        headerChannelView.fillView(channelList, smoothListView);
//
//        // 设置运营数据
//        headerOperationViewView = new HeaderOperationViewView(this);
//        headerOperationViewView.fillView(operationList, smoothListView);
//
//
//        //设置横向滑动banner
//        headerHorzontalListBannerView = new HeaderHorzontalListBannerView(this);
//        headerHorzontalListBannerView.fillView(horizontalList, smoothListView);
//
//
//        // 设置分割线
//        headerDividerViewView = new HeaderDividerViewView(this);
//        headerDividerViewView.fillView("", smoothListView);
//
//        // 设置筛选数据
//        headerFilterViewView = new HeaderFilterViewView(this);
//        headerFilterViewView.fillView(new Object(), smoothListView);
//
//        // 设置ListView数据
//        mAdapter = new TravelingAdapter(this, travelingList);
//        smoothListView.setAdapter(mAdapter);
//
//        filterViewPosition = smoothListView.getHeaderViewsCount() - 1;
//
////
//
//
//    }
//
//    private void initListener() {
//        // 关于
//        flActionMore.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(mActivity, AboutActivity.class));
//            }
//        });
//
//        // (假的ListView头部展示的)筛选视图点击
//        headerFilterViewView.setOnFilterClickListener(new HeaderFilterViewView.OnFilterClickListener() {
//            @Override
//            public void onFilterClick(int position) {
//                filterPosition = position;
//                isSmooth = true;
//                if (smoothScrollListener != null) {
//                    smoothScrollListener.setSmooth(isSmooth);
//                    smoothScrollListener.setFilterPosition(position);
//                }
//                LogUtils.d("假的筛选menu  " + "filterViewPosition  " + filterViewPosition + "  titleViewHeight  " + titleViewHeight);
//                smoothListView.smoothScrollToPositionFromTop(filterViewPosition, DensityUtil.dip2px(mContext, 0), 50);
//            }
//        });
//
//        smoothListView.setRefreshEnable(false);
//        smoothListView.setLoadMoreEnable(true);
//        smoothListView.setSmoothListViewListener(this);
//
//        smoothScrollListener = new DropDownMenuSmoothScrollListener(this, smoothListView, mDropDownMenu);
//        smoothScrollListener.setOnDataChangeListener(new DropDownMenuSmoothScrollListener.OnDataChangeListener() {
//            @Override
//            public void isSitcky(boolean isSicky) {
//                isStickyTop = isSicky;
//            }
//
//            @Override
//            public void filterViewTopSpace(int topspace) {
//                filterViewTopSpace = topspace;
//            }
//        });
//        smoothListView.setOnScrollListener(smoothScrollListener);
//    }
//
//    // 填充数据
//    private void fillAdapter(List<TravelingEntity> list) {
//        if (list == null || list.size() == 0) {
//            smoothListView.setLoadMoreEnable(false);
//            int height = mScreenHeight - DensityUtil.dip2px(mContext, 95); // 95 = 标题栏高度 ＋ FilterView的高度
//            mAdapter.setData(ModelUtil.getNoDataEntity(height));
//        } else {
//            smoothListView.setLoadMoreEnable(list.size() > TravelingAdapter.ONE_REQUEST_COUNT);
//            mAdapter.setData(list);
//        }
//    }
//
//
//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        if (listViewAdHeaderView != null) {
//            listViewAdHeaderView.stopADRotate();
//        }
//    }
//
//    @Override
//    public void onBackPressed() {
//        //退出activity前关闭菜单
//        if (mDropDownMenu.isShowing()) {
//            mDropDownMenu.close();
//        } else {
//            super.onBackPressed();
//        }
//    }
//
//    @Override
//    public void onRefresh() {
//        mHandler.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                smoothListView.stopRefresh();
//                smoothListView.setRefreshTime("刚刚");
//            }
//        }, 2000);
//    }
//
//    @Override
//    public void onLoadMore() {
//        mHandler.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                smoothListView.stopLoadMore();
//            }
//        }, 2000);
//    }
//
//
//    @Override
//    public void onWindowFocusChanged(boolean hasFocus) {
//        super.onWindowFocusChanged(hasFocus);
//
//        titleViewHeightPx = rlBar.getHeight();
//        titleViewHeight = DensityUtil.px2dip(this, rlBar.getHeight());
//        if (smoothScrollListener != null) {
//            smoothScrollListener.setTitleViewHeightPx(titleViewHeightPx);
//            smoothScrollListener.setTitleViewHeight(titleViewHeight);
//        }
//        qfangframelayout.cancelAll();
//    }
//
//    private void initFilterDropDownView() {
//        String[] titleList = new String[]{"第一个", "第二个", "第三个", "更多"};
//        dropMenuAdapter = new DropMenuAdapter(this, titleList, this, hashMap);
//        mDropDownMenu.setMenuAdapter(dropMenuAdapter);
////        dropDownMenu.setContentView(tv_conttentview);
//    }
//
//    @Override
//    public void onFilterDone(int position, String title, String urlValue) {
//        mDropDownMenu.close();
////        if (position != 3) {
//        mDropDownMenu.setPositionIndicatorText(position, urlValue);
////        }
//        LogUtils.d("筛选菜单...position.  " + position + "  title " + title + "   urlValue " + urlValue);
//    }
//
//    @Override
//    public void onFilterAreaDone(int positionTitle, int leftPosition, String title, String value) {
//
//    }
//
//    @Override
//    public void onFilterMoreDone(int positionTitle, Map<String, List<FilterBean>> selectedItems) {
//
//    }
//
////    @Override
////    public void onFilterDone(int positionTitle, List<Integer> selectedItems) {
////
////        area = hashMap.get(houseAge);
////        label = hashMap.get(houseLabel);
////        age = hashMap.get(houseAge);
////        decoration = hashMap.get(houseDecorate);
////
////        firstSection = area.size() + 1;
////        secondSection = area.size() + label.size() + 2;
////        thirdSection = area.size() + label.size() + age.size() + 3;
////
////        getFilterParam(selectedItems);
////    }
//
//    /**
//     * 获取 更多菜单的 请求参数
//     *
//     * @param selectedItems
//     */
//    private void getFilterParam(List<Integer> selectedItems) {
//        LogUtils.d("getSelectedItems   " + selectedItems);
//
//        for (int i = 0; i < selectedItems.size(); i++) {
//            Integer position = selectedItems.get(i);
//            if (position < firstSection) { //
//                int location = position - 1;
//                FilterBean filterDescBean = area.get(location);
//                String desc = filterDescBean.getDesc();
//                String value = filterDescBean.getValue();
//                LogUtils.d("desc  " + desc + "   value   " + value);
//
//            } else if (position > firstSection && position < secondSection) {
//                int location = position - area.size() - 2;
//
//                FilterBean filterDescBean = label.get(location);
//                String desc = filterDescBean.getDesc();
//                String value = filterDescBean.getValue();
//                LogUtils.d("desc  " + desc + "   value   " + value);
//            } else if (position > secondSection && position < thirdSection) {
//                int location = position - area.size() - label.size() - 3;
//                FilterBean filterDescBean = age.get(location);
//                String desc = filterDescBean.getDesc();
//                String value = filterDescBean.getValue();
//                LogUtils.d("desc  " + desc + "   value   " + value);
//            } else if (position > thirdSection) {
//                int location = position - area.size() - label.size() - age.size() - 4;
//                FilterBean filterDescBean = decoration.get(location);
//                String desc = filterDescBean.getDesc();
//                String value = filterDescBean.getValue();
//                LogUtils.d("desc  " + desc + "   value   " + value);
//            }
//        }
////        return
//    }
//
//    @Override
//    public void onClick(View v) {
////        LogUtils.d(MainDropDownMenuActivity.class.getName()+"多选点击 ...........................");
//    }
//
//
//    private String filter_more_url = "http://10.251.93.254:8010/appapi/v4_4/enums/filters/room?bizType=SALE&dataSource=SHENZHEN";
//
//    private void requestFilterData() {
//        LogUtils.d("请求筛选菜单的列表");
//        OkHttpUtils
//                .post()//
//                .url(filter_more_url)//
////                .addParams("currentPage",currentPageStr)
//                .build()//
//                .execute(new SecondHandFilterListCallback());
////            .execute(new Callback() {
////                @Override
////                public Object parseNetworkResponse(Response response) throws Exception {
////                    String string = response.body().string();
////                    SecondHandFilterBean bean = new Gson().fromJson(string, SecondHandFilterBean.class);
////                    return bean;
////                }
////
////                @Override
////                public void onError(Call call, Exception e) {
////
////                }
////
////                @Override
////                public void onResponse(Object response) {
////
////                }
////            });
//    }
//}
