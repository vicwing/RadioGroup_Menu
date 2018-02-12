package com.sunfusheng.StickyHeaderListView.newhouseui;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.apkfuns.logutils.LogUtils;
import com.baiiu.filter.DropDownMenu;
import com.baiiu.filter.interfaces.OnFilterDoneListener;
import com.google.gson.Gson;
import com.orhanobut.logger.Logger;
import com.sunfusheng.StickyHeaderListView.R;
import com.sunfusheng.StickyHeaderListView.adapter.NewhouseListAdapter;
import com.sunfusheng.StickyHeaderListView.adapter.TravelingAdapter;
import com.sunfusheng.StickyHeaderListView.base.quick.BaseAdapterHelper;
import com.sunfusheng.StickyHeaderListView.base.quick.QuickAdapter;
import com.sunfusheng.StickyHeaderListView.model.ChannelEntity;
import com.sunfusheng.StickyHeaderListView.model.CityItem;
import com.sunfusheng.StickyHeaderListView.model.FilterAreaBean;
import com.sunfusheng.StickyHeaderListView.model.FilterBean;
import com.sunfusheng.StickyHeaderListView.model.HotGroupBuyListBean;
import com.sunfusheng.StickyHeaderListView.model.NewHouseFilterBean;
import com.sunfusheng.StickyHeaderListView.model.NewHouseHomeAdvTopBanner;
import com.sunfusheng.StickyHeaderListView.model.NewHouseHomeBean;
import com.sunfusheng.StickyHeaderListView.model.NewHouseListItemBean;
import com.sunfusheng.StickyHeaderListView.model.NewhouseFilterMetroCallback;
import com.sunfusheng.StickyHeaderListView.model.OperationEntity;
import com.sunfusheng.StickyHeaderListView.model.TravelingEntity;
import com.sunfusheng.StickyHeaderListView.newDropDownMenu.DropMenuAdapter;
import com.sunfusheng.StickyHeaderListView.ui.AboutActivity;
import com.sunfusheng.StickyHeaderListView.ui.BasePtrPullToResfrshActivity;
import com.sunfusheng.StickyHeaderListView.ui.OrderDailog;
import com.sunfusheng.StickyHeaderListView.util.DensityUtil;
import com.sunfusheng.StickyHeaderListView.util.ModelUtil;
import com.sunfusheng.StickyHeaderListView.util.UrlUtils;
import com.sunfusheng.StickyHeaderListView.view.HeaderAdvTopBannerView;
import com.sunfusheng.StickyHeaderListView.view.HeaderDividerViewView;
import com.sunfusheng.StickyHeaderListView.view.HeaderGuideListView;
import com.sunfusheng.StickyHeaderListView.view.HeaderHorzontalListBannerView;
import com.sunfusheng.StickyHeaderListView.view.HeaderHotGroupBuyView;
import com.sunfusheng.StickyHeaderListView.view.HeaderHotNewHouseView;
import com.sunfusheng.StickyHeaderListView.view.HeaderNewFilterView;
import com.sunfusheng.StickyHeaderListView.view.SmoothListView.SmoothListView;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.Callback;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Response;


/**
 * 新房首页列表
 */
public class NewhouseHomeListActivity extends BasePtrPullToResfrshActivity implements OnFilterDoneListener<FilterBean>, SmoothListView.ISmoothListViewListener, View.OnClickListener {
    public static final String houseArea = "面积";

    public static final String houseLabel = "标签";

    public static final String houseAge = "房龄";

    public static final String houseDecorate = "装修";

    public static final String houseProperty = "类型";

    public static final String houseFeature = "标签";

    public static final String houseSalestatus = "销售状态";

    private boolean isRefresh = false;

    SmoothListView smoothListView;

    DropDownMenu mDropDownMenu;

    private DropMenuAdapter dropMenuAdapter;

    RelativeLayout rlBar;

    TextView tvTitle;

    TextView tv_orderby;

    View viewTitleBg;

    View viewActionMoreBg;

    FrameLayout flActionMore;

