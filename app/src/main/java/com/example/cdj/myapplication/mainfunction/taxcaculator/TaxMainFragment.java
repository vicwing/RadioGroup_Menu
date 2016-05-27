package com.example.cdj.myapplication.mainfunction.taxcaculator;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.cdj.myapplication.R;
import com.example.cdj.myapplication.base.BackHandledBaseFragment;
import com.example.cdj.myapplication.cusview.CommonFormLayout;
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
    public static String ENUM = "enum";

    private int defaultPrice = 100;
    private int mHouseArea;//房屋面积
    private int mHousePrice = defaultPrice;//房屋总价

    private String mHouseType = "普通住房";//住宅类型
    private String mSaleOnlyOne = "唯一一套";//卖方唯一
    private String mLatestSale = "满5年";//距离上次交易
    private String mPayTaxType = "总价";//计征方式
    private String mBuyFirst = "首套";//买方首套



    private Button btn_do_caculate;
    private CommonFormLayout form_house_area;
    private CommonFormLayout form_house_price;
    private CommonFormLayout mForm_house_type;
    private CommonFormLayout form_house_sale_only;
    private CommonFormLayout form_house_latest_sale;
    private CommonFormLayout form_house_pay_type;
    private CommonFormLayout form_house_first_buy;

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

    public static final String HOUSE_PRICE = "house_price";
    public static final String HOUSE_AREA = "house_area";

    private void init(View layout) {
        ((TextView) layout.findViewById(R.id.tv_title)).setText(R.string.tax_caculator);

        form_house_area = (CommonFormLayout) layout.findViewById(R.id.form_house_area);
        form_house_area.setOnClickListener(this);
        form_house_price = (CommonFormLayout) layout.findViewById(R.id.form_house_price);
        form_house_price.setOnClickListener(this);

        mForm_house_type = (CommonFormLayout) layout.findViewById(R.id.form_house_type);
        mForm_house_type.setOnClickListener(this);
        form_house_sale_only = (CommonFormLayout) layout.findViewById(R.id.form_house_sale_only);
        form_house_sale_only.setOnClickListener(this);
        form_house_latest_sale = (CommonFormLayout) layout.findViewById(R.id.form_house_latest_sale);
        form_house_latest_sale.setOnClickListener(this);
        form_house_pay_type = (CommonFormLayout) layout.findViewById(R.id.form_house_pay_type);
        form_house_pay_type.setOnClickListener(this);
        form_house_first_buy = (CommonFormLayout) layout.findViewById(R.id.form_house_first_buy);
        form_house_first_buy.setOnClickListener(this);

        layout.findViewById(R.id.btn_do_caculate).setOnClickListener(this);
        layout.findViewById(R.id.iv_back).setOnClickListener(this);

        TaxType.HOUSE_TYPE.setName(getString(R.string.Tax_housetype_normal));
        TaxType.SALE_ONLY.setName(getString(R.string.Tax_housetype_onlyone));
        TaxType.LATEST_SALE.setName(getString(R.string.Tax_housetype_5year));
        TaxType.PAYTAX_TYPE.setName(getString(R.string.Tax_housetype_total));
        TaxType.FIRST_BUY.setName(getString(R.string.Tax_housetype_first));

        Bundle bundle = getArguments();
        if (bundle != null) {
            defaultPrice = bundle.getInt(HOUSE_PRICE, 0);
            mHousePrice = defaultPrice;

            mHouseArea = bundle.getInt(HOUSE_AREA, 0);
            form_house_area.setContentText(mHouseArea + "㎡");
            form_house_price.setContentText(mHousePrice +"万元");
            Logger.d("price  " + mHousePrice + "  defaultPrice " + defaultPrice + "  area  " + mHouseArea);
        } else {
            form_house_area.setContentText("");
            form_house_price.setContentText("");

            mForm_house_type.setContentText(TaxType.HOUSE_TYPE.getName());
            form_house_sale_only.setContentText(TaxType.SALE_ONLY.getName());
            form_house_latest_sale.setContentText(TaxType.LATEST_SALE.getName());
            form_house_pay_type.setContentText(TaxType.PAYTAX_TYPE.getName());
            form_house_first_buy.setContentText(TaxType.FIRST_BUY.getName());
        }
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
        mCallback.onAddFragment(TaxResultFragment.class.getName(), bundle);
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
            bundle.putSerializable(ENUM, TaxType.HOUSE_TYPE);
            mCallback.onAddFragment(TaxCaculatorListFragment.class.getName(), bundle);
        } else if (id == R.id.form_house_sale_only) {//卖方唯一
            bundle.putInt(KEY, TAX_HOUSE_SALE_ONLY);
            bundle.putSerializable(ENUM, TaxType.SALE_ONLY);
            mCallback.onAddFragment(TaxCaculatorListFragment.class.getName(), bundle);
        } else if (id == R.id.form_house_latest_sale) {//距离上次交易
            bundle.putInt(KEY, TAX_HOUSE_LATEST_SALE);
            bundle.putSerializable(ENUM, TaxType.LATEST_SALE);
            mCallback.onAddFragment(TaxCaculatorListFragment.class.getName(), bundle);
        } else if (id == R.id.form_house_pay_type) {//计征方式
            bundle.putInt(KEY, TAX_HOUSE_PAY_TYPE);
            bundle.putSerializable(ENUM, TaxType.PAYTAX_TYPE);
            mCallback.onAddFragment(TaxCaculatorListFragment.class.getName(), bundle);
        } else if (id == R.id.form_house_first_buy) {//买方首套
            bundle.putInt(KEY, TAX_HOUSE_FIRST_BUY);
            bundle.putSerializable(ENUM, TaxType.FIRST_BUY);
            mCallback.onAddFragment(TaxCaculatorListFragment.class.getName(), bundle);
        }
    }

    public void reFreshView() {

        form_house_area.setContentText(mHouseArea + "㎡");
        form_house_price.setContentText(mHousePrice +"万元");

        mForm_house_type.setContentText(TaxType.HOUSE_TYPE.getName());
        form_house_sale_only.setContentText(TaxType.SALE_ONLY.getName());
        form_house_latest_sale.setContentText(TaxType.LATEST_SALE.getName());
        form_house_pay_type.setContentText(TaxType.PAYTAX_TYPE.getName());
        form_house_first_buy.setContentText(TaxType.FIRST_BUY.getName());

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

    public String getBuyFirst() {
        return mBuyFirst;
    }

    public void setBuyFirst(String buyFirst) {
        mBuyFirst = buyFirst;
    }

    public String getPayTaxType() {
        return mPayTaxType;
    }

    public void setPayTaxType(String payTaxType) {
        mPayTaxType = payTaxType;
    }

    public String getLatestSale() {
        return mLatestSale;
    }

    public void setLatestSale(String latestSale) {
        mLatestSale = latestSale;
    }

    public String getHouseType() {
        return mHouseType;
    }

    public void setHouseType(String houseType) {
        mHouseType = houseType;
    }

    public String getSaleOnlyOne() {
        return mSaleOnlyOne;
    }

    public void setSaleOnlyOne(String saleOnlyOne) {
        mSaleOnlyOne = saleOnlyOne;
    }

//    public enum TaxType {
//        HOUSE_TYPE, SALE_ONLY, LATEST_SALE, PAYTAX_TYPE, FIRST_BUY;
//        private String name;
//
//        public String getName() {
//            return name;
//        }
//
//        public void setName(String name) {
//            this.name = name;
//        }
//    }
}