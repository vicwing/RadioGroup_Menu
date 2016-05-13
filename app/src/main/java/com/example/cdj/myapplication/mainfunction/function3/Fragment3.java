package com.example.cdj.myapplication.mainfunction.function3;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.cdj.myapplication.Bean.SecListBean;
import com.example.cdj.myapplication.Bean.SecListItemEntity;
import com.example.cdj.myapplication.R;
import com.example.cdj.myapplication.SecListItemBeanCallback;
import com.example.cdj.myapplication.adapter.SecListItemAdapter;
import com.example.cdj.myapplication.adapter.adapterhelper.QuickAdapter;
import com.example.cdj.myapplication.cusview.segmentcontrol.SegmentControl;
import com.example.cdj.myapplication.loadmore.LoadMoreContainer;
import com.example.cdj.myapplication.loadmore.LoadMoreHandler;
import com.example.cdj.myapplication.loadmore.LoadMoreListViewContainer;
import com.example.cdj.myapplication.loadmore.LoadMoreUIHandler;
import com.orhanobut.logger.Logger;
import com.zhy.http.okhttp.OkHttpUtils;

import java.util.ArrayList;
import java.util.List;

import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;
import okhttp3.Call;

/**
 * Created by cdj on 2016/5/6.
 */
public class Fragment3 extends Fragment {


//    public static  String Url = "http://10.251.93.254:8010/appapi/v4_3/room/list?bizType=SALE&dataSource=SHENZHEN&pageSize=20&currentPage=1&s=SHENZHEN";
    public static  String Url = "http://10.251.93.254:8010/appapi/v4_3/room/list?bizType=SALE&dataSource=SHENZHEN&pageSize=10";

    // 名字根据实际需求进行更改
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // 这里的参数只是一个举例可以根据需求更改
    private String mParam1;
    private String mParam2;

    private PtrClassicFrameLayout mPtrFrameLayout;
    private ListView mListView;
    private QuickAdapter<SecListItemEntity> mAdapter;
    private List<SecListItemEntity> mSecListItemEntities  = new ArrayList<SecListItemEntity>();
    private LoadMoreListViewContainer loadMoreListViewContainer;
    private SegmentControl mSegmentControl;

    /**
     * 通过工厂方法来创建Fragment实例
     * 同时给Fragment来提供参数来使用
     * @return Master_Fragment的实例.
     */
    public static Fragment3 newInstance(String param1, String param2) {
        Fragment3 fragment = new Fragment3();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public Fragment3() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
//        return inflater.inflate(R.layout.content_main3, container, false);
        // set up views
        final View layout = inflater.inflate(R.layout.content_main3, null);

        initSegmentControl(layout);



//        // pull to refresh
        mPtrFrameLayout = (PtrClassicFrameLayout) layout.findViewById(R.id.load_more_list_view_ptr_frame);

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
                Logger.d("checkCanDoRefresh  ");
                // here check list view, not content.
                return PtrDefaultHandler.checkContentCanBePulledDown(frame, mListView, header);
            }

            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                Logger.i("onRefreshBegin  ");
                requestUpdate();
            }

        });
//        // list view
        mListView = (ListView) layout.findViewById(R.id.load_more_small_image_list_view);

        // header place holder
//        View headerMarginView = new View(getContext());
//        headerMarginView.setLayoutParams(new AbsListView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, LocalDisplay.dp2px(20)));
//        mListView.addHeaderView(headerMarginView);

       loadMoreListViewContainer = (LoadMoreListViewContainer) layout.findViewById(R.id.load_more_list_view_container);
        setLoadMoreDefaultFootView(loadMoreListViewContainer);
//        setLoadMoreFootView(loadMoreListViewContainer);
        //设定view可以加载更多

