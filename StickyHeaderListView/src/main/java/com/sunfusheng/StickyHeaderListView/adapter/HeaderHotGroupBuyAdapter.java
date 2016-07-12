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
import com.sunfusheng.StickyHeaderListView.model.HotGroupBuyListBean;

import java.util.List;

/**
 * 新房首页: 热门团购
 */
public class HeaderHotGroupBuyAdapter extends BaseListAdapter<HotGroupBuyListBean> {

    public HeaderHotGroupBuyAdapter(Context context, List<HotGroupBuyListBean> list) {
        super(context, list);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_channel, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        HotGroupBuyListBean entity = getItem(position);

            setBoldText(holder.tvTitle, entity.getGarden().getName());

        List<HotGroupBuyListBean.GroupBuyListBean> groupBuyList = entity.getGroupBuyList();
        if (groupBuyList!=null&&groupBuyList.size()!=0){//团购打折,有多个优惠信息,目前取第一个by 2016-7-12
            HotGroupBuyListBean.GroupBuyListBean groupBuyListBean = groupBuyList.get(0);
            setBoldText(holder.discount, groupBuyListBean.getFavorableTitle());
            holder.tv_applicantsNumber.setText( groupBuyListBean.getApplicantsNumber()+"已报名");
        }else {
            holder.discount.setVisibility(View.GONE);
            holder.tv_applicantsNumber.setVisibility(View.GONE);
        }


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
