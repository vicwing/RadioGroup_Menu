package com.example.cdj.myapplication.mainfunction.function4;

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
import com.example.cdj.myapplication.adapter.adapterhelper.QuickAdapter;
import com.example.cdj.myapplication.cusview.segmentcontrol.SegmentControl;
import com.example.cdj.myapplication.loadmore.LoadMoreListViewContainer;
import com.orhanobut.logger.Logger;
import com.zhy.http.okhttp.OkHttpUtils;

import java.util.ArrayList;
import java.util.List;

import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import okhttp3.Call;

/**
 * Created by cdj on 2016/5/6.
 */
public class Fragment4 extends Fragment {


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
//        return inflater.inflate(R.layout.content_main3, container, false);
        // set up views
        final View layout = inflater.inflate(R.layout.content_main3, null);
        initSegmentControl(layout);

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
}