package com.example.cdj.myapplication.mainfunction.function4;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;

import com.apkfuns.logutils.LogUtils;
import com.example.cdj.myapplication.Bean.SecListBean;
import com.example.cdj.myapplication.R;
import com.example.cdj.myapplication.RootViewActivity;
import com.example.cdj.myapplication.SecListItemBeanCallback;
import com.example.cdj.myapplication.mainfunction.caculate.MortgageCaculatorAcitivity;
import com.example.cdj.myapplication.mainfunction.taxcaculator.TaxCaculatorActivity;
import com.example.cdj.myapplication.utils.DateUtil;
import com.zhy.http.okhttp.OkHttpUtils;

import butterknife.Bind;
import butterknife.ButterKnife;
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

    @Bind(R.id.tv_go)
    TextView mTextGo;

    @Bind(R.id.tv_text_end)
    TextView mTextEnd;
    @Bind(R.id.btn_login)
    Button btnLogin;

    // 这里的参数只是一个举例可以根据需求更改
    private String mParam1;
    private String mParam2;

    private int pageCount = 3;
    private TextView tv_time;

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
                Intent intent = new Intent(getActivity(), MortgageCaculatorAcitivity.class);
                startActivity(intent);
            }
        });
        mBtnStartTaxCaculator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), TaxCaculatorActivity.class);
                startActivity(intent);
            }
        });
//        initSegmentControl(layout);
        mTextGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                mTvTextgo.scrollTo();
                scrollTo(layout);
            }
        });
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), RootViewActivity.class);
                startActivity(intent);
            }
        });
        tv_time = (TextView) layout.findViewById(R.id.tv_time);
        tv_time.setText(DateUtil.timeStamp2Date(System.currentTimeMillis(),DateUtil.DateStyle.YYYY_MM_DD_HH_MM_SS.getValue())+"");
        LogUtils.d("222222222222222222222");
        return layout;
    }

    /**
     * 滚动的到指定的,view的位置
     *
     * @param layout
     */
    private void scrollTo(View layout) {
        //        final boolean scrollDown = getIntent().getBooleanExtra(SCROLL_DOWN, false);
        final ScrollView mRootScrollView = (ScrollView) layout.findViewById(R.id.scrollView);
        mRootScrollView.post(new Runnable() {
            @Override
            public void run() {
                //To change body of implemented methods use File | Settings | File Templates.
//                    mRootScrollView.fullScroll(ScrollView.FOCUS_DOWN);
                int[] location = new int[2];
                mTextEnd.getLocationOnScreen(location);
                int offset = location[1] - mRootScrollView.getMeasuredHeight();
                if (offset < 0) {
                    offset = 0;
                }
                mRootScrollView.smoothScrollTo(0, offset);
            }
        });
    }

    private void requestUpdate(final String currentPageStr) {
        OkHttpUtils
                .post()//
                .url(Url)//
                .addParams("currentPage", currentPageStr)
                .build()//
                .execute(new SecListItemBeanCallback() {
                    @Override
                    public void onError(Call call, Exception e) {
                    }

                    @Override
                    public void onResponse(SecListBean response) {

                    }
                });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}