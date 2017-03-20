package com.example.cdj.myapplication.mainfunction.taxcaculator;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.cdj.myapplication.R;
import com.example.cdj.myapplication.base.BackHandledBaseFragment;
import com.example.cdj.myapplication.widget.CommonFormLayout;
import com.example.cdj.myapplication.mainfunction.caculate.impl.OnHeadlineSelectedListener;
import com.orhanobut.logger.Logger;

/**
 * 税费计算器
 * Created by cdj onCallBackData 2016/5/6.
 */
public class TaxMainFragment extends BackHandledBaseFragment implements View.OnClickListener ,TextWatcher{

    public static final int KEY_TAX_HOUSE_AREA = 0x01; //房屋面积
    public static final int KEY_TAX_HOUSE_PRICE = 0x02;//房屋总价
    public static final int KEY_TAX_DIFFERENCE_PRICE = 0x13;//差价
    private String defaultCity = "shenzhen";
    public String mCurrentCity = defaultCity;
    private static final  String CURRENT_CITY = "current_city";

    public static final int TAX_HOUSE_PAY_TYPE = 0x06;//计征方式
    public static final int TAX_HOUSE_TYPE = 0x03;//住宅类型
    public static final int TAX_HOUSE_SALE_ONLY = 0x04;//卖方唯一
    public static final int TAX_HOUSE_LATEST_SALE = 0x05;//距离上次交易
    public static final int TAX_HOUSE_FIRST_BUY = 0x07;//买方首套

    private static final String ARG_PARAM1 = "";
    private static final String ARG_PARAM2 = "";

    public static String KEY = "KEY";

    private int defaultPrice = 100;
    private int mHouseArea;//房屋面积
    private int mHousePrice = defaultPrice;//房屋总价



    public static String HOUSE_NORMAL = "普通住房";//住宅类型
    public static String House_NOT_NORMAL = "非普通住房";//住宅类型

    public static String ONLYONE = "唯一一套";//卖方唯一
    public static String NOT_ONLYONE = "非唯一一套";//卖方唯一

    public static String OVER_5_YEARS = "满5年";//距离上次交易
    public static String OVER_2_5_YEARS = "满2年";
    public static String LESS_2_YEARS = "不满2年";

    public static String TOTAL_PRICE = "总价";//计征方式
    public static String DIFFERENCE_PRICE = "差价";

    public static String Buy_First = "首套";//买方首套
    public static String NOT_BUY_FIRST = "非首套";//买方首套

    private int differencePrice=0 ;//差价
    private String inputDifferenceStr ="请输入差价";
    public CommonFormLayout form_house_area;
    public CommonFormLayout form_house_price;
    public CommonFormLayout mForm_house_type;
    public CommonFormLayout form_house_sale_only;
    public CommonFormLayout form_house_latest_sale;
    public CommonFormLayout form_house_pay_type;
    public CommonFormLayout form_house_first_buy;

    private Button mBtn_do_caculate;


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
        ((TextView) layout.findViewById(R.id.tv_title)).setText(R.string.tax_caculator);

        form_house_area = (CommonFormLayout) layout.findViewById(R.id.form_house_area);
        form_house_area.setOnClickListener(this);
        form_house_area.addTextChangedListener(this);

        form_house_price = (CommonFormLayout) layout.findViewById(R.id.form_house_price);
        form_house_price.setOnClickListener(this);
        form_house_price.addTextChangedListener(this);

        mForm_house_type = (CommonFormLayout) layout.findViewById(R.id.form_house_type);
        mForm_house_type.setOnClickListener(this);
        mForm_house_type.addTextChangedListener(this);

        form_house_sale_only = (CommonFormLayout) layout.findViewById(R.id.form_house_sale_only);
        form_house_sale_only.setOnClickListener(this);

        form_house_latest_sale = (CommonFormLayout) layout.findViewById(R.id.form_house_latest_sale);
        form_house_latest_sale.setOnClickListener(this);
        form_house_latest_sale.addTextChangedListener(this);

        form_house_pay_type = (CommonFormLayout) layout.findViewById(R.id.form_house_pay_type);//计征方式
        form_house_pay_type.setOnClickListener(this);
//        form_house_pay_type.addTextChangedListener(this);

