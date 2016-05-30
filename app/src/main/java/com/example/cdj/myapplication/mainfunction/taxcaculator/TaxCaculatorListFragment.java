package com.example.cdj.myapplication.mainfunction.taxcaculator;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.example.cdj.myapplication.R;
import com.example.cdj.myapplication.base.BackHandledBaseFragment;
import com.example.cdj.myapplication.cusview.CommonFormLayout;
import com.orhanobut.logger.Logger;

/**
 * 住宅类型,卖方,距离上次交易,计征方式,买房首套界面
 * Created by cdj on 2016/5/26.
 */
public class TaxCaculatorListFragment extends BackHandledBaseFragment implements View.OnClickListener {

    private TextView tv_title;
    private CommonFormLayout form_house_one;
    private CommonFormLayout form_house_two;
    //    private TaxType mTaxType;
    private ListView lv_listview;
    private CommonFormLayout form_house_three;
    private int mKey;
    private TaxMainFragment mMainFragment;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootview = inflater.inflate(R.layout.fragment_tax_caculator_list, null);
        init(rootview);
        return rootview;
    }

    private void init(View rootView) {
        rootView.setClickable(true);

        mMainFragment = (TaxMainFragment) getFragmentManager().findFragmentByTag(TaxMainFragment.class.getName());


        rootView.findViewById(R.id.iv_back).setOnClickListener(this);
        form_house_one = (CommonFormLayout) rootView.findViewById(R.id.form_house_one);
        form_house_one.setOnClickListener(this);
        form_house_two = (CommonFormLayout) rootView.findViewById(R.id.form_house_two);
        form_house_two.setOnClickListener(this);
        form_house_three = (CommonFormLayout) rootView.findViewById(R.id.form_house_three);
        form_house_three.setOnClickListener(this);

//        lv_listview = (ListView) rootView.findViewById(R.id.lv_listview);

        tv_title = (TextView) rootView.findViewById(R.id.tv_title);
        Bundle bundle = getArguments();
        if (bundle != null) {
            mKey = bundle.getInt(TaxMainFragment.KEY);
//            mTaxType = (TaxType) bundle.getSerializable(TaxMainFragment.ENUM);
            switch (mKey) {
                case TaxMainFragment.TAX_HOUSE_TYPE:
                    tv_title.setText("住宅类型");
                    form_house_one.setContentText(R.string.Tax_housetype_normal);
                    form_house_two.setContentText(R.string.Tax_housetype_not_normal);
                    break;
//                case SALE_ONLY:
                case TaxMainFragment.TAX_HOUSE_SALE_ONLY:
                    tv_title.setText("卖方家庭唯一一套");
                    form_house_one.setContentText(R.string.Tax_housetype_onlyone);
                    form_house_two.setContentText(R.string.Tax_housetype_not_only);
                    break;
                case TaxMainFragment.TAX_HOUSE_LATEST_SALE:
                    tv_title.setText("距离上次交易");
                    form_house_one.setContentText(R.string.Tax_housetype_5year);
                    form_house_two.setContentText(R.string.Tax_housetype_2and_5year);
                    form_house_three.setVisibility(View.VISIBLE);
                    form_house_three.setContentText(R.string.Tax_housetype_not_2);
                    break;
                case TaxMainFragment.TAX_HOUSE_PAY_TYPE:
                    tv_title.setText("计征方式");
                    form_house_one.setContentText(R.string.Tax_housetype_total);
                    form_house_two.setContentText(R.string.Tax_housetype_chajia);

                    String houseType = mMainFragment.mForm_house_type.getContentText().toString();
                    String latestStr = mMainFragment.form_house_latest_sale.getContentText().toString();
                    int buyHouseTime = TaxUitls.getBuyHouseTime(latestStr);
                    if (houseType.equals(TaxMainFragment.House_NOT_NORMAL) && TaxUitls.isSpcialCcity(mMainFragment.mCurrentCity)&&buyHouseTime<2) {
                        Logger.d("22222222222222222222222222222");
                        form_house_one.setClickable(false);
                        form_house_one.setContentTextColor(getResources().getColor(R.color.grey_888888));
                    }
                    break;
                case TaxMainFragment.TAX_HOUSE_FIRST_BUY:
                    tv_title.setText("卖方家庭首套");
                    form_house_one.setContentText(R.string.Tax_housetype_first);
                    form_house_two.setContentText(R.string.Tax_housetype_not_first);
                    break;
            }
//            if (TaxMainFragment.TAX_HOUSE_TYPE == key) {
//                tv_title.setText("住宅类型");
////                form_house_one.setContentText(R.st);
//
//            } else if (TaxMainFragment.TAX_HOUSE_SALE_ONLY == key) {
//                tv_title.setText("卖方家庭唯一一套");
//            } else if (TaxMainFragment.TAX_HOUSE_LATEST_SALE == key) {
//                tv_title.setText("距离上次交易");
//            } else if (TaxMainFragment.TAX_HOUSE_PAY_TYPE == key) {
//                tv_title.setText("计征方式");
//            } else if (TaxMainFragment.TAX_HOUSE_FIRST_BUY == key) {
//                tv_title.setText("卖方家庭首套");
//            }
        }
    }

    @Override
    public void onClick(View v) {
        TaxMainFragment mainFragment = (TaxMainFragment) getFragmentManager().findFragmentByTag(TaxMainFragment.class.getName());
        int id = v.getId();
        if (v.getId() == R.id.iv_back) {
            getFragmentManager().popBackStack();
        } else if (id == R.id.form_house_one) {

            switch (mKey) {
                case TaxMainFragment.TAX_HOUSE_TYPE:
//                    TaxType.HOUSE_TYPE.setName(TaxMainFragment.HOUSE_NORMAL);
                    mainFragment.mForm_house_type.setContentText(TaxMainFragment.HOUSE_NORMAL);
                    break;
                case TaxMainFragment.TAX_HOUSE_SALE_ONLY:
//                    TaxType.SALE_ONLY.setName(TaxMainFragment.ONLYONE);
                    mainFragment.form_house_sale_only.setContentText(TaxMainFragment.ONLYONE);
                    break;
                case TaxMainFragment.TAX_HOUSE_LATEST_SALE:
//                    TaxType.LATEST_SALE.setName(TaxMainFragment.OVER_5_YEARS);
                    mainFragment.form_house_latest_sale.setContentText(TaxMainFragment.OVER_5_YEARS);
                    break;
                case TaxMainFragment.TAX_HOUSE_PAY_TYPE:
//                    TaxType.PAYTAX_TYPE.setName(TaxMainFragment.TOTAL_PRICE);
//                    mainFragment.form_house_pay_type.setContentText(TaxMainFragment.TOTAL_PRICE);
                    mainFragment.setHousePayTypeTotalPrice();
                    mainFragment.verifyBtn();
                    break;
                case TaxMainFragment.TAX_HOUSE_FIRST_BUY:
//                    TaxType.FIRST_BUY.setName(TaxMainFragment.Buy_First);
                    mainFragment.form_house_first_buy.setContentText(TaxMainFragment.Buy_First);
                    break;
            }
            getFragmentManager().popBackStack(TaxMainFragment.class.getName(), 0);
//            taxMainFragment.reFreshView();

        } else if (id == R.id.form_house_two) {

            if (mKey == TaxMainFragment.TAX_HOUSE_PAY_TYPE) { //计征方式是差价时. 打开 TaxCaculatorInputFragment 输入界面
                Bundle bundle = new Bundle();
                bundle.putInt(TaxMainFragment.KEY, TaxMainFragment.KEY_TAX_DIFFERENCE_PRICE);
                mCallback.onAddFragment(TaxCaculatorInputFragment.class.getName(), bundle);
            } else {
                switch (mKey) {
                    case TaxMainFragment.TAX_HOUSE_TYPE:
//                        TaxType.HOUSE_TYPE.setName(TaxMainFragment.House_NOT_NORMAL);
                        mainFragment.mForm_house_type.setContentText(TaxMainFragment.House_NOT_NORMAL);
                        break;
                    case TaxMainFragment.TAX_HOUSE_SALE_ONLY:
//                        TaxType.SALE_ONLY.setName(TaxMainFragment.NOT_ONLYONE);
                        mainFragment.form_house_sale_only.setContentText(TaxMainFragment.NOT_ONLYONE);
                        break;
                    case TaxMainFragment.TAX_HOUSE_LATEST_SALE:
//                        TaxType.LATEST_SALE.setName(TaxMainFragment.OVER_2_5_YEARS);
                        mainFragment.form_house_latest_sale.setContentText(TaxMainFragment.OVER_2_5_YEARS);
                        break;
                    case TaxMainFragment.TAX_HOUSE_FIRST_BUY:
//                        TaxType.FIRST_BUY.setName(TaxMainFragment.NOT_BUY_FIRST);
                        mainFragment.form_house_first_buy.setContentText(TaxMainFragment.NOT_BUY_FIRST);
                        break;
//                    case TaxMainFragment.TAX_HOUSE_PAY_TYPE://差价
//                        Bundle bundle = new Bundle();
//                        bundle.putInt(TaxMainFragment.KEY, TaxMainFragment.KEY_TAX_DIFFERENCE_PRICE);
//                        mCallback.onAddFragment(TaxCaculatorInputFragment.class.getName(), bundle);
//                        TaxType.PAYTAX_TYPE.setName(TaxMainFragment.DIFFERENCE_PRICE);
//                        break;
                }
                getFragmentManager().popBackStack(TaxMainFragment.class.getName(), 0);
//                taxMainFragment.reFreshView();
            }
        } else if (id == R.id.form_house_three) {
//            TaxType.LATEST_SALE.setName(TaxMainFragment.LESS_2_YEARS);
            mainFragment.form_house_latest_sale.setContentText(TaxMainFragment.LESS_2_YEARS);
            getFragmentManager().popBackStack(TaxMainFragment.class.getName(), 0);
//            taxMainFragment.reFreshView();
        }
    }


}

