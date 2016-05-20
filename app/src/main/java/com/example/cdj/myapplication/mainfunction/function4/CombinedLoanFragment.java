package com.example.cdj.myapplication.mainfunction.function4;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.cdj.myapplication.R;
import com.example.cdj.myapplication.base.BackHandledBaseFragment;
import com.example.cdj.myapplication.cusview.CommonFormLayout;
import com.example.cdj.myapplication.mainfunction.function4.sub.InputLoanNumFragment;
import com.example.cdj.myapplication.mainfunction.function4.sub.SelectLoanNumFragment;
import com.orhanobut.logger.Logger;

import butterknife.ButterKnife;

/**
 * 组合贷款
 * Created by cdj onCallBackData 2016/5/17.
 */
public class CombinedLoanFragment extends BackHandledBaseFragment implements SubRefreshListener{

    // 名字根据实际需求进行更改
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // 这里的参数只是一个举例可以根据需求更改
    private String mParam1;
    private String mParam2;
    private Fragment4 mParentFragment;

    /**
     * 通过工厂方法来创建Fragment实例
     * 同时给Fragment来提供参数来使用
     *
     * @return Master_Fragment的实例.
     */
    public static CombinedLoanFragment newInstance(String param1, String param2) {
        CombinedLoanFragment fragment = new CombinedLoanFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public CombinedLoanFragment() {
        // Required empty public constructor
    }

    //商业贷款
    CommonFormLayout mFrameLoan;//贷款金额
    CommonFormLayout mFrameInterestRate;//房贷利率

    CommonFormLayout mFrameFundLoan;//公积金贷款
    CommonFormLayout mFrameFundRate;//公积金贷款金额

    CommonFormLayout mFrameLoanYear;//贷款年限




    private int defaultPrice = 100;//默认贷款额
    private  int totalPrice = defaultPrice;//贷款总额

    private float mIntrestRate = 4.9f;
    private int  mLoanTerm = 30;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View layout = inflater.inflate(R.layout.fragment_caculate_combinedloan, null);
        ButterKnife.bind(this, layout);
        init(layout);
        return layout;
    }

    private void init(View layout) {
        mParentFragment = (Fragment4) getParentFragment();
        mFrameLoan = (CommonFormLayout) layout.findViewById(R.id.frame_loan);
        mFrameInterestRate = (CommonFormLayout) layout.findViewById(R.id.frame_interest_rate);

        mFrameFundLoan = (CommonFormLayout) layout.findViewById(R.id.frame_fund_loan);
        mFrameFundRate = (CommonFormLayout) layout.findViewById(R.id.frame_fundloan_rate);

        mFrameLoanYear = (CommonFormLayout) layout.findViewById(R.id.frame_loan_year);


        mFrameLoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mParentFragment.isFromDetail()){
                    mCallback.onReplaceFragment(SelectLoanNumFragment.class.getName(),getArguments());
                }else{
                    mCallback.onReplaceFragment(InputLoanNumFragment.class.getName(),getArguments());
                }
            }
        });


        mFrameLoan.setTitleText("贷款金额");
        mFrameLoan.setHasRightArrow(true);
        mFrameLoan.setContentText("7成"+totalPrice);

        mFrameInterestRate.setTitleText("贷款利率");
        mFrameInterestRate.setContentText("最新基准利率"+mIntrestRate+"%");
        mFrameInterestRate.setHasRightArrow(true);

        //公积金贷款
        mFrameFundLoan.setTitleText("公贷金额");
        mFrameFundLoan.setContentText(totalPrice+"");
        mFrameFundLoan.setHasRightArrow(true);

        mFrameFundRate.setTitleText("公贷利率");
        mFrameFundRate.setContentText(mIntrestRate+"%");
        mFrameFundRate.setHasRightArrow(true);




        mFrameLoanYear.setTitleText("贷款年限");
        mFrameLoanYear.setContentText(mLoanTerm+"年");
        mFrameLoanYear.setHasRightArrow(true);
    }

    @Override
    public void reFreshView() {
        Fragment4 parentFragment = (Fragment4) getParentFragment();
        if (parentFragment!=null){
            Logger.d(AccumulationFunLoanFragment.class.getSimpleName()+"price  "+parentFragment.getTotalPrice()+" 利率 "+parentFragment.getIntrestRate()+
                    " 期数 "+parentFragment.getLoanTerm()+ "  详情?  "+parentFragment.isFromDetail());
        }
        Logger.d("组合贷款.................");
    }
}
