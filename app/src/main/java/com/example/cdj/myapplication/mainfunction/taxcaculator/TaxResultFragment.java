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

import java.math.BigDecimal;
import java.math.RoundingMode;

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
    private TextView mTv_value_add_tax;
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
        ((TextView) rootView.findViewById(R.id.tv_title)).setText(R.string.tax_caculator);
        mForm_contract_tax = (CommonFormLayout) rootView.findViewById(R.id.form_contract_tax);

        iv_value_add_desc = rootView.findViewById(R.id.iv_value_add_desc);
        mTv_value_add_tax = (TextView) rootView.findViewById(R.id.tv_value_add_tax);

        mForm_personal_tax = (CommonFormLayout) rootView.findViewById(R.id.form_personal_tax);
        mTv_total_tax = (CommonFormLayout) rootView.findViewById(R.id.tv_total_tax);

        Logger.d("结果..." + TaxType.HOUSE_TYPE.getName() + TaxType.SALE_ONLY.getName() + "  " + TaxType.LATEST_SALE.getName());

        int valueAddTax = getValueAddTax(1000000, 5, "非普通住房", "shenzhen", 200000);
        Logger.d("增值税  "+valueAddTax);

        tv_connect_agent = (TextView) rootView.findViewById(R.id.tv_connect_agent);
        setAgentTextView();
    }

    /**
     * 获取增值税
     *
     * @param totalPrice       房屋总价
     * @param houseYear       房屋年限
     * @param houseType       普通住房/非普通住房
     * @param currentCity     当前城市
     * @param differencePrice 房屋差价
     * @return
     */
    public int getValueAddTax(int totalPrice, int houseYear, String houseType, String currentCity, int differencePrice) {
        if (houseYear < 2) {//未满2年
            return BigDecimal.valueOf(totalPrice / 1.05f * 0.056f).setScale(2, RoundingMode.HALF_UP).intValue();
        } else {
            if ("普通住房".equals(houseType)) {
                return 0;
            } else {//非普通住房
                if (currentCity.equalsIgnoreCase(City.SHENZHEN.name) || currentCity.equalsIgnoreCase(City.GUANGZHOU.name) ||
                        currentCity.equalsIgnoreCase(City.BEIJING.name) || currentCity.equalsIgnoreCase(City.SHANGHAI.name)) { //北上广深
                    return BigDecimal.valueOf(differencePrice * 0.056f).setScale(2, RoundingMode.HALF_UP).intValue();
                } else {
                    return 0;
                }
            }
        }
    }


    /**
     * 个人所得税
     * @param totalPrice  房屋总价
     * @param houseYear   房屋年限
     * @param houseOnly   是否家庭唯一一套
     * @return
     */
    public int getPersonaIncomeTax(int totalPrice, int houseYear, boolean houseOnly) {

        if (houseYear < 5 || isOnlyOne) {//满5年免征
            return BigDecimal.valueOf(totalPrice).setScale(2, RoundingMode.HALF_UP).intValue();
        } else {
            return 0;
        }
    }

    /**
     * 契税
     * @param price       交易的价格(减去增值税后的价格)
     * @param area        房子面积
     * @param type        买方 是否首套,
     * @param currentCity 当前城市
     * @return
     */
    public int getContractTaxTax(int price, int area, String type, String currentCity) {
        if ("首套".equals(type)) {
            if (area > 90) {
                return BigDecimal.valueOf(price * 0.015f).setScale(1, RoundingMode.HALF_UP).intValue();
            } else {
                return BigDecimal.valueOf(price * 0.01f).setScale(1, RoundingMode.HALF_UP).intValue();
            }
        } else {
            if (currentCity.equalsIgnoreCase(City.SHENZHEN.name) || currentCity.equalsIgnoreCase(City.GUANGZHOU.name) ||
                    currentCity.equalsIgnoreCase(City.BEIJING.name) || currentCity.equalsIgnoreCase(City.SHANGHAI.name)) {
                return BigDecimal.valueOf(price * 0.03f).setScale(1, RoundingMode.HALF_UP).intValue();
            } else {
                if (area < 90) {
                    return BigDecimal.valueOf(price * 0.01f).setScale(1, RoundingMode.HALF_UP).intValue();
                } else {
                    return BigDecimal.valueOf(price * 0.02f).setScale(1, RoundingMode.HALF_UP).intValue();
                }
            }
        }
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id==R.id.iv_value_add_desc){//弹框

        }else if (id==R.id.iv_back){
            getFragmentManager().popBackStack();
        }
    }

    public enum City {
        SHENZHEN("shenzhen"), GUANGZHOU("guangzhou"), BEIJING("beijing"), SHANGHAI("shanghai");
        public String name;

        City(String name) {
            this.name = name;
        }
    }


    /**
     * 联系经纪人
     */
    private void setAgentTextView() {
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
//            ds.setColor(getResources().getColor(R.color.video_comment_like_number));
        }
    }
}