        form_house_first_buy = (CommonFormLayout) layout.findViewById(R.id.form_house_first_buy);
        form_house_first_buy.setOnClickListener(this);

        mBtn_do_caculate = (Button) layout.findViewById(R.id.btn_do_caculate);
        mBtn_do_caculate.setOnClickListener(this);
        layout.findViewById(R.id.iv_back).setOnClickListener(this);

        TaxType.HOUSE_TYPE.setName(HOUSE_NORMAL);
        TaxType.SALE_ONLY.setName(ONLYONE);
        TaxType.LATEST_SALE.setName(OVER_5_YEARS);
        TaxType.PAYTAX_TYPE.setName(TOTAL_PRICE);
        TaxType.FIRST_BUY.setName(Buy_First);

        Bundle bundle = getArguments();
        if (bundle != null) {
            defaultPrice = bundle.getInt(TaxCaculatorActivity.HOUSE_PRICE, 0);
            mHouseArea = bundle.getInt(TaxCaculatorActivity.HOUSE_AREA, 0);
            if (defaultPrice==0&&mHouseArea==0){
                form_house_area.setContentText("");
                form_house_price.setContentText("");
            }else {
                mHousePrice = defaultPrice;
                form_house_area.setContentText(mHouseArea + "㎡");
                form_house_price.setContentText(mHousePrice +"万元");
            }
//            Logger.d("price  " + mHousePrice + "  defaultPrice " + defaultPrice + "  area  " + mHouseArea);
            mCurrentCity = bundle.getString(CURRENT_CITY,defaultCity);

        } else {
            form_house_area.setContentText("");
            form_house_price.setContentText("");
        }

        mForm_house_type.setContentText(TaxType.HOUSE_TYPE.getName());
        form_house_sale_only.setContentText(TaxType.SALE_ONLY.getName());
        form_house_latest_sale.setContentText(TaxType.LATEST_SALE.getName());
        form_house_pay_type.setContentText(TaxType.PAYTAX_TYPE.getName());
        form_house_first_buy.setContentText(TaxType.FIRST_BUY.getName());

//        verifyPayType();
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
            bundle.putInt(KEY, KEY_TAX_HOUSE_AREA);
            mCallback.onAddFragment(TaxCaculatorInputFragment.class.getName(), bundle);
        } else if (id == R.id.form_house_price) {//房屋总价
            bundle.putInt(KEY, KEY_TAX_HOUSE_PRICE);
            mCallback.onAddFragment(TaxCaculatorInputFragment.class.getName(), bundle);
        } else if (id == R.id.form_house_type) {//住宅类型
            bundle.putInt(KEY, TAX_HOUSE_TYPE);
//            bundle.putSerializable(ENUM, TaxType.HOUSE_TYPE);
            mCallback.onAddFragment(TaxCaculatorListFragment.class.getName(), bundle);
        } else if (id == R.id.form_house_sale_only) {//卖方唯一
            bundle.putInt(KEY, TAX_HOUSE_SALE_ONLY);
//            bundle.putSerializable(ENUM, TaxType.SALE_ONLY);
            mCallback.onAddFragment(TaxCaculatorListFragment.class.getName(), bundle);
        } else if (id == R.id.form_house_latest_sale) {//距离上次交易
            bundle.putInt(KEY, TAX_HOUSE_LATEST_SALE);
//            bundle.putSerializable(ENUM, TaxType.LATEST_SALE);
            mCallback.onAddFragment(TaxCaculatorListFragment.class.getName(), bundle);
        } else if (id == R.id.form_house_pay_type) {//计征方式
//            String typeText = form_house_pay_type.getContentText().toString();
//            if (typeText.equals(inputDifferenceStr)){
//                bundle.putInt(TaxMainFragment.KEY, TaxMainFragment.KEY_TAX_DIFFERENCE_PRICE);
//                mCallback.onAddFragment(TaxCaculatorInputFragment.class.getName(), bundle);
//            }else {
                bundle.putInt(KEY, TAX_HOUSE_PAY_TYPE);
                mCallback.onAddFragment(TaxCaculatorListFragment.class.getName(), bundle);
//            }
        } else if (id == R.id.form_house_first_buy) {//买方首套
            bundle.putInt(KEY, TAX_HOUSE_FIRST_BUY);
            mCallback.onAddFragment(TaxCaculatorListFragment.class.getName(), bundle);
        }
    }

