package com.sunfusheng.StickyHeaderListView.view;

import android.app.Activity;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.sunfusheng.StickyHeaderListView.R;
import com.sunfusheng.StickyHeaderListView.adapter.HeaderHorizontalBannerAdapter;
import com.sunfusheng.StickyHeaderListView.model.CityItem;
import com.sunfusheng.StickyHeaderListView.model.NewHouseHomeBean;
import com.sunfusheng.StickyHeaderListView.util.DensityUtil;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by sunfusheng on 16/4/20.
 */
public class HeaderHorzontalListBannerView extends HeaderViewInterface<List<NewHouseHomeBean.ResultBean.BrickListBean> > {


    @Bind(R.id.horizontal_grid)
    GridView horGridView;
    private Activity context;

    public HeaderHorzontalListBannerView(Activity context) {
        super(context);
        this.context = context;
    }

    @Override
    protected void getView(List<NewHouseHomeBean.ResultBean.BrickListBean> list, ListView listView) {
        View view = mInflate.inflate(R.layout.header_horzontal_list_banner, listView, false);
        ButterKnife.bind(this, view);

        dealWithTheView(list);
        listView.addHeaderView(view);
    }
    private void dealWithTheView(List<NewHouseHomeBean.ResultBean.BrickListBean> list) {
        HeaderHorizontalBannerAdapter adapter = new HeaderHorizontalBannerAdapter(mContext, list);
        int size = list.size();
        int length = 100;

        DisplayMetrics dm = new DisplayMetrics();
        context.  getWindowManager().getDefaultDisplay().getMetrics(dm);
        float density = dm.density;
        int gridviewWidth = (int) (size * (length + 4) * density);
        int itemWidth = (int) (length * density);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(gridviewWidth, LinearLayout.LayoutParams.FILL_PARENT);
//        params.leftMargin=DensityUtil.dip2px(context,15);
        horGridView.setLayoutParams(params); // 重点
        horGridView.setColumnWidth(itemWidth); // 重点
        horGridView.setHorizontalSpacing(DensityUtil.dip2px(context,15)); // 间距
        horGridView.setStretchMode(GridView.NO_STRETCH);
        horGridView.setNumColumns(size); // 重点
        horGridView.setAdapter(adapter);
    }
}
