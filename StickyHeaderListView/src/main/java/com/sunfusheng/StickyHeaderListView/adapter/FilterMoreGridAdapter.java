package com.sunfusheng.StickyHeaderListView.adapter;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.sunfusheng.StickyHeaderListView.R;
import com.sunfusheng.StickyHeaderListView.ui.GridViewCompat;

/**
 * Created by vic on 2016/6/30.
 */
public class FilterMoreGridAdapter  extends BaseAdapter {

    private Context mContext;
    private String[] mThumbIds = {"1111", "22222", "3333", "444", "3333", "444"};


    public FilterMoreGridAdapter(Context c) {
        mContext = c;
    }

    public int getCount() {
        return mThumbIds.length;
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    // create a new grid view item for each item referenced by the Adapter
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView text;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_list_filter_more_grid_content, parent, false);
        }
        text = (TextView) convertView.findViewById(R.id.text);
        GridViewCompat gvc = (GridViewCompat) parent;
        if (gvc.getChoiceMode() == ListView.CHOICE_MODE_MULTIPLE) {
            SparseBooleanArray checkArray;
            checkArray = gvc.getCheckedItemPositions();
            text.setTextColor(Color.BLACK);
            if (checkArray != null) {
                if (checkArray.get(position)) {
                    text.setTextColor(Color.RED);
                }
            }

        }
        text.setText("选择" + position);
        return convertView;
    }
}