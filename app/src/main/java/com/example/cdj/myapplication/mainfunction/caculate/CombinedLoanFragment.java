package com.example.cdj.myapplication.mainfunction.caculate;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
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
 * 组合贷款
 * Created by cdj onCallBackData 2016/5/17.
 */
public class CombinedLoanFragment extends BackHandledBaseFragment implements SubRefreshListener {

    // 名字根据实际需求进行更改
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private CaculateMainFragment mParentFragment;
    private CaculateMainFragment parentFragment;

    /**
     * 通过工厂方法来创建Fragment实例
     * 同时给Fragment来提供参数来使用
     *
     * @return Master_Fragment的实例.
     */
    public static CombinedLoanFragment newInstance(Bundle bundle) {
        CombinedLoanFragment fragment = new CombinedLoanFragment();
        fragment.setArguments(bundle);
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

    CommonFormLayout mFrameLoanTerm;//贷款年限

    private Button btn_do_caculate;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View layout = inflater.inflate(R.layout.fragment_caculate_combinedloan, null);
        init(layout);
        return layout;
    }

    private void init(View layout) {
        mParentFragment = (CaculateMainFragment) getParentFragment();
        mFrameLoan = (CommonFormLayout) layout.findViewById(R.id.frame_loan);
        mFrameInterestRate = (CommonFormLayout) layout.findViewById(R.id.frame_interest_rate);

        mFrameFundLoan = (CommonFormLayout) layout.findViewById(R.id.frame_fund_loan);
        mFrameFundRate = (CommonFormLayout) layout.findViewById(R.id.frame_fundloan_rate);

        mFrameLoanTerm = (CommonFormLayout) layout.findViewById(R.id.frame_loan_year);

        final Bundle bundle = new Bundle();
        bundle.putString(CaculateMainFragment.FROM_TAG, CombinedLoanFragment.class.getSimpleName());


        mFrameLoan.setTitleText("商贷金额");
        mFrameInterestRate.setTitleText("商贷利率");

        mFrameLoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bundle.putInt(CaculateMainFragment.KEY, CaculateMainFragment.FROMDETAIL_COMBINED_COMERCIAL_AMOUNT);
                if (mParentFragment.isFromDetail()) {
                    mCallback.onAddFragment(LoanAmountListFragment.class.getName(), bundle);
                } else {
                    mCallback.onAddFragment(MortCaculatorInputFragment.class.getName(), bundle);
                }
            }
        });

        mFrameInterestRate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bundle.putInt(CaculateMainFragment.KEY, CaculateMainFragment.FROMDETAIL_COMBINED_COMERCIAL_RATE);
                    mCallback.onAddFragment(LoanRateListFragment.class.getName(), bundle);
            }
        });


        btn_do_caculate = (Button) layout.findViewById(R.id.btn_do_caculate);
        btn_do_caculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                parentFragment. startResulFragment();
            }
        });


        //公积金贷款
        mFrameFundLoan.setTitleText("公贷金额");
        mFrameFundRate.setTitleText("公贷利率");

        mFrameFundLoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bundle.putInt(CaculateMainFragment.KEY, CaculateMainFragment.FROMDETAIL_COMBINED_FUND_AMOUNT);
                if (mParentFragment.isFromDetail()) {
                    mCallback.onAddFragment(LoanAmountListFragment.class.getName(), bundle);
                } else {
                    mCallback.onAddFragment(MortCaculatorInputFragment.class.getName(), bundle);
                }
            }
        });

        mFrameFundRate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bundle.putInt(CaculateMainFragment.KEY, CaculateMainFragment.FROMDETAIL_COMBINED_FUND_RATE);
//                if (mParentFragment.isFromDetail()) {
                    mCallback.onAddFragment(LoanRateListFragment.class.getName(), bundle);
//                } else {
//                    mCallback.onAddFragment(InputNumFragment.class.getName(), bundle);
//                }
            }
        });


        mFrameLoanTerm.setTitleText("贷款年限");

        mFrameLoanTerm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bundle.putInt(CaculateMainFragment.KEY, CaculateMainFragment.FROMDETAIL_COMBINED_LOANTERM);
                mCallback.onAddFragment(LoanTermListFragment.class.getName(),bundle);
            }
        });

        reFreshView();
    }

    @Override
    public void reFreshView() {
        parentFragment = (CaculateMainFragment) getParentFragment();
        if (parentFragment != null) {
            //商业贷款
//            String price = parentFragment.getCommercialAmount() + "万元";
//            mFrameLoan.setContentText(parentFragment.isFromDetail() ? (int) (parentFragment.getPercent() * 10) + "成 " + price : price);

            String commercialAmount =parentFragment.getCommercialAmount();
            if (!TextUtils.isEmpty(commercialAmount)){
                String price = commercialAmount + "万元";
                mFrameLoan.setContentText(parentFragment.isFromDetail() ? (int) (parentFragment.getPercent() * 10) + "成 " + price : price);
            }else {
                mFrameLoan.setContentText("");
            }

            String interestRateText = parentFragment.getCommercialRate() + "%";
            String commercialRateDesc = parentFragment.getCommercialRateDesc();
            mFrameInterestRate.setContentText(parentFragment.isShowCommercialRateDesc() ? interestRateText : commercialRateDesc+" " + interestRateText);

//            mFrameInterestRate.setContentText(parentFragment.isFromDetail() ? getString(R.string.caculate_new_interest_rate) + interestRateText : interestRateText);

            //公积金
//            String fundAmountText = parentFragment.getFundAmount() + "万元";
//            mFrameFundLoan.setContentText(parentFragment.isFromDetail() ? (int) (parentFragment.getPercent() * 10) + "成 " + fundAmountText : fundAmountText);
            String fundAmount = parentFragment.getFundAmount();
            if (!TextUtils.isEmpty(fundAmount)){
                String price =  fundAmount + "万元";
                mFrameFundLoan.setContentText(parentFragment.isFromDetail()? (int)(parentFragment.getPercent()*10)+"成 "+price : price);
            }else {
                mFrameFundLoan.setContentText("");
            }

            String funInterestRateText = parentFragment.getFundRate() + "%";
            String fundRateDes = parentFragment.getFundRateDes();
            mFrameFundRate.setContentText(parentFragment.isShowFundRateDesc() ? funInterestRateText : fundRateDes+" " + funInterestRateText);
//            mFrameFundRate.setContentText(parentFragment.isFromDetail() ? getString(R.string.caculate_new_interest_rate) + funInterestRateText : funInterestRateText);

            mFrameLoanTerm.setContentText(parentFragment.getLoanYear() + "年");

            if (!TextUtils.isEmpty(fundAmount)&&!TextUtils.isEmpty(commercialAmount)){
                btn_do_caculate.setEnabled(true);
            }else {
                btn_do_caculate.setEnabled(false);
            }
        }
    }
}
