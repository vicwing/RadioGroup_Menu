package com.sunfusheng.StickyHeaderListView.adapter;

import android.content.Context;
import android.text.TextPaint;
import android.text.TextUtils;
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
 * 新房首页: 热门新盘
 */
public class HeaderHotNewhouseAdapter extends BaseListAdapter<NewHouseHomeBean.ResultBean.HotNewhouselistBean> {

    private final Context context;

    public HeaderHotNewhouseAdapter(Context context, List<NewHouseHomeBean.ResultBean.HotNewhouselistBean> list) {
        super(context, list);
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_newhouse_hotgroupbuy, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        NewHouseHomeBean.ResultBean.HotNewhouselistBean entity = getItem(position);

        holder.tvTitle.setMaxEms(7);
        setBoldText(holder.tvTitle, entity.getGarden().getName());
        holder.tvTitle.setBackgroundDrawable(context.getResources().getDrawable(R.drawable.xml_oval_orange_icon));

        String avgPrice = entity.getAvgPrice();

        holder.discount.setBackgroundDrawable(null);
        holder.discount.setText(TextHelper.replaceNullTOTarget(avgPrice,"价格待定","元/㎡"));


        NewHouseHomeBean.ResultBean.HotNewhouselistBean.GardenBean.RegionBean region = entity.getGarden().getRegion();
        holder.tv_applicantsNumber.setText(TextHelper.replaceNullTOTarget(region.getParent().getName()+"  "+region.getName(),""));


        mImageManager.loadUrlImage(entity.getHomePictureUrl(),holder.ivImage);
        return convertView;
    }

    /**
     * 设置加粗字体
     * @param textview
     * @param text
     */
    private void setBoldText(TextView textview, String text) {
        if (!TextUtils.isEmpty(text)){
            textview.setText(text);
            TextPaint tp =   textview.getPaint();
            tp.setFakeBoldText(true);
        }else {
            textview.setVisibility(View.GONE);
        }
    }

    static class ViewHolder {
        ImageView ivImage;
        TextView tvTitle;
        TextView discount;
        TextView tv_applicantsNumber;

        ViewHolder(View view) {
            ivImage = (ImageView) view.findViewById(R.id.iv_image);

            tvTitle = (TextView) view.findViewById(R.id.tv_title);

            discount = (TextView) view.findViewById(R.id.tv_discount);

            tv_applicantsNumber = (TextView) view.findViewById(R.id.tv_applicantsNumber);
        }
    }
}
