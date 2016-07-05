package com.sunfusheng.StickyHeaderListView.newDropDownMenu.view.betterDoubleGrid;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.SparseBooleanArray;
import android.view.ViewGroup;

import com.apkfuns.logutils.LogUtils;
import com.sunfusheng.StickyHeaderListView.model.SecondHandFilterBean;
import com.sunfusheng.StickyHeaderListView.newDropDownMenu.view.betterDoubleGrid.holder.ItemViewHolder;
import com.sunfusheng.StickyHeaderListView.newDropDownMenu.view.betterDoubleGrid.holder.TitleViewHolder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * auther: baiiu
 * time: 16/6/5 05 23:28
 * description: 实现多选功能
 */
public class DoubleGridAdapter2 extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int TYPE_TITLE = 0;
    private static final int TYPE_ITEM = 1;

    public static final String houseArea = "面积";
    public static final String houseLabel = "标签";
    public static final String houseAge = "房龄";
    public static final String houseDecorate = "装修";
    private SparseBooleanArray selectedItems;
    private ItemViewHolder.ClickListener clickListener;
    private Context mContext;
    private HashMap<String, List<SecondHandFilterBean.FilterDescBean>> hashMap;
    private List<SecondHandFilterBean.FilterDescBean> label;
    private List<SecondHandFilterBean.FilterDescBean> age;
    private List<SecondHandFilterBean.FilterDescBean> decoration;
    private List<SecondHandFilterBean.FilterDescBean> area;
    private int firstSection;
    private int secondSection;
    private int thirdSection;

    public DoubleGridAdapter2(Context context, HashMap<String, List<SecondHandFilterBean.FilterDescBean>> hashMap, ItemViewHolder.ClickListener mCallback) {
        this.mContext = context;
        this.hashMap = hashMap;
        this.clickListener = mCallback;
        init();
    }

    public void init() {
        this.selectedItems = new SparseBooleanArray();

        area = hashMap.get("面积");
        label = hashMap.get("标签");
        age = hashMap.get("房龄");
        decoration = hashMap.get("装修");

        firstSection = area.size() + 1;
        secondSection = area.size() + label.size() + 2;
        thirdSection = area.size() + label.size() + age.size() + 3;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0 || position == firstSection ||
                position == secondSection || position == thirdSection) {

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
                holder = new ItemViewHolder(mContext, parent, clickListener);
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
                    titleViewHolder.bind(houseArea);
                } else if ( position == firstSection ){
                    titleViewHolder.bind(houseLabel);
                }else if ( position == secondSection ){
                    titleViewHolder.bind(houseAge);
                }else if ( position == thirdSection ){
                    titleViewHolder.bind(houseDecorate);
                }
                break;
            case TYPE_ITEM:
                ItemViewHolder itemViewHolder = (ItemViewHolder) holder;
                if (position < firstSection) { //
                    int location = position - 1;
                    itemViewHolder.bind(area.get(location).getDesc());
//                    itemViewHolder.textView.setTextColor(isSelected(position) ? Color.WHITE : Color.parseColor("#888888"));
//                    itemViewHolder.textView.setTag(area.get(location));
                    LogUtils.d("location  " + location + "   position   " + position);
                } else if (position > firstSection && position <secondSection) {
                    int location = position - area.size() - 2;
                    itemViewHolder.bind(label.get(location).getDesc());
//                    itemViewHolder.textView.setTextColor(isSelected(position) ? Color.WHITE :  Color.parseColor("#888888"));

                    LogUtils.d("location  " + location + "   position   " + position);
                } else if (position > secondSection && position < thirdSection) {
                    int location = position - area.size()-label.size() - 3;
                    itemViewHolder.bind(age.get(location).getDesc());
//                    itemViewHolder.textView.setTextColor(isSelected(position) ? Color.WHITE : Color.parseColor("#888888"));
                    LogUtils.d("location  " + location + "   position   " + position);
                }else if(position>thirdSection){
                    int location = position - area.size()-label.size()-age.size() - 4;
                    itemViewHolder.bind(decoration.get(location).getDesc());
//                    itemViewHolder.textView.setTextColor(isSelected(position) ? Color.WHITE :  Color.parseColor("#888888"));
                    LogUtils.d("location  " + location + "   position   " + position);
                }
                itemViewHolder.textView.setTextColor(isSelected(position) ? Color.WHITE :  Color.parseColor("#888888"));
                itemViewHolder.textView.setSelected(isSelected(position) ? true:false);
        break;
    }

}

    @Override
    public int getItemCount() {
        int keySetSize = hashMap.keySet().size();
        return keySetSize + area.size()+label.size()+age.size()+decoration.size();
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
