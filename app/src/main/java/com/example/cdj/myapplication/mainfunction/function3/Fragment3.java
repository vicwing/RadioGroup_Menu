package com.example.cdj.myapplication.mainfunction.function3;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.cdj.myapplication.Bean.SecListItemEntity;
import com.example.cdj.myapplication.R;
import com.example.cdj.myapplication.baseadapter.adapterhelper.QuickAdapter;
import com.example.cdj.myapplication.loadmore.LoadMoreContainer;
import com.example.cdj.myapplication.loadmore.LoadMoreHandler;
import com.example.cdj.myapplication.loadmore.LoadMoreListViewContainer;
import com.example.cdj.myapplication.loadmore.LoadMoreUIHandler;
import com.example.cdj.myapplication.widget.segmentcontrol.SegmentControl;
import com.orhanobut.logger.Logger;
import com.zhy.http.okhttp.OkHttpUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import okhttp3.CacheControl;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by cdj onCallBackData 2016/5/6.
 */
public class Fragment3 extends Fragment {
    //    public static  String Url = "http://10.251.93.254:8010/appapi/v4_3/room/list?bizType=SALE&dataSource=SHENZHEN&pageSize=20&currentPage=1&s=SHENZHEN";
    // 名字根据实际需求进行更改
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    @BindView(R.id.segment_control)
    SegmentControl mSegmentControl;

    @BindView(R.id.recycleview)
    RecyclerView recycleview;
    Unbinder unbinder;

    // 这里的参数只是一个举例可以根据需求更改
    private String mParam1;
    private String mParam2;

    //    private PtrClassicFrameLayout mPtrFrameLayout;
    private ListView mListView;
    private QuickAdapter<SecListItemEntity> mAdapter;
    private List<SecListItemEntity> mSecListItemEntities = new ArrayList<SecListItemEntity>();
    private LoadMoreListViewContainer loadMoreListViewContainer;
    private int pageCount = 0;

