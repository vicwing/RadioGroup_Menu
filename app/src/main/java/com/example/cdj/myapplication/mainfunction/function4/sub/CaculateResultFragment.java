package com.example.cdj.myapplication.mainfunction.function4.sub;

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
import com.example.cdj.myapplication.mainfunction.function4.CaculateMainFragment;

import java.math.BigDecimal;
import java.math.RoundingMode;

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
    private int mMonthPay;
    private int mTotalInterest;
    private int mTotalAmount;
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
            float monthRate = getMonthRate(commercialRate);
            setData(loanYear, amount, monthRate, totalMonth);
        } else if (1 == currentIndex) {
            int amount = fundAmount * 10000;
            float monthRate = getMonthRate(fundRate);
            setData(loanYear, amount, monthRate, totalMonth);
        } else {
            int cAmount = commercialAmount * 10000;
            float cMonthRate = getMonthRate(commercialRate);

            int fAmount = fundAmount * 10000;
            float fMonthRate = getMonthRate(fundRate);

            setCombinedData(totalMonth, cAmount, cMonthRate, fAmount, fMonthRate);
        }

        //等额本息
        String unit = getString(R.string.caculate_unit_yuan);
        tv_xi_monthlypay.setText(mMonthPay + unit);
        tv_xi_total_interest.setText(mTotalInterest + unit);
        tv_xi_total_principal_tInterest.setText(mTotalAmount + unit);

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
    private void setCombinedData(int totalMonth, int cAmount, float cMonthRate, int fAmount, float fMonthRate) {
        int cMonthPay = getEqualityInterestMonthPay(cAmount, cMonthRate, totalMonth);
        int fMonthPay = getEqualityInterestMonthPay(fAmount, fMonthRate, totalMonth);
        mMonthPay =cMonthPay+ fMonthPay;

        //总利息
        int cTotalInterest = getEqualityTotalInterest(cAmount, cMonthPay, totalMonth);
        int ftotalInterest = getEqualityTotalInterest(fAmount, fMonthPay, totalMonth);
        mTotalInterest = cTotalInterest + ftotalInterest;

        mTotalAmount = getEqualitTotalAmount(cAmount+fAmount, mTotalInterest);

        //等额本金

        int cFirstMonthPay = getFirstMonthPay(cAmount, cMonthRate, totalMonth);
        int fFirstMonthPay = getFirstMonthPay(fAmount, fMonthRate, totalMonth);
        mFirstMonthPay = cFirstMonthPay+fFirstMonthPay;


        float cDeclinePay = getDecreasePay(cAmount, cMonthRate, totalMonth);
        float fDeclinePay = getDecreasePay(fAmount, fMonthRate, totalMonth);
        mDecreasePay = cDeclinePay+fDeclinePay;


        int cTotalAmount = getEqualityCorpusTotalAmount(cAmount, cMonthRate, totalMonth);
        int fTotalAmount = getEqualityCorpusTotalAmount(fAmount, fMonthRate, totalMonth);
        mEqualityCorpusTotalAmount = cTotalAmount + fTotalAmount;

        mCorpusTotalInterest = getEqualityCorpusTotalInterest(mEqualityCorpusTotalAmount, cAmount+fAmount);
    }

    /**
     *计算公积金和商业贷款
     * @param loanYear
     * @param amount
     * @param monthRate
     * @param totalMonth
     */
    private void setData(int loanYear, int amount, float monthRate, int totalMonth) {
        mMonthPay = getEqualityInterestMonthPay(amount, monthRate, totalMonth);
        mTotalInterest = getEqualityTotalInterest(amount, mMonthPay, totalMonth);
        mTotalAmount = getEqualitTotalAmount(amount, mTotalInterest);

        //等额本金
        mFirstMonthPay = getFirstMonthPay(amount, monthRate, loanYear * 12);
        mDecreasePay = getDecreasePay(amount, monthRate, totalMonth);
        mEqualityCorpusTotalAmount = getEqualityCorpusTotalAmount(amount, monthRate, totalMonth);
        mCorpusTotalInterest = getEqualityCorpusTotalInterest(mEqualityCorpusTotalAmount, amount);
    }

    /**
     * 等额本金
     * 本息总额: 还款月数×(总贷款额×月利率-月利率×(总贷款额÷还款月数)*(还款月数-1)÷2+总贷款额÷还款月数
     *
     * @param amoun      本金
     * @param monthRate  月利率
     * @param totalMonth 总月数
     * @return
     */
    public int getEqualityCorpusTotalAmount(int amoun, float monthRate, int totalMonth) {
        float totalAmount = totalMonth * (amoun * monthRate - monthRate * (amoun / totalMonth) * (totalMonth - 1) / 2 + amoun / totalMonth);
        return BigDecimal.valueOf(totalAmount).setScale(2, RoundingMode.HALF_UP).intValue();
    }

    /**
     * 等额本金
     * 总利息 :本息总额-本金
     *
     * @param totalAmount 本息总额
     * @param amount
     * @return
     */
    public int getEqualityCorpusTotalInterest(int totalAmount, int amount) {
        return totalAmount - amount;
    }

    /**
     * 等额本金
     * 首月月供金额：(贷款本金÷还款月数)+(贷款本金)×月利率
     *
     * @param amount
     * @param monthRate
     * @param totalMonth
     * @return
     */
    public int getFirstMonthPay(int amount, float monthRate, int totalMonth) {
        float firstMonthPay = (amount / totalMonth) + amount * monthRate;
        return BigDecimal.valueOf(firstMonthPay).setScale(2, RoundingMode.HALF_UP).intValue();
    }

    /**
     * 等额本金
     * 月供递减金额：贷款本金÷还款月数×月利率
     *
     * @param amount     本金
     * @param monthRate  月利率
     * @param totalMonth 还款总月数
     * @return
     */
    public float getDecreasePay(int amount, float monthRate, int totalMonth) {
        float v = amount / totalMonth * monthRate;
        return BigDecimal.valueOf(v).setScale(2, RoundingMode.HALF_UP).floatValue();
    }

    /**
     * 等额本息  月供计算结果.
     *
     * @param amount     贷款本金
     * @param monthRate  月利率
     * @param totalMonth 贷款月数
     * @return
     */
    public int getEqualityInterestMonthPay(int amount, float monthRate, int totalMonth) {
        double pow = Math.pow(1 + monthRate, totalMonth);
        double monthPay = amount * (monthRate * pow) / (pow - 1);
        return BigDecimal.valueOf(monthPay).setScale(2, RoundingMode.HALF_UP).intValue();
    }

    /**
     * 等额本息 总利息
     *
     * @param amount     本金
     * @param monthPay   月供
     * @param totalMonth 还款总月数
     * @return
     */
    public int getEqualityTotalInterest(int amount, int monthPay, int totalMonth) {
        return monthPay * totalMonth - amount;
    }

    /**
     * 等额本息  本息总额
     *
     * @param amount      本金
     * @param totalInrest 总利息
     * @return
     */
    public int getEqualitTotalAmount(int amount, int totalInrest) {
        return totalInrest + amount;
    }


    /**
     * 获取月利率
     *
     * @param rate
     * @return
     */
    private float getMonthRate(float rate) {
        return BigDecimal.valueOf(rate / 100 / 12).setScale(6, BigDecimal.ROUND_HALF_UP).floatValue();
    }

    /**
     * 联系经纪人
     */
    private void setAgentTextView() {
        tv_connect_agent.setHighlightColor(getResources().getColor(android.R.color.transparent));
        SpannableString spanableInfo = new SpannableString("如需了解更多房贷信息，请与附近的经纪人联系。");
        spanableInfo.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.orange_ff9933)), 13, spanableInfo.length(), Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
        spanableInfo.setSpan(new Clickable(clickListener), 13, spanableInfo.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
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
