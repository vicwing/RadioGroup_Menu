package com.sunfusheng.StickyHeaderListView.view;

import android.app.Activity;
import android.view.View;
import android.widget.ListView;

import com.baiiu.filter.DropDownMenu;
import com.sunfusheng.StickyHeaderListView.R;
import com.sunfusheng.StickyHeaderListView.newDropDownMenu.DropMenuAdapter;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by sunfusheng on 16/4/20.
 */
public class HeaderNewFilterView extends HeaderViewInterface<Object>   {

    @Bind(R.id.fv_filter)
    DropDownMenu dropDownMenu;
    private Activity context;

    public HeaderNewFilterView(Activity context) {
        super(context);
        this.context = context;
    }

    @Override
    protected void getView(Object obj, ListView listView) {
        View view = mInflate.inflate(R.layout.header_new_filter_layout, listView, false);
        ButterKnife.bind(this, view);

        dealWithTheView(obj);
        listView.addHeaderView(view);
    }

    private void dealWithTheView(Object obj) {
        DropMenuAdapter dropMenuAdapter = new DropMenuAdapter(context);
        dropDownMenu.setMenuAdapter(dropMenuAdapter,false);
        dropDownMenu.setOnTabclickListener(new DropDownMenu.TabOnclickListener() {
            @Override
            public void tabOnclick(int position) {
                if (onFilterClickListener != null) {
                    onFilterClickListener.onFilterClick(position);
                }
            }
        });
    }

//    @Override
//    public void onFilterClick(int position) {
//        if (onFilterClickListener != null) {
//            onFilterClickListener.onFilterClick(position);
//        }
//    }

    private OnFilterClickListener onFilterClickListener;

    public void setOnFilterClickListener(OnFilterClickListener onFilterClickListener) {
        this.onFilterClickListener = onFilterClickListener;
    }

    public interface OnFilterClickListener {
        void onFilterClick(int position);
    }
    public void setTabText(int position ,String text){
        dropDownMenu.setPositionIndicatorText(position,text);
    }
}
