package com.sunfusheng.StickyHeaderListView.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.apkfuns.logutils.LogUtils;
import com.sunfusheng.StickyHeaderListView.R;
import com.sunfusheng.StickyHeaderListView.loadmore.LoadMoreContainer;
import com.sunfusheng.StickyHeaderListView.loadmore.LoadMoreHandler;
import com.sunfusheng.StickyHeaderListView.loadmore.LoadMoreListViewContainer;
import com.sunfusheng.StickyHeaderListView.view.QfangFrameLayout;

import cc.easyandroid.customview.progress.core.SimpleProgressClickListener;
import cc.easyandroid.easyui.utils.EasyViewUtil;
import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;

/**
 * Created by vic on 2016/6/27.
 */
public abstract class BasePtrPullToResfrshActivity extends AppCompatActivity {

    protected PtrClassicFrameLayout mPtrFrameLayout;
    protected QfangFrameLayout qfangframelayout;
    protected LoadMoreListViewContainer loadMoreListViewContainer;
//    protected Listview
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(getlayoutId());
    }
    protected abstract int getlayoutId() ;

    protected abstract View getListView();

    protected void initPtrFrame() {
        mPtrFrameLayout = (PtrClassicFrameLayout) findViewById(R.id.load_more_list_view_ptr_frame);
//        mPtrFrameLayout.setHeaderView(getLayoutInflater().inflate(R.layout.smoothlistview_header,null))
// ;
//        MaterialHeader materialHeader = new MaterialHeader(this);
//        MaterialProgressDrawable materialHeader = new MaterialProgressDrawable(this);
//        mPtrFrameLayout.setHeaderView(materialHeader);
//        mPtrFrameLayout.addPtrUIHandler(materialHeader);
//        materialHeader.setPtrFrameLayout(mPtrFrameLayout);
        mPtrFrameLayout.setLoadingMinTime(1000);
        mPtrFrameLayout.setLastUpdateTimeKey("vievivivivivi");
        // the following are default settings
        mPtrFrameLayout.setResistance(1.7f);
        mPtrFrameLayout.setRatioOfHeaderHeightToRefresh(1.2f);
        mPtrFrameLayout.setDurationToClose(200);
        mPtrFrameLayout.setDurationToCloseHeader(1000);
        // default is false
        mPtrFrameLayout.setPullToRefresh(false);
        // default is true
        mPtrFrameLayout.setKeepHeaderWhenRefresh(true);
        mPtrFrameLayout.setPtrHandler(new PtrHandler() {
            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                LogUtils.d("checkCanDoRefresh  ");
                // here check list view, not content.
                return PtrDefaultHandler.checkContentCanBePulledDown(frame, getListView(), header);
            }

            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                LogUtils.i("onRefreshBegin  ");
//                currentPage =1;
//                loadMoreListViewContainer.loadMoreFinish(true,false);
//                loadMoreListViewContainer.setShowLoadingForFirstPage(true);
//                requestUpdate(String.valueOf(currentPage));
            }

        });

        loadMoreListViewContainer = (LoadMoreListViewContainer) findViewById(R.id.load_more_list_view_container);
        setLoadMoreDefaultFootView(loadMoreListViewContainer);
        //设定view可以加载更多
        loadMoreListViewContainer.setShowLoadingForFirstPage(true);

        qfangframelayout = EasyViewUtil.findViewById(this, R.id.qfangframelayout);
        qfangframelayout.showLoadingView();
        qfangframelayout.setKProgressClickListener(new SimpleProgressClickListener() {
            @Override
            public void onErrorViewClick() {
                super.onErrorViewClick();
                LogUtils.d("qfangframelayout   onError Click.....................");
//                presenter.execute();

            }
        });
    }




    /**
     * 设置默认的加载更多.
     * @param loadMoreListViewContainer
     */
    private void setLoadMoreDefaultFootView(final LoadMoreListViewContainer loadMoreListViewContainer) {
        // load more container
        loadMoreListViewContainer.useDefaultFooter();
        loadMoreListViewContainer.setLoadMoreHandler(new LoadMoreHandler() {
            @Override
            public void onLoadMore(LoadMoreContainer loadMoreContainer) {
//                currentPage++;
//                Logger.i("LoadMoreHandler  加载更多..............currentPage "+currentPage);
//                if (currentPage<=pageCount){
//                    requestUpdate(String.valueOf(currentPage));
//                } else {
//                    loadMoreListViewContainer.loadMoreFinish(true,false);
//                }
            }
        });
    }
}
