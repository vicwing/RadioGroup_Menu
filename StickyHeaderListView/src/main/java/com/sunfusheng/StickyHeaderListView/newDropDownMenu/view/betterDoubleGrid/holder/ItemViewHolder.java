package com.sunfusheng.StickyHeaderListView.newDropDownMenu.view.betterDoubleGrid.holder;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.apkfuns.logutils.LogUtils;
import com.baiiu.filter.util.UIUtil;
import com.baiiu.filter.view.FilterCheckedTextView;
import com.sunfusheng.StickyHeaderListView.R;

import butterknife.ButterKnife;

/**
 * auther: baiiu
 * time: 16/6/5 05 23:35
 * description:
 */
public class ItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public  FilterCheckedTextView textView;
    private View.OnClickListener mListener;
    private ItemViewHolder.ClickListener clickListener;

    public ItemViewHolder(Context mContext, ViewGroup parent,View.OnClickListener mListener) {
        super(UIUtil.infalte(mContext, R.layout.holder_item, parent));
        textView = ButterKnife.findById(itemView, R.id.tv_item);
        this.mListener = mListener;
    }
    public ItemViewHolder(Context mContext, ViewGroup parent,View.OnClickListener mListener, ItemViewHolder.ClickListener clickListener) {
        super(UIUtil.infalte(mContext, R.layout.holder_item, parent));
        textView = ButterKnife.findById(itemView, R.id.tv_item);
        this.mListener = mListener;
        this.clickListener = clickListener;
    }


    public void bind(String s) {
        textView.setText(s);
        textView.setTag(s);
        textView.setOnClickListener(mListener);
    }


    @Override
    public void onClick(View v) {
        if (clickListener != null) {
            clickListener.onItemClicked(getAdapterPosition());
            LogUtils.d("clickListener   position "+getAdapterPosition());
        }
    }

    public interface ClickListener {
        void onItemClicked(int position);

    }
}
