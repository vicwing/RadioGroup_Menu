package com.example.cdj.myapplication.mainfunction.taxcaculator;

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
import com.example.cdj.myapplication.cusview.CommonFormLayout;
import com.orhanobut.logger.Logger;

import java.text.NumberFormat;
import java.util.Locale;

/**
 * Created by cdj on 2016/5/27.
 */
public class TaxResultFragment extends BackHandledBaseFragment implements View.OnClickListener {

    private int contractTax;//契税
    private int valueAddTax;//value added tax 增值税
    private int personaIncomeTax;//个人所得税

    private float mValueAddTaxRate = 0.05f;//增值税税率
    private float mAddtionTax = 0.12f;//城建附加税
    private boolean isOnlyOne = false;
    private CommonFormLayout mForm_contract_tax;
    private CommonFormLayout mForm_personal_tax;
    private CommonFormLayout mTv_total_tax;
    private TextView tv_value_add_tax;
    private View iv_value_add_desc;
    private TextView tv_connect_agent;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View rootView = inflater.inflate(R.layout.fragment_tax_result, null);
        initView(rootView);
        return rootView;
    }

    private void initView(View rootView) {
        rootView.setClickable(true);
        rootView.findViewById(R.id.iv_back).setOnClickListener(this);
        rootView.findViewById(R.id.btn_do_back).setOnClickListener(this);
        ((TextView) rootView.findViewById(R.id.tv_title)).setText(R.string.tax_caculator);
        mForm_contract_tax = (CommonFormLayout) rootView.findViewById(R.id.form_contract_tax);

        iv_value_add_desc = rootView.findViewById(R.id.iv_value_add_desc);
        tv_value_add_tax = (TextView) rootView.findViewById(R.id.tv_value_add_tax);

        mForm_personal_tax = (CommonFormLayout) rootView.findViewById(R.id.form_personal_tax);
        mTv_total_tax = (CommonFormLayout) rootView.findViewById(R.id.tv_total_tax);

        Logger.d("结果..." + TaxType.HOUSE_TYPE.getName() + TaxType.SALE_ONLY.getName() + "  " + TaxType.LATEST_SALE.getName());
        tv_connect_agent = (TextView) rootView.findViewById(R.id.tv_connect_agent);
        initAgentTextView();

        TaxMainFragment mainFragment = (TaxMainFragment) getFragmentManager().findFragmentByTag(TaxMainFragment.class.getName());
//        String housePrice = mainFragment.form_house_price.getContentText().toString();
//        String houseArea = mainFragment.form_house_area.getContentText().toString();

        String houseType = mainFragment.mForm_house_type.getContentText().toString();
        String saleOnlyOne = mainFragment.form_house_sale_only.getContentText().toString();
        String latestSale = mainFragment.form_house_latest_sale.getContentText().toString();
        String payType = mainFragment.form_house_pay_type.getContentText().toString();
        String buyFirstBuy = mainFragment.form_house_first_buy.getContentText().toString();


        String currentCity = mainFragment.mCurrentCity;

        int housePriceInt = mainFragment.getHousePrice() * 10000;
        int houseAreaInt = mainFragment.getHouseArea();
        int differencePrice = mainFragment.getDifferencePrice() * 10000;

        int valueAddTax = TaxCaculatorUitls.getValueAddTax(housePriceInt, TaxCaculatorUitls.getBuyHouseTime(latestSale), houseType, currentCity, differencePrice);

        int contractTax = TaxCaculatorUitls.getContractTax(housePriceInt - valueAddTax, houseAreaInt, buyFirstBuy, currentCity);
        int personaIncomeTax = TaxCaculatorUitls.getPersonaIncomeTax(housePriceInt - valueAddTax, TaxCaculatorUitls.getBuyHouseTime(latestSale), TaxCaculatorUitls.isOnlyHouse(saleOnlyOne));

        mForm_contract_tax.setContentText(String.valueOf(contractTax) + "元");
        tv_value_add_tax.setText(String.valueOf(valueAddTax) + "元");
        mForm_personal_tax.setContentText(String.valueOf(personaIncomeTax) + "元");

        mTv_total_tax.setContentText(String.valueOf(contractTax + valueAddTax + personaIncomeTax) + "元");


        NumberFormat numberFormat = NumberFormat.getInstance(Locale.CHINESE);
        tv_value_add_tax.setText(numberFormat.format(valueAddTax) + "元");
        mForm_contract_tax.setContentText(numberFormat.format(contractTax) + "元");
        mForm_personal_tax.setContentText(numberFormat.format(personaIncomeTax) + "元");

        mTv_total_tax.setContentText(numberFormat.format(contractTax + valueAddTax + personaIncomeTax) + "元");

        Logger.d("价格 " + housePriceInt + "  面积  " + houseAreaInt + " 住宅类型 " + houseType + "  卖方唯一 " + saleOnlyOne
                + "  上次交易 " + latestSale + "  计征方式 " + payType + " 卖方首套  " + buyFirstBuy);
    }


    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.iv_value_add_desc) {//弹框

        } else if (id == R.id.iv_back) {
            getFragmentManager().popBackStack(TaxMainFragment.class.getName(), 0);
        } else if (id == R.id.btn_do_back) {
//            getFragmentManager().popBackStack();
            getFragmentManager().popBackStack(TaxMainFragment.class.getName(), 0);
        }
    }

    /**
     * 联系经纪人
     */
    private void initAgentTextView() {
        tv_connect_agent.setHighlightColor(getResources().getColor(android.R.color.transparent));
        SpannableString spanableInfo = new SpannableString(getString(R.string.tax_caculator_contact_agent));
        int start = 9;
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

        }
    }
}
