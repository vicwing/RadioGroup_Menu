package com.example.cdj.myapplication.mainfunction.taxcaculator;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.cdj.myapplication.R;
import com.example.cdj.myapplication.base.BackHandledBaseFragment;
import com.orhanobut.logger.Logger;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Created by cdj on 2016/5/27.
 */
public class TaxResultFragment extends BackHandledBaseFragment {

    private int contractTax;//契税
    private int valueAddTax;//value added tax 增值税
    private int personaIncomeTax;//个人所得税

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View rootView = inflater.inflate(R.layout.fragment_caculator_sub_input, null);
        initView(rootView);
        return rootView;
    }

    private void initView(View rootView) {
        ((TextView) rootView.findViewById(R.id.tv_title)).setText(R.string.tax_caculator);

        Logger.d("结果..." + TaxType.HOUSE_TYPE.getName() + TaxType.SALE_ONLY.getName() + "  " + TaxType.LATEST_SALE.getName());
    }


    /**
     * 个人所得税
     *
     * @return
     */
    public int getPersonaIncomeTax() {

        return 0;
    }

    public int getValueAddTax() {

        return 0;
    }

    /**
     * 契税
     * @param price     交易的价格(减去增值税后的价格)
     * @param area      房子面积
     * @param type      买方 是否首套,
     * @param currentCity  当前城市
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
            if (currentCity.equalsIgnoreCase(City.SHENZHEN.name) ||   currentCity.equalsIgnoreCase(City.GUANGZHOU.name) ||
                    currentCity.equalsIgnoreCase(City.BEIJING.name) || currentCity.equalsIgnoreCase(City.SHANGHAI.name)){
                return BigDecimal.valueOf(price * 0.03f).setScale(1, RoundingMode.HALF_UP).intValue();
            }else {
                if (area<90){
                    return BigDecimal.valueOf(price * 0.01f).setScale(1, RoundingMode.HALF_UP).intValue();
                }else {
                    return BigDecimal.valueOf(price * 0.02f).setScale(1, RoundingMode.HALF_UP).intValue();
                }
            }
        }
    }

    public enum City {
        SHENZHEN("shenzhen"), GUANGZHOU("guangzhou"), BEIJING("beijing"), SHANGHAI("shanghai");
        public String name;

        City(String name) {
            this.name = name;
        }

    }

}
