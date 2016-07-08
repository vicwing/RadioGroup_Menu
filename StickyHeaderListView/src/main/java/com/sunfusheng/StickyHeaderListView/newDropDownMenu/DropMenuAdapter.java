package com.sunfusheng.StickyHeaderListView.newDropDownMenu;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;

import com.baiiu.filter.adapter.MenuAdapter;
import com.baiiu.filter.adapter.SimpleTextAdapter;
import com.baiiu.filter.interfaces.OnFilterDoneListener;
import com.baiiu.filter.interfaces.OnFilterItemClickListener;
import com.baiiu.filter.typeview.DoubleListView;
import com.baiiu.filter.typeview.PirceListView;
import com.baiiu.filter.typeview.SingleGridView;
import com.baiiu.filter.typeview.ThreeListView;
import com.baiiu.filter.util.CommonUtil;
import com.baiiu.filter.util.UIUtil;
import com.baiiu.filter.view.FilterCheckedTextView;
import com.orhanobut.logger.Logger;
import com.sunfusheng.StickyHeaderListView.R;
import com.sunfusheng.StickyHeaderListView.model.FilterAreaBean;
import com.sunfusheng.StickyHeaderListView.model.FilterBean;
import com.sunfusheng.StickyHeaderListView.model.SubregionsBean;
import com.sunfusheng.StickyHeaderListView.newDropDownMenu.view.NewDoubleSelectedGrid.NewhouseFilterMoreView;
import com.sunfusheng.StickyHeaderListView.newDropDownMenu.view.doubleGrid.DoubleGridView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * author: baiiu
 * date: on 16/1/17 21:14
 * description:
 */
