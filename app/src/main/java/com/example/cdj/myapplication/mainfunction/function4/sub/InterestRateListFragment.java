package com.example.cdj.myapplication.mainfunction.function4.sub;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.cdj.myapplication.R;
import com.example.cdj.myapplication.adapter.adapterhelper.BaseAdapterHelper;
import com.example.cdj.myapplication.adapter.adapterhelper.QuickAdapter;
import com.example.cdj.myapplication.base.BackHandledBaseFragment;
import com.example.cdj.myapplication.cusview.CommonFormLayout;
import com.example.cdj.myapplication.mainfunction.function4.Bean.InterestRateListBean;
import com.example.cdj.myapplication.mainfunction.function4.CombinedLoanFragment;
import com.example.cdj.myapplication.mainfunction.function4.Fragment4;
import com.example.cdj.myapplication.utils.PreciseCompute;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;

/**
 * 商业贷款 利率列表 界面
 * Created by cdj on 2016/5/23.
 */
public class InterestRateListFragment extends BackHandledBaseFragment implements View.OnClickListener {

    private View rootView;
    private ImageView iv_back;
    private CommonFormLayout commonFormLayout;

    private TextView tv_title;
    private TextView tv_unit;
    private EditText edt_content;
    private ListView mListView;
    private Fragment4 fragment4;
    private ArrayList<InterestRateListBean> mInterestRateLists;
    private String mFromFragment;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        if (rootView == null)
            rootView = inflater.inflate(R.layout.fragment_caculate_sub_select_loannum, null);
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
    }

    private void initView() {
        iv_back = (ImageView) rootView.findViewById(R.id.iv_back);
        iv_back.setOnClickListener(this);
        tv_title = (TextView) rootView.findViewById(R.id.tv_title);

        commonFormLayout = (CommonFormLayout) rootView.findViewById(R.id.frame_other_price);
        commonFormLayout.setOnClickListener(this);
        commonFormLayout.setTitleText("其他利率");
        mListView = (ListView) rootView.findViewById(R.id.lv_listview);
        fragment4 = (Fragment4) getFragmentManager().findFragmentByTag(Fragment4.class.getName());

        fragment4.getInterestRate();

        mFromFragment = getArguments().getString(Fragment4.FROM_FRAGMENT);
        int currentIndex = fragment4.getCurrentIndex();
        if (0 == currentIndex) {
//            if (CommercialLoanFragment.class.getSimpleName().equals(mFromFragment)){
            tv_title.setText(R.string.caculate_commercial_title_loanprice);
            mInterestRateLists = getInterestRateLists();
//            }else{
//                tv_title.setText(R.string.caculate_fund_title_interest_rate);
//                mInterestRateLists = getFundInterestRateLists();
//            }
        } else if (1 == currentIndex) {
            tv_title.setText(R.string.caculate_fund_title_interest_rate);
            mInterestRateLists = getFundInterestRateLists();
        } else {
            String mFromFragment = getArguments().getString(Fragment4.FROM_FRAGMENT);
            if (CombinedLoanFragment.COMINED_COMMERCIAL_LOAN_AMOUNT.equals(mFromFragment)) {
                tv_title.setText(R.string.caculate_commercial_title_loanprice);
                mInterestRateLists = getInterestRateLists();
            } else {
                tv_title.setText(R.string.caculate_fund_title_interest_rate);
                mInterestRateLists = getFundInterestRateLists();
            }
        }
        mListView.setAdapter(new QuickAdapter<InterestRateListBean>(getActivity(), R.layout.item_list_caculate_commom_form, mInterestRateLists) {
            @Override
            protected void convert(BaseAdapterHelper helper, InterestRateListBean item) {
                helper.setText(R.id.tv_common_title, item.getDescription());
                helper.setText(R.id.tv_common_content, item.getInterestRate() + "%");
            }
        });
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                InterestRateListBean item = (InterestRateListBean) parent.getAdapter().getItem(position);

                if (CombinedLoanFragment.COMINED_COMMERCIAL_LOAN_AMOUNT.equals(mFromFragment)) {
                    fragment4.setIntrestRate(Float.valueOf(item.getInterestRate()));
                } else {
                    fragment4.setFundIntrestRate(Float.valueOf(item.getInterestRate()));
                }
//                fragment4.setIntrestRate(Float.valueOf(item.getInterestRate()));
                fragment4.getCurrentFragment().reFreshView();
                getFragmentManager().popBackStack();
            }
        });


    }

    /**
     * 获取商业贷款利率list数据
     *
     * @return
     */
    @NonNull
    private ArrayList<InterestRateListBean> getInterestRateLists() {
        ArrayList<InterestRateListBean> interestRateListBeen = new ArrayList<>();
        float intrestRate = fragment4.getInterestRate();
        java.text.DecimalFormat decimalFormat = new java.text.DecimalFormat("0.00");
        interestRateListBeen.add(new InterestRateListBean("最新基准利率上浮1.2倍", decimalFormat.format(PreciseCompute.mul(intrestRate, 1.2f))));
        interestRateListBeen.add(new InterestRateListBean("最新基准利率上浮1.1倍", decimalFormat.format(PreciseCompute.mul(intrestRate, 1.1f))));
        interestRateListBeen.add(new InterestRateListBean("最新基准利率", decimalFormat.format(intrestRate)));
        interestRateListBeen.add(new InterestRateListBean("最新基准利率95折", decimalFormat.format(PreciseCompute.mul(intrestRate, 0.95f))));
        interestRateListBeen.add(new InterestRateListBean("最新基准利率9折", decimalFormat.format(PreciseCompute.mul(intrestRate, 0.9f))));
        interestRateListBeen.add(new InterestRateListBean("最新基准利率85折", decimalFormat.format(PreciseCompute.mul(intrestRate, 0.85f))));
        interestRateListBeen.add(new InterestRateListBean("最新基准利率8折", decimalFormat.format(PreciseCompute.mul(intrestRate, 0.8f))));
        return interestRateListBeen;
    }


    /**
     * 获取商公积金贷款利率
     *
     * @return
     */
    @NonNull
    private ArrayList<InterestRateListBean> getFundInterestRateLists() {
        ArrayList<InterestRateListBean> interestRateListBeen = new ArrayList<>();
        float intrestRate = fragment4.getInterestRate();
        java.text.DecimalFormat decimalFormat = new java.text.DecimalFormat("0.00");
        interestRateListBeen.add(new InterestRateListBean("最新基准利率上浮1.2倍", decimalFormat.format(PreciseCompute.mul(intrestRate, 1.2f))));
        interestRateListBeen.add(new InterestRateListBean("最新基准利率上浮1.1倍", decimalFormat.format(PreciseCompute.mul(intrestRate, 1.1f))));
        interestRateListBeen.add(new InterestRateListBean("最新基准利率", decimalFormat.format(intrestRate)));
        return interestRateListBeen;
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.iv_back) {
            Logger.d(this.getClass().getName() + "返回" + "  stackCount " + getFragmentManager().getBackStackEntryCount());
            getFragmentManager().popBackStack();
        } else if (id == R.id.frame_other_price) {
            Bundle bundle = new Bundle();
            bundle.putString(Fragment4.FROM_FRAGMENT, InterestRateListFragment.class.getSimpleName());
            bundle.putBoolean(Fragment4.isFromList, true);
            mCallback.onAddFragment(InputLoanNumFragment.class.getName(), bundle);
        }
    }
}
