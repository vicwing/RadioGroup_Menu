package com.example.cdj.myapplication.mainfunction.function3;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.cdj.myapplication.R;

//import in.srain.cube.views.ptr.PtrDefaultHandler;
//import in.srain.cube.views.ptr.PtrFrameLayout;
//import in.srain.cube.views.ptr.PtrHandler;

/**
 * Created by cdj on 2016/5/6.
 */
public class Fragment3 extends Fragment {
    // 名字根据实际需求进行更改
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // 这里的参数只是一个举例可以根据需求更改
    private String mParam1;
    private String mParam2;

//    private PtrFrameLayout mPtrFrameLayout;
    private ListView mListView;

    /**
     * 通过工厂方法来创建Fragment实例
     * 同时给Fragment来提供参数来使用
     *
     * @param param1 参数1.
     * @param param2 参数2.
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
        final View view = inflater.inflate(R.layout.content_main3, null);
//        // pull to refresh
//        mPtrFrameLayout = (PtrFrameLayout) view.findViewById(R.id.load_more_list_view_ptr_frame);
//
//        mPtrFrameLayout.setLoadingMinTime(1000);
//
//        mPtrFrameLayout.setPtrHandler(new PtrHandler() {
//            @Override
//            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
//
//                Logger.d("checkCanDoRefresh  ");
//                // here check list view, not content.
//                KLog.d(" 下拉刷新");
//                return PtrDefaultHandler.checkContentCanBePulledDown(frame, mListView, header);
//            }
//
//            @Override
//            public void onRefreshBegin(PtrFrameLayout frame) {
////                mDataModel.queryFirstPage();
//
//                Logger.d("onRefreshBegin  ");
//            }
//        });
//
//        // list view
//        mListView = (ListView) view.findViewById(R.id.load_more_small_image_list_view);
//
//        // header place holder
//        View headerMarginView = new View(getContext());
//        headerMarginView.setLayoutParams(new AbsListView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, LocalDisplay.dp2px(20)));
//        mListView.addHeaderView(headerMarginView);

        // load more container
//        final LoadMoreListViewContainer loadMoreListViewContainer = (LoadMoreListViewContainer) view.findViewById(R.id.load_more_list_view_container);
//        loadMoreListViewContainer.useDefaultHeader();
//
//        loadMoreListViewContainer.setLoadMoreHandler(new LoadMoreHandler() {
//            @Override
//            public void onLoadMore(LoadMoreContainer loadMoreContainer) {
//                Logger.d("加载更多..............");
////                mDataModel.queryNextPage();
//            }
//        });


//        // process data
//        EventCenter.bindContainerAndHandler(this, new DemoSimpleEventHandler() {
//
//            public void onEvent(ImageListDataEvent event) {
//
//                // ptr
//                mPtrFrameLayout.refreshComplete();
//
//                // load more
//                loadMoreListViewContainer.loadMoreFinish(mDataModel.getListPageInfo().isEmpty(), mDataModel.getListPageInfo().hasMore());
//
//                mAdapter.notifyDataSetChanged();
//            }
//
//            public void onEvent(ErrorMessageDataEvent event) {
//                loadMoreListViewContainer.loadMoreError(0, event.message);
//            }
//
//        }).tryToRegisterIfNot();








//        ArrayList<String> list = new ArrayList<>();
//        for (int i = 0; i < 50; i++) {
//            list.add(i,i+"   number");
//        }
//        // binding view and data
//        mListView.setAdapter(new QuickAdapter<String>(getActivity(),R.layout.item_textview,list) {
//            @Override
//            protected void convert(BaseAdapterHelper helper, String item) {
//                helper.setText(R.id.calculatorBtn,item);
//            }
//        });
//
//        mPtrFrameLayout.setPullToRefresh(true);
        return view;
    }

}