//    public void reFreshView() {
//
////        form_house_area.setContentText(mHouseArea + "㎡");
////        form_house_price.setContentText(mHousePrice +"万元");
//
//        mForm_house_type.setContentText(TaxType.HOUSE_TYPE.getName());
//        form_house_sale_only.setContentText(TaxType.SALE_ONLY.getName());
//        form_house_latest_sale.setContentText(TaxType.LATEST_SALE.getName());
//
//
//        if (TaxType.PAYTAX_TYPE.getName().equals(TOTAL_PRICE)){
//            form_house_pay_type.setContentText(TaxType.PAYTAX_TYPE.getName());
//        }else if (TaxType.PAYTAX_TYPE.getName().equals(inputDifferenceStr)){
//            form_house_pay_type.setContentText(inputDifferenceStr);
//        }else {
//            form_house_pay_type.setContentText("差价 "+differencePrice+"万");
//        }
//        form_house_first_buy.setContentText(TaxType.FIRST_BUY.getName());
//
//    }


    public int getHouseArea() {
        return mHouseArea;
    }

    public void setHouseArea(int houseArea) {
        mHouseArea = houseArea;
        form_house_area.setContentText(mHouseArea + "㎡");
    }

    public int getHousePrice() {
        return mHousePrice;
    }

    public void setHousePrice(int housePrice) {
        mHousePrice = housePrice;
        form_house_price.setContentText(mHousePrice +"万元");
    }

    public int getDifferencePrice() {
        return differencePrice;
    }

    public void setDifferencePrice(int differencePrice) {
        this.differencePrice = differencePrice;
        form_house_pay_type.setContentTextColor(getResources().getColor(R.color.black_33333));
        form_house_pay_type.setContentText("差价 "+differencePrice+"万元");
        verifyBtn();
    }
    /**
     *  计征方式,选择总价时,
     */
    public void setHousePayTypeTotalPrice(){
        this.differencePrice = 0;
        form_house_pay_type.setContentTextColor(getResources().getColor(R.color.black_33333));
        form_house_pay_type.setContentText(TaxMainFragment.TOTAL_PRICE);
//        verifyBtn();
    }

    public void setFormHuoseType(){

    }
    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {


    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        verifyBtn();
//        verifyPayType();
    }

    /**
     *  检验按钮是否可点击
     */
    public void verifyBtn() {
        CharSequence areaContentText = form_house_area.getContentText();
        CharSequence priceContentText = form_house_price.getContentText();
        if (verifyPayType()&&!TextUtils.isEmpty(areaContentText)&&!TextUtils.isEmpty(priceContentText)){
            mBtn_do_caculate.setEnabled(true);
        }else {
            mBtn_do_caculate.setEnabled(false);
        }
    }


    /**
     * 验证计征方式
     */
    private boolean verifyPayType() {
        if (mCurrentCity.equalsIgnoreCase(EnumCity.SHENZHEN.name) || mCurrentCity.equalsIgnoreCase(EnumCity.GUANGZHOU.name) ||
                mCurrentCity.equalsIgnoreCase(EnumCity.BEIJING.name) || mCurrentCity.equalsIgnoreCase(EnumCity.SHANGHAI.name)) {
            String houseTypeContentText =  mForm_house_type.getContentText().toString();
            String latestContentText = form_house_latest_sale.getContentText().toString();
            Logger.d("!latestContentText.equals(LESS_2_YEARS)  "+!latestContentText.equals(LESS_2_YEARS));
            if (houseTypeContentText.equals(House_NOT_NORMAL)&&!latestContentText.equals(LESS_2_YEARS)){
//                TaxType.PAYTAX_TYPE.setName(inputDifferenceStr);
                if (differencePrice==0){
//                    form_house_pay_type.setContentText(inputDifferenceStr);
//                    setDifferencePrice();
                    form_house_pay_type.setContentText(inputDifferenceStr);
                    form_house_pay_type.setContentTextColor(getResources().getColor(R.color.grey_d3d3d3));
                    return  false;
                }
            }else {
                setHousePayTypeTotalPrice();
            }

        }
        return true;
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