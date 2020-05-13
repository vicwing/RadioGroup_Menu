package com.example.cdj.myapplication.mainfunction.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.cdj.myapplication.R;

import java.util.List;

/**
 * 类描述：
 * 创建人：vicwing
 * 创建时间：2019-12-12 11:36
 * 最后修改人：vicwing
 */
public class MySimpledapter extends BaseQuickAdapter<String, BaseViewHolder> {

    public MySimpledapter(@Nullable List data) {
        super(R.layout.item_list_simple_text, data);
    }


    @Override
    protected void convert(BaseViewHolder helper, String item) {
        helper.setText(R.id.tv_avg_price, "item " + helper.getAdapterPosition());
    }
}
