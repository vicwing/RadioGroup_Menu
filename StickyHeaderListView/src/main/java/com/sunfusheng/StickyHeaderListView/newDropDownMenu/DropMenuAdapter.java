package com.sunfusheng.StickyHeaderListView.newDropDownMenu;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;

import com.apkfuns.logutils.LogUtils;
import com.baiiu.filter.adapter.MenuAdapter;
import com.baiiu.filter.adapter.SimpleTextAdapter;
import com.baiiu.filter.interfaces.OnFilterDoneListener;
import com.baiiu.filter.interfaces.OnFilterItemClickListener;
import com.baiiu.filter.typeview.DoubleListView;
import com.baiiu.filter.typeview.SingleGridView;
import com.baiiu.filter.typeview.SingleListView;
import com.baiiu.filter.util.CommonUtil;
import com.baiiu.filter.util.UIUtil;
import com.baiiu.filter.view.FilterCheckedTextView;
import com.sunfusheng.StickyHeaderListView.R;
import com.sunfusheng.StickyHeaderListView.model.FilterBean;
import com.sunfusheng.StickyHeaderListView.newDropDownMenu.view.NewDoubleSelectedGrid.MultSelctBetterDoubleGridView;
import com.sunfusheng.StickyHeaderListView.newDropDownMenu.view.NewDoubleSelectedGrid.BetterThreeGridNewhouseFilterView;
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
    private List<FilterBean> filterBeen;

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
                view = createSingleListView(0);
                break;
            case 1:
                view = createDoubleListView(1);
                break;
            case 2:
//                view = createSingleGridView(2);
                view = createSingleListView(2);
                break;
            case 3:
                // view = createDoubleGrid();
                view = createBetterDoubleGrid(3);
//                view = createMultiSelectGrid(3);
                break;
        }

        return view;
    }

    /**
     * 区域
     *
     * @return
     */
    private View createSingleListView(final int positionTitle) {
        SingleListView<FilterBean> singleListView = new SingleListView<FilterBean>(mContext)
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
                        onFilterDoneListener.onFilterDone(positionTitle, item.getDesc(),item.getDesc());
//                        onFilterDone();
                    }
                });
        singleListView.setList(filterBeen, -1);

        return singleListView;
    }


    private View createDoubleListView(final int positionTitle) {
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
     * 创建新的,可多选的GridView
     *
     * @return
     */
    private View createMultiSelectGrid(final int positionTitle) {
        return new MultSelctBetterDoubleGridView(mContext)
//                .setmTopGridData(phases)
//                .setmBottomGridList(areas)
                .setOnFilterDoneListener(onFilterDoneListener)
                .build();
    }

    private View createBetterDoubleGrid(final int positionTitle) {

        List<String> phases = new ArrayList<>();
        for (int i = 0; i < 10; ++i) {
            phases.add("3top" + i);
            LogUtils.d("3top" + i);
        }
        List<String> areas = new ArrayList<>();
        for (int i = 0; i < 10; ++i) {
            areas.add("3bottom" + i);
        }
        betterDoubleGridView = new BetterThreeGridNewhouseFilterView(mContext)
                .setGidMap(hashMap)
                .setOnFilterDoneListener(onFilterDoneListener)
                .build();
        return betterDoubleGridView;
    }


    private BetterThreeGridNewhouseFilterView betterDoubleGridView;
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
        filterBeen = featureData;
    }
}
