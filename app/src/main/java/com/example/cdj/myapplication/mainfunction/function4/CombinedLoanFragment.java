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
import com.example.cdj.myapplication.mainfunction.function4.sub.LoanRateListFragment;
import com.example.cdj.myapplication.mainfunction.function4.sub.LoanAmountListFragment;
import com.example.cdj.myapplication.mainfunction.function4.sub.LoanTermListFragment;

import butterknife.ButterKnife;

/**
 * 组合贷款
 * Created by cdj onCallBackData 2016/5/17.
 */
public class CombinedLoanFragment extends BackHandledBaseFragment implements SubRefreshListener {

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

    CommonFormLayout mFrameLoanTerm;//贷款年限


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View layout = inflater.inflate(R.layout.fragment_caculate_combinedloan, null);
        ButterKnife.bind(this, layout);
        init(layout);
        return layout;
    }

    public static String COMINED_COMMERCIAL_LOAN_AMOUNT = "Comined_Commercial_Loan_Amount";//商业贷款金额
    public static String COMINED_FUND_LOAN_AMOUNT = "Comined_Fund_Loan_Amount";   //公积金贷款金额

    private void init(View layout) {
        mParentFragment = (Fragment4) getParentFragment();
        mFrameLoan = (CommonFormLayout) layout.findViewById(R.id.frame_loan);
        mFrameInterestRate = (CommonFormLayout) layout.findViewById(R.id.frame_interest_rate);

        mFrameFundLoan = (CommonFormLayout) layout.findViewById(R.id.frame_fund_loan);
        mFrameFundRate = (CommonFormLayout) layout.findViewById(R.id.frame_fundloan_rate);

        mFrameLoanTerm = (CommonFormLayout) layout.findViewById(R.id.frame_loan_year);

        final Bundle bundle = new Bundle();
        bundle.putString(Fragment4.FROM_TAG, CombinedLoanFragment.class.getSimpleName());


        mFrameLoan.setTitleText("商贷金额");
        mFrameInterestRate.setTitleText("商贷利率");

        mFrameLoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bundle.putInt(Fragment4.KEY,Fragment4.FROMDETAIL_COMBINED_COMERCIAL_AMOUNT);
                if (mParentFragment.isFromDetail()) {
                    mCallback.onAddFragment(LoanAmountListFragment.class.getName(), bundle);
                } else {
                    mCallback.onAddFragment(InputNumFragment.class.getName(), bundle);
                }
            }
        });

        mFrameInterestRate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bundle.putInt(Fragment4.KEY,Fragment4.FROMDETAIL_COMBINED_COMERCIAL_RATE);
                if (mParentFragment.isFromDetail()) {
                    mCallback.onAddFragment(LoanRateListFragment.class.getName(), bundle);
                } else {
                    mCallback.onAddFragment(InputNumFragment.class.getName(), bundle);
                }
            }
        });




        //公积金贷款
        mFrameFundLoan.setTitleText("公贷金额");
        mFrameFundRate.setTitleText("公贷利率");

        mFrameFundLoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bundle.putInt(Fragment4.KEY,Fragment4.FROMDETAIL_COMBINED_FUND_AMOUNT);
                if (mParentFragment.isFromDetail()) {
                    mCallback.onAddFragment(LoanAmountListFragment.class.getName(), bundle);
                } else {
                    mCallback.onAddFragment(InputNumFragment.class.getName(), bundle);
                }
            }
        });

        mFrameFundRate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bundle.putInt(Fragment4.KEY,Fragment4.FROMDETAIL_COMBINED_FUND_RATE);
                if (mParentFragment.isFromDetail()) {
                    mCallback.onAddFragment(LoanRateListFragment.class.getName(), bundle);
                } else {
                    mCallback.onAddFragment(InputNumFragment.class.getName(), bundle);
                }
            }
        });


        mFrameLoanTerm.setTitleText("贷款年限");

        mFrameLoanTerm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bundle.putInt(Fragment4.KEY,Fragment4.FROMDETAIL_COMBINED_LOANTERM);
                mCallback.onAddFragment(LoanTermListFragment.class.getName(),bundle);
            }
        });

        reFreshView();
    }

    @Override
    public void reFreshView() {
        Fragment4 parentFragment = (Fragment4) getParentFragment();
        if (parentFragment != null) {
            //商业贷款
            String price = parentFragment.getTotalPrice() + "万元";
            mFrameLoan.setContentText(parentFragment.isFromDetail() ? (int) (parentFragment.getPercent() * 10) + "成" + price : price);

            String interestRateText = parentFragment.getInterestRate() + "%";
            mFrameInterestRate.setContentText(parentFragment.isFromDetail() ? getString(R.string.caculate_new_interest_rate) + interestRateText : interestRateText);

            //公积金
            String fundAmountText = parentFragment.getFundTtotalPrice() + "万元";
            mFrameFundLoan.setContentText(parentFragment.isFromDetail() ? (int) (parentFragment.getPercent() * 10) + "成" + fundAmountText : fundAmountText);

            String funInterestRateText = parentFragment.getFundIntrestRate() + "%";
            mFrameFundRate.setContentText(parentFragment.isFromDetail() ? getString(R.string.caculate_new_interest_rate) + funInterestRateText : funInterestRateText);

            mFrameLoanTerm.setContentText(parentFragment.getLoanTerm() + "年");
        }
    }
}
