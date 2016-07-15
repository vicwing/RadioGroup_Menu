package com.sunfusheng.StickyHeaderListView.newDropDownMenu.view.NewDoubleSelectedGrid;

/**
 * Created by vic on 2016/7/4.
 */

import android.content.Context;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.apkfuns.logutils.LogUtils;
import com.orhanobut.logger.Logger;
import com.sunfusheng.StickyHeaderListView.R;
import com.sunfusheng.StickyHeaderListView.ui.GridViewCompat;
import com.sunfusheng.StickyHeaderListView.ui.GridViewUtils;
import com.sunfusheng.StickyHeaderListView.view.StickyListView.StickyListHeadersAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * MultSelctBetterDoubleGridView 的多选标题可固定adapter
 * 新房筛选更多,界面的adapter
 * 标题可以固定
 */
public class FilterMoreStickyListGridAdapter extends BaseAdapter implements StickyListHeadersAdapter {

    private final Context mContext;
    List<Integer> adapterTag = new ArrayList<>();

    private LayoutInflater mInflater;
    private final List<String> parentList;//

    public FilterMoreStickyListGridAdapter(Context context) {
        mContext = context;
        mInflater = LayoutInflater.from(context);
        parentList = new ArrayList<>();
        for (int i = 0; i < 4; ++i) {
            parentList.add(i + "3top");
        }
    }


    @Override
    public int getCount() {
        return parentList.size();
    }

    @Override
    public Object getItem(int position) {
        return parentList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = mInflater.inflate(R.layout.gridview_filter_more, parent, false);
            holder.gridView = (GridViewCompat) convertView.findViewById(R.id.my_gridview);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
//

//        if (!adapterTag.contains(position)){
            holder.gridView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
            holder.gridView.setAdapter(new FilterMoreGridAdapterBug(mContext));
            Logger.d("创建了 "+position+" 个对象    ");
//            adapterTag.add(position);
//        }

//        holder.gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//
//            @Override
//            public void onItemClick(AdapterView<?> view, View arg1, int gridPosition, long id) {
//                // We need to invalidate all views on 4.x versions
//                GridViewCompat gridView = (GridViewCompat) view;
//                gridView.invalidateViews();
//                // gridView.invalidate();
////                int position = (int)gridView.getTag();
//                LogUtils.d("list  position " + "   grid view   pos  " + gridPosition);
//            }
//        });
        holder.gridView.setOnItemClickListener(new OnItemChildClickListener(0,position));

        // 计算GridView宽度,
        GridViewUtils.updateGridViewLayoutParams(holder.gridView, 4);
//        LogUtils.d( "mCountries[position]  " + position + "   " + mCountries[position]);
        return convertView;
    }

    @Override
    public View getHeaderView(int position, View convertView, ViewGroup parent) {
        HeaderViewHolder holder;
        if (convertView == null) {
            holder = new HeaderViewHolder();
            convertView = mInflater.inflate(R.layout.item_list_filter_more_grid_title, parent, false);
            holder.text = (TextView) convertView.findViewById(R.id.tv_text);
            convertView.setTag(holder);
        } else {
            holder = (HeaderViewHolder) convertView.getTag();
        }

        // set header text as first char in name
//        CharSequence headerChar = mCountries[position].subSequence(0, 1);
        String headerChar = parentList.get(position);
        holder.text.setText(headerChar);
//        LogUtils.d("listSize  " + parentList.size() + "  headerChar  " + headerChar + "   position");
        return convertView;
    }

    public SparseBooleanArray getCheckedItemPositions() {
        return null;
    }

    /**
     * Remember that these have to be static, postion=1 should always return
     * the same Id that is.
     */
    @Override
    public long getHeaderId(int position) {
//        parentList.get(position).charAt(4);
        char c = parentList.get(position).charAt(0);
        return c;
    }

    public class HeaderViewHolder {
        TextView text;
    }

    static class ViewHolder {
        TextView text;
        GridViewCompat gridView;
    }


    public class OnItemChildClickListener implements AdapterView.OnItemClickListener {
        // 点击类型索引，对应前面的CLICK_INDEX_xxx
        private int clickIndex;
        // 点击列表位置
        private int rowItem;
        private SparseBooleanArray sparseBooleanArray;

        public OnItemChildClickListener(int clickIndex, int position) {
            this.clickIndex = clickIndex;
            this.rowItem = position;
        }

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            // We need to invalidate all views on 4.x versions
                GridViewCompat gridView = (GridViewCompat) parent;
                gridView.invalidateViews();
////                int position = (int)gridView.getTag();
            sparseBooleanArray = gridView.getCheckedItemPositions();
            LogUtils.d("list  rowItem " + rowItem+"   grid view   sparseBooleanArray  " + sparseBooleanArray);
            Logger.d("list  rowItem " + rowItem+"   grid view   sparseBooleanArray  " + sparseBooleanArray);
        }
    }
}