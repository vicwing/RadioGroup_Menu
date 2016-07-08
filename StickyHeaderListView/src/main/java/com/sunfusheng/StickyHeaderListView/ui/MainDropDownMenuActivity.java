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
import android.widget.Toast;

import com.apkfuns.logutils.LogUtils;
import com.baiiu.filter.interfaces.OnFilterDoneListener;
import com.google.gson.Gson;
import com.orhanobut.logger.Logger;
import com.sunfusheng.StickyHeaderListView.R;
import com.sunfusheng.StickyHeaderListView.adapter.TravelingAdapter;
import com.sunfusheng.StickyHeaderListView.model.ChannelEntity;
import com.sunfusheng.StickyHeaderListView.model.CityItem;
import com.sunfusheng.StickyHeaderListView.model.FilterAreaBean;
import com.sunfusheng.StickyHeaderListView.model.FilterBean;
import com.sunfusheng.StickyHeaderListView.model.FilterData;
import com.sunfusheng.StickyHeaderListView.model.NewHouseFilterBean;
import com.sunfusheng.StickyHeaderListView.model.NewhouseFilterMetroCallback;
import com.sunfusheng.StickyHeaderListView.model.OperationEntity;
import com.sunfusheng.StickyHeaderListView.model.TravelingEntity;
import com.sunfusheng.StickyHeaderListView.newDropDownMenu.DropMenuAdapter;
import com.sunfusheng.StickyHeaderListView.util.DensityUtil;
import com.sunfusheng.StickyHeaderListView.util.ModelUtil;
import com.sunfusheng.StickyHeaderListView.view.HeaderAdViewView;
import com.sunfusheng.StickyHeaderListView.view.HeaderChannelViewView;
import com.sunfusheng.StickyHeaderListView.view.HeaderDividerViewView;
import com.sunfusheng.StickyHeaderListView.view.HeaderFilterViewView;
import com.sunfusheng.StickyHeaderListView.view.HeaderHorzontalListBannerView;
import com.sunfusheng.StickyHeaderListView.view.HeaderOperationViewView;
import com.sunfusheng.StickyHeaderListView.view.SmoothListView.DropDownMenuSmoothScrollListener;
import com.sunfusheng.StickyHeaderListView.view.SmoothListView.SmoothListView;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.Callback;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Response;


/**
 *
 */
public class MainDropDownMenuActivity extends BasePtrPullToResfrshActivity implements OnFilterDoneListener<FilterBean>, SmoothListView.ISmoothListViewListener, View.OnClickListener {
    public static final String houseArea = "面积";
    public static final String houseLabel = "标签";
    public static final String houseAge = "房龄";
    public static final String houseDecorate = "装修";

    public static final String houseProperty = "类型";
    public static final String houseFeature = "标签";
    public static final String houseSalestatus = "销售状态";


    SmoothListView smoothListView;
    com.baiiu.filter.DropDownMenu mDropDownMenu;
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

    private boolean isStickyTop = false; // 是否吸附在顶部
    private boolean isSmooth = false; // 没有吸附的前提下，是否在滑动
    private int titleViewHeight = 50; // 标题栏的高度
    private int filterPosition = -1; // 点击FilterView的位置：分类(0)、排序(1)、筛选(2)


    private int filterViewPosition = 4; // 筛选视图的位置
    private int filterViewTopSpace; // 筛选视图距离顶部的距离
    private int titleViewHeightPx;

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
        }
    };
    private DropDownMenuSmoothScrollListener smoothScrollListener;
    private DropMenuAdapter dropMenuAdapter;

    private HashMap<String, List<FilterBean>> hashMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_stickylistview_main);

        tvTitle = (TextView) findViewById(R.id.tv_title);
        viewTitleBg = findViewById(R.id.view_title_bg);
        flActionMore = (FrameLayout) findViewById(R.id.fl_action_more);
        mDropDownMenu = (com.baiiu.filter.DropDownMenu) findViewById(R.id.dropDownMenu);
        rlBar = (RelativeLayout) findViewById(R.id.rl_bar);
        smoothListView = (SmoothListView) findViewById(R.id.listview);
        viewActionMoreBg = findViewById(R.id.view_action_more_bg);


        initData();
        initView();
        initListener();

        initFilterDropDownView();
        requestFilterData();
