package com.example.cdj.myapplication.adapter;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.cdj.myapplication.Bean.SecListItemEntity;
import com.example.cdj.myapplication.R;
import com.example.cdj.myapplication.adapter.adapterhelper.BaseAdapterHelper;
import com.example.cdj.myapplication.adapter.adapterhelper.QuickAdapter;
import com.example.cdj.myapplication.config.GlobalConstant;

import java.util.List;

/**
 * 二手房列表页
 * Created by cdj on 2016/5/12.
 */
public class SecListItemAdapter extends QuickAdapter<SecListItemEntity> {





//    public SecListItemAdapter(Context context, List<SecListItemEntity> list) {
//        super(context, 0, list);
//        getLayout();
//    }

    public SecListItemAdapter(Context context, int item_list_secondlist) {
        super(context, item_list_secondlist);
    }

    public SecListItemAdapter(Context context, int layoutResId, List<SecListItemEntity> data) {
        super(context, layoutResId, data);
    }


    private void getLayout() {
        layoutResId = R.layout.item_list_secondlist;
    }

    @Override
    protected void convert(BaseAdapterHelper helper, SecListItemEntity item) {
        helper.setText(R.id.tv_house_title, item.getGarden().getName());
//        helper.setImageBitmap(R.id.iv_seclist_logo, Glide.)
        Glide.with(context).load(item.getLivingRoomPictrue().replace("{size}", GlobalConstant.ImgSize_180_135)).into((ImageView) helper.getView(R.id.iv_seclist_logo));
    }
}
