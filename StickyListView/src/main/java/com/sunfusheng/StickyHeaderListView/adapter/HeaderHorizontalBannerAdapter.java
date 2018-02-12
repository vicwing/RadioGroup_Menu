package com.sunfusheng.StickyHeaderListView.adapter;

import android.content.Context;
import android.text.TextPaint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.sunfusheng.StickyHeaderListView.R;
import com.sunfusheng.StickyHeaderListView.model.NewHouseHomeBean;

import java.util.List;




/**
 * 新房首页: 全部楼盘.横向的广告
 */
public class HeaderHorizontalBannerAdapter extends BaseListAdapter<NewHouseHomeBean.ResultBean.BrickListBean> {

    public HeaderHorizontalBannerAdapter(Context context) {
        super(context);
    }

    public HeaderHorizontalBannerAdapter(Context context, List<NewHouseHomeBean.ResultBean.BrickListBean> list) {
        super(context, list);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_newhouse_horizontal_banner, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        NewHouseHomeBean.ResultBean.BrickListBean item = getItem(position);

        mImageManager.loadUrlImage(item.getPicture(), holder.ivImage);
        holder.tv_feature.setText(item.getNameTemp());
        TextPaint tp =   holder.tv_feature.getPaint();
        tp.setFakeBoldText(true);
        return convertView;

    }

    static class ViewHolder {

        ImageView ivImage;
        TextView tv_feature;

        ViewHolder(View view) {
            ivImage = (ImageView) view.findViewById(R.id.iv_image);

            tv_feature = (TextView) view.findViewById(R.id.tv_feature);
        }
    }
}
