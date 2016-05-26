package com.example.cdj.myapplication.mainfunction.taxcaculator;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.cdj.myapplication.R;
import com.example.cdj.myapplication.base.BackHandledBaseFragment;
import com.example.cdj.myapplication.mainfunction.caculate.impl.OnHeadlineSelectedListener;
import com.orhanobut.logger.Logger;

/**
 * 税费计算器
 * Created by cdj onCallBackData 2016/5/6.
 */
public class TaxMainFragment extends BackHandledBaseFragment implements View.OnClickListener {

    public static final int TAX_HOUSE_AREA = 0x01; //房屋面积
    public static final int TAX_HOUSE_PRICE = 0x02;//房屋总价

    public static final int TAX_HOUSE_TYPE = 0x03;//住宅类型
    public static final int TAX_HOUSE_SALE_ONLY = 0x04;//卖方唯一
    public static final int TAX_HOUSE_LATEST_SALE = 0x05;//距离上次交易
    public static final int TAX_HOUSE_PAY_TYPE = 0x06;//计征方式
    public static final int TAX_HOUSE_FIRST_BUY = 0x07;//买方首套

    private static final String ARG_PARAM1 = "";
    private static final String ARG_PARAM2 = "";
    public static String FROM_TAG = "from_tag";

    public static String KEY = "KEY";

    private int mHouseArea;//房屋面积
    private int mHousePrice;//房屋总价

    private int mHouseType;//住宅类型
    private int mSaleOnlyOne;//卖方唯一
    private int mLatestSale;//距离上次交易
    private int mPayTaxType;//计征方式
    private int mBuyFirst;//买方首套


    private Button btn_do_caculate;

    /**
     * 通过工厂方法来创建Fragment实例
     * 同时给Fragment来提供参数来使用
     *
     * @return Master_Fragment的实例.
     */
    public static TaxMainFragment newInstance(String param1, String param2) {
        TaxMainFragment fragment = new TaxMainFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public TaxMainFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View layout = inflater.inflate(R.layout.fragment_tax_caculator_main, null);
        init(layout);
        return layout;
    }


    private void init(View layout) {

        layout.findViewById(R.id.form_house_area).setOnClickListener(this);
        layout.findViewById(R.id.form_house_price).setOnClickListener(this);

        layout.findViewById(R.id.form_house_type).setOnClickListener(this);
        layout.findViewById(R.id.form_house_sale_only).setOnClickListener(this);
        layout.findViewById(R.id.form_house_latest_sale).setOnClickListener(this);
        layout.findViewById(R.id.form_house_pay_type).setOnClickListener(this);
        layout.findViewById(R.id.form_house_first_buy).setOnClickListener(this);

        layout.findViewById(R.id.btn_do_caculate).setOnClickListener(this);
        layout.findViewById(R.id.iv_back).setOnClickListener(this);


    }

    /**
     * 结果页
     */
    private void startResulFragment() {
//        Logger.d("贷款总额  " + "  利率  " + " 公积金利率 " + "贷款期限  " + " stack" + getChildFragmentManager().getBackStackEntryCount());
        Bundle bundle = new Bundle();
//        bundle.putInt(TOTAL_PRICE, mCommercialAmount);
//        bundle.putFloat(INTREST_RATE, mCommercialRate);
//        bundle.putInt(LOAN_TERM, mLoanTerm);
//        mCallback.onAddFragment(CaculateResultFragment.class.getName(), bundle);
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        // This makes sure that the container activity has implemented
        // the callback impl. If not, it throws an exception
        try {
            //
            mCallback = (OnHeadlineSelectedListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement OnHeadlineSelectedListener");
        }
    }


    @Override
    public void onClick(View v) {
        Bundle bundle = new Bundle();
        int id = v.getId();
        if (id == R.id.iv_back) {
            getActivity().finish();
        } else if (id == R.id.btn_do_caculate) {//开始计算
            startResulFragment();
        } else if (id == R.id.form_house_area) {//房屋面积
            bundle.putInt(KEY, TAX_HOUSE_AREA);
            mCallback.onAddFragment(TaxCaculatorInputFragment.class.getName(), bundle);
        } else if (id == R.id.form_house_price) {//房屋总价
            bundle.putInt(KEY, TAX_HOUSE_PRICE);
            mCallback.onAddFragment(TaxCaculatorInputFragment.class.getName(), bundle);
        } else if (id == R.id.form_house_type) {//住宅类型
            bundle.putInt(KEY, TAX_HOUSE_TYPE);
            mCallback.onAddFragment(TaxCaculatorListFragment.class.getName(), bundle);
        } else if (id == R.id.form_house_sale_only) {//卖方唯一
            bundle.putInt(KEY, TAX_HOUSE_SALE_ONLY);
            mCallback.onAddFragment(TaxCaculatorListFragment.class.getName(), bundle);
        } else if (id == R.id.form_house_latest_sale) {//距离上次交易
            bundle.putInt(KEY, TAX_HOUSE_LATEST_SALE);
            mCallback.onAddFragment(TaxCaculatorListFragment.class.getName(), bundle);
        } else if (id == R.id.form_house_pay_type) {//计征方式
            bundle.putInt(KEY, TAX_HOUSE_PAY_TYPE);
            mCallback.onAddFragment(TaxCaculatorListFragment.class.getName(), bundle);
        } else if (id == R.id.form_house_first_buy) {//买方首套
            bundle.putInt(KEY, TAX_HOUSE_FIRST_BUY);
            mCallback.onAddFragment(TaxCaculatorListFragment.class.getName(), bundle);
        }
    }

    public void reFreshView() {

    }


    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    public void onPause() {
        super.onPause();
        Logger.d("main fragment  onPause ");
    }

    @Override
    public void onStop() {
        super.onStop();
        Logger.i("main fragment  onStop " + getFragmentManager().getBackStackEntryCount());
    }

    public int getHouseArea() {
        return mHouseArea;
    }

    public void setHouseArea(int houseArea) {
        mHouseArea = houseArea;
    }

    public int getHousePrice() {
        return mHousePrice;
    }

    public void setHousePrice(int housePrice) {
        mHousePrice = housePrice;
    }

    public int getHouseType() {
        return mHouseType;
    }

    public void setHouseType(int houseType) {
        mHouseType = houseType;
    }

    public int getSaleOnlyOne() {
        return mSaleOnlyOne;
    }

    public void setSaleOnlyOne(int saleOnlyOne) {
        mSaleOnlyOne = saleOnlyOne;
    }

    public int getLatestSale() {
        return mLatestSale;
    }

    public void setLatestSale(int latestSale) {
        mLatestSale = latestSale;
    }

    public int getPayTaxType() {
        return mPayTaxType;
    }

    public void setPayTaxType(int payTaxType) {
        mPayTaxType = payTaxType;
    }

    public int getBuyFirst() {
        return mBuyFirst;
    }

    public void setBuyFirst(int buyFirst) {
        mBuyFirst = buyFirst;
    }
}