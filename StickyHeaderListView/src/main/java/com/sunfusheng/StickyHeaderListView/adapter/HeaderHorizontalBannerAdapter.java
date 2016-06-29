package com.sunfusheng.StickyHeaderListView.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.sunfusheng.StickyHeaderListView.R;
import com.sunfusheng.StickyHeaderListView.model.CityItem;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by sunfusheng on 16/4/20.
 */
public class HeaderHorizontalBannerAdapter extends BaseListAdapter<CityItem> {

    public HeaderHorizontalBannerAdapter(Context context) {
        super(context);
    }

    public HeaderHorizontalBannerAdapter(Context context, List<CityItem> list) {
        super(context, list);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.list_item, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        CityItem city = getItem(position);

        mImageManager.loadUrlImage(city.getImageUrl(), holder.ivImage);
//        holder.tvCity.setText(city.getCityName());
//        holder.tvCity.setText(city.getCityCode());
        return convertView;

    }

    static class ViewHolder {
        @Bind(R.id.iv_image)
        ImageView ivImage;
        @Bind(R.id.tvCity)
        TextView tvCity;
        @Bind(R.id.tvCode)
        TextView tvCode;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
