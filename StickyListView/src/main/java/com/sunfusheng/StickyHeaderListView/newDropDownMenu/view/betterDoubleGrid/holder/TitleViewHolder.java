package com.sunfusheng.StickyHeaderListView.newDropDownMenu.view.betterDoubleGrid.holder;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sunfusheng.StickyHeaderListView.R;

/**
 * auther: baiiu
 * time: 16/6/5 05 23:30
 * description:
 */
public class TitleViewHolder extends RecyclerView.ViewHolder {

    private final TextView textView;

    public TitleViewHolder(Context mContext, ViewGroup parent) {
//        super(UIUtil.infalte(mContext, R.layout.holder_title, parent));
        super(LayoutInflater.from(mContext).inflate(R.layout.holder_title, parent, false));
//        View inflate = LayoutInflater.from(mContext).inflate(R.layout.holder_title, parent, false);
        textView = (TextView) itemView.findViewById(R.id.textView);
    }


    public void bind(String text) {
//        ((TextView) itemView).setText(s);
        textView.setText(text);
    }
}
