package com.sunfusheng.StickyHeaderListView.view;

import android.app.Activity;
import android.text.TextPaint;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.orhanobut.logger.Logger;
import com.sunfusheng.StickyHeaderListView.R;
import com.sunfusheng.StickyHeaderListView.adapter.HeaderHotGroupBuyAdapter;
import com.sunfusheng.StickyHeaderListView.model.HotGroupBuyListBean;

import java.util.List;




/**
 *新房首页: 热门团购
 * Created by sunfusheng on 16/4/20.
 */
public class HeaderHotGroupBuyView extends HeaderViewInterface<List<HotGroupBuyListBean>> {


    FixedGridView gvChannel;

    public HeaderHotGroupBuyView(Activity context) {
        super(context);
    }

    @Override
    protected void getView(List<HotGroupBuyListBean> list, ListView listView) {
        View view = mInflate.inflate(R.layout.header_channel_layout, listView, false);

        gvChannel = (FixedGridView) view.findViewById(R.id.gv_channel);

        view.findViewById(R.id.tv_go_groupbuy).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("3213","进如团购界面.............");
            }
        });
        setBoldText(view);

        if (list.size()<2)
                return;
        dealWithTheView(list);
        listView.addHeaderView(view);
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

    private void dealWithTheView(List<HotGroupBuyListBean> list) {
        int size = list.size();

//        if (size <= 4) {
//            gvChannel.setNumColumns(size);
//        } else if (size == 6) {
//            gvChannel.setNumColumns(3);
//        } else if (size == 8) {
//            gvChannel.setNumColumns(4);
//        } else {
//            gvChannel.setNumColumns(4);
//        }

        if (size > 2) {
            if(size%2!=0){
                list.remove(size-1);
                Logger.d("热门团购  "+list.size());
            }
        } else if (size>8){
          list.subList(0, 8);
        }
        HeaderHotGroupBuyAdapter adapter = new HeaderHotGroupBuyAdapter(mContext, list);
        gvChannel.setAdapter(adapter);
    }

}
