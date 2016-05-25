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
import com.example.cdj.myapplication.base.BackHandledBaseFragment;
import com.example.cdj.myapplication.cusview.segmentcontrol.SegmentControl;
import com.example.cdj.myapplication.mainfunction.function4.sub.CaculateResultFragment;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;

/**
 * Created by cdj onCallBackData 2016/5/6.
 */
public class CaculateMainFragment extends BackHandledBaseFragment {
    private static String ARG_PARAM1;
    private static String ARG_PARAM2;
    public final static String HOUSE_STYLE = "houseStyle";//房屋名称+类型+面积
    public final static String TOTAL_PRICE = "total_price";
    public final static String INTREST_RATE = "intrest_rate";// 利率
    public final static String LOAN_TERM = "loan_term";

    TextView mTvHouseStyle;
    TextView mTvTotalPrice;
    private SegmentControl mSegmentControl;
    private FragmentActivity mActivity;

    private ArrayList<Fragment> fragmentArrayList;
    private Fragment mCurrentFrgment;
    private int currentIndex = 0;
    private Button btn_do_caculate;

    /**
     * 通过工厂方法来创建Fragment实例
     * 同时给Fragment来提供参数来使用
     * @return Master_Fragment的实例.
     */
    public static CaculateMainFragment newInstance(String param1, String param2) {
        CaculateMainFragment fragment = new CaculateMainFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public CaculateMainFragment() {
        // Required empty public constructor
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

    private float percent = 0.7f;//房贷几成
    private int mDefaultAmount = 100;//默认贷款金额


    public static float mDefaultCommercialRate =  4.9f; //商贷默认基准利率
    public static  float mDefaultFundRate =  3.25f; //公贷默认基准利率

    private int mCommercialAmount = mDefaultAmount;// 普通房贷:金额
    private float mCommercialRate = mDefaultCommercialRate;  // 普通房贷:利率默认4.9

    private String mFundRateDes = "最新基准利率";
    private String mCommercialRateDesc = "最新基准利率";

    private int mFundAmount = mDefaultAmount;// 公积金房贷:金额
    private float mFundRate = mDefaultFundRate;// 公积金:利率默认4.9

    private int mLoanTerm = 30;//贷款期限
    public static String FROM_TAG = "from_tag";

    public static String KEY = "KEY";

    public static final int FROMDETAIL_COMMERCIAL_AMOUNT = 0x01; //商业贷款金额
    public static final int FROMDETAIL_COMMERCIAL_INTEREST_RATE = 0x02;//商业贷款利率
    public static final int FROMDETAIL_COMMERCIAL_LOAN_TERM = 0x03;//商贷年限

    public static final int FROMDETAIL_Fund_AMOUNT = 0x04;//公贷金额
    public static final int FROMDETAIL_Fund_INTEREST_RATE = 0x05;//公贷利率
    public static final int FROMDETAIL_Fund_LOAN_TERM = 0x06;//公贷年限

    public static final int FROMDETAIL_COMBINED_COMERCIAL_AMOUNT = 0x07;//组合商贷金额
    public static final int FROMDETAIL_COMBINED_FUND_AMOUNT = 0x08;//组合公贷金额
    public static final int FROMDETAIL_COMBINED_COMERCIAL_RATE = 0x09;//组合商贷利率
    public static final int FROMDETAIL_COMBINED_FUND_RATE = 0x10;///组合公贷利率
    public static final int FROMDETAIL_COMBINED_LOANTERM = 0x11;//组合贷款年限

    public static String isFromList = "isFromList";
    private boolean isFromDetail = false;

    private boolean showFundRateDesc = false;//显示公贷利率描述
    private boolean showCommercialRateDesc = false;//显示商贷利率描述

    private void init(View layout) {
        mActivity = getActivity();

        if (getArguments() != null) {
            String mHouseSytle = getArguments().getString(HOUSE_STYLE);
            if (!TextUtils.isEmpty(mHouseSytle)) {
                isFromDetail = true;//来自详情页
                layout.findViewById(R.id.rl_houses_style).setVisibility(View.VISIBLE);
                ((TextView) layout.findViewById(R.id.tv_house_style)).setText(mHouseSytle + "xx大厦机房急停");
            } else {
                layout.findViewById(R.id.rl_houses_style).setVisibility(View.GONE);
            }
            String price = getArguments().getString(TOTAL_PRICE);
            if (!TextUtils.isEmpty(price))
                mCommercialAmount = Integer.parseInt(price);

            String intrestRate = getArguments().getString(INTREST_RATE);
            if (!TextUtils.isEmpty(intrestRate))
                mCommercialRate = Float.parseFloat(intrestRate);
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
        Bundle bundle = new Bundle();
        bundle.putInt(TOTAL_PRICE, mCommercialAmount);
        bundle.putFloat(INTREST_RATE, mCommercialRate);
        bundle.putInt(LOAN_TERM, mLoanTerm);
        mCallback.onAddFragment(CaculateResultFragment.class.getName(), bundle);
    }


    private void initSegmentControl(View layout) {
        mSegmentControl = (SegmentControl) layout.findViewById(R.id.segment_control);
        mSegmentControl.setOnSegmentControlClickListener(new SegmentControl.OnSegmentControlClickListener() {
            @Override
            public void onSegmentControlClick(int index) {
                Logger.d("index" + index + "   fragmentStackCount  " + getFragmentManager().getBackStackEntryCount());
                if (currentIndex==0){
                    if (index==1){//商贷-->公贷
                        mFundAmount = mCommercialAmount;
                    } else if (index == 2) {//商贷-->组合贷
//                        mFundAmount=0;
//                        mFundRate =mDefaultFundRate;
                    }
                }else if(currentIndex==1){
                    if (index==0){//公贷-->商贷
                        mCommercialAmount  = mFundAmount;
                    } else if (index == 2) {//公贷-->组合贷
//                        mCommercialAmount = 0;
                    }
                }else if(currentIndex==2){
                    if (index==0){//组合贷-->商贷

                    } else if (index == 1) {//组合贷-->公贷

                    }
                }

                changeFragment(index);
                getCurrentFragment().reFreshView();
            }
        });
//        mSegmentControl.setBackgroundDrawableColor(getResources().getColor(R.color.black));
        mSegmentControl.setSelectdTextColor(getResources().getColor(R.color.black_33333));
        mSegmentControl.setDefaultTextColor(getResources().getColor(R.color.white));
//        mSegmentControl.setSelectedDrawableColor(getResources().getColor(R.color.white));

    }


    private void initFragment() {
        mSegmentControl.setSelectedIndex(0);
        Bundle bundle = new Bundle();
        bundle.putInt(TOTAL_PRICE, mCommercialAmount);
        bundle.putFloat(INTREST_RATE, mCommercialRate);
        bundle.putBoolean(FROM_TAG, isFromDetail);
        fragmentArrayList = new ArrayList<Fragment>();
//        fragmentArrayList.add(CommercialLoanFragment.newInstance(String.valueOf(mCommercialAmount), String.valueOf(mCommercialRate)));
        fragmentArrayList.add(CommercialLoanFragment.newInstance(bundle));
        fragmentArrayList.add(FundLoanFragment.newInstance("", null));
        fragmentArrayList.add(CombinedLoanFragment.newInstance("", null));
        changeFragment(0);
    }

    /**
     * 切换fragment
     *
     * @param index
     */
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

    public boolean isFromDetail() {
        return isFromDetail;
    }

    public void setFromDetail(boolean isFromDetail) {
        this.isFromDetail = isFromDetail;
    }

    /**
     * 贷款几成数
     *
     * @return
     */
    public void setPercent(float percent) {
        this.percent = percent;
    }

    public float getPercent() {
        return percent;
    }

    public int getCommercialAmount() {
        return mCommercialAmount;
    }

    public void setCommercialAmount(int commercialAmount) {
        this.mCommercialAmount = commercialAmount;
    }

    public void setCommercialRate(float commercialRate) {
        mCommercialRate = commercialRate;
    }

    public float getCommercialRate() {
        return mCommercialRate;
    }

    public int getLoanTerm() {
        return mLoanTerm;
    }

    public void setLoanTerm(int loanTerm) {
        mLoanTerm = loanTerm;
    }

    public int getFundAmount() {
        return mFundAmount;
    }

    public void setFundAmount(int fundAmount) {
        this.mFundAmount = fundAmount;
    }

    public float getFundRate() {
        return mFundRate;
    }

    public void setFundRate(float fundRate) {
        this.mFundRate = fundRate;
    }

    public boolean isShowFundRateDesc() {
        return showFundRateDesc;
    }

    public void setShowFundRateDesc(boolean showFundRateDesc) {
        this.showFundRateDesc = showFundRateDesc;
    }

    public String getFundRateDes() {
        return mFundRateDes;
    }

    public void setFundRateDes(String fundRateDes) {
        mFundRateDes = fundRateDes;
    }

    public String getCommercialRateDesc() {
        return mCommercialRateDesc;
    }

    public void setCommercialRateDesc(String commercialRateDesc) {
        mCommercialRateDesc = commercialRateDesc;
    }

    public boolean isShowCommercialRateDesc() {
        return showCommercialRateDesc;
    }

    public void setShowCommercialRateDesc(boolean showCommercialRateDesc) {
        this.showCommercialRateDesc = showCommercialRateDesc;
    }

    /**
     * 获取当前显示的fragment实例.
     *
     * @return
     */
    public SubRefreshListener getCurrentFragment() {
        return (SubRefreshListener) mCurrentFrgment;
    }

    public int getCurrentIndex() {
        return currentIndex;
    }
}