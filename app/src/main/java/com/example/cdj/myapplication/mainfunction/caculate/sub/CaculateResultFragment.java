package com.example.cdj.myapplication.mainfunction.caculate.sub;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cdj.myapplication.R;
import com.example.cdj.myapplication.base.BackHandledBaseFragment;
import com.example.cdj.myapplication.mainfunction.BigDecialUtils;
import com.example.cdj.myapplication.mainfunction.caculate.CaculateMainFragment;
import com.example.cdj.myapplication.mainfunction.caculate.MortCaculatorUtils;

/**
 * Created by cdj on 2016/5/19.
 */
public class CaculateResultFragment extends BackHandledBaseFragment {

    //等额本息
    private TextView tv_xi_monthlypay;          //月供
    private TextView tv_xi_total_interest;  //总利息
    private TextView tv_xi_total_principal_tInterest;//本息总额

    //等额本金
    private TextView tv_jin_monthlypay;
    private TextView tv_jin_total_interest;
    private TextView tv_monthly_decline;  //每月递减
    private TextView tv_jin_total_principal_tInterest;
    private double mMonthPay;
    private double mTotalInterest;
    private double mTotalAmount;
    private int mFirstMonthPay;
    private float mDecreasePay;
    private int mEqualityCorpusTotalAmount;
    private int mCorpusTotalInterest;
    private TextView tv_connect_agent;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
//            mParam1 = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_caculate_result, null);
        initView(rootView);
        return rootView;
    }


    protected void initView(View rootView) {
        rootView.setClickable(true);
        rootView.findViewById(R.id.iv_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().popBackStack();
            }
        });
        rootView.findViewById(R.id.btn_do_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().popBackStack();
            }
        });

        tv_xi_monthlypay = (TextView) rootView.findViewById(R.id.tv_xi_monthlypay);
        tv_xi_total_interest = (TextView) rootView.findViewById(R.id.tv_xi_total_interest);
        tv_xi_total_principal_tInterest = (TextView) rootView.findViewById(R.id.tv_xi_total_principal_tInterest);

        tv_jin_monthlypay = (TextView) rootView.findViewById(R.id.tv_jin_monthlypay);
        tv_jin_total_interest = (TextView) rootView.findViewById(R.id.tv_jin_total_interest);
        tv_monthly_decline = (TextView) rootView.findViewById(R.id.tv_monthly_decline);
        tv_jin_total_principal_tInterest = (TextView) rootView.findViewById(R.id.tv_jin_total_principal_tInterest);

        tv_connect_agent = (TextView) rootView.findViewById(R.id.tv_connect_agent);
        setAgentTextView();

        CaculateMainFragment parentFragment = (CaculateMainFragment) getFragmentManager().findFragmentByTag(CaculateMainFragment.class.getName());

        int commercialAmount = parentFragment.getCommercialAmount();
        float commercialRate = parentFragment.getCommercialRate();

        int fundAmount = parentFragment.getFundAmount();
        float fundRate = parentFragment.getFundRate();

        int loanYear = parentFragment.getLoanYear();

        int currentIndex = parentFragment.getCurrentIndex();
        int totalMonth = loanYear * 12;
        if (0 == currentIndex) {
            int amount = commercialAmount * 10000;
            double monthRate =  MortCaculatorUtils.getMonthRate(commercialRate);
            setData(loanYear, amount, monthRate, totalMonth);
        } else if (1 == currentIndex) {
            int amount = fundAmount * 10000;
            double monthRate =  MortCaculatorUtils.getMonthRate(fundRate);
            setData(loanYear, amount, monthRate, totalMonth);
        } else {
            int cAmount = commercialAmount * 10000;
            double cMonthRate =  MortCaculatorUtils.getMonthRate(commercialRate);

            int fAmount = fundAmount * 10000;
            double fMonthRate = MortCaculatorUtils. getMonthRate(fundRate);

            setCombinedData(totalMonth, cAmount, cMonthRate, fAmount, fMonthRate);
        }

        //等额本息
        String unit = getString(R.string.caculate_unit_yuan);