//        mAdapter = new SecListItemAdapter(getActivity(), mSecListItemEntities);
        mAdapter = new SecListItemAdapter(getActivity(), R.layout.item_list_secondlist);
        mListView.setAdapter(mAdapter);

        mPtrFrameLayout.setPullToRefresh(true);

        loadMoreListViewContainer.setAutoLoadMore(true);
        loadMoreListViewContainer.setShowLoadingForFirstPage(true);
        requestUpdate();
        return layout;
    }

    private void initSegmentControl(View layout) {
        mSegmentControl = (SegmentControl)layout.findViewById(R.id.segment_control);
        mSegmentControl.setOnSegmentControlClickListener(new SegmentControl.OnSegmentControlClickListener() {
            @Override
            public void onSegmentControlClick(int index) {
                Log.d("tag","index"+index);
            }
        });
//        mSegmentControl.setBackgroundDrawableColor(getResources().getColor(R.color.black));
//        mSegmentControl.setSelectdTextColor(getResources().getColor(R.color.black));
//        mSegmentControl.setDefaultTextColor(getResources().getColor(R.color.white));
//        mSegmentControl.setSelectedDrawableColor(getResources().getColor(R.color.white));
    }


    private void requestUpdate() {
        Logger.d("下拉刷新控件啦......");
        OkHttpUtils
                .post()//
                .url(Url)//
                .addParams("currentPage", "1")//
                .build()//
                .execute(new  SecListItemBeanCallback() {
                    @Override
                    public void onError(Call call, Exception e) {
                        mPtrFrameLayout.refreshComplete();
                    }

                    @Override
                    public void onResponse(SecListBean response) {
                        mPtrFrameLayout.refreshComplete();
                        mSecListItemEntities = response.getResult().getList();

//                        mAdapter = new SecListItemAdapter(getActivity(), R.layout.item_list_secondlist);
//                        mListView.setAdapter(mAdapter);
                        mAdapter.addAll(mSecListItemEntities);
                        Logger.d("response  "+response.getMessage()+"  count  "+mAdapter.getCount());
                    }
                });
    }

    /**
     * 自定义loadmore footview
     * @param loadMoreListViewContainer
     */
    private void setLoadMoreFootView(final LoadMoreListViewContainer loadMoreListViewContainer) {
//        loadMoreListViewContainer.setLoadMoreView( LayoutInflater.from(getContext()).inflate(R.layout.item_textview, null));
        loadMoreListViewContainer.setLoadMoreHandler(new LoadMoreHandler() {
            @Override
            public void onLoadMore(LoadMoreContainer loadMoreContainer) {
                Logger.d("自定义footview   加载更多...........");
                loadMoreListViewContainer.setLoadMoreView( LayoutInflater.from(getContext()).inflate(R.layout.item_textview, null));
                loadMoreContainer.loadMoreFinish(true,true);
            }
        });
        loadMoreListViewContainer.setLoadMoreUIHandler(new LoadMoreUIHandler() {
            @Override
            public void onLoading(LoadMoreContainer container) {
                Logger.i("LoadMoreUIHandler   onLoading...");
            }

            @Override
            public void onLoadFinish(LoadMoreContainer container, boolean empty, boolean hasMore) {
                Logger.i("LoadMoreUIHandler   onLoadFinish...");
            }

            @Override
            public void onWaitToLoadMore(LoadMoreContainer container) {
                Logger.i("LoadMoreUIHandler   onWaitToLoadMore...");
            }

            @Override
            public void onLoadError(LoadMoreContainer container, int errorCode, String errorMessage) {
                Logger.i("LoadMoreUIHandler   onLoadError...");
            }
        });
    }

    /**
     * 设置默认的加载更多.
     * @param loadMoreListViewContainer
     */
    private void setLoadMoreDefaultFootView(final LoadMoreListViewContainer loadMoreListViewContainer) {
        // load more container
        loadMoreListViewContainer.useDefaultHeader();
        loadMoreListViewContainer.setLoadMoreHandler(new LoadMoreHandler() {
            @Override
            public void onLoadMore(LoadMoreContainer loadMoreContainer) {
                Logger.i("LoadMoreHandler  加载更多..............");
//                testThread();
//                loadMoreContainer.loadMoreFinish(true,false);
            }
        });
//        loadMoreListViewContainer.loadMoreFinish(true,true);
    }


    private void testThread() {
        new Thread(){
            @Override
            public void run() {
                super.run();
                getActivity(). runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
//                                simpleAdapter.addAll("","","","","","","","","","");
//                        for (int i = 0; i <10 ; i++) {
//                            mSecListItemEntities.add("i  "+i+"number");
//                        }
                        mAdapter.addAll(mSecListItemEntities);
                        loadMoreListViewContainer.loadMoreFinish(false,true);
                    }
                });
            }
        }.start();
    }
}