public class DropMenuAdapter implements MenuAdapter
//        ItemViewHolder.ClickListener
{
    private final Context mContext;
    private OnFilterDoneListener onFilterDoneListener;
    private String[] titles;
    private HashMap<String, List<FilterBean>> hashMap;
    private List<FilterBean> featureData;
    private List<FilterBean> priceData;
    private List<FilterAreaBean.ResultBean> areaData;
    private List<FilterAreaBean.ResultBean> stationsData;
    //    private List<FilterAreaBean.ResultBean> metroData;
    private NewhouseFilterMoreView betterDoubleGridView;
    private final String metroStr = "地铁";
    private final String areaStr = "区域";

    public DropMenuAdapter(Context context, String[] titles, OnFilterDoneListener onFilterDoneListener) {
        this.mContext = context;
        this.titles = titles;
        this.onFilterDoneListener = onFilterDoneListener;
    }

    public DropMenuAdapter(Context context, String[] titleList, OnFilterDoneListener onFilterDoneListener, HashMap<String, List<FilterBean>> hashMap) {
        this.mContext = context;
        this.titles = titleList;
        this.onFilterDoneListener = onFilterDoneListener;
        this.hashMap = hashMap;
    }

    @Override
    public int getMenuCount() {
        return titles.length;
    }

    @Override
    public String getMenuTitle(int position) {
        return titles[position];
    }

    @Override
    public int getBottomMargin(int position) {
        if (position == 3) {
            return 0;
        }

        return UIUtil.dp(mContext, 140);
    }

    @Override
    public View getView(int position, FrameLayout parentContainer) {
        View view = parentContainer.getChildAt(position);

        switch (position) {
            case 0:
//                view = createSingleListView(0,priceData);
                view = createThreeListView(0, areaData, stationsData);
                break;
            case 1:
//                view = createThreeListView(1);
                view = createSingleListView(1, priceData);
                break;
            case 2:
//                view = createSingleGridView(2);
                view = createSingleListView(2, featureData);
                break;
            case 3:
                // view = createDoubleGrid();
                view = createNewhouseFilterMoreView(3);
//                view = createMultiSelectGrid(3);
                break;
        }

        return view;
    }

    /**
     * 区域
     * @return
     */
    private View createSingleListView(final int positionTitle, List<FilterBean> data) {
        PirceListView<FilterBean> singleListView = new PirceListView<FilterBean>(mContext)
                .adapter(new SimpleTextAdapter<FilterBean>(null, mContext) {

                    @Override
                    public String provideText(FilterBean filterBean) {
                        return filterBean.getDesc();
                    }

                    @Override
                    protected void initCheckedTextView(FilterCheckedTextView checkedTextView) {
                        int dp = UIUtil.dp(mContext, 15);
                        checkedTextView.setPadding(dp, dp, 0, dp);
                    }
                })
                .onItemClick(new OnFilterItemClickListener<FilterBean>() {
                    @Override
                    public void onItemClick(FilterBean item) {
//                        FilterUrl.instance().singleListPosition = item;
//                        FilterUrl.instance().position = 0;
//                        FilterUrl.instance().positionTitle = item;
                        onFilterDoneListener.onFilterDone(positionTitle, item.getDesc(), item.getValue());
//                        onFilterDone();
                    }
                });
        singleListView.setList(data, -1);

        return singleListView;
    }

    /**
     * 筛选 : 区域,metroStr
     *
     * @return
     */
    private View createThreeListView(int positionTitle, List<FilterAreaBean.ResultBean> areaList, List<FilterAreaBean.ResultBean> metroList) {
        Logger.d("创建 区域 筛选视图....................");
        ThreeListView<FilterAreaBean.ResultBean, SubregionsBean> threeList = new ThreeListView<FilterAreaBean.ResultBean, SubregionsBean>(mContext)
                .leftAdapter(new SimpleTextAdapter<FilterAreaBean.ResultBean>(null, mContext) {
                    @Override
                    public String provideText(FilterAreaBean.ResultBean filterType) {
                        return filterType.getName();
                    }

                    @Override
                    protected void initCheckedTextView(FilterCheckedTextView checkedTextView) {
//                        checkedTextView.setPadding(UIUtil.dp(mContext, 44), UIUtil.dp(mContext, 15), 0, UIUtil.dp(mContext, 15));
                    }
                }).midAdapter(new SimpleTextAdapter<FilterAreaBean.ResultBean>(null, mContext) {
                    @Override
                    public String provideText(FilterAreaBean.ResultBean resultBean) {
                        return resultBean.getName();
                    }

                    @Override
                    protected void initCheckedTextView(FilterCheckedTextView checkedTextView) {
//                        checkedTextView.setPadding(UIUtil.dp(mContext, 44), UIUtil.dp(mContext, 15), 0, UIUtil.dp(mContext, 15));
                    }
                })
                .rightAdapter(new SimpleTextAdapter<SubregionsBean>(null, mContext) {
                    @Override
                    public String provideText(SubregionsBean s) {
                        return s.getName();
                    }

                    @Override
                    protected void initCheckedTextView(FilterCheckedTextView checkedTextView) {
//                        checkedTextView.setPadding(UIUtil.dp(mContext, 30), UIUtil.dp(mContext, 15), 0, UIUtil.dp(mContext, 15));
//                        checkedTextView.setBackgroundResource(android.R.color.white);
                    }
                }).onLeftItemClickListener(new ThreeListView.OnLeftItemClickListener<FilterAreaBean.ResultBean, SubregionsBean>() {
                    @Override
                    public List<FilterAreaBean.ResultBean> provideMidList(FilterAreaBean.ResultBean item, int position) {
                        List<FilterAreaBean.ResultBean> resultBeen = item.getMidList();
                        Logger.d("onLeftItemClickListener  " + "  name : " + item.getName() + "  size  : " + resultBeen.size() + "  position : " + position);
                        return resultBeen;
                    }
                }).onMidItemClickListener(new ThreeListView.OnMidItemClickListener<FilterAreaBean.ResultBean, SubregionsBean>() {

                    @Override
                    public List<SubregionsBean> provideRightList(FilterAreaBean.ResultBean item, int position, int mLeftLastPosition) {
                        List<SubregionsBean> childList=null;
                        if (mLeftLastPosition==0){//位置是0时显示区域.1显示地铁线路
                            childList = item.getSubregions();
                        }else {
                            childList = item.getStations();
                        }
                        Logger.d("MiddleItemClickListener  "  + "  position : " + position+"  mLeftLastPosition "+mLeftLastPosition);
                        if (childList!=null){
                            Logger.d("MiddleItemClickListener  " + "  name : " + item.getName() + "  size  : " + childList.size() + "  position : " + position);
                            return childList;
                        }else {
                            return null;
                        }
                    }
                })
                .onRightItemClickListener(new ThreeListView.OnRightItemClickListener<FilterAreaBean.ResultBean, SubregionsBean>() {
                    @Override
                    public void onRightItemClick(FilterAreaBean.ResultBean item, SubregionsBean string) {
                        Logger.d("onRightItemClickListener  " + "  name : " + item.getName() + "  string  : " + string);
                        onFilterDoneListener.onFilterDone(1, String.valueOf(FilterUrl.instance().position), FilterUrl.instance().doubleListRight);
                    }
                });

        List<FilterAreaBean.ResultBean> leftList = new ArrayList<>();
        FilterAreaBean.ResultBean area = new FilterAreaBean.ResultBean();
        area.setName(areaStr);
        area.setMidList(areaList);
        leftList.add(area);

        FilterAreaBean.ResultBean metro = new FilterAreaBean.ResultBean();
        metro.setName(metroStr);
        metro.setMidList(metroList);
        leftList.add(metro);


        threeList.setLeftList(leftList, 0);
        threeList.setMidList(leftList.get(0).getMidList(),0);
//        threeList.setRightList(leftList.get(0).getMidList().get(0).getSubregions(), -1);
//        threeList.getLeftListView().setBackgroundColor(mContext.getResources().getColor(R.color.b_c_fafafa));

//        threeList.getLeftListView().performItemClick(threeList.getLeftListView(),0,0);

        return threeList;
    }


    private View createDoubleListView1(final int positionTitle, List<FilterAreaBean.ResultBean> areaData) {
        DoubleListView<FilterType, String> comTypeDoubleListView = new DoubleListView<FilterType, String>(mContext)
                .leftAdapter(new SimpleTextAdapter<FilterType>(null, mContext) {
                    @Override
                    public String provideText(FilterType filterType) {
                        return filterType.desc;
                    }

                    @Override
                    protected void initCheckedTextView(FilterCheckedTextView checkedTextView) {
                        checkedTextView.setPadding(UIUtil.dp(mContext, 44), UIUtil.dp(mContext, 15), 0, UIUtil.dp(mContext, 15));
                    }
                })
                .rightAdapter(new SimpleTextAdapter<String>(null, mContext) {
                    @Override
                    public String provideText(String s) {
                        return s;
                    }

                    @Override
                    protected void initCheckedTextView(FilterCheckedTextView checkedTextView) {
                        checkedTextView.setPadding(UIUtil.dp(mContext, 30), UIUtil.dp(mContext, 15), 0, UIUtil.dp(mContext, 15));
                        checkedTextView.setBackgroundResource(android.R.color.white);
                    }
                })
                .onLeftItemClickListener(new DoubleListView.OnLeftItemClickListener<FilterType, String>() {
                    @Override
                    public List<String> provideRightList(FilterType item, int position) {
                        List<String> child = item.child;
                        if (CommonUtil.isEmpty(child)) {
                            FilterUrl.instance().doubleListLeft = item.desc;
                            FilterUrl.instance().doubleListRight = "";

                            FilterUrl.instance().position = 1;
                            FilterUrl.instance().positionTitle = item.desc;

//                            onFilterDone();
                            onFilterDoneListener.onFilterDone(1, String.valueOf(position), item.desc);
                        }

                        return child;
                    }
                })
                .onRightItemClickListener(new DoubleListView.OnRightItemClickListener<FilterType, String>() {
                    @Override
                    public void onRightItemClick(FilterType item, String string) {
                        FilterUrl.instance().doubleListLeft = item.desc;
                        FilterUrl.instance().doubleListRight = string;

                        FilterUrl.instance().position = 1;
                        FilterUrl.instance().positionTitle = string;
//                        onFilterDone();

                        onFilterDoneListener.onFilterDone(1, String.valueOf(FilterUrl.instance().position), FilterUrl.instance().doubleListRight);
                    }
                });


        List<FilterType> list = new ArrayList<>();

        //第一项
        FilterType filterType = new FilterType();
        filterType.desc = "10";
        list.add(filterType);

        //第二项
        filterType = new FilterType();
        filterType.desc = "11";
        List<String> childList = new ArrayList<>();
        for (int i = 0; i < 13; ++i) {
            childList.add("11" + i);
        }
        filterType.child = childList;
        list.add(filterType);

        //第三项
        filterType = new FilterType();
        filterType.desc = "12";
        childList = new ArrayList<>();
        for (int i = 0; i < 3; ++i) {
            childList.add("12" + i);
        }
        filterType.child = childList;
        list.add(filterType);

        //初始化选中.
        comTypeDoubleListView.setLeftList(list, 1);
        comTypeDoubleListView.setRightList(list.get(1).child, -1);
        comTypeDoubleListView.getLeftListView().setBackgroundColor(mContext.getResources().getColor(R.color.b_c_fafafa));

        return comTypeDoubleListView;
    }

    private View createSingleGridView(final int positionTitle) {
        SingleGridView<String> singleGridView = new SingleGridView<String>(mContext)
                .adapter(new SimpleTextAdapter<String>(null, mContext) {
                    @Override
                    public String provideText(String s) {
                        return s;
                    }

                    @Override
                    protected void initCheckedTextView(FilterCheckedTextView checkedTextView) {
                        checkedTextView.setPadding(0, UIUtil.dp(context, 3), 0, UIUtil.dp(context, 3));
                        checkedTextView.setGravity(Gravity.CENTER);
                        checkedTextView.setBackgroundResource(R.drawable.selector_filter_grid);
                    }
                })
                .onItemClick(new OnFilterItemClickListener<String>() {
                    @Override
                    public void onItemClick(String item) {
                        FilterUrl.instance().singleGridPosition = item;

                        FilterUrl.instance().position = 2;
                        FilterUrl.instance().positionTitle = item;

//                        onFilterDone();
                        onFilterDoneListener.onFilterDone(positionTitle, item, item);
                    }
                });

        List<String> list = new ArrayList<>();
        for (int i = 20; i < 39; ++i) {
            list.add(String.valueOf(i));
        }
        singleGridView.setList(list, -1);


        return singleGridView;
    }

    /**
     * 创建新房筛选菜单更多界面.
     * @param positionTitle
     * @return
     */
    private View createNewhouseFilterMoreView(final int positionTitle) {
        NewhouseFilterMoreView<FilterBean> filterMoreView = new NewhouseFilterMoreView<FilterBean>(mContext)
                .setGidMap(hashMap)
//                .onItemClick(new OnFilterItemClickListener() {
//                    @Override
//                    public void onItemClick(Object item) {
//                        Map<String, List<FilterBean>>  map = (Map<String, List<FilterBean>>) item;
//                        if (onFilterDoneListener!=null){
//                            onFilterDoneListener.onFilterDone(3,map);
//                        }
////                    Logger.d("item  "+item.getClass().toString());
//                    }
//                })
                .setOnFilterDoneListener(onFilterDoneListener)
                .build();
        return filterMoreView;
    }


    @Deprecated
    private View createDoubleGrid() {
        DoubleGridView doubleGridView = new DoubleGridView(mContext);
        doubleGridView.setOnFilterDoneListener(onFilterDoneListener);
        List<String> phases = new ArrayList<>();
        for (int i = 0; i < 10; ++i) {
            phases.add("3top" + i);
        }
        doubleGridView.setTopGridData(phases);

        List<String> areas = new ArrayList<>();
        for (int i = 0; i < 10; ++i) {
            areas.add("3bottom" + i);
        }
        doubleGridView.setBottomGridList(areas);

        return doubleGridView;
    }


    private void onFilterDone() {
        if (onFilterDoneListener != null) {
            onFilterDoneListener.onFilterDone(0, "", "");
        }
    }


    public void setFeatureData(List<FilterBean> featureData) {
        this.featureData = featureData;
    }

    public void setPriceData(List<FilterBean> priceData) {
        this.priceData = priceData;
    }



    public void setMoreData(HashMap<String, List<FilterBean>> hashMap) {
        this.hashMap = hashMap;
    }



    /**
     * 区域
     * @param resultBeanList
     */
    public void setAreaData(List<FilterAreaBean.ResultBean> resultBeanList) {
         insetData(resultBeanList,"区域");
        this.areaData = resultBeanList;
    }

    public void setSubStationData(List<FilterAreaBean.ResultBean> stationsData) {
        insetData(stationsData, metroStr);
        this.stationsData = stationsData;
    }

    /**
     * 给区域和地铁的数据添加 不限的选项.
     * @param resultBeanList
     * @param tag
     */
    private void insetData(List<FilterAreaBean.ResultBean> resultBeanList, String tag) {
        if (resultBeanList!=null &&resultBeanList.size()!=0){
            FilterAreaBean.ResultBean filterAreaBean = new FilterAreaBean.ResultBean();
            filterAreaBean.setName("不限");
            resultBeanList.add(0,filterAreaBean);
            for (int i = 0; i < resultBeanList.size(); i++) {
//                insertSubregion(resultBeanList, i ,tag);
                List<SubregionsBean> subregionsList=  null;
                if (tag.equals(areaStr)){
                    subregionsList = resultBeanList.get(i).getSubregions();
                }else if (tag.equals(metroStr)){
                    subregionsList = resultBeanList.get(i).getStations();
                }
                if (subregionsList!=null){
                    SubregionsBean    subregionsBean  = new SubregionsBean();
                    subregionsBean.setName("不限");
                    subregionsList.add(0,subregionsBean);
                }
            }
        }
    }

}
