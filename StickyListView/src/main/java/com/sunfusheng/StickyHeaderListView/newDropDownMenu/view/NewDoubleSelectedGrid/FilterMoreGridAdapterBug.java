package com.sunfusheng.StickyHeaderListView.newDropDownMenu.view.NewDoubleSelectedGrid;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;

import com.baiiu.filter.view.FilterCheckedTextView;
import com.sunfusheng.StickyHeaderListView.R;
import com.sunfusheng.StickyHeaderListView.model.FilterBean;
import com.sunfusheng.StickyHeaderListView.ui.GridViewCompat;

import java.util.List;

/**
 * 更多筛选项里面的gridview:滑动后不保存记录
 * Created by vic on 2016/6/30.
 */
public class FilterMoreGridAdapterBug extends BaseAdapter {

    private List<FilterBean> data;
    private Context mContext;

    public FilterMoreGridAdapterBug(Context c) {
        mContext = c;
    }

    public FilterMoreGridAdapterBug(Context context, List<FilterBean> data) {
        this.mContext = context;
        this.data = data;
    }

    public int getCount() {
        return data.size();
    }

    public Object getItem(int position) {
        return data.get(position);
    }

    public long getItemId(int position) {
        return position;
    }

    // create a new grid view item for each item referenced by the Adapter
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public View getView(int position, View convertView, ViewGroup parent) {
        FilterCheckedTextView text;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_list_filter_more_grid_content, parent, false);
        }
        text = (FilterCheckedTextView) convertView.findViewById(R.id.tv_text);
        text.setText(data.get(position).getDesc());
        GridViewCompat gvc = (GridViewCompat) parent;
        if (gvc.getChoiceMode() == ListView.CHOICE_MODE_MULTIPLE) {
            SparseBooleanArray checkArray;
            checkArray = gvc.getCheckedItemPositions();
//            text.setTextColor(Color.BLACK);
//            text.setSelected(false);
            if (checkArray != null) {
                if (checkArray.get(position)) {
//                    text.setTextColor(Color.RED);
                    text.setSelected(true);
                }
            }
        }

        return convertView;
    }
}