//        requestFilterAreaData();
//        requestFilterMetroData1();
    }

    @Override
    protected int getlayoutId() {
        return R.layout.activity_stickylistview_dropdown_main;
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
        mDropDownMenu.setVisibility(View.INVISIBLE);

        // 设置筛选数据
//        mDropDownMenu.setFilterData(mActivity, filterData);

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
                if (smoothScrollListener != null) {
                    smoothScrollListener.setSmooth(isSmooth);
                    smoothScrollListener.setFilterPosition(position);
                }
                LogUtils.d("假的筛选menu  " + "filterViewPosition  " + filterViewPosition + "  titleViewHeight  " + titleViewHeight);
                smoothListView.smoothScrollToPositionFromTop(filterViewPosition, DensityUtil.dip2px(mContext, 0), 50);
            }
        });

        smoothListView.setRefreshEnable(false);
        smoothListView.setLoadMoreEnable(true);
        smoothListView.setSmoothListViewListener(this);

        smoothScrollListener = new DropDownMenuSmoothScrollListener(this, smoothListView, mDropDownMenu);
        smoothScrollListener.setOnDataChangeListener(new DropDownMenuSmoothScrollListener.OnDataChangeListener() {
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


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (listViewAdHeaderView != null) {
            listViewAdHeaderView.stopADRotate();
        }
    }

    @Override
    public void onBackPressed() {
        //退出activity前关闭菜单
        if (mDropDownMenu.isShowing()) {
            mDropDownMenu.close();
        } else {
            super.onBackPressed();
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
        if (smoothScrollListener != null) {
            smoothScrollListener.setTitleViewHeightPx(titleViewHeightPx);
            smoothScrollListener.setTitleViewHeight(titleViewHeight);
        }
        qfangframelayout.cancelAll();
    }

    String[] titleList = new String[]{"区域", "价格", "标签", "更多"};

    private void initFilterDropDownView() {
        dropMenuAdapter = new DropMenuAdapter(this, titleList, this, hashMap);
//        dropMenuAdapter = new DropMenuAdapter(this, titleList, this, hashMap);
//        mDropDownMenu.setMenuAdapter(dropMenuAdapter);
//        dropDownMenu.setContentView(tv_conttentview);
    }

    @Override
    public void onFilterDone(int position, String title, String urlValue) {
        mDropDownMenu.close();
//        if (position != 3) {
        mDropDownMenu.setPositionIndicatorText(position, title);
//        }
        LogUtils.d("筛选菜单...position.  " + position + "  title " + title + "   urlValue " + urlValue);
    }


    @Override
    public void onFilterDone(int positionTitle, Map<String, List<FilterBean>> selectedMap) {
        LogUtils.d("positionTitle  " + positionTitle + " selectedMap " + selectedMap.size());
        for (Map.Entry<String, List<FilterBean>> entry : selectedMap.entrySet()) {
//            LogUtils.d("Key = " + entry.getKey() + ", Value = " + entry.getValue().size());
            for (int i = 0; i < entry.getValue().size(); i++) {
                FilterBean filterBean = entry.getValue().get(i);
                LogUtils.d("Key = " + entry.getKey() + "   desc  " + filterBean.getDesc() + "   value" + filterBean.getValue());
            }
        }
        mDropDownMenu.close();
    }

    /**
     * 获取 更多菜单的 请求参数
     *
     * @param selectedItems
     */
    private void getFilterParam(List<Integer> selectedItems) {
        LogUtils.d("getSelectedItems   " + selectedItems);
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
//                List<FilterBean> label = this.label;
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
//        return
    }

    @Override
    public void onClick(View v) {
//        LogUtils.d(MainDropDownMenuActivity.class.getName()+"多选点击 ...........................");
    }


    private String filter_more_url = "http://10.251.93.254:8010/appapi/v4_4/enums/filters/room?bizType=SALE&dataSource=SHENZHEN";

    private String newhouse_filter_more_url = "http://172.16.72.153:9393/qfang-api/appapi/v4_5/enums/filters/new?dataSource=SHENZHEN";
    private String newhouse_filter_city_area_url = "http://172.16.72.153:9393/qfang-api/appapi/v4_5/area?dataSource=shenzhen";//区域
//    private String newhouse_filter_city_area_url = "http://10.251.93.254:8010/appapi/v4_5/area?dataSource=shenzhen";//区域
    private String newhouse_filter_metro_url = "http://172.16.72.153:9393/qfang-api/appapi/v4_5/enums/subwaynums?dataSource=SHENZHEN";//地铁


    private void requestFilterData() {
        LogUtils.d("请求筛选菜单的列表");
        OkHttpUtils
                .get()//
                .url(newhouse_filter_more_url)//
//                .addParams("currentPage",currentPageStr)
                .build()//
                .execute(new Callback() {
                    @Override
                    public Object parseNetworkResponse(Response response, int id) throws Exception {
                        String string = response.body().string();
                        NewHouseFilterBean bean = new Gson().fromJson(string, NewHouseFilterBean.class);
                        return bean;
                    }

                    @Override
                    public void onError(Call call, Exception e, int id) {
                    }

                    @Override
                    public void onResponse(Object response1, int id) {
                        LogUtils.d("返回结果");
                        NewHouseFilterBean response = (NewHouseFilterBean) response1;
                        String status = response.getStatus();
                        if (status.equals("C0000")) {
                            NewHouseFilterBean.ResultBean result = response.getResult();
                            List<FilterBean> property = result.getProperty();
                            List<FilterBean> features = result.getFeatures();
                            List<FilterBean> saleStatus = result.getSaleStatus();
                            List<FilterBean> price = result.getPrice();

                            hashMap = new HashMap<>();
                            hashMap.put(houseProperty, property);
//                            hashMap.put(houseFeature, features);
                            hashMap.put(houseSalestatus, saleStatus);

                            dropMenuAdapter.setMoreData(hashMap);
                            dropMenuAdapter.setFeatureData(features);
                            dropMenuAdapter.setPriceData(price);
//                            mDropDownMenu.setMenuAdapter(dropMenuAdapter);
                        } else {//返回错误message
                            Toast.makeText(MainDropDownMenuActivity.this, "message  " + response.getMessage() + "\n statsus  " + response.getStatus(), Toast.LENGTH_SHORT);
                        }
                        requestFilterAreaData();
                    }
                });
    }


    /**
     * 请求筛选:区域
     */
    private void requestFilterAreaData() {
        OkHttpUtils
                .get()//
                .url(newhouse_filter_city_area_url)//
                .build()//
                .execute(new Callback() {
                    @Override
                    public Object parseNetworkResponse(Response response, int id) throws Exception {
                        String string = response.body().string();
                        FilterAreaBean bean = new Gson().fromJson(string, FilterAreaBean.class);
                        Logger.d("区域请求  bean   " + bean.getMessage());
                        return bean;
                    }

                    @Override
                    public void onError(Call call, Exception e, int id) {
                    }
                    @Override
                    public void onResponse(Object response, int id) {
                        Logger.d("区域 " + response.toString());
                        FilterAreaBean filterAreaBean = (FilterAreaBean) response;
                        String status = filterAreaBean.getStatus();
                        if (status.equals("C0000")) {
                            List<FilterAreaBean.ResultBean> resul = filterAreaBean.getResult();
                            dropMenuAdapter.setAreaData(resul);
//                            mDropDownMenu.setMenuAdapter(dropMenuAdapter);
                        } else {

                        }
                    requestFilterMetroData();
                    }
                });
    }



    /**
     * 请求筛选:地铁
     */
    private void requestFilterMetroData() {
        OkHttpUtils
                .get()//
                .url(newhouse_filter_metro_url)//
                .build()
                .execute(new NewhouseFilterMetroCallback(){

                    @Override
                    public void onResponse(FilterAreaBean response, int id) {
                        super.onResponse(response, id);
                        Logger.d("成功...............................");
                        FilterAreaBean filterAreaBean = response;
                        String status = filterAreaBean.getStatus();
                        if (status.equals("C0000")) {
                            List<FilterAreaBean.ResultBean> result = filterAreaBean.getResult();
                            dropMenuAdapter.setSubStationData(result);
                            mDropDownMenu.setMenuAdapter(dropMenuAdapter);
                        } else {

                        }
                    }
                });
    }
}
