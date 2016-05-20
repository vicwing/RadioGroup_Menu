package com.example.cdj.myapplication.mainfunction.function4.sub;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.cdj.myapplication.R;
import com.example.cdj.myapplication.base.BackHandledBaseFragment;

/**
 * Created by cdj on 2016/5/19.
 */
public class CaculateResultFragment extends BackHandledBaseFragment  {

    //等额本息
    private TextView tv_xi_monthlypay;          //月供
    private TextView tv_xi_total_interest;  //总利息
    private TextView tv_xi_total_principal_tInterest;//本息总额

    //等额本金
    private TextView tv_jin_monthlypay;
    private TextView tv_jin_total_interest;
    private TextView tv_monthly_decline;  //每月递减
    private TextView tv_jin_total_principal_tInterest;



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

    }
}
