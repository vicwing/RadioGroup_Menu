package com.sunfusheng.StickyHeaderListView.view;

import android.app.Activity;
import android.view.View;
import android.widget.ListView;

import com.sunfusheng.StickyHeaderListView.R;
import com.sunfusheng.StickyHeaderListView.adapter.HeaderGuideListAdapter;
import com.sunfusheng.StickyHeaderListView.model.NewHouseHomeBean;

import java.util.List;




/**
 *导购咨询
 */
public class HeaderGuideListView extends HeaderViewInterface<List<NewHouseHomeBean.ResultBean.GuideListBean>> {


    FixedGridView fixedGridView;

    public HeaderGuideListView(Activity context) {
        super(context);
    }

    @Override
    protected void getView(List<NewHouseHomeBean.ResultBean.GuideListBean> list, ListView listView) {
        View view = mInflate.inflate(R.layout.header_guideview_layout, listView, false);
        fixedGridView = (FixedGridView) view.findViewById(R.id.gv_operation);
        if (list!=null&&list.size()>0){
            dealWithTheView(list.subList(0,1));
            listView.addHeaderView(view);
        }
    }

    private void dealWithTheView(List<NewHouseHomeBean.ResultBean.GuideListBean> list) {
        HeaderGuideListAdapter adapter = new HeaderGuideListAdapter(mContext, list);
        fixedGridView.setNumColumns(1);
        fixedGridView.setAdapter(adapter);
    }

}
