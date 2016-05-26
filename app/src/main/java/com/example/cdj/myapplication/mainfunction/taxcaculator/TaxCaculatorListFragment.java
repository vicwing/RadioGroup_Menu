package com.example.cdj.myapplication.mainfunction.taxcaculator;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.cdj.myapplication.R;
import com.example.cdj.myapplication.base.BackHandledBaseFragment;

/**
 * 住宅类型,卖方,距离上次交易,计征方式,买房首套界面
 * Created by cdj on 2016/5/26.
 */
public class TaxCaculatorListFragment extends BackHandledBaseFragment implements View.OnClickListener {

    private TextView tv_title;

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
        rootView.findViewById(R.id.form_house_one).setOnClickListener(this);
        rootView.findViewById(R.id.form_house_two).setOnClickListener(this);


//        public static final int TAX_HOUSE_TYPE = 0x03;//住宅类型
//        public static final int TAX_HOUSE_SALE_ONLY = 0x04;//卖方唯一
//        public static final int TAX_HOUSE_LATEST_SALE = 0x05;//距离上次交易
//        public static final int TAX_HOUSE_PAY_TYPE = 0x06;//计征方式
//        public static final int TAX_HOUSE_FIRST_BUY = 0x07;//买方首套
        tv_title = (TextView) rootView.findViewById(R.id.tv_title);
        Bundle bundle = getArguments();
        if (bundle!=null){
            int key = bundle.getInt(TaxMainFragment.KEY);
            if (TaxMainFragment.TAX_HOUSE_TYPE==key){
                tv_title.setText("住宅类型");
            }else if (TaxMainFragment.TAX_HOUSE_SALE_ONLY==key){
                tv_title.setText("卖方家庭唯一一套");
            }else if (TaxMainFragment.TAX_HOUSE_LATEST_SALE==key){
                tv_title.setText("距离上次交易");
            }else if (TaxMainFragment.TAX_HOUSE_PAY_TYPE ==key){
                tv_title.setText("计征方式");
            }else if (TaxMainFragment.TAX_HOUSE_FIRST_BUY==key){
                tv_title.setText("卖方家庭首套");
            }
        }


    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (v.getId() == R.id.iv_back) {
            getFragmentManager().popBackStack();
        } else if (id == R.id.form_house_one) {

        } else if (id == R.id.form_house_two) {

        }
    }
}
