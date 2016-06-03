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
import com.example.cdj.myapplication.mainfunction.caculate.sub.MortCaculatorInputFragment;
import com.example.cdj.myapplication.mainfunction.caculate.sub.LoanAmountListFragment;
import com.example.cdj.myapplication.mainfunction.caculate.sub.LoanRateListFragment;
import com.example.cdj.myapplication.mainfunction.caculate.sub.LoanTermListFragment;
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

    /**
     * 通过工厂方法来创建Fragment实例
     * 同时给Fragment来提供参数来使用
     *
     * @return Master_Fragment的实例.
     */
    public static CommercialLoanFragment newInstance(Bundle bundle) {
        CommercialLoanFragment fragment = new CommercialLoanFragment();
//        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, price);
//        args.putString(ARG_PARAM2, interstRate);
        fragment.setArguments(bundle);
        return fragment;
    }

    public CommercialLoanFragment() {
        // Required empty public constructor
    }

    CommonFormLayout mFrameLoan;//贷款金额

    CommonFormLayout mFrameInterestRate;//房贷利率

    CommonFormLayout  mFrameLoanTerm;//贷款年限
    private Button btn_do_caculate;
    private CaculateMainFragment mCaculateMainFragment;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
             mCaculateMainFragment = (CaculateMainFragment)  getParentFragment();
        }
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
        final Bundle bundle = new Bundle();
//        bundle.putInt(Fragment4.FROM_TAG,CommercialLoanFragment.class.getSimpleName());
        mFrameLoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bundle.putInt(CaculateMainFragment.KEY, CaculateMainFragment.FROMDETAIL_COMMERCIAL_AMOUNT);
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
                bundle.putInt(CaculateMainFragment.KEY, CaculateMainFragment.FROMDETAIL_COMMERCIAL_INTEREST_RATE);
                mCallback.onAddFragment(LoanRateListFragment.class.getName(),bundle);
            }
        });
         mFrameLoanTerm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bundle.putInt(CaculateMainFragment.KEY, CaculateMainFragment.FROMDETAIL_COMMERCIAL_LOAN_TERM);
                mCallback.onAddFragment(LoanTermListFragment.class.getName(),bundle);
            }
        });

        mFrameLoan.setTitleText("贷款金额");
        mFrameLoan.setHasRightArrow(true);

        mFrameInterestRate.setTitleText("贷款利率");
        mFrameInterestRate.setHasRightArrow(true);

         mFrameLoanTerm.setTitleText("贷款年限");
         mFrameLoanTerm.setHasRightArrow(true);

        reFreshView();
    }

    /**
     * 进入子布局选择数据后确认提交后 ,返回刷新fragment
     * @param text
     */
    public void refreshFragment(String text){
        Logger.d(" 商业贷款界面  "+text);
        mFrameLoan.setContentText(text);
    }

    @Override
    public void reFreshView() {
        CaculateMainFragment parentFragment = (CaculateMainFragment) getParentFragment();
        if (parentFragment!=null){
            String price =  parentFragment.getCommercialAmount() + "万元";
            mFrameLoan.setContentText(parentFragment.isFromDetail()? (int)(parentFragment.getPercent()*10)+"成 "+price : price);

            String interestRateText = parentFragment.getCommercialRate() + "%";
            String rateDescription = parentFragment.getCommercialRateDesc();
            mFrameInterestRate.setContentText(parentFragment.isShowCommercialRateDesc() ? interestRateText : rateDescription+" " + interestRateText);

            mFrameLoanTerm.setContentText(parentFragment.getLoanYear()+"年");

//            Logger.d(CommercialLoanFragment.class.getSimpleName()+"price  "+parentFragment.getCommercialAmount()+" 利率 "+parentFragment.getCommercialRate()+
//                    " 期数 "+parentFragment.getLoanYear()+ "  详情?  "+parentFragment.isFromDetail());
        }
    }
}
