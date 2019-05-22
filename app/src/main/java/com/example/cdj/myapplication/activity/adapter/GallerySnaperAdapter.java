package com.example.cdj.myapplication.activity.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.cdj.myapplication.R;

import java.util.List;

/**
 * 类描述：
 * 创建人：vicwing
 * 创建时间：2019-05-13 17:45
 * 最后修改人：vicwing
 */
public class GallerySnaperAdapter extends BaseQuickAdapter<String,BaseViewHolder> {
    public GallerySnaperAdapter(@Nullable List data) {
        super(R.layout.gallery_item_layout, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        helper.setImageResource(R.id.image, R.mipmap.ic_launcher);
        helper.setText(R.id.tv_num, item);
    }

}