    private Context mContext;

    private Activity mActivity;

    private int mScreenHeight; // 屏幕高度

    private List<NewHouseHomeAdvTopBanner> advList = new ArrayList<>(); // 广告数据

    private List<ChannelEntity> groupbuyList = new ArrayList<>(); // 频道数据

    private List<OperationEntity> operationList = new ArrayList<>(); // 运营数据

    private List<CityItem> horizontalList = new ArrayList<>(); // 运营数据

    private List<TravelingEntity> travelingList = new ArrayList<>(); // ListView数据

    private HeaderAdvTopBannerView listViewAdHeaderView; // 广告视图

    private HeaderHotGroupBuyView headerGrouBuyView; // 热门团购视图

    private HeaderHotNewHouseView headerHotNewHouseList; // 热门新盘视图

    private HeaderGuideListView headerGuideListiew; // 导购咨询

    private HeaderHorzontalListBannerView headerHorzontalListBannerView; // 设置横向滑动banner

    private HeaderDividerViewView headerDividerViewView; // 分割线占位图

    private HeaderNewFilterView headerFakeFilterViewView; // 分类筛选视图

    private TravelingAdapter mAdapter; // 主页数据

    private boolean isStickyTop = false; // 是否吸附在顶部

    private boolean isSmooth = false; // 没有吸附的前提下，是否在滑动

    private int titleViewHeight = 50; // 标题栏的高度


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

    private HashMap<String, List<FilterBean>> hashMap;


    private String pageSize = "10";

    private int currentPage = 1;

    private String region;

    private String price;

    private String houseType;

    private String orderby;

    private String stationLine;

    private String station;

    private String latitude;

    private String longitude;

    private String nearDistance;

    private String feature;

    private String saleStatus;

    private String property;

    private String fromPrice;

    private String toPrice;

    private NewhouseListAdapter adapter;

    private String tag = NewhouseHomeListActivity.class.getSimpleName();

    private PopupWindow mPopupWindow;

    private List<FilterBean> orderByData;

    private View ll_root;

    private View orderBg;

    private ListView poplistView;

    private View orderTriangle;

    private String homepage_url = "http://172.16.72.153:9393/qfang-api/appapi/v4_5/newHouse/index?dataSource=SHENZHEN";

    private String newHouseListUrl = "http://172.16.72.153:9393/qfang-api/appapi/v4_3/newHouse/list";

    private String filter_more_url = "http://10.251.93.254:8010/appapi/v4_4/enums/filters/room?bizType=SALE&dataSource=SHENZHEN";

    private String newhouse_filter_more_url = "http://172.16.72.153:9393/qfang-api/appapi/v4_5/enums/filters/new?dataSource=SHENZHEN";

    private String newhouse_filter_city_area_url = "http://172.16.72.153:9393/qfang-api/appapi/v4_5/area?dataSource=shenzhen";//区域

    private String newhouse_filter_metro_url = "http://172.16.72.153:9393/qfang-api/appapi/v4_5/enums/subwaynums?dataSource=SHENZHEN";//地铁

    private int filterDataTag = 0;

    private OkHttpUtils okHttpUtilsCache;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_stickylistview_main);

        tvTitle = (TextView) findViewById(R.id.tv_title);
        tv_orderby = (TextView) findViewById(R.id.tv_orderby);
        viewTitleBg = findViewById(R.id.view_title_bg);
        flActionMore = (FrameLayout) findViewById(R.id.fl_action_more);
        mDropDownMenu = (DropDownMenu) findViewById(R.id.dropDownMenu);
        rlBar = (RelativeLayout) findViewById(R.id.rl_bar);
        smoothListView = (SmoothListView) findViewById(R.id.listview);
        viewActionMoreBg = findViewById(R.id.view_action_more_bg);


        initData();
        initView();
        initListener();


        initFilterDropDownView();

        requestFilterData();
        requestFilterAreaData();
        requestFilterMetroData();

        if (true) {
            requestNewHouseHomePage();
        } else {
            headerFakeFilterViewView.fillView(new Object(), smoothListView);
        }
        requestNewHouseList();