//        tv_xi_monthlypay.setText(format.format(mMonthPay)+ unit);
//        tv_xi_total_interest.setText(format.format(mTotalInterest) + unit);
//        tv_xi_total_principal_tInterest.setText(format.format(mTotalAmount) + unit);
        tv_xi_monthlypay.setText(BigDecialUtils.getBigDecimalInt(mMonthPay)+ unit);
        tv_xi_total_interest.setText(BigDecialUtils.getBigDecimalInt(mTotalInterest) + unit);
        tv_xi_total_principal_tInterest.setText(BigDecialUtils.getBigDecimalInt(mTotalAmount) + unit);

        tv_jin_monthlypay.setText(mFirstMonthPay + unit);
        tv_monthly_decline.setText("每月递减" + mDecreasePay + unit);
        tv_jin_total_interest.setText(mCorpusTotalInterest + unit);
        tv_jin_total_principal_tInterest.setText(mEqualityCorpusTotalAmount + unit);

    }

    /**
     * 组合贷款
     * @param totalMonth
     * @param cAmount
     * @param cMonthRate
     * @param fAmount
     * @param fMonthRate
     */
    private void setCombinedData(int totalMonth, int cAmount, double cMonthRate, int fAmount, double fMonthRate) {
        double cMonthPay = MortCaculatorUtils. getEqualityInterestMonthPay(cAmount, cMonthRate, totalMonth);
        double fMonthPay = MortCaculatorUtils. getEqualityInterestMonthPay(fAmount, fMonthRate, totalMonth);
        mMonthPay =cMonthPay+ fMonthPay;

        //总利息
        double cTotalInterest =  MortCaculatorUtils.getEqualityTotalInterest(cAmount, cMonthRate, totalMonth);
        double ftotalInterest =  MortCaculatorUtils.getEqualityTotalInterest(fAmount, fMonthRate, totalMonth);
        mTotalInterest = cTotalInterest + ftotalInterest;

        mTotalAmount =  MortCaculatorUtils.getCombinedTotalAmount(cAmount+fAmount, mTotalInterest);



        //等额本金
        int cFirstMonthPay =  MortCaculatorUtils.getFirstMonthPay(cAmount, cMonthRate, totalMonth);
        int fFirstMonthPay =  MortCaculatorUtils.getFirstMonthPay(fAmount, fMonthRate, totalMonth);
        mFirstMonthPay = cFirstMonthPay+fFirstMonthPay;


        float cDeclinePay =  MortCaculatorUtils.getDecreasePay(cAmount, cMonthRate, totalMonth);
        float fDeclinePay =  MortCaculatorUtils.getDecreasePay(fAmount, fMonthRate, totalMonth);
        mDecreasePay = cDeclinePay+fDeclinePay;


        int cTotalAmount =  MortCaculatorUtils.getEqualityCorpusTotalAmount(cAmount, cMonthRate, totalMonth);
        int fTotalAmount =  MortCaculatorUtils.getEqualityCorpusTotalAmount(fAmount, fMonthRate, totalMonth);
        mEqualityCorpusTotalAmount = cTotalAmount + fTotalAmount;

        mCorpusTotalInterest =  MortCaculatorUtils.getEqualityCorpusTotalInterest(mEqualityCorpusTotalAmount, cAmount+fAmount);
    }

    /**
     *计算公积金和商业贷款
     * @param loanYear
     * @param amount
     * @param monthRate
     * @param totalMonth
     */
    private void setData(int loanYear, int amount, double monthRate, int totalMonth) {

        mMonthPay = MortCaculatorUtils.getEqualityInterestMonthPay(amount, monthRate, totalMonth);
        mTotalInterest =  MortCaculatorUtils.getEqualityTotalInterest(amount, monthRate, totalMonth);
        mTotalAmount =  MortCaculatorUtils.getEqualitTotalAmount(amount, monthRate, totalMonth);

        //等额本金
        mFirstMonthPay =  MortCaculatorUtils.getFirstMonthPay(amount, monthRate,totalMonth);
        mDecreasePay =  MortCaculatorUtils.getDecreasePay(amount, monthRate, totalMonth);
        mEqualityCorpusTotalAmount =  MortCaculatorUtils.getEqualityCorpusTotalAmount(amount, monthRate, totalMonth);
        mCorpusTotalInterest =  MortCaculatorUtils.getEqualityCorpusTotalInterest(mEqualityCorpusTotalAmount, amount);
    }

    /**
     * 联系经纪人
     */
    private void setAgentTextView() {
        tv_connect_agent.setHighlightColor(getResources().getColor(android.R.color.transparent));
        SpannableString spanableInfo = new SpannableString(getString(R.string.caculator_contact_agent1));
        int start = 13;
        spanableInfo.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.orange_ff9933)), start, spanableInfo.length(), Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
        spanableInfo.setSpan(new Clickable(clickListener), start, spanableInfo.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        tv_connect_agent.setText(spanableInfo);
        tv_connect_agent.setMovementMethod(LinkMovementMethod.getInstance());

    }

    private View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Toast.makeText(getActivity(), "点击成功....", Toast.LENGTH_SHORT).show();
        }
    };

    class Clickable extends ClickableSpan {
        private final View.OnClickListener mListener;

        public Clickable(View.OnClickListener l) {
            mListener = l;
        }
        /**
         * 重写父类点击事件
         */
        @Override
        public void onClick(View v) {
            mListener.onClick(v);
        }

        /**
         * 重写父类updateDrawState方法  我们可以给TextView设置字体颜色,背景颜色等等...
         */
        @Override
        public void updateDrawState(TextPaint ds) {
//            ds.setColor(getResources().getColor(R.color.video_comment_like_number));
        }
    }

}
