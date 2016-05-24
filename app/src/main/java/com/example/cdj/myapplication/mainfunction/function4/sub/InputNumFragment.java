package com.example.cdj.myapplication.mainfunction.function4.sub;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.cdj.myapplication.R;
import com.example.cdj.myapplication.base.BackHandledBaseFragment;
import com.example.cdj.myapplication.cusview.CommomEditText;
import com.example.cdj.myapplication.mainfunction.function4.CombinedLoanFragment;
import com.example.cdj.myapplication.mainfunction.function4.CommercialLoanFragment;
import com.example.cdj.myapplication.mainfunction.function4.Fragment4;
import com.example.cdj.myapplication.mainfunction.function4.FundLoanFragment;
import com.orhanobut.logger.Logger;

/**
 * 商业贷款输入金额
 * Created by cdj onCallBackData 2016/5/18.
 */
public class InputNumFragment extends BackHandledBaseFragment implements View.OnClickListener {

    View rootView;

    private ImageView iv_back;
    private CommomEditText mCommomEditText;
    private String mFromFragment;
    private int key;
    private boolean isFromList;
    private Fragment4 mFragment4;
    private int mCurrentIndex; //当前fragment 下标

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        if (rootView == null)
            rootView = inflater.inflate(R.layout.fragment_caculate_sub_inputloan, null);
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
    }

    private TextView tv_title;
    private TextView tv_unit;
    private EditText edt_content;
    private Button btn_commit;

    private void initView() {
        rootView.setClickable(true);
        iv_back = (ImageView) rootView.findViewById(R.id.iv_back);
        iv_back.setOnClickListener(this);
        tv_title = (TextView) rootView.findViewById(R.id.tv_title);


        edt_content = (EditText) rootView.findViewById(R.id.edt_content);

        tv_unit = (TextView) rootView.findViewById(R.id.tv_unit);

        tv_unit = (Button) rootView.findViewById(R.id.btn_commit);

        mCommomEditText = (CommomEditText) rootView.findViewById(R.id.custom_edt_loan);

        mFragment4 = (Fragment4) getFragmentManager().findFragmentByTag(Fragment4.class.getName());
        mCurrentIndex = mFragment4.getCurrentIndex();
        Bundle bundle = getArguments();
        if (bundle != null) {
            mFromFragment = bundle.getString(Fragment4.FROM_TAG);
            key = bundle.getInt(Fragment4.KEY);
            isFromList = bundle.getBoolean(Fragment4.isFromList);
            initNewKey();
//            if (mFragment4.isFromDetail()) {//直接进入
//                if (0 == mCurrentIndex) {//商业贷款
//                    initCommercialAmountText();
//                } else if (1 == mCurrentIndex) { //公积金贷款
//                    initFundAmountText();
//                } else {
//                    if (CombinedLoanFragment.COMINED_COMMERCIAL_LOAN_AMOUNT.equals(mFromFragment)) {
//                        initCommercialAmountText();
//                    } else {
//                        initFundAmountText();
//                    }
//                }
//            } else {
////                if (LoanPriceListFragment.class.getSimpleName().equals(mFromFragment)) {//商业贷款
////                    initCommercialAmountText();
////                } else if (CombinedLoanFragment.COMINED_FUND_LOAN_AMOUNT.equals(mFromFragment) || CombinedLoanFragment.COMINED_COMMERCIAL_LOAN_AMOUNT.equals(mFromFragment)) {//组合贷款 : 商业贷款
////                    initFundAmountText();
////                } else if (FundLoanFragment.class.getSimpleName().equals(mFromFragment)) {// 公积金贷款:
////                    initFundAmountText();
////                } else if (InterestRateListFragment.class.getSimpleName().equals(mFromFragment)) {//商业贷款利率
////                    tv_title.setText(R.string.caculate_input_interest_rate);
////                    mCommomEditText.setTextUnit("%");
////                    mCommomEditText.setKeyListener("0123456789.");
////                    mCommomEditText.setEditHint(R.string.caculate_input_interest_rate);
////                } else if (LoanTermListFragment.class.getSimpleName().equals(mFromFragment)) {//贷款期限
////                    tv_title.setText(R.string.caculate_input_loanterm);
////                    mCommomEditText.setTextUnit("年");
////                    mCommomEditText.setEditHint(R.string.caculate_input_loanterm);
////                }
//                if (0 == mCurrentIndex) {//商业贷款
//                    if (LoanPriceListFragment.class.getSimpleName().equals(mFromFragment)) {
//                        initCommercialAmountText();
//                    } else if (InterestRateListFragment.class.getSimpleName().equals(mFromFragment)) {
//                        initCommecialInterate();
//                    } else if (LoanTermListFragment.class.getSimpleName().equals(mFromFragment)) {
//                        initCommecialInterate();
//                    }
//                } else if (1 == mCurrentIndex) {// 公积金贷款:
//                    if (LoanPriceListFragment.class.getSimpleName().equals(mFromFragment)) {
//                        initFundAmountText();
//                    } else if (InterestRateListFragment.class.getSimpleName().equals(mFromFragment)) {
//                        initFundInterate();
//                    } else if (LoanTermListFragment.class.getSimpleName().equals(mFromFragment)) {
//                        initLoanTerm();
//                    }
//                } else   {//组合贷款
//                    if (CombinedLoanFragment.COMINED_FUND_LOAN_AMOUNT.equals(mFromFragment)){
//
//                    }else{
//
//                    }
//                }
//            }
        }


        mCommomEditText.setOnCommitListener(new CommomEditText.OnCommitClickListener() {
            @Override
            public void onClick(String num) {
                Fragment4 fragment4 = (Fragment4) getFragmentManager().findFragmentByTag(Fragment4.class.getName());
//                oldOnclik(num, fragment4);
                    newOnclick(fragment4 , num);
                fragment4.setFromDetail(false);
                if (isFromList) {//来自列表.回退要2次
                    popBackToMain(fragment4);
                } else {
                    getFragmentManager().popBackStack();
                    fragment4.getCurrentFragment().reFreshView();
                }
            }
        });
    }

    private void newOnclick(Fragment4 fragment4,String num) {
        switch (key) {
            case Fragment4.FROMDETAIL_COMMERCIAL_AMOUNT:
                fragment4.setTotalPrice(Integer.parseInt(num));
                break;
            case Fragment4.FROMDETAIL_COMMERCIAL_INTEREST_RATE:
                fragment4.setIntrestRate(Float.parseFloat(num));
                break;

            case Fragment4.FROMDETAIL_Fund_AMOUNT:
                fragment4.setFundTtotalPrice(Integer.parseInt(num));
                break;
            case Fragment4.FROMDETAIL_Fund_INTEREST_RATE:
                fragment4.setFundIntrestRate(Float.parseFloat(num));
                break;

            case Fragment4.FROMDETAIL_COMBINED_COMERCIAL_AMOUNT://组合贷款金额
                fragment4.setTotalPrice(Integer.parseInt(num));
                break;
            case Fragment4.FROMDETAIL_COMBINED_FUND_AMOUNT:
                fragment4.setFundTtotalPrice(Integer.parseInt(num));
                break;

            case Fragment4.FROMDETAIL_COMBINED_COMERCIAL_RATE://组合贷款利率
                fragment4.setIntrestRate(Float.parseFloat(num));
                break;
            case Fragment4.FROMDETAIL_COMBINED_FUND_RATE:
                fragment4.setFundIntrestRate(Float.parseFloat(num));
                break;
            case Fragment4.FROMDETAIL_COMBINED_LOANTERM:
            case Fragment4.FROMDETAIL_COMMERCIAL_LOAN_TERM:
            case Fragment4.FROMDETAIL_Fund_LOAN_TERM:
                fragment4.setLoanTerm(Integer.parseInt(num));
                break;
            default:
                break;
        }

    }

    @Deprecated
    private void oldOnclik(String num, Fragment4 fragment4) {
        int currentIndex = fragment4.getCurrentIndex();
        if (CommercialLoanFragment.class.getSimpleName().equals(mFromFragment)) {//商业贷款界面直接进入
            fragment4.setTotalPrice(Integer.parseInt(num));
        } else if (FundLoanFragment.class.getSimpleName().equals(mFromFragment)) {//公积金界面直接进入
            fragment4.setFundTtotalPrice(Integer.parseInt(num));
        } else if (CombinedLoanFragment.COMINED_FUND_LOAN_AMOUNT.equals(mFromFragment)) {//组合贷款 商业贷款金额:界面直接进入
            fragment4.setTotalPrice(Integer.parseInt(num));
        } else if (CombinedLoanFragment.COMINED_COMMERCIAL_LOAN_AMOUNT.equals(mFromFragment)) {//组合贷款 公积金贷款金额:界面直接进入
            fragment4.setFundTtotalPrice(Integer.parseInt(num));
        } else if (LoanAmountListFragment.class.getSimpleName().equals(mFromFragment)) {  //贷款列表选择页
            if (currentIndex == 0) {
                fragment4.setTotalPrice(Integer.parseInt(num));
            } else if (currentIndex == 1) {
                fragment4.setFundTtotalPrice(Integer.parseInt(num));
            } else {//组合贷款
                fragment4.setTotalPrice(Integer.parseInt(num));
                fragment4.setFundTtotalPrice(Integer.parseInt(num));
            }
        } else if (LoanRateListFragment.class.getSimpleName().equals(mFromFragment)) {//利率列表选择页
            if (currentIndex == 0) {
                fragment4.setIntrestRate(Float.parseFloat(num));
            } else if (currentIndex == 1) {
                fragment4.setFundIntrestRate(Float.parseFloat(num));
            } else {
                fragment4.setIntrestRate(Float.parseFloat(num));
                fragment4.setFundIntrestRate(Float.parseFloat(num));
            }
        } else if (LoanTermListFragment.class.getSimpleName().equals(mFromFragment)) {//贷款期限
            fragment4.setLoanTerm(Integer.parseInt(num));
        }
    }

    private void initNewKey() {

        switch (key) {
            case Fragment4.FROMDETAIL_COMMERCIAL_AMOUNT:
                initCommercialAmountText();
                break;
            case Fragment4.FROMDETAIL_COMMERCIAL_INTEREST_RATE:
                initCommecialInterate();
                break;
            case Fragment4.FROMDETAIL_Fund_AMOUNT:
                initFundAmountText();
                break;
            case Fragment4.FROMDETAIL_Fund_INTEREST_RATE:
                initFundInterate();
                break;
            case Fragment4.FROMDETAIL_COMBINED_COMERCIAL_AMOUNT:
                initCommercialAmountText();
                break;
            case Fragment4.FROMDETAIL_COMBINED_FUND_AMOUNT:
                initFundAmountText();
                break;
            case Fragment4.FROMDETAIL_COMBINED_COMERCIAL_RATE:
                initCommecialInterate();
                break;
            case Fragment4.FROMDETAIL_COMBINED_FUND_RATE:
                initFundInterate();
                break;
            case Fragment4.FROMDETAIL_COMBINED_LOANTERM:
            case Fragment4.FROMDETAIL_COMMERCIAL_LOAN_TERM:
            case Fragment4.FROMDETAIL_Fund_LOAN_TERM:
                initLoanTerm();
                break;
            default:
                break;
        }

    }

    /**
     * 初始化贷款年限
     */
    private void initLoanTerm() {
        tv_title.setText(R.string.caculate_input_loanterm);
        mCommomEditText.setTextUnit("年");
        mCommomEditText.setEditHint(R.string.caculate_input_loanterm);
    }


    /**
     * 公积金贷款利率
     */
    private void initFundInterate() {
        tv_title.setText(R.string.caculate_fund_title_interest_rate);
        mCommomEditText.setTextUnit("%");
        mCommomEditText.setKeyListener("0123456789.");
        mCommomEditText.setEditHint(R.string.caculate_input_fund_interest_rate);
    }

    /**
     * 商业贷款利率
     */
    private void initCommecialInterate() {
        tv_title.setText(R.string.caculate_input_interest_rate);
        mCommomEditText.setTextUnit("%");
        mCommomEditText.setKeyListener("0123456789.");
        mCommomEditText.setEditHint(R.string.caculate_input_interest_rate);
    }

    /**
     * 公积金贷款初始化
     */
    private void initFundAmountText() {
        tv_title.setText(R.string.caculate_fund_title_price);
        mCommomEditText.setTextUnit("万元");
        mCommomEditText.setEditHint(R.string.caculate_input_fund_interest_rate);
    }

    /**
     * 商业贷款初始化
     */
    private void initCommercialAmountText() {
        tv_title.setText(R.string.caculate_commercial_title_loanprice);
        mCommomEditText.setTextUnit("万元");
        mCommomEditText.setEditHint(R.string.caculate_input_loan_totalprice);
    }

    private void popBackToMain(Fragment4 fragment4) {
        fragment4.getCurrentFragment().reFreshView();
//        fragment4.setFromDetail(false);
        getFragmentManager().popBackStack();
        getFragmentManager().popBackStack();
        Logger.d("stack count == " + getFragmentManager().getBackStackEntryCount() + " indexAt ");
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.iv_back) {
            getFragmentManager().popBackStack();
        }
    }
}
