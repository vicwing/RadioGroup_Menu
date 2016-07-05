package com.sunfusheng.StickyHeaderListView.newDropDownMenu.view.betterDoubleGrid;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.SparseBooleanArray;
import android.view.View;
import android.view.ViewGroup;

import com.apkfuns.logutils.LogUtils;
import com.sunfusheng.StickyHeaderListView.model.SecondHandFilterBean;
import com.sunfusheng.StickyHeaderListView.newDropDownMenu.view.betterDoubleGrid.holder.ItemViewHolder;
import com.sunfusheng.StickyHeaderListView.newDropDownMenu.view.betterDoubleGrid.holder.TitleViewHolder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * auther: baiiu
 * time: 16/6/5 05 23:28
 * description: 实现多选功能
 */
public class DoubleGridAdapter3 extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private static final int TYPE_TITLE = 0;
    private static final int TYPE_ITEM = 1;

    private SparseBooleanArray selectedItems;
    private ItemViewHolder.ClickListener clickListener;

    private Context mContext;
    private HashMap<String, List<SecondHandFilterBean.FilterDescBean>> gridList;
    private List<String> topGridData;
    private List<String> bottomGridData;
    private View.OnClickListener mListener;

    public DoubleGridAdapter3(Context context, List<String> topGridData, List<String> bottomGridList, View.OnClickListener listener, ItemViewHolder.ClickListener clickListener) {
        this.mContext = context;
        this.topGridData = topGridData;
        this.bottomGridData = bottomGridList;
        this.mListener = listener;
        this.clickListener = clickListener;

    }

    public DoubleGridAdapter3(Context context, HashMap<String, List<SecondHandFilterBean.FilterDescBean>> gridList, ItemViewHolder.ClickListener mCallback) {
        this.mContext = context;
        this.gridList = gridList;
        this.clickListener = mCallback;
    }

    public void init(){
        this.selectedItems = new SparseBooleanArray();
    }



    @Override
    public int getItemViewType(int position) {
        if (position == 0 || position == topGridData.size() + 1) {
            return TYPE_TITLE;
        }

        return TYPE_ITEM;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder holder = null;

        switch (viewType) {
            case TYPE_TITLE:
                holder = new TitleViewHolder(mContext, parent);
                break;
            case TYPE_ITEM:
                holder = new ItemViewHolder(mContext, parent,mListener, clickListener);
                break;
        }


        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        int itemViewType = getItemViewType(position);
        switch (itemViewType) {
            case TYPE_TITLE:
                TitleViewHolder titleViewHolder = (TitleViewHolder) holder;
                if (position == 0) {
                    titleViewHolder.bind("Top");
                } else {
                    titleViewHolder.bind("Bottom");
                }
                break;
            case TYPE_ITEM:
                ItemViewHolder itemViewHolder = (ItemViewHolder) holder;
                if (position < topGridData.size() + 1) {
                    itemViewHolder.bind(topGridData.get(position - 1));
                    itemViewHolder.textView.setTextColor(isSelected(position) ? Color.RED : Color.BLACK);
                } else {
                    int location = position - topGridData.size() - 2;
                    itemViewHolder.bind(bottomGridData.get(location));
                    itemViewHolder.textView.setTextColor(isSelected(position) ? Color.RED : Color.BLACK);
                    LogUtils.d("location  "+location+"   position   "+position);
                }
                break;
        }
    }

    @Override
    public int getItemCount() {
        return topGridData.size() + bottomGridData.size() + 2;
    }



    public boolean isSelected(int position) {
        return getSelectedItems().contains(position);
    }
    public void switchSelectedState(int position) {
        if (selectedItems.get(position, false)) {
            selectedItems.delete(position);
        } else {
            selectedItems.put(position, true);
        }
        notifyItemChanged(position);
    }
    public void clearSelectedState() {
        List<Integer> selection = getSelectedItems();
        selectedItems.clear();
        for (Integer i : selection) {
            notifyItemChanged(i);
        }
    }
    public int getSelectedItemCount() {
        return selectedItems.size();
    }

    public List<Integer> getSelectedItems() {
        List<Integer> items = new ArrayList<>(selectedItems.size());
        for (int i = 0; i < selectedItems.size(); ++i) {
            items.add(selectedItems.keyAt(i));
        }
        return items;
    }

}
