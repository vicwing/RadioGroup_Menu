package com.example.cdj.myapplication.mainfunction.function4;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import com.example.cdj.myapplication.Bean.SecListBean;
import com.example.cdj.myapplication.Bean.SecListItemEntity;
import com.example.cdj.myapplication.R;
import com.example.cdj.myapplication.SecListItemBeanCallback;
import com.example.cdj.myapplication.adapter.adapterhelper.QuickAdapter;
import com.example.cdj.myapplication.cusview.segmentcontrol.SegmentControl;
import com.example.cdj.myapplication.loadmore.LoadMoreContainer;
import com.example.cdj.myapplication.loadmore.LoadMoreHandler;
import com.example.cdj.myapplication.loadmore.LoadMoreListViewContainer;
import com.example.cdj.myapplication.loadmore.LoadMoreUIHandler;
import com.example.cdj.myapplication.mainfunction.taxcaculator.TaxCaculatorActivity;
import com.orhanobut.logger.Logger;
import com.zhy.http.okhttp.OkHttpUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import okhttp3.Call;

/**
 * Created by cdj onCallBackData 2016/5/6.
 */
public class Fragment4 extends Fragment {


    //    public static  String Url = "http://10.251.93.254:8010/appapi/v4_3/room/list?bizType=SALE&dataSource=SHENZHEN&pageSize=20&currentPage=1&s=SHENZHEN";
    public static String Url = "http://10.251.93.254:8010/appapi/v4_3/room/list?bizType=SALE&dataSource=SHENZHEN&pageSize=10";

    // 名字根据实际需求进行更改
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    @Bind(R.id.btn_start_caculator)
    Button mBtnStartCaculator;
    @Bind(R.id.btn_start_tax_caculator)
    Button mBtnStartTaxCaculator;

    // 这里的参数只是一个举例可以根据需求更改
    private String mParam1;
    private String mParam2;

    private PtrClassicFrameLayout mPtrFrameLayout;
    private ListView mListView;
    private QuickAdapter<SecListItemEntity> mAdapter;
    private List<SecListItemEntity> mSecListItemEntities = new ArrayList<SecListItemEntity>();
    private LoadMoreListViewContainer loadMoreListViewContainer;
    private SegmentControl mSegmentControl;
    private int pageCount = 3;

    /**
     * 通过工厂方法来创建Fragment实例
     * 同时给Fragment来提供参数来使用
     *
     * @return Master_Fragment的实例.
     */
    public static Fragment4 newInstance(String param1, String param2) {
        Fragment4 fragment = new Fragment4();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public Fragment4() {
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
        final View layout = inflater.inflate(R.layout.content_main4, null);
        ButterKnife.bind(this, layout);
        mBtnStartCaculator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(MortgageCaculatorAcitivity.class);
//                startActivity(intent);
            }
        });
        mBtnStartTaxCaculator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),TaxCaculatorActivity.class);
                startActivity(intent);
            }
        });
//        initSegmentControl(layout);

        return layout;
    }

    private void initSegmentControl(View layout) {
        mSegmentControl = (SegmentControl) layout.findViewById(R.id.segment_control);
        mSegmentControl.setOnSegmentControlClickListener(new SegmentControl.OnSegmentControlClickListener() {
            @Override
            public void onSegmentControlClick(int index) {
                Log.d("tag", "index" + index);
            }
        });
//        mSegmentControl.setBackgroundDrawableColor(getResources().getColor(R.color.black));
//        mSegmentControl.setSelectdTextColor(getResources().getColor(R.color.black));
//        mSegmentControl.setDefaultTextColor(getResources().getColor(R.color.white));
//        mSegmentControl.setSelectedDrawableColor(getResources().getColor(R.color.white));
    }

    private int currentPage = 1;

    private void requestUpdate(final String currentPageStr) {
        Logger.d("下拉刷新控件啦......currentPage  " + currentPageStr);
        OkHttpUtils
                .post()//
                .url(Url)//
                .addParams("currentPage", currentPageStr)
                .build()//
                .execute(new SecListItemBeanCallback() {
                    @Override
                    public void onError(Call call, Exception e) {
                        mPtrFrameLayout.refreshComplete();
                    }

                    @Override
                    public void onResponse(SecListBean response) {

                        mPtrFrameLayout.refreshComplete();
//                        mSecListItemEntities.addAll(response.getResult().getList());
                        mAdapter.addAll(response.getResult().getList());
                        Logger.d("response  " + response.getMessage() + "  count  " + mAdapter.getCount());
//                        pageCount = response.getResult().getPageCount();
                        if (currentPage <= pageCount) {
                            loadMoreListViewContainer.loadMoreFinish(false, true);
                        }
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
        loadMoreListViewContainer.useDefaultHeader();
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
        ButterKnife.unbind(this);
    }
}