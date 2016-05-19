package com.example.cdj.myapplication.mainfunction.function4;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.cdj.myapplication.R;
import com.example.cdj.myapplication.cusview.CommonFormLayout;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 公积金贷款
 * Created by cdj onCallBackData 2016/5/17.
 */
public class AccumulationFunLoanFragment extends Fragment{

    // 名字根据实际需求进行更改
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // 这里的参数只是一个举例可以根据需求更改
    private String mParam1;
    private String mParam2;

    /**
     * 通过工厂方法来创建Fragment实例
     * 同时给Fragment来提供参数来使用
     *
     * @return Master_Fragment的实例.
     */
    public static AccumulationFunLoanFragment newInstance(String param1, String param2) {
        AccumulationFunLoanFragment fragment = new AccumulationFunLoanFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);

        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public AccumulationFunLoanFragment() {
        // Required empty public constructor
    }

    @Bind(R.id.frame_loan)
    CommonFormLayout mFrameLoan;//贷款金额

    @Bind(R.id.frame_interest_rate)
    CommonFormLayout mFrameInterestRate;//房贷利率

    @Bind(R.id.frame_loan_year)
    CommonFormLayout mFrameLoanYear;//贷款年限




    private int defaultPrice = 100;//默认贷款额
    private  int totalPrice = defaultPrice;//贷款总额

    private float mIntrestRate = 4.9f;
    private int  mLoanTerm = 30;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View layout = inflater.inflate(R.layout.fragment_caculate_commercialloan, null);
        ButterKnife.bind(this, layout);
        init(layout);
        return layout;
    }

    private void init(View layout) {

        mFrameLoan = (CommonFormLayout) layout.findViewById(R.id.frame_loan);
        mFrameInterestRate = (CommonFormLayout) layout.findViewById(R.id.frame_interest_rate);
        mFrameLoanYear = (CommonFormLayout) layout.findViewById(R.id.frame_loan_year);


        mFrameLoan.setTitleText("公积金贷款金额");
        mFrameLoan.setHasRightArrow(true);
        mFrameLoan.setContentText("7成"+totalPrice);


        mFrameInterestRate.setTitleText("公积金贷款利率");
        mFrameInterestRate.setContentText("最新基准利率"+mIntrestRate+"%");
        mFrameInterestRate.setHasRightArrow(true);

        mFrameLoanYear.setTitleText("公积金贷款年限");
        mFrameLoanYear.setContentText(mLoanTerm+"年");
        mFrameLoanYear.setHasRightArrow(true);
    }
}
