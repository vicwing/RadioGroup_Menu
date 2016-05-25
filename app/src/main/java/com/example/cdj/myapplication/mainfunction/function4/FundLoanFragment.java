package com.example.cdj.myapplication.mainfunction.function4;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.cdj.myapplication.R;
import com.example.cdj.myapplication.base.BackHandledBaseFragment;
import com.example.cdj.myapplication.cusview.CommonFormLayout;
import com.example.cdj.myapplication.mainfunction.function4.sub.InputNumFragment;
import com.example.cdj.myapplication.mainfunction.function4.sub.LoanAmountListFragment;
import com.example.cdj.myapplication.mainfunction.function4.sub.LoanRateListFragment;
import com.example.cdj.myapplication.mainfunction.function4.sub.LoanTermListFragment;

/**
 * 公积金贷款
 * Created by cdj onCallBackData 2016/5/17.
 */
public class FundLoanFragment extends BackHandledBaseFragment implements SubRefreshListener {

    // 名字根据实际需求进行更改
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // 这里的参数只是一个举例可以根据需求更改
    private String mParam1;
    private String mParam2;

    public FundLoanFragment() {
        // Required empty public constructor
    }

    /**
     * 通过工厂方法来创建Fragment实例
     * 同时给Fragment来提供参数来使用
     *
     * @return Master_Fragment的实例.
     */
    public static FundLoanFragment newInstance(String param1, String param2) {
        FundLoanFragment fragment = new FundLoanFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    CommonFormLayout mFrameLoan;//贷款金额

    CommonFormLayout mFrameInterestRate;//房贷利率

    CommonFormLayout mFrameLoanTerm;//贷款年限

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View layout = inflater.inflate(R.layout.fragment_caculate_commercialloan, null);
        init(layout);
        return layout;
    }
    CaculateMainFragment mCaculateMainFragment;
    private void init(View layout) {
        mCaculateMainFragment = (CaculateMainFragment) getParentFragment();
        mFrameLoan = (CommonFormLayout) layout.findViewById(R.id.frame_loan);
        mFrameInterestRate = (CommonFormLayout) layout.findViewById(R.id.frame_interest_rate);
        mFrameLoanTerm = (CommonFormLayout) layout.findViewById(R.id.frame_loan_year);

        final  Bundle bundle = new Bundle();
        bundle.putString(CaculateMainFragment.FROM_TAG,FundLoanFragment.class.getSimpleName());

        mFrameLoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bundle.putInt(CaculateMainFragment.KEY, CaculateMainFragment.FROMDETAIL_Fund_AMOUNT);
                if (mCaculateMainFragment.isFromDetail()){
                    mCallback.onAddFragment(LoanAmountListFragment.class.getName(),bundle);
                }else{
                    mCallback.onAddFragment(InputNumFragment.class.getName(),bundle);
                }
            }
        });

        mFrameInterestRate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bundle.putInt(CaculateMainFragment.KEY, CaculateMainFragment.FROMDETAIL_Fund_INTEREST_RATE);
                mCallback.onAddFragment(LoanRateListFragment.class.getName(),bundle);
            }
        });


        mFrameLoanTerm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bundle.putInt(CaculateMainFragment.KEY, CaculateMainFragment.FROMDETAIL_Fund_LOAN_TERM);
                    mCallback.onAddFragment(LoanTermListFragment.class.getName(),bundle);
            }
        });

        mFrameLoan.setTitleText(R.string.caculate_fund_title_amount);
        mFrameLoan.setHasRightArrow(true);
//        mFrameLoan.setContentText("7成"+totalPrice);


        mFrameInterestRate.setTitleText(R.string.caculate_fund_title_interest_rate);
//        mFrameInterestRate.setContentText(R.string.caculate_newbase_interest_rate+mIntrestRate+"%");
        mFrameInterestRate.setHasRightArrow(true);

        mFrameLoanTerm.setTitleText(R.string.caculate_fund_title_loan_term);
//        mFrameLoanTerm.setContentText(mLoanTerm+"年");
        mFrameLoanTerm.setHasRightArrow(true);
        reFreshView();
    }

    @Override
    public void reFreshView() {
        CaculateMainFragment parentFragment = (CaculateMainFragment) getParentFragment();
        if (parentFragment!=null){

            String price =  parentFragment.getFundAmount() + "万元";
            mFrameLoan.setContentText(parentFragment.isFromDetail()? (int)(parentFragment.getPercent()*10)+"成 "+price : price);

            String interestRateText = parentFragment.getFundRate() + "%";
            String rateDescription = parentFragment.getFundRateDes();
//            if (TextUtils.isEmpty(rateDescription)){
//                rateDescription = getString(R.string.caculate_new_interest_rate);
//            }
            mFrameInterestRate.setContentText(parentFragment.isShowFundRateDesc() ? interestRateText : rateDescription+" " + interestRateText);

            mFrameLoanTerm.setContentText(parentFragment.getLoanYear()+"年");
        }
    }
}
