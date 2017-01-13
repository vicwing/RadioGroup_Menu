package com.example.cdj.myapplication.mainfunction.newhouse;

import android.app.Activity;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.apkfuns.logutils.LogUtils;
import com.example.cdj.myapplication.Bean.SecListBean;
import com.example.cdj.myapplication.R;
import com.example.cdj.myapplication.SecListItemBeanCallback;
import com.example.cdj.myapplication.baseadapter.SecListItemAdapter;
import com.example.cdj.myapplication.cusview.DropDownMenu;
import com.example.cdj.myapplication.cusview.MyListView;
import com.example.cdj.myapplication.cusview.StickyScrollView;
import com.example.cdj.myapplication.mainfunction.newhouse.adapter.ConstellationAdapter;
import com.example.cdj.myapplication.mainfunction.newhouse.adapter.GirdDropDownAdapter;
import com.example.cdj.myapplication.mainfunction.newhouse.adapter.ListDropDownAdapter;
import com.orhanobut.logger.Logger;
import com.zhy.http.okhttp.OkHttpUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;

/**
 * scrollview嵌套太多错误很难开发.
 * 问题1 上拉加载判断不容易.
 * 问题2 没办法显示footer 空白部分.
 * Created by vic on 2016/6/23.
 */
@Deprecated
public class NewHouseListActivity  extends Activity {


    @Bind(R.id.dropDownMenu)
    DropDownMenu mDropDownMenu;
    private String headers[] = {"城市", "年龄", "性别", "星座"};
    private List<View> popupViews = new ArrayList<>();

    private GirdDropDownAdapter cityAdapter;
    private ListDropDownAdapter ageAdapter;
    private ListDropDownAdapter sexAdapter;
    private ConstellationAdapter constellationAdapter;

    private String citys[] = {"不限", "武汉", "北京", "上海", "成都", "广州", "深圳", "重庆", "天津", "西安", "南京", "杭州"};
    private String ages[] = {"不限", "18岁以下", "18-22岁", "23-26岁", "27-35岁", "35岁以上"};
    private String sexs[] = {"不限", "男", "女"};
    private String constellations[] = {"不限", "白羊座", "金牛座", "双子座", "巨蟹座", "狮子座", "处女座", "天秤座", "天蝎座", "射手座", "摩羯座", "水瓶座", "双鱼座"};

    private int constellationPosition = 0;
    private View viewById;
    private ListView listViewMain;
    private SecListItemAdapter secListItemAdapter;
    private MyListView myListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newhouse_list);
        ButterKnife.bind(this);
        findViewById(R.id.mybutton).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "hej", Toast.LENGTH_SHORT).show();
            }
        });
        initView();
    }


    private void initView() {
        requestUpdate(String.valueOf(currentPage));
        setDropDownMenu();
        mDropDownMenu.setOnDropDownMenuListener(new DropDownMenu.DropDownMenuListener() {
            @Override
            public void onClickListener(View v) {
                LogUtils.d("setOnDropDownMenuListener  被点击了............");
                View tabMenuView = mDropDownMenu.getTabMenuView();
                scrollTo(tabMenuView);
            }
        });

    }
    private  int currentPage=1;
    public static  String Url = "http://10.251.93.254:8010/appapi/v4_3/room/list?bizType=SALE&dataSource=SHENZHEN&pageSize=20";
    private void requestUpdate(final String currentPageStr) {
        Logger.d("下拉刷新控件啦......currentPage  "+currentPageStr);
        OkHttpUtils
                .post()//
                .url(Url)//
                .addParams("currentPage",currentPageStr)
                .build()//
                .execute(new  SecListItemBeanCallback() {
                    @Override
                    public void onError(Call call, Exception e,int id) {
//                        mPtrFrameLayout.refreshComplete();
                    }


                    @Override
                    public void onResponse(SecListBean response,int id) {

//                        mPtrFrameLayout.refreshComplete();
                        if (secListItemAdapter!=null)
                            secListItemAdapter.addAll(response.getResult().getList());
                          LogUtils.d("okhttp     response ....................");
//                        setListViewHeightBasedOnChildren(myListView);
//                        listViewMain.setFocusable(true);
//                        listViewMain.setFocusableInTouchMode(true);
//                        listViewMain.requestFocus();
//                        Logger.d("response  "+response.getMessage()+"  count  "+mAdapter.getCount());
//                        if (currentPage<=pageCount){
//                            loadMoreListViewContainer.loadMoreFinish(false,true);
//                        }
                    }
                });
    }
    /**
     * 筛选菜单按钮
     */
    private void setDropDownMenu() {
        //init city menu
        final ListView cityView = new ListView(this);
        cityAdapter = new GirdDropDownAdapter(this, Arrays.asList(citys));
        cityView.setDividerHeight(0);
        cityView.setAdapter(cityAdapter);

        //init age menu
        final ListView ageView = new ListView(this);
        ageView.setDividerHeight(0);
        ageAdapter = new ListDropDownAdapter(this, Arrays.asList(ages));
        ageView.setAdapter(ageAdapter);

        //init sex menu
        final ListView sexView = new ListView(this);
        sexView.setDividerHeight(0);
        sexAdapter = new ListDropDownAdapter(this, Arrays.asList(sexs));
        sexView.setAdapter(sexAdapter);

        //init constellation
        final View constellationView = getLayoutInflater().inflate(R.layout.drop_down_menu_custom_layout, null);
        GridView constellation = ButterKnife.findById(constellationView, R.id.constellation);
        constellationAdapter = new ConstellationAdapter(this, Arrays.asList(constellations));
        constellation.setAdapter(constellationAdapter);
        TextView ok = ButterKnife.findById(constellationView, R.id.ok);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDropDownMenu.setTabText(constellationPosition == 0 ? headers[3] : constellations[constellationPosition]);
                mDropDownMenu.closeMenu();
            }
        });

        //init popupViews
        popupViews.add(cityView);
        popupViews.add(ageView);
        popupViews.add(sexView);
        popupViews.add(constellationView);

        //add item click event
        cityView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                cityAdapter.setCheckItem(position);
                mDropDownMenu.setTabText(position == 0 ? headers[0] : citys[position]);
                mDropDownMenu.closeMenu();
            }
        });

        ageView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ageAdapter.setCheckItem(position);
                mDropDownMenu.setTabText(position == 0 ? headers[1] : ages[position]);
                mDropDownMenu.closeMenu();
            }
        });

        sexView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                sexAdapter.setCheckItem(position);
                mDropDownMenu.setTabText(position == 0 ? headers[2] : sexs[position]);
                mDropDownMenu.closeMenu();
            }
        });

        constellation.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                constellationAdapter.setCheckItem(position);
                constellationPosition = position;
            }
        });

        //init context view
        TextView contentView = new TextView(this);
        contentView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        contentView.setText("内容显示区域");
        contentView.setGravity(Gravity.CENTER);
        contentView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
        //init dropdownview