//        OkHttpClient okHttpClient = OkHttpUtils.getInstance().getOkHttpClient();
//
//        File cacheFile = new File(this.getCacheDir(), "[缓存目录]");
//        Cache cache = new Cache(cacheFile, 1024 * 1024 * 100); //100Mb
//        okHttpClient.newBuilder().cache(cache);
//        long maxSize = okHttpClient.cache().maxSize();
//        Logger.d("cache  "+maxSize);

        OkhttpCache();
    }

    @Override
    protected int getlayoutId() {
        return R.layout.activity_newhouse_homepage_stickylistview_dropdown;
    }

    private void OkhttpCache() {
//        OkHttpClient okHttpClient = new OkHttpClient();

        File cacheFile = new File(this.getFilesDir(), "OkhttpCacheFile.txt");
//        byte[]
        try {
            OutputStream outputStream = new FileOutputStream(cacheFile);

//            outputStream.write();


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

//        Cache cache = new Cache(cacheFile, 1024 * 1024 * 100); //100Mb
//        boolean mkdir = cacheFile.mkdir();
//        if ( cacheFile.mkdir())

//        okHttpClient.newBuilder().cache(cache);
//        long maxSize = okHttpClient.cache().maxSize();
//        Logger.d("cache  "+maxSize);
        Logger.d("cacheFile   " + cacheFile.getAbsolutePath());
        OkHttpClient okHttpClient = OkHttpUtils.getInstance().getOkHttpClient();
        OkHttpClient newClient = okHttpClient.newBuilder()
//                .cache(cache)
                .connectTimeout(20, TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS)
                .build();

//        OkHttpUtils.getInstance().getOkHttpClient()
        okHttpUtilsCache = new OkHttpUtils(newClient);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected View getListView() {
        return smoothListView;
    }

    private void initData() {
        mContext = this;
        mActivity = this;
        mScreenHeight = DensityUtil.getWindowHeight(this);

        // ListView数据
        travelingList = ModelUtil.getTravelingData();
    }

    private void initView() {

        mDropDownMenu.setVisibility(View.INVISIBLE);

        // 设置筛选数据
//        mDropDownMenu.setFilterData(mActivity, filterData);

        // 设置广告数据
        listViewAdHeaderView = new HeaderAdvTopBannerView(this);

        //设置横向滑动banner
        headerHorzontalListBannerView = new HeaderHorzontalListBannerView(this);

        // 设置热门团购
        headerGrouBuyView = new HeaderHotGroupBuyView(this);

        // 设置热门新盘
        headerHotNewHouseList = new HeaderHotNewHouseView(this);

        // 导购咨询
        headerGuideListiew = new HeaderGuideListView(this);

        // 设置分割线
        headerDividerViewView = new HeaderDividerViewView(this);


        // 设置筛选数据
        headerFakeFilterViewView = new HeaderNewFilterView(this);

//
//        // 设置ListView数据
//        mAdapter = new TravelingAdapter(this, travelingList);
//        smoothListView.setAdapter(mAdapter);
//

        adapter = new NewhouseListAdapter(NewhouseHomeListActivity.this, R.layout.item_newhouse_list);
        smoothListView.setAdapter(adapter);
//        filterViewPosition = smoothListView.getHeaderViewsCount() - 1;
    }

    private void initListener() {

        tv_orderby.setOnClickListener(this);
        // 关于
        flActionMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(mActivity, AboutActivity.class));
            }
        });

        // (假的ListView头部展示的)筛选视图点击
        headerFakeFilterViewView.setOnFilterClickListener(new HeaderNewFilterView.OnFilterClickListener() {
            @Override
            public void onFilterClick(int position) {
                isSmooth = true;
                if (smoothScrollListener != null) {
                    smoothScrollListener.setSmooth(isSmooth);
                    smoothScrollListener.setFilterPosition(position);
                }
                smoothListView.smoothScrollToPositionFromTop(filterViewPosition, DensityUtil.dip2px(mContext, 0), 50);
                LogUtils.d("假的筛选menu  " + "filterViewPosition  " + filterViewPosition + "  titleViewHeight  " + titleViewHeight);
            }
        });

        smoothListView.setRefreshEnable(false);
        smoothListView.setLoadMoreEnable(true);
        smoothListView.setSmoothListViewListener(this);

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

    /**
     * 设置筛选菜单的数据
     */
    private void setFilterData() {
        filterDataTag++;
        if (filterDataTag == 3) {
            mDropDownMenu.setMenuAdapter(dropMenuAdapter);
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
        currentPage++;
        requestNewHouseList();
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


    private void initFilterDropDownView() {
        dropMenuAdapter = new DropMenuAdapter(this, this, hashMap);
//        dropMenuAdapter = new DropMenuAdapter(this, titleList, this, hashMap);
//        mDropDownMenu.setMenuAdapter(dropMenuAdapter);
//        dropDownMenu.setContentView(tv_conttentview);
    }

    @Override
    public void onFilterDone(int position, String title, String urlValue) {
        LogUtils.d("筛选菜单...position.  " + position + "  title " + title + "   urlValue " + urlValue);
        switch (position) {
            case 1://价格
                price = "";
                fromPrice = "";
                toPrice = "";
                if (urlValue.contains("p")) {
                    price = urlValue;
                } else {    //当value不包含p时,代表自定义价格
                    fromPrice = title;
                    toPrice = urlValue;
                }
                break;
            case 2://标签
                feature = urlValue;
                break;
            default:
                break;
        }
        closeDropDownMenu(position, title);
    }

    /**
     * 筛选关闭后的操作.
     *
     * @param position
     * @param title
     */
    private void closeDropDownMenu(int position, String title) {
        isRefresh = true;
        mDropDownMenu.setPositionIndicatorText(position, title);
        headerFakeFilterViewView.setTabText(position, title);
        mDropDownMenu.close();
//        adapter.clear();
        requestNewHouseList();
    }

    /**
     * 筛选 区域 选项
     *
     * @param position
     * @param leftPosition
     * @param title
     * @param id
     */
    @Override
    public void onFilterAreaDone(int position, int leftPosition, String title, String id) {
        //清空上次选择的数据
        region = "";
        stationLine = "";
        station = "";
        if (leftPosition == 0) {//区域
            region = id;
        } else {//地铁
            if (title.equals("不限")) {//当name==不限时搜索全城.
                stationLine = id;
            } else {
                station = id;
            }
        }
//        mDropDownMenu.setPositionIndicatorText(position, name);
//        headerFakeFilterViewView.setTabText(position,name);
//        mDropDownMenu.close();
//        requestNewHouseList();

        closeDropDownMenu(position, title);
    }

    @Override
    public void onFilterMoreDone(int positionTitle, Map<String, List<FilterBean>> selectedMap) {
        LogUtils.d("positionTitle  " + positionTitle + " selectedMap " + selectedMap.size());
        for (Map.Entry<String, List<FilterBean>> entry : selectedMap.entrySet()) {
            for (int i = 0; i < entry.getValue().size(); i++) {
                FilterBean filterBean = entry.getValue().get(i);
                LogUtils.d("Key = " + entry.getKey() + "   desc  " + filterBean.getDesc() + "   value" + filterBean.getValue());
            }
        }
        isRefresh = true;
        mDropDownMenu.close();
        requestNewHouseList();
    }

    /**
     * 获取 更多菜单的 请求参数
     */
    @Override
    public void onClick(View v) {
        ll_root = findViewById(R.id.ll_root);
        int id = v.getId();
        if (id == R.id.tv_orderby) {
//            LogUtils.d(NewhouseHomeListActivity.class.getName() + "多选点击 ...........................");
//            if (poplistView==null){
//                initOrderList();
//            }else  if (isShowOrderList) {
//                hideOrderList();
//            }else {
//                showOrderList();
//            }
//            showHideDialog();
            shoePopwindow(v);
        } else if (id == R.id.orderBg) {
            hideOrderList();
        }
    }


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
                        NewHouseFilterBean response = (NewHouseFilterBean) response1;
                        String status = response.getStatus();
                        if (status.equals("C0000")) {
                            NewHouseFilterBean.ResultBean result = response.getResult();
                            List<FilterBean> property = result.getProperty();
                            List<FilterBean> features = result.getFeatures();
                            List<FilterBean> saleStatus = result.getSaleStatus();
                            List<FilterBean> price = result.getPrice();
                            orderByData = result.getOrderBy();
                            hashMap = new HashMap<>();
                            hashMap.put(houseProperty, property);
//                            hashMap.put(houseFeature, features);
                            hashMap.put(houseSalestatus, saleStatus);

                            dropMenuAdapter.setMoreData(hashMap);
                            dropMenuAdapter.setFeatureData(features);
                            dropMenuAdapter.setPriceData(price);
                            setFilterData();
                        } else {//返回错误message
                            Toast.makeText(NewhouseHomeListActivity.this, "message  " + response.getMessage() + "\n statsus  " + response.getStatus(), Toast.LENGTH_SHORT);
                        }
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
                            setFilterData();
                        } else {

                        }
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
                .execute(new NewhouseFilterMetroCallback() {

                    @Override
                    public void onResponse(FilterAreaBean response, int id) {
                        super.onResponse(response, id);
                        Logger.d("成功...............................");
                        FilterAreaBean filterAreaBean = response;
                        String status = filterAreaBean.getStatus();
                        if (status.equals("C0000")) {
                            List<FilterAreaBean.ResultBean> result = filterAreaBean.getResult();
                            dropMenuAdapter.setSubStationData(result);
                            setFilterData();
                        } else {

                        }
                    }
                });
    }

    public void requestNewHouseList() {
        String spliceUrl = UrlUtils.spliceUrl(newHouseListUrl, getNewHouseListParam(
                pageSize, String.valueOf(currentPage), region, price, houseType, orderby, stationLine, station, latitude, longitude, nearDistance, feature, saleStatus, property, fromPrice, toPrice, null));

        Logger.d("请求列表的URl  " + spliceUrl);
        OkHttpUtils
                .get()
                .url(spliceUrl)//
                .build()
                .execute(new Callback<NewHouseListItemBean>() {

                    @Override
                    public NewHouseListItemBean parseNetworkResponse(Response response, int id) throws Exception {
                        String string = response.body().string();
                        NewHouseListItemBean newHouseListItemBean = new Gson().fromJson(string, NewHouseListItemBean.class);
                        Logger.d("地铁  bean  " + newHouseListItemBean.getMessage());
                        return newHouseListItemBean;
                    }

                    @Override
                    public void onResponse(NewHouseListItemBean response, int id) {
                        Logger.d("新房列表成功...............................");
                        String status = response.getStatus();
                        if (status.equals("C0000")) {
                            List<NewHouseListItemBean.NewHouseListItem> houseListItems = response.getResult().getList();
//                            NewhouseListAdapter adapter = new NewhouseListAdapter(NewhouseHomeListActivity.this, R.layout.item_newhouse_list, houseListItems);
                            smoothListView.stopLoadMore();
                            if (isRefresh) {
                                isRefresh = false;
                                adapter.replaceAll(houseListItems);
                                Logger.d("刷新的列表 ...........");
                            } else {
                                adapter.addAll(houseListItems);
                            }
                        }
                    }

                    @Override
                    public void onError(Call call, Exception e, int id) {
                        e.printStackTrace();
                        LogUtils.d("失败啦....." + e.toString());
                    }
                });
    }

    /**
     * 获取新房列表 参数
     *
     * @param pageSize
     * @param keyword
     * @param currentPage
     * @param region
     * @param price
     * @param houseType
     * @param orderby
     * @param stationLine
     * @param station
     * @param latitude
     * @param longitude
     * @param nearDistance
     * @param feature
     * @param saleStatus
     * @param property
     * @return
     */
    public static HashMap<String, String> getNewHouseListParam(String pageSize, String currentPage, String region,
                                                               String price, String houseType, String orderby, String stationLine, String station, String latitude, String longitude, String nearDistance, String feature,
                                                               String saleStatus, String property, String fromPrice, String toPrice, String keyword) {
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("dataSource", "shenzhen");
        params.put("keyword", keyword);
        params.put("pageSize", pageSize);
        params.put("currentPage", currentPage);
        params.put("region", region);
        params.put("p", price);
        params.put("t", houseType);
        params.put("o", orderby);
        params.put("l", stationLine);
        params.put("s", station);
        params.put("latitude", latitude);
        params.put("longitude", longitude);
        params.put("nearDistance", nearDistance);
        params.put("feature", feature);
        params.put("saleStatus", saleStatus);
        params.put("property", property);
        params.put("fromPrice", fromPrice);
        params.put("toPrice", toPrice);
        return params;
    }


    /**
     * 新房首页数据
     */
    public void requestNewHouseHomePage() {

        Logger.d("新房首页成功..............................." + homepage_url);
        OkHttpUtils
                .get()
                .url(homepage_url)//
                .build()
                .execute(new Callback<NewHouseHomeBean>() {

                    @Override
                    public NewHouseHomeBean parseNetworkResponse(Response response, int id) throws Exception {
                        String string = response.body().string();
                        NewHouseHomeBean newHouseListItemBean = new Gson().fromJson(string, NewHouseHomeBean.class);
                        Logger.d("新房首页  bean  " + newHouseListItemBean.getMessage());
                        return newHouseListItemBean;
                    }

                    @Override
                    public void onResponse(NewHouseHomeBean response, int id) {


                        String status = response.getStatus();
                        if (status.equals("C0000")) {
                            List<NewHouseHomeAdvTopBanner> indexTopBannerAdList = response.getResult().getIndexTopBannerAdList();
                            List<NewHouseHomeBean.ResultBean.BrickListBean> brickList = response.getResult().getBrickList();
                            List<HotGroupBuyListBean> hotGroupBuyList = response.getResult().getHotGroupBuyList();
                            List<NewHouseHomeBean.ResultBean.HotNewhouselistBean> hotNewhouselist = response.getResult().getHotNewhouselist();
                            List<NewHouseHomeBean.ResultBean.GuideListBean> guideList = response.getResult().getGuideList();
//                            advList = indexTopBannerAdList;
                            listViewAdHeaderView.fillView(indexTopBannerAdList, smoothListView);
                            headerHorzontalListBannerView.fillView(brickList, smoothListView);
                            headerGrouBuyView.fillView(hotGroupBuyList, smoothListView);
                            headerHotNewHouseList.fillView(hotNewhouselist.subList(0, 2), smoothListView);
                            headerGuideListiew.fillView(guideList, smoothListView);

                            headerDividerViewView.fillView("", smoothListView);
                            headerFakeFilterViewView.fillView(new Object(), smoothListView);


                            // 设置ListView数据
//                            mAdapter = new TravelingAdapter(NewhouseHomeListActivity.this, travelingList);
//                            smoothListView.setAdapter(mAdapter);
                            filterViewPosition = smoothListView.getHeaderViewsCount() - 1;


                            smoothScrollListener = new DropDownMenuSmoothScrollListener(NewhouseHomeListActivity.this, smoothListView, mDropDownMenu);
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

                        } else {

                        }

                    }

                    @Override
                    public void onError(Call call, Exception e, int id) {
                        e.printStackTrace();
                        Logger.d("新房首页失败啦....." + e.toString());
                    }
                });
    }

    /**
     * 排序列表
     */
    private void initOrderList() {
        poplistView = (ListView) findViewById(R.id.lv_poplistview);
        poplistView.setAdapter(new QuickAdapter<FilterBean>(NewhouseHomeListActivity.this, R.layout.item_listview_popwindow, orderByData) {

            @Override
            protected void convert(BaseAdapterHelper helper, FilterBean item) {
                helper.setText(R.id.tv_text, item.getDesc());
            }
        });
        poplistView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                FilterBean item = (FilterBean) parent.getAdapter().getItem(position);
                Logger.d("itemt  Name" + item.getDesc() + " valuse " + item.getValue());
                orderby = item.getValue();
                hideOrderList();
            }
        });

        orderBg = findViewById(R.id.orderBg);
        orderTriangle = findViewById(R.id.orderTriangle);
        orderBg.setOnClickListener(this);
        showOrderList();
    }

    private void showOrderList() {
        isShowOrderList = true;
        poplistView.setVisibility(View.VISIBLE);
        orderBg.setVisibility(View.VISIBLE);
        orderTriangle.setVisibility(View.VISIBLE);
    }

    private void hideOrderList() {
        isShowOrderList = false;
        poplistView.setVisibility(View.GONE);
        orderBg.setVisibility(View.GONE);
        orderTriangle.setVisibility(View.GONE);
    }

    boolean isShowOrderList = false;

    /**
     * 显示daialog
     */
    private void showHideDialog() {
        OrderDailog orderDailog = null;
        if (orderDailog == null) {
            orderDailog = new OrderDailog(NewhouseHomeListActivity.this, orderByData);
            orderDailog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                @Override
                public void onCancel(DialogInterface dialog) {
                    Logger.d("cancel............");
                }
            });
            orderDailog.setOnclickListener(new OrderDailog.OnBtnClickListener() {
                @Override
                public void onBtnClick(String value) {
                    orderby = value;
                }
            });
            orderDailog.showDialog();
        } else {
            if (orderDailog.isShowing())
                orderDailog.dismiss();
            else
                orderDailog.showDialog();
        }
    }

    private void shoePopwindow(View v) {

        View root = findViewById(R.id.ll_root);
        DisplayMetrics dm = getResources().getDisplayMetrics();
        int w_screen = dm.widthPixels;
        int h_screen = dm.heightPixels;

        View view = getLayoutInflater().inflate(R.layout.layout_newhouse_popupwindow, null);
//        final PopupWindow popupWindow = new PopupWindow(view, DensityUtil.dip2px(this,200), DensityUtil.dip2px(this,400));
        final PopupWindow popupWindow = new PopupWindow(view, DensityUtil.dip2px(this, 200), ViewGroup.LayoutParams.WRAP_CONTENT);
        poplistView = (ListView) view.findViewById(R.id.lv_poplistview);
        poplistView.setAdapter(new QuickAdapter<FilterBean>(NewhouseHomeListActivity.this, R.layout.item_listview_popwindow, orderByData) {

            @Override
            protected void convert(BaseAdapterHelper helper, FilterBean item) {
                helper.setText(R.id.tv_text, item.getDesc());
            }
        });
        view.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);

//        int w = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
//        int h = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
//        poplistView.measure(w,h);
//        int poplistViewHeight = poplistView.getHeight();
//        int poplistViewWidth = poplistView.getWidth();

        int popupWidth = view.getMeasuredWidth();
        int popupHeight = view.getMeasuredHeight();
        int[] location = new int[2];
        // 允许点击外部消失
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        popupWindow.setOutsideTouchable(true);
        popupWindow.setFocusable(true);
        // 获得位置
        v.getLocationOnScreen(location);
        popupWindow.setAnimationStyle(R.style.anim_menu_bottombar);
        int x = (location[0] + v.getWidth() / 2) - popupWidth / 2;
        int y = location[1] - popupHeight;


        x = w_screen - popupWidth;
        y = location[1] - popupHeight;
        popupWindow.showAtLocation(v, Gravity.NO_GRAVITY, x, y);
        Logger.d("poplistViewHeight  " + popupWindow.getHeight() + "  poplistViewWidth " + popupWindow.getWidth());
        Logger.d("lcoation     x   " + x + "  y  " + y + "  pWidth " + popupWidth + " pHeight " +
                popupHeight + "  screenW " + w_screen + "  screenH " + h_screen);
    }
}