    /**
     * 通过工厂方法来创建Fragment实例
     * 同时给Fragment来提供参数来使用
     *
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
//        return inflater.inflate(R.layout.content_fragment3, container, false);
        // set up views
        final View layout = inflater.inflate(R.layout.content_fragment3, null);
        unbinder = ButterKnife.bind(this, layout);
        initSegmentControl(layout);
        return layout;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("A");
        arrayList.add("b");
        arrayList.add("c");
        arrayList.add("d");
        arrayList.add("e");
        arrayList.add("f");
        arrayList.add("h");
        arrayList.add("i");
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL);
        MySimpledapter mySimpledapter = new MySimpledapter(arrayList);
        recycleview.addItemDecoration(dividerItemDecoration);
        recycleview.setLayoutManager(linearLayoutManager);
        recycleview.setAdapter(mySimpledapter);
    }


    private void initSegmentControl(View layout) {
        mSegmentControl.setOnSegmentControlClickListener(new SegmentControl.OnSegmentControlClickListener() {
            @Override
            public void onSegmentControlClick(int index) {
            }
        });
//        mSegmentControl.setBackgroundDrawableColor(getResources().getColor(R.color.black));
//        mSegmentControl.setSelectdTextColor(getResources().getColor(R.color.black));
//        mSegmentControl.setDefaultTextColor(getResources().getColor(R.color.white));
//        mSegmentControl.setSelectedDrawableColor(getResources().getColor(R.color.white));
    }

    private int currentPage = 1;
    public static String Url = "http://shenzhen.qfang.com/appapi/v4_5/room/list?bizType=SALE&dataSource=SHENZHEN&pageSize=10";

    private void requestUpdate(final String currentPageStr) {
        String httpUrl = Url + "&currentPage=" + currentPageStr;
//        Logger.d("下拉刷新控件啦......currentPage  "+httpUrl);
//        OkHttpUtils
//                .get()//
//                .url(httpUrl)
//                .addHeader("Cache-Control","no-cache")
//                .build()
//                .execute(new SecListItemBeanCallback() {
//
//                    @Override
//                    public void onError(Call call, Exception e, int id) {
//                        mPtrFrameLayout.refreshComplete();
//                    }
//
//                    @Override
//                    public void onResponse(SecListBean response, int id) {
//                        mPtrFrameLayout.refreshComplete();
////                        mSecListItemEntities.addAll(response.getResult().getList());
//                        mAdapter.addAll(response.getResult().getList());
////                        Logger.d("response  "+response.getMessage()+"  count  "+mAdapter.getCount());
//                        pageCount = response.getResult().getPageCount();
//                        if (currentPage <= pageCount) {
//                            loadMoreListViewContainer.loadMoreFinish(false, true);
//                        }
//                    }
//                });

//        getCacheRequset(httpUrl);

    }

    /**
     * 实现了缓存的okhttp
     *
     * @param httpUrl
     */
    private void getCacheRequset(String httpUrl) {
        Request request = new Request.Builder().url(httpUrl).cacheControl(new CacheControl.Builder()
                .maxAge(5, TimeUnit.SECONDS)
                .maxStale(5, TimeUnit.SECONDS).build())
                .build();
        OkHttpClient okHttpClient = OkHttpUtils.getInstance().getOkHttpClient();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Logger.d("onFailure  ........");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String s = response.body().string();
                Logger.d("response " + s);
            }
        });
    }

    private int pageSize = 10;

    /**
     * 自定义loadmore footview
     *
     * @param loadMoreListViewContainer
     */
    private void setLoadMoreFootView(final LoadMoreListViewContainer loadMoreListViewContainer) {
//        loadMoreListViewContainer.setLoadMoreView( LayoutInflater.from(getContext()).inflate(R.layout.item_textview, null));
        loadMoreListViewContainer.setLoadMoreHandler(new LoadMoreHandler() {
            @Override
            public void onLoadMore(LoadMoreContainer loadMoreContainer) {
                Logger.d("自定义footview   加载更多...........");
                loadMoreListViewContainer.setLoadMoreView(LayoutInflater.from(getContext()).inflate(R.layout.item_textview, null));
                loadMoreContainer.loadMoreFinish(true, true);
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
     *
     * @param loadMoreListViewContainer
     */
    private void setLoadMoreDefaultFootView(final LoadMoreListViewContainer loadMoreListViewContainer) {
        // load more container
        //设定view可以加载更多
        loadMoreListViewContainer.useDefaultHeader();
        loadMoreListViewContainer.setAutoLoadMore(true);
        loadMoreListViewContainer.setShowLoadingForFirstPage(true);
        loadMoreListViewContainer.setLoadMoreHandler(new LoadMoreHandler() {
            @Override
            public void onLoadMore(LoadMoreContainer loadMoreContainer) {
                currentPage++;
                Logger.i("LoadMoreHandler  加载更多..............currentPage " + currentPage);
                if (currentPage <= pageCount) {
                    requestUpdate(String.valueOf(currentPage));
                } else {
                    loadMoreListViewContainer.loadMoreFinish(true, false);
                }
            }
        });
    }


    private void testThread() {
        new Thread() {
            @Override
            public void run() {
                super.run();
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
//                                simpleAdapter.addAll("","","","","","","","","","");
//                        for (int i = 0; i <10 ; i++) {
//                            mSecListItemEntities.add("i  "+i+"number");
//                        }
                        mAdapter.addAll(mSecListItemEntities);
                        loadMoreListViewContainer.loadMoreFinish(false, true);
                    }
                });
            }
        }.start();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    public static class MySimpledapter extends BaseQuickAdapter<String, BaseViewHolder> {

        public MySimpledapter(@Nullable List data) {
            super(R.layout.item_list_simple_text, data);
        }


        @Override
        protected void convert(BaseViewHolder helper, String item) {
            helper.setText(R.id.tv_avg_price, "item " + helper.getAdapterPosition());
        }
    }
}