//        mDropDownMenu.setDropDownMenu(Arrays.asList(headers), popupViews, listViewMain);

//
        myListView = new MyListView(this);
        myListView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        secListItemAdapter = new SecListItemAdapter(this, R.layout.item_list_secondlist);
        myListView.setAdapter(secListItemAdapter);
        mDropDownMenu.setDropDownMenu(Arrays.asList(headers), popupViews, myListView);

//
//        listViewMain = (ListView)findViewById(R.id.lv_listview);
//        secListItemAdapter = new SecListItemAdapter(this, R.layout.item_list_secondlist);
//        listViewMain.setAdapter(secListItemAdapter);
//        mDropDownMenu.setDropDownMenu(Arrays.asList(headers), popupViews);
//        mDropDownMenu.setDropDownMenu(Arrays.asList(headers), popupViews,contentView);
    }

    public static void setListViewHeightBasedOnChildren(ListView listView) {
        //获取ListView对应的Adapter
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            // pre-condition
            return;
        }

        int totalHeight = 0;
        for (int i = 0, len = listAdapter.getCount(); i < len; i++) { //listAdapter.getCount()返回数据项的数目
            View listItem = listAdapter.getView(i, null, listView);
            listItem.measure(0, 0); //计算子项View 的宽高
            totalHeight += listItem.getMeasuredHeight(); //统计所有子项的总高度
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        //myListView.getDividerHeight()获取子项间分隔符占用的高度
        //params.height最后得到整个ListView完整显示需要的高度
        listView.setLayoutParams(params);
    }
    @Override
    public void onBackPressed() {
        //退出activity前关闭菜单
        if (mDropDownMenu.isShowing()) {
            mDropDownMenu.closeMenu();
        } else {
            super.onBackPressed();
        }
    }

    /**
     * 滚动的到指定的,view的位置
     * @param view
     */
    private void scrollTo(final View view) {
        final StickyScrollView mRootScrollView = (StickyScrollView) findViewById(R.id.sticky_scrollview);
        mRootScrollView.post(new Runnable() {
            @Override
            public void run() {
                int[] location = new int[2];
                view.getLocationOnScreen(location);
                int offset = location[1] - mRootScrollView.getMeasuredHeight()+view.getHeight();
                offset = Math.abs(offset);
                if (offset < 0) {
                    offset = 0;
                }
//                mRootScrollView.smoothScrollTo(0, offset);
                mRootScrollView.smoothScrollBySlow(0, offset,1500);
            }
        });
    }


}
