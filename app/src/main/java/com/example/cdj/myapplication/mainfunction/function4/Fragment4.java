package com.example.cdj.myapplication.mainfunction.function4;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

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

    public final static String HOUSE_STYLE = "houseStyle";
    public final static String TOTAL_PRICE = "totalPrice";


    // 名字根据实际需求进行更改
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    TextView mTvHouseStyle;
    TextView mTvTotalPrice;

    // 这里的参数只是一个举例可以根据需求更改
    private String mParam1;
    private String mParam2;

    private PtrClassicFrameLayout mPtrFrameLayout;
    private ListView mListView;
    private QuickAdapter<SecListItemEntity> mAdapter;
    private List<SecListItemEntity> mSecListItemEntities = new ArrayList<SecListItemEntity>();
    private LoadMoreListViewContainer loadMoreListViewContainer;
    private SegmentControl mSegmentControl;
    private FragmentActivity mActivity;

    private int defaultPrice = 100;//默认贷款额
    private int totalPrice = defaultPrice;//贷款总额

    private float mIntrestRate = 4.9f;
    private int mLoanTerm = 30;

    private ArrayList<Fragment> fragmentArrayList;
    private Fragment mCurrentFrgment;
    private int currentIndex = 0;
    private Button btn_do_caculate;
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
        initSegmentControl(layout);
        init(layout);
        initFragment();
        return layout;
    }

    private void init(View layout) {
        mActivity = getActivity();
        String houseStyleStr = mActivity.getIntent().getStringExtra(HOUSE_STYLE);
        if (!TextUtils.isEmpty(houseStyleStr)) {

            layout.findViewById(R.id.rl_houses_style).setVisibility(View.VISIBLE);

            totalPrice = mActivity.getIntent().getIntExtra(TOTAL_PRICE, defaultPrice);

            mTvHouseStyle = (TextView) layout.findViewById(R.id.tv_house_style);

            mTvHouseStyle.setText("宏宏大厦  3房1厅 112m2");

            mTvTotalPrice = (TextView) layout.findViewById(R.id.tv_total_price);
            mTvTotalPrice.setText(totalPrice);
        } else {
            layout.findViewById(R.id.rl_houses_style).setVisibility(View.GONE);
        }

        btn_do_caculate = (Button) layout.findViewById(R.id.btn_do_caculate);
        btn_do_caculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                caculateData();
            }
        });
    }

    /**
     * 计算房贷结果
     */
    private void caculateData() {
        Logger.d("贷款总额  "+"  利率  "+" 公积金利率 "+ "贷款期限  ");

    }


    private void initSegmentControl(View layout) {
        mSegmentControl = (SegmentControl) layout.findViewById(R.id.segment_control);
        mSegmentControl.setOnSegmentControlClickListener(new SegmentControl.OnSegmentControlClickListener() {
            @Override
            public void onSegmentControlClick(int index) {
                changeFragment(index);
                Logger.d( "index" + index+"   fragmentStackCount  "+  getFragmentManager().getBackStackEntryCount());
            }
        });
//        mSegmentControl.setBackgroundDrawableColor(getResources().getColor(R.color.black));
        mSegmentControl.setSelectdTextColor(getResources().getColor(R.color.black_33333));
        mSegmentControl.setDefaultTextColor(getResources().getColor(R.color.white));
//        mSegmentControl.setSelectedDrawableColor(getResources().getColor(R.color.white));

    }


    private void initFragment() {
        mSegmentControl.setSelectedIndex(0);
        fragmentArrayList = new ArrayList<Fragment>();
        fragmentArrayList.add(CommercialLoanFragment.newInstance("新添加",null));
        fragmentArrayList.add(AccumulationFunLoanFragment.newInstance("新添加",null));
        fragmentArrayList.add(CombinedLoanFragment.newInstance("新添加",null));
        changeFragment(0);
    }

    private  void changeFragment(int index){
        currentIndex = index;
        FragmentTransaction ft = getChildFragmentManager().beginTransaction();
        //判断当前的Fragment是否为空，不为空则隐藏
        if (null != mCurrentFrgment) {
            ft.hide(mCurrentFrgment);
        }
        //先根据Tag从FragmentTransaction事物获取之前添加的Fragment
        Fragment fragment = getChildFragmentManager().findFragmentByTag(fragmentArrayList.get(currentIndex).getClass().getName());

        if (null == fragment) {
            //如fragment为空，则之前未添加此Fragment。便从集合中取出
            fragment = fragmentArrayList.get(currentIndex);
        }
        mCurrentFrgment = fragment;

        //判断此Fragment是否已经添加到FragmentTransaction事物中
        if (!fragment.isAdded()) {
            ft.add(R.id.frame_caculate, fragment, fragment.getClass().getName());
        } else {
            ft.show(fragment);
        }
        ft.commit();
    }




    private void requestUpdate() {
        OkHttpUtils
                .post()//
                .url("")//
                .addParams("currentPage", "1")//
                .build()//
                .execute(new SecListItemBeanCallback() {
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
                        Logger.d("response  " + response.getMessage() + "  count  " + mAdapter.getCount());
                    }
                });
    }

    private void addFragment(Fragment fragment) {
//        CommercialLoanFragment commercialLoanFragment = new CommercialLoanFragment();
        Bundle bundle = new Bundle();
//        bundle.putInt(Data.ID, index);
        fragment.setArguments(bundle);

        FragmentManager childFragMan = getChildFragmentManager();
        FragmentTransaction childFragTrans = childFragMan.beginTransaction();
        //开始Fragment的事务Transaction
//        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        //替换容器(container)原来的Fragment
        childFragTrans.replace(R.id.frame_caculate, fragment);
        //设置转换效果
        childFragTrans.setTransition(FragmentTransaction.TRANSIT_NONE);
        //将事务添加到Back栈.即按下Back键时回到替换Fragment之前的状态.类似于Activity的返回
        childFragTrans.addToBackStack(null);
        //提交事务
        childFragTrans.commit();
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}