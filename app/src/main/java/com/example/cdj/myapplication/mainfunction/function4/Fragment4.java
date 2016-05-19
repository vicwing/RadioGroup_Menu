package com.example.cdj.myapplication.mainfunction.function4;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.cdj.myapplication.R;
import com.example.cdj.myapplication.cusview.segmentcontrol.SegmentControl;
import com.example.cdj.myapplication.mainfunction.function4.sub.CaculateResultFragment;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;

/**
 * Created by cdj onCallBackData 2016/5/6.
 */
public class Fragment4 extends Fragment   {

    public final static String HOUSE_STYLE = "houseStyle";
    public final static String TOTAL_PRICE = "total_price";
    public final static String INTREST_RATE = "intrest_rate";
    public final static String LOAN_TERM = "loan_term";


    // 名字根据实际需求进行更改
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    TextView mTvHouseStyle;
    TextView mTvTotalPrice;

    // 这里的参数只是一个举例可以根据需求更改
    private String mParam1;
    private String mParam2;

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

        layout.findViewById(R.id.iv_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });
    }

    /**
     * 计算房贷结果
     */
    private void caculateData() {
        Logger.d("贷款总额  " + "  利率  " + " 公积金利率 " + "贷款期限  " + " stack" + getChildFragmentManager().getBackStackEntryCount());
        CommercialLoanFragment commercialLoanFragment = (CommercialLoanFragment) fragmentArrayList.get(currentIndex);
        String totalPrice = commercialLoanFragment.getTotalPrice();
        String intrestRate = commercialLoanFragment.getIntrestRate();
        String loanTerm = commercialLoanFragment.getLoanTerm();
        Bundle bundle = new Bundle();
        bundle.putString(TOTAL_PRICE,totalPrice);
        bundle.putString(INTREST_RATE,intrestRate);
        bundle.putString(LOAN_TERM,LOAN_TERM);
        mCallback.onReplaceFragment(CaculateResultFragment.class.getName(),null);
    }


    private void initSegmentControl(View layout) {
        mSegmentControl = (SegmentControl) layout.findViewById(R.id.segment_control);
        mSegmentControl.setOnSegmentControlClickListener(new SegmentControl.OnSegmentControlClickListener() {
            @Override
            public void onSegmentControlClick(int index) {
                changeFragment(index);
                Logger.d("index" + index + "   fragmentStackCount  " + getFragmentManager().getBackStackEntryCount());
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
        fragmentArrayList.add(CommercialLoanFragment.newInstance("新添加", null));
        fragmentArrayList.add(AccumulationFunLoanFragment.newInstance("新添加", null));
        fragmentArrayList.add(CombinedLoanFragment.newInstance("新添加", null));
        changeFragment(0);
    }

    private void changeFragment(int index) {
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

    OnHeadlineSelectedListener mCallback;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        // This makes sure that the container activity has implemented
        // the callback interface. If not, it throws an exception
        try {
            //
            mCallback = (OnHeadlineSelectedListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement OnHeadlineSelectedListener");
        }
    }


//    @Override
//    public Animation onCreateAnimation(int transit, final boolean enter, int nextAnim) {
//        final Animation anim = AnimationUtils.loadAnimation(getActivity(), nextAnim);
//
//            anim.setAnimationListener(new Animation.AnimationListener() {
//
//                public void onAnimationStart(Animation animation) {
//                    //动画开始
//                }
//
//                public void onAnimationRepeat(Animation animation) {
//                    //动画循环
//                }
//
//                public void onAnimationEnd(Animation animation) {
//                    //动画结束
//                    if (!enter) {
//                          getActivity().finish();
//                    }
//                }
//            });
//        return anim;
//    }

}