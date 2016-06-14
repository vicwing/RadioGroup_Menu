package com.example.cdj.myapplication.mainfunction.caculate.sub;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.InputFilter;
import android.text.method.DigitsKeyListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.cdj.myapplication.R;
import com.example.cdj.myapplication.base.BackHandledBaseFragment;
import com.example.cdj.myapplication.cusview.CommomEditText;
import com.example.cdj.myapplication.mainfunction.caculate.CaculateMainFragment;
import com.example.cdj.myapplication.mainfunction.taxcaculator.CashierInputFilter;
import com.example.cdj.myapplication.mainfunction.taxcaculator.InputFilterMinMax;
import com.orhanobut.logger.Logger;

/**
 * 商业贷款输入金额
 * Created by cdj onCallBackData 2016/5/18.
 */
public class MortCaculatorInputFragment extends BackHandledBaseFragment implements View.OnClickListener {

    View rootView;
    private int mMin = 1;
    private int mMax = 99999;

    private ImageView iv_back;
    private CommomEditText mCommomEditText;
    private String mFromFragment;
    private int key;
    private boolean isFromList;
    private CaculateMainFragment mCaculateMainFragment;
    private int mCurrentIndex; //当前fragment 下标

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        if (rootView == null)
            rootView = inflater.inflate(R.layout.fragment_caculator_sub_input, null);
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

        mCaculateMainFragment = (CaculateMainFragment) getFragmentManager().findFragmentByTag(CaculateMainFragment.class.getName());
        mCurrentIndex = mCaculateMainFragment.getCurrentIndex();
        Bundle bundle = getArguments();
        if (bundle != null) {
            mFromFragment = bundle.getString(CaculateMainFragment.FROM_TAG);
            key = bundle.getInt(CaculateMainFragment.KEY);
            isFromList = bundle.getBoolean(CaculateMainFragment.isFromList);
            initContent();
        }


        mCommomEditText.setOnCommitListener(new CommomEditText.OnCommitClickListener() {
            @Override
            public void onClick(String num) {
                CaculateMainFragment caculateMainFragment = (CaculateMainFragment) getFragmentManager().findFragmentByTag(CaculateMainFragment.class.getName());
//                oldOnclik(num, fragment4);
                switchViewRefresh(caculateMainFragment, num);
                caculateMainFragment.setFromDetail(false);
                popBackToMain(caculateMainFragment);

//                if (isFromList) {//来自列表.回退要2次
//                    popBackToMain(caculateMainFragment);
//                } else {
//                    getFragmentManager().popBackStack();
//                    caculateMainFragment.getCurrentFragment().reFreshView();
//                }
            }
        });

