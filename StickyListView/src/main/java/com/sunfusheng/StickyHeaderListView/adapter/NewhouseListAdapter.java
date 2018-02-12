package com.sunfusheng.StickyHeaderListView.adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.StyleSpan;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.sunfusheng.StickyHeaderListView.R;
import com.sunfusheng.StickyHeaderListView.base.quick.BaseAdapterHelper;
import com.sunfusheng.StickyHeaderListView.base.quick.QuickAdapter;
import com.sunfusheng.StickyHeaderListView.manager.ImageManager;
import com.sunfusheng.StickyHeaderListView.model.NewHouseListItemBean;
import com.sunfusheng.StickyHeaderListView.util.DensityUtil;
import com.sunfusheng.StickyHeaderListView.util.TextHelper;
import com.sunfusheng.StickyHeaderListView.view.TagContainerLayout.TagContainerLayout;
import com.sunfusheng.StickyHeaderListView.view.TagContainerLayout.TagView;

import java.util.List;

/**
 * Created by vic on 2016/7/13.
 */
public class NewhouseListAdapter extends QuickAdapter<NewHouseListItemBean.NewHouseListItem> {


    private Context context;
    private ImageManager imageManager;
    private  int[] colors = {Color.parseColor("#f37f3f"),Color.parseColor("#1dc119"),Color.parseColor("#3aa5ef"),Color.parseColor("#3aa5ef")};

    public NewhouseListAdapter(Context context, int layoutResId) {
        super(context, layoutResId);
        this.context = context;
        imageManager = new ImageManager(context);
    }

    public NewhouseListAdapter(Context context, int layoutResId, List<NewHouseListItemBean.NewHouseListItem> data) {
        super(context, layoutResId, data);
        this.context = context;
        imageManager = new ImageManager(context);
    }

    @Override
    protected void convert(BaseAdapterHelper helper, NewHouseListItemBean.NewHouseListItem item) {
        imageManager.loadUrlImage(item.getGarden().getIndexPictureUrl(), (ImageView) helper.getView(R.id.iv_newhouse_image));
//        helper.setText(R.id.tv_title,item.getGarden().getName());
        TextView titleTextview = helper.getView(R.id.tv_title);
        titleTextview.setText(item.getGarden().getName());
        titleTextview.getPaint().setFakeBoldText(true);

        helper.setText(R.id.tv_address, item.getGarden().getAddress());
        helper.setText(R.id.tv_area, TextHelper.replaceNullTOEmpty(item.getGarden().getArea(), "㎡"));
//        helper.setText(R.id.tv_price, TextHelper.replaceNullTOTarget(item.getAvgPrice().toString(), "暂无数据", "元/㎡"));
        helper.setText(R.id.tv_price, TextHelper.replaceNullTOTarget(setAvgPriceSpan(context, String.valueOf(item.getAvgPrice().intValue()), "元/㎡"), "暂无数据"));




        setFeatures(helper, item);

        //团购
        List<NewHouseListItemBean.NewHouseListItem.GroupBuyList> groupBuyList = item.getGroupBuyList();
        if (groupBuyList != null && groupBuyList.size() != 0) {
            helper.getView(R.id.tv_graoupbuy).setVisibility(View.VISIBLE);
            helper.setText(R.id.tv_graoupbuy, groupBuyList.get(0).getFavorableTitle());
        } else {
            helper.getView(R.id.tv_graoupbuy).setVisibility(View.GONE);
        }
    }

    /**
     *   //标签
     * @param helper
     * @param item
     */
    private void setFeatures(BaseAdapterHelper helper, NewHouseListItemBean.NewHouseListItem item) {
        List<String> features = item.getFeatures();
        if (features != null && features.size() != 0) {
            TagContainerLayout tagContainerLayout = helper.getView(R.id.tagcontainerLayout1);
            if (features.size()>3)//只显示3个标签
                features.subList(0,3);

            tagContainerLayout.setTags(features);
//            tagContainerLayout.setChildHeight(context,20);
            //这个要起作用,需要注释掉  TagContainerLayout的下面的一段代码.
            // mChildHeight = i == 0 ? height : Math.min(mChildHeight, height);

            for (int i = 0; i < features.size(); i++) {
                TagView tagView = tagContainerLayout.getTagView(i);
                tagView.setTagTextColor(colors[i]);
                tagView.setTagBorderColor(colors[i]);
            }
        }
    }

    public String setAvgPriceSpan(Context ctx, String priceStr, String unit) {
        SpannableStringBuilder word = new SpannableStringBuilder(priceStr + unit);
        word.setSpan(new StyleSpan(Typeface.BOLD), 0, priceStr.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        word.setSpan(new AbsoluteSizeSpan(DensityUtil.dip2px(ctx, 11)), priceStr.length(), word.length(), Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        return word.toString();
    }
}
