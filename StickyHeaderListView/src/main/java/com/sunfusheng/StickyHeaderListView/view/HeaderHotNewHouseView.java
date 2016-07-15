package com.sunfusheng.StickyHeaderListView.view;

import android.app.Activity;
import android.text.TextPaint;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.sunfusheng.StickyHeaderListView.R;
import com.sunfusheng.StickyHeaderListView.adapter.HeaderHotNewhouseAdapter;
import com.sunfusheng.StickyHeaderListView.model.NewHouseHomeBean;

import java.util.List;


/**
 *新房首页: 热门新盘
 * Created by sunfusheng on 16/4/20.
 */
public class HeaderHotNewHouseView extends HeaderViewInterface<List<NewHouseHomeBean.ResultBean.HotNewhouselistBean>> {


    FixedGridView fixedGridView;

    public HeaderHotNewHouseView(Activity context) {
        super(context);
    }

    @Override
    protected void getView(List<NewHouseHomeBean.ResultBean.HotNewhouselistBean> list, ListView listView) {
        View view = mInflate.inflate(R.layout.header_hot_groupbuy_layout, listView, false);

        fixedGridView = (FixedGridView) view.findViewById(R.id.gv_channel);
        ((TextView) view.findViewById(R.id.tv_groupbuy)).setText("热门新盘");
        view.findViewById(R.id.tv_go_groupbuy).setVisibility(View.GONE);
        view.findViewById(R.id.bottom_dividerline).setVisibility(View.GONE);

        setBoldText(view);
        dealWithTheView(list);
        if (list.size()>=2){
            listView.addHeaderView(view);
        }
    }

    /**
     * 字体加粗
     * @param view
     */
    private void setBoldText(View view) {
        TextView tv = (TextView)view.findViewById(R.id.tv_groupbuy);
        TextPaint tp = tv.getPaint();
        tp.setFakeBoldText(true);
    }

    private void dealWithTheView(List<NewHouseHomeBean.ResultBean.HotNewhouselistBean> list) {
        HeaderHotNewhouseAdapter adapter = new HeaderHotNewhouseAdapter(mContext, list);
        fixedGridView.setAdapter(adapter);
    }

}
