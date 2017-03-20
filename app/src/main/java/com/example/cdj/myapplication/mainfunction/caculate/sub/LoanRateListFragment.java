package com.example.cdj.myapplication.mainfunction.caculate.sub;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.cdj.myapplication.R;
import com.example.cdj.myapplication.baseadapter.adapterhelper.BaseAdapterHelper;
import com.example.cdj.myapplication.baseadapter.adapterhelper.QuickAdapter;
import com.example.cdj.myapplication.base.BackHandledBaseFragment;
import com.example.cdj.myapplication.widget.CommonFormLayout;
import com.example.cdj.myapplication.mainfunction.caculate.Bean.InterestRateListBean;
import com.example.cdj.myapplication.mainfunction.caculate.CaculateMainFragment;
import com.example.cdj.myapplication.utils.PreciseCompute;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;

/**
 * 商业贷款 利率列表 界面
 * Created by cdj on 2016/5/23.
 */
public class LoanRateListFragment extends BackHandledBaseFragment implements View.OnClickListener, View.OnTouchListener {

    private View rootView;
    private ImageView iv_back;
    private CommonFormLayout commonFormLayout;

    private TextView tv_title;
    private TextView tv_unit;
    private EditText edt_content;
    private ListView mListView;
    private CaculateMainFragment mCaculateMainFragment;
    private ArrayList<InterestRateListBean> mInterestRateLists;
    private String mFromFragment;
    private int mCurrentIndex;
    private int key;

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
//        rootView.setOnTouchListener(this);
        rootView.setClickable(true);
        iv_back = (ImageView) rootView.findViewById(R.id.iv_back);
        iv_back.setOnClickListener(this);
        tv_title = (TextView) rootView.findViewById(R.id.tv_title);

        commonFormLayout = (CommonFormLayout) rootView.findViewById(R.id.frame_other_price);
        commonFormLayout.setOnClickListener(this);
        commonFormLayout.setTitleText(R.string.caculate_other_rate);
        mListView = (ListView) rootView.findViewById(R.id.lv_listview);
        mCaculateMainFragment = (CaculateMainFragment) getFragmentManager().findFragmentByTag(CaculateMainFragment.class.getName());

        Bundle arguments = getArguments();
        if (arguments != null) {
            mFromFragment = arguments.getString(CaculateMainFragment.FROM_TAG);
            key = arguments.getInt(CaculateMainFragment.KEY);
            initContent();
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
                switch (key){
                    case CaculateMainFragment.FROMDETAIL_COMMERCIAL_INTEREST_RATE:
                    case CaculateMainFragment.FROMDETAIL_COMBINED_COMERCIAL_RATE:
                        mCaculateMainFragment.setShowCommercialRateDesc(false);
                        mCaculateMainFragment.setCommercialRateDesc(item.getDescription());
                        mCaculateMainFragment.setCommercialRate(Float.valueOf(item.getInterestRate()));
                        break;
                    case CaculateMainFragment.FROMDETAIL_Fund_INTEREST_RATE:
                    case CaculateMainFragment.FROMDETAIL_COMBINED_FUND_RATE:
                        mCaculateMainFragment.setShowFundRateDesc(false);
                        mCaculateMainFragment.setFundRate(Float.valueOf(item.getInterestRate()));
                        mCaculateMainFragment.setFundRateDes(item.getDescription());
                        break;
                    default:
                        break;
                }

                mCaculateMainFragment.getCurrentFragment().reFreshView();
                getFragmentManager().popBackStack();
            }
        });


    }

    /**
     * 初始化.要显示的内容
     */
    private void initContent() {
        switch (key) {
            case CaculateMainFragment.FROMDETAIL_COMMERCIAL_INTEREST_RATE:
                tv_title.setText(R.string.caculate_commercial_title_rate);
                mInterestRateLists = getInterestRateLists();
                break;
            case CaculateMainFragment.FROMDETAIL_Fund_INTEREST_RATE:
                tv_title.setText(R.string.caculate_fund_title_interest_rate);
                mInterestRateLists = getFundInterestRateLists();
                break;
            case CaculateMainFragment.FROMDETAIL_COMBINED_COMERCIAL_RATE:
                tv_title.setText(R.string.caculate_commercial_title_loan_amount);
                mInterestRateLists = getInterestRateLists();
                break;
            case CaculateMainFragment.FROMDETAIL_COMBINED_FUND_RATE:
                tv_title.setText(R.string.caculate_fund_title_interest_rate);
                mInterestRateLists = getFundInterestRateLists();
                break;
            default:
                break;
        }
    }

    /**
     * 获取商业贷款利率list数据
     * @return
     */
    @NonNull
    private ArrayList<InterestRateListBean> getInterestRateLists() {
        ArrayList<InterestRateListBean> interestRateListBeen = new ArrayList<>();
        float intrestRate = CaculateMainFragment.mDefaultCommercialRate;
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
        float intrestRate =  CaculateMainFragment.mDefaultFundRate;
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
            bundle.putBoolean(CaculateMainFragment.isFromList, true);
            bundle.putInt(CaculateMainFragment.KEY, key);
            mCallback.onAddFragment(MortCaculatorInputFragment.class.getName(), bundle);
        }
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return true;
    }
}