        openInputMethod();
    }

    /**
     * 打开输入法
     */
    private void openInputMethod() {
        edt_content.setFocusable(true);
        edt_content.requestFocus();
        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        // 接受软键盘输入的编辑文本或其它视图
        imm.showSoftInput(edt_content, InputMethodManager.SHOW_FORCED);
    }

    /**
     * 根据key,指定刷新的view
     *
     * @param caculateMainFragment
     * @param num
     */
    private void switchViewRefresh(CaculateMainFragment caculateMainFragment, String num) {
        switch (key) {
            case CaculateMainFragment.FROMDETAIL_COMMERCIAL_AMOUNT:
                caculateMainFragment.setCommercialAmount(num);
                break;
            case CaculateMainFragment.FROMDETAIL_COMMERCIAL_INTEREST_RATE:
                caculateMainFragment.setShowCommercialRateDesc(true);
                caculateMainFragment.setCommercialRate(Float.parseFloat(num));
                break;

            case CaculateMainFragment.FROMDETAIL_Fund_AMOUNT:
                caculateMainFragment.setFundAmount(num);
                break;
            case CaculateMainFragment.FROMDETAIL_Fund_INTEREST_RATE:
                caculateMainFragment.setShowFundRateDesc(true);
                caculateMainFragment.setFundRate(Float.parseFloat(num));
                break;

            case CaculateMainFragment.FROMDETAIL_COMBINED_COMERCIAL_AMOUNT://组合贷款金额
                caculateMainFragment.setCommercialAmount(num);
                break;
            case CaculateMainFragment.FROMDETAIL_COMBINED_FUND_AMOUNT:
                caculateMainFragment.setFundAmount(num);
                break;

            case CaculateMainFragment.FROMDETAIL_COMBINED_COMERCIAL_RATE://组合贷款利率
                caculateMainFragment.setShowCommercialRateDesc(true);
                caculateMainFragment.setCommercialRate(Float.parseFloat(num));
                break;
            case CaculateMainFragment.FROMDETAIL_COMBINED_FUND_RATE:
                caculateMainFragment.setShowFundRateDesc(true);
                caculateMainFragment.setFundRate(Float.parseFloat(num));
                break;
            case CaculateMainFragment.FROMDETAIL_COMBINED_LOANTERM:
            case CaculateMainFragment.FROMDETAIL_COMMERCIAL_LOAN_TERM:
            case CaculateMainFragment.FROMDETAIL_Fund_LOAN_TERM:
                caculateMainFragment.setLoanTerm(Integer.parseInt(num));
                break;
            default:
                break;
        }

    }

    /**
     * 根据传入的 key 初始化显示的内容.
     */
    private void initContent() {

        switch (key) {
            case CaculateMainFragment.FROMDETAIL_COMMERCIAL_AMOUNT:
                initCommercialAmountText();
                break;
            case CaculateMainFragment.FROMDETAIL_COMMERCIAL_INTEREST_RATE:
                initCommecialInterate();
                break;
            case CaculateMainFragment.FROMDETAIL_Fund_AMOUNT:
                initFundAmountText();
                break;
            case CaculateMainFragment.FROMDETAIL_Fund_INTEREST_RATE:
                initFundInterate();
                break;
            case CaculateMainFragment.FROMDETAIL_COMBINED_COMERCIAL_AMOUNT:
                initCommercialAmountText();
                break;
            case CaculateMainFragment.FROMDETAIL_COMBINED_FUND_AMOUNT:
                initFundAmountText();
                break;
            case CaculateMainFragment.FROMDETAIL_COMBINED_COMERCIAL_RATE:
                initCommecialInterate();
                break;
            case CaculateMainFragment.FROMDETAIL_COMBINED_FUND_RATE:
                initFundInterate();
                break;
            case CaculateMainFragment.FROMDETAIL_COMBINED_LOANTERM:
            case CaculateMainFragment.FROMDETAIL_COMMERCIAL_LOAN_TERM:
            case CaculateMainFragment.FROMDETAIL_Fund_LOAN_TERM:
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
        edt_content.setFilters(new InputFilter[]{new InputFilterMinMax(0, 99)});

    }

    private String rateFiltetStr = "0123456789.";

    /**
     * 公积金贷款利率
     */
    private void initFundInterate() {
        tv_title.setText(R.string.caculate_fund_title_interest_rate);
        mCommomEditText.setTextUnit("%");
        mCommomEditText.setEditHint(R.string.caculate_input_fund_interest_rate);
        edt_content.setKeyListener(DigitsKeyListener.getInstance(rateFiltetStr));
        edt_content.setFilters(new InputFilter[]{new InputFilter.LengthFilter(5),new InputFilterMinMax(0, 99)});
    }

    /**
     * 商业贷款利率
     */
    private void initCommecialInterate() {
        tv_title.setText(R.string.caculate_input_interest_rate);
        mCommomEditText.setTextUnit("%");
        mCommomEditText.setEditHint(R.string.caculate_input_interest_rate);
        edt_content.setKeyListener(DigitsKeyListener.getInstance(rateFiltetStr));
//        edt_content.setFilters(new InputFilter[]{new InputFilter.LengthFilter(5)});
//        edt_content.setFilters(new InputFilter[]{new InputFilterMinMax(0, 99),new InputFilter.LengthFilter(5)});
        edt_content.setFilters(new InputFilter[]{new CashierInputFilter(99)});
//        edt_content.setFilters(new InputFilter[]{new EditInputFilter()});

    }

    /**
     * 公积金贷款初始化
     */
    private void initFundAmountText() {

        tv_title.setText(R.string.caculate_fund_title_amount);
        mCommomEditText.setTextUnit("万元");
        mCommomEditText.setEditHint(R.string.caculate_input_fund_interest_rate);
        edt_content.setFilters(new InputFilter[]{new InputFilterMinMax(mMin, mMax)});
    }

    /**
     * 商业贷款初始化
     */
    private void initCommercialAmountText() {
        tv_title.setText(R.string.caculate_commercial_title_loan_amount);
        mCommomEditText.setTextUnit("万元");
        mCommomEditText.setEditHint(R.string.caculate_input_loan_amount);
        edt_content.setFilters(new InputFilter[]{new InputFilterMinMax(mMin, mMax)});
    }

    private void popBackToMain(CaculateMainFragment caculateMainFragment) {
        caculateMainFragment.getCurrentFragment().reFreshView();
//        fragment4.setFromDetail(false);
//        getFragmentManager().popBackStack();
//        getFragmentManager().popBackStack();
        getFragmentManager().popBackStack(CaculateMainFragment.class.getName(), 0);
        Logger.d("stack count == " + getFragmentManager().getBackStackEntryCount() + " indexAt ");
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.iv_back) {
            getFragmentManager().popBackStack();
        }
    }
}
