package com.example.cdj.myapplication.mainfunction.caculate;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.cdj.myapplication.R;
import com.example.cdj.myapplication.base.BackHandledBaseFragment;
import com.example.cdj.myapplication.cusview.CommonFormLayout;
import com.example.cdj.myapplication.mainfunction.caculate.impl.SubRefreshListener;
import com.example.cdj.myapplication.mainfunction.caculate.sub.LoanAmountListFragment;
import com.example.cdj.myapplication.mainfunction.caculate.sub.LoanRateListFragment;
import com.example.cdj.myapplication.mainfunction.caculate.sub.LoanTermListFragment;
import com.example.cdj.myapplication.mainfunction.caculate.sub.MortCaculatorInputFragment;

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
    private CaculateMainFragment parentFragment;

    public FundLoanFragment() {
        // Required empty public constructor
    }

    /**
     * 通过工厂方法来创建Fragment实例
     * 同时给Fragment来提供参数来使用
     *
     * @return Master_Fragment的实例.
     */
    public static FundLoanFragment newInstance(Bundle bundle) {
        FundLoanFragment fragment = new FundLoanFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    CommonFormLayout mFrameLoan;//贷款金额

    CommonFormLayout mFrameInterestRate;//房贷利率

    CommonFormLayout mFrameLoanTerm;//贷款年限

    private Button btn_do_caculate;

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
                    mCallback.onAddFragment(MortCaculatorInputFragment.class.getName(),bundle);
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



        btn_do_caculate = (Button) layout.findViewById(R.id.btn_do_caculate);
        btn_do_caculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                parentFragment. startResulFragment();
            }
        });

        mFrameLoan.setTitleText(R.string.caculate_fund_title_amount);
//        mFrameLoan.setContentText("7成"+totalPrice);


        mFrameInterestRate.setTitleText(R.string.caculate_fund_title_interest_rate);
//        mFrameInterestRate.setContentText(R.string.caculate_newbase_interest_rate+mIntrestRate+"%");

        mFrameLoanTerm.setTitleText(R.string.caculate_fund_title_loan_term);
//        mFrameLoanTerm.setContentText(mLoanTerm+"年");

        reFreshView();
    }

    @Override
    public void reFreshView() {
        parentFragment = (CaculateMainFragment) getParentFragment();
        if (parentFragment !=null){

            parentFragment. setFundAmountView(btn_do_caculate,mFrameLoan);


            String interestRateText = parentFragment.getFundRate() + "%";
            String rateDescription = parentFragment.getFundRateDes();
            mFrameInterestRate.setContentText(parentFragment.isShowFundRateDesc() ? interestRateText : rateDescription+" " + interestRateText);

            mFrameLoanTerm.setContentText(parentFragment.getLoanYear()+"年");
        }
    }


}
