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
import com.orhanobut.logger.Logger;

/**
 * 商业贷款界面
 * Created by cdj onCallBackData 2016/5/17.
 */
public class CommercialLoanFragment extends BackHandledBaseFragment implements SubRefreshListener {

    // 名字根据实际需求进行更改
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // 这里的参数只是一个举例可以根据需求更改
    private int totalPrice;
    private float mInterestRate;
    private Button btn_do_caculate;
    private CaculateMainFragment parentFragment;

    /**
     * 通过工厂方法来创建Fragment实例
     * 同时给Fragment来提供参数来使用
     *
     * @return Master_Fragment的实例.
     */
    public static CommercialLoanFragment newInstance(Bundle bundle) {
        CommercialLoanFragment fragment = new CommercialLoanFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    public CommercialLoanFragment() {
        // Required empty public constructor
    }

    CommonFormLayout mFrameLoan;//贷款金额

    CommonFormLayout mFrameInterestRate;//房贷利率

    CommonFormLayout mFrameLoanTerm;//贷款年限
    private CaculateMainFragment mCaculateMainFragment;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mCaculateMainFragment = (CaculateMainFragment) getParentFragment();
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View layout = inflater.inflate(R.layout.fragment_caculate_commercialloan, null);
        init(layout);
        return layout;
    }

    private void init(View layout) {
        mFrameLoan = (CommonFormLayout) layout.findViewById(R.id.frame_loan);
        mFrameInterestRate = (CommonFormLayout) layout.findViewById(R.id.frame_interest_rate);
        mFrameLoanTerm = (CommonFormLayout) layout.findViewById(R.id.frame_loan_year);

        btn_do_caculate = (Button) layout.findViewById(R.id.btn_do_caculate);
        btn_do_caculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                parentFragment. startResulFragment();
            }
        });
//        btn_do_caculate.setEnabled(false);

        final Bundle bundle = new Bundle();
        mFrameLoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bundle.putInt(CaculateMainFragment.KEY, CaculateMainFragment.FROMDETAIL_COMMERCIAL_AMOUNT);
                if (mCaculateMainFragment.isFromDetail()) {
                    mCallback.onAddFragment(LoanAmountListFragment.class.getName(), bundle);
                } else {
                    mCallback.onAddFragment(MortCaculatorInputFragment.class.getName(), bundle);
                }
            }
        });


        mFrameInterestRate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bundle.putInt(CaculateMainFragment.KEY, CaculateMainFragment.FROMDETAIL_COMMERCIAL_INTEREST_RATE);
                mCallback.onAddFragment(LoanRateListFragment.class.getName(), bundle);
            }
        });
        mFrameLoanTerm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bundle.putInt(CaculateMainFragment.KEY, CaculateMainFragment.FROMDETAIL_COMMERCIAL_LOAN_TERM);
                mCallback.onAddFragment(LoanTermListFragment.class.getName(), bundle);
            }
        });

        mFrameLoan.setTitleText("贷款金额");
//        mFrameLoan.setHasRightArrow(true);

        mFrameInterestRate.setTitleText("贷款利率");
//        mFrameInterestRate.setHasRightArrow(true);

        mFrameLoanTerm.setTitleText("贷款年限");
//        mFrameLoanTerm.setHasRightArrow(true);



//        mFrameLoan.setContentText("");

        reFreshView();
    }

    @Override
    public void reFreshView() {
        parentFragment = (CaculateMainFragment) getParentFragment();
        if (parentFragment != null ) {
            parentFragment.setCommercialAmountView(btn_do_caculate,mFrameLoan);

            String interestRateText = parentFragment.getCommercialRate() + "%";
            String rateDescription = parentFragment.getCommercialRateDesc();
            mFrameInterestRate.setContentText(parentFragment.isShowCommercialRateDesc() ? interestRateText : rateDescription + " " + interestRateText);

            mFrameLoanTerm.setContentText(parentFragment.getLoanYear() + "年");
        }
    }



    /**
     * 立即计算按钮可点击.
     */
    public void btnCanClick(){
        CharSequence contentText = mFrameLoan.getContentText();
        Logger.d("contentText   "+contentText);
        if (!TextUtils.isEmpty(contentText)){
//            btn_do_caculate.set
        }
    }
}
