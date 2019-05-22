package com.example.cdj.myapplication.mainfunction.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.cdj.myapplication.R;

import java.util.List;

/**
 * 类描述：
 * 创建人：vicwing
 * 创建时间：2019-05-13 17:00
 * 最后修改人：vicwing
 */
public class FragmentAAdapter extends BaseQuickAdapter<String, BaseViewHolder> {
    public FragmentAAdapter(@Nullable List<String> data) {
        super(R.layout.item_fragmenta_recyclview, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        helper.setText(R.id.tv_content, item);
    }
}
