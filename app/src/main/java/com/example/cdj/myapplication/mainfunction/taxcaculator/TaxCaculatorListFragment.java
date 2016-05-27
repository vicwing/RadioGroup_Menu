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

/**
 * 住宅类型,卖方,距离上次交易,计征方式,买房首套界面
 * Created by cdj on 2016/5/26.
 */
public class TaxCaculatorListFragment extends BackHandledBaseFragment implements View.OnClickListener {

    private TextView tv_title;
    private CommonFormLayout form_house_one;
    private CommonFormLayout form_house_two;
    private TaxType mTaxType;
    private ListView lv_listview;
    private CommonFormLayout form_house_three;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootview = inflater.inflate(R.layout.fragment_tax_caculator_list, null);
        init(rootview);
        return rootview;
    }

    private void init(View rootView) {
        rootView.setClickable(true);

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
            int key = bundle.getInt(TaxMainFragment.KEY);
            mTaxType = (TaxType) bundle.getSerializable(TaxMainFragment.ENUM);
            switch (mTaxType) {
                case HOUSE_TYPE:
                    tv_title.setText("住宅类型");
                    form_house_one.setContentText(R.string.Tax_housetype_normal);
                    form_house_two.setContentText(R.string.Tax_housetype_not_normal);
                    break;
                case SALE_ONLY:
                    tv_title.setText("卖方家庭唯一一套");
                    form_house_one.setContentText(R.string.Tax_housetype_onlyone);
                    form_house_two.setContentText(R.string.Tax_housetype_not_only);
                    break;
                case LATEST_SALE:
                    tv_title.setText("距离上次交易");
                    form_house_one.setContentText(R.string.Tax_housetype_5year);
                    form_house_two.setContentText(R.string.Tax_housetype_2and_5year);
                    form_house_three.setVisibility(View.VISIBLE);
                    form_house_three.setContentText(R.string.Tax_housetype_not_2);
                    break;
                case PAYTAX_TYPE:
                    tv_title.setText("计征方式");
                    form_house_one.setContentText(R.string.Tax_housetype_total);
                    form_house_two.setContentText(R.string.Tax_housetype_chajia);
                    break;
                case FIRST_BUY:
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
        TaxMainFragment taxMainFragment = (TaxMainFragment) getFragmentManager().findFragmentByTag(TaxMainFragment.class.getName());
        int id = v.getId();
        if (v.getId() == R.id.iv_back) {
            getFragmentManager().popBackStack();
        } else if (id == R.id.form_house_one) {

            switch (mTaxType) {
                case HOUSE_TYPE:
                    TaxType.HOUSE_TYPE.setName(getString(R.string.Tax_housetype_not_normal));
                    break;
                case SALE_ONLY:
                    TaxType.SALE_ONLY.setName(getString(R.string.Tax_housetype_onlyone));
                    break;
                case LATEST_SALE:
                    TaxType.LATEST_SALE.setName(getString(R.string.Tax_housetype_5year));
                    break;
                case PAYTAX_TYPE:
                    TaxType.PAYTAX_TYPE.setName(getString(R.string.Tax_housetype_total));
                    break;
                case FIRST_BUY:
                    TaxType.FIRST_BUY.setName(getString(R.string.Tax_housetype_first));
                    break;
            }
            getFragmentManager().popBackStack(TaxMainFragment.class.getName(), 0);
            taxMainFragment.reFreshView();

        } else if (id == R.id.form_house_two) {

            switch (mTaxType) {
                case HOUSE_TYPE:
                    TaxType.HOUSE_TYPE.setName(getString(R.string.Tax_housetype_not_normal));
                    break;
                case SALE_ONLY:
                    TaxType.SALE_ONLY.setName(getString(R.string.Tax_housetype_not_only));
                    break;
                case LATEST_SALE:
                    TaxType.LATEST_SALE.setName(getString(R.string.Tax_housetype_2and_5year));
                    break;
                case PAYTAX_TYPE:
                    TaxType.PAYTAX_TYPE.setName(getString(R.string.Tax_housetype_chajia));
                    break;
                case FIRST_BUY:
                    TaxType.FIRST_BUY.setName(getString(R.string.Tax_housetype_not_first));
                    break;
            }
            getFragmentManager().popBackStack(TaxMainFragment.class.getName(), 0);
            taxMainFragment.reFreshView();
        } else if (id == R.id.form_house_three) {
            TaxType.LATEST_SALE.setName(getString(R.string.Tax_housetype_not_2));
            getFragmentManager().popBackStack(TaxMainFragment.class.getName(), 0);
            taxMainFragment.reFreshView();
        }
    }


}

