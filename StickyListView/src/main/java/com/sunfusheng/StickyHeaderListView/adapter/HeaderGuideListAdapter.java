package com.sunfusheng.StickyHeaderListView.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.sunfusheng.StickyHeaderListView.R;
import com.sunfusheng.StickyHeaderListView.model.NewHouseHomeBean;
import com.sunfusheng.StickyHeaderListView.util.TextHelper;

import java.util.List;


/**
 * 导购
 */
public class HeaderGuideListAdapter extends BaseListAdapter<NewHouseHomeBean.ResultBean.GuideListBean> {

    public HeaderGuideListAdapter(Context context) {
        super(context);
    }

    public HeaderGuideListAdapter(Context context, List<NewHouseHomeBean.ResultBean.GuideListBean> list) {
        super(context, list);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.layout_newhouse_guide, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        NewHouseHomeBean.ResultBean.GuideListBean item = getItem(position);

//        holder.top_view_guide_line.setVisibility(View.GONE);
        holder.view_guide_line.setVisibility(View.GONE);

        holder.tvTitle.setText(TextHelper.replaceNullTOEmpty(item.getTitle()));
        holder.tv_publish_time.setText(TextHelper.replaceNullTOEmpty(item.getPublishTime()));


        mImageManager.loadUrlImage(item.getInfoPicture(), holder.ivImage);
        return convertView;
    }

    static class ViewHolder {

        ImageView ivImage;

        TextView tvTitle;

        TextView tv_publish_time;
        View view_guide_line;
        View top_view_guide_line;

        ViewHolder(View view) {
            ivImage = (ImageView) view.findViewById(R.id.iv_guide);
            tvTitle = (TextView) view.findViewById(R.id.tv_guide_title);
            tv_publish_time = (TextView) view.findViewById(R.id.tv_publish_time);
            top_view_guide_line = view.findViewById(R.id.top_view_guide_line);
            view_guide_line = view.findViewById(R.id.view_guide_line);
        }
    }
}
