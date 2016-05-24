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
import com.example.cdj.myapplication.mainfunction.function4.Bean.LoanPercent;
import com.example.cdj.myapplication.mainfunction.function4.CombinedLoanFragment;
import com.example.cdj.myapplication.mainfunction.function4.Fragment4;
import com.orhanobut.logger.Logger;

import java.math.BigDecimal;
import java.util.ArrayList;

/**
 * 商业贷款金额 列表
 * Created by cdj on 2016/5/20.
 */
public class LoanAmountListFragment extends BackHandledBaseFragment implements View.OnClickListener {
    private View rootView;
    private ImageView iv_back;
    private TextView tv_title;
    private TextView tv_unit;
    private EditText edt_content;
    private ListView mListView;
    private com.example.cdj.myapplication.cusview.CommonFormLayout frame_other_price;
    private Fragment4 fragment4;
    private String mFromFragment;
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
        iv_back = (ImageView) rootView.findViewById(R.id.iv_back);
        iv_back.setOnClickListener(this);
        tv_title = (TextView) rootView.findViewById(R.id.tv_title);


        frame_other_price = (CommonFormLayout) rootView.findViewById(R.id.frame_other_price);
        frame_other_price.setOnClickListener(this);

        mListView = (ListView) rootView.findViewById(R.id.lv_listview);
        fragment4 = (Fragment4) getFragmentManager().findFragmentByTag(Fragment4.class.getName());

        Bundle bundle = getArguments();
        ArrayList<LoanPercent> loanPercentsData = null;
        if (bundle != null) {
            mFromFragment = bundle.getString(Fragment4.FROM_TAG);
            key = bundle.getInt(Fragment4.KEY);
            int currentIndex = fragment4.getCurrentIndex();
            if (0 == currentIndex) {
                tv_title.setText(R.string.caculate_commercial_title_loanprice);
                loanPercentsData = getLoanPercents(8);
            } else if (1 == currentIndex) {
                tv_title.setText(R.string.caculate_fund_title_price);
                loanPercentsData = getLoanPercents(9);
            } else {
                if (CombinedLoanFragment.COMINED_COMMERCIAL_LOAN_AMOUNT.equals(mFromFragment)) {
                    tv_title.setText(R.string.caculate_commercial_title_loanprice);
                    loanPercentsData = getLoanPercents(8);
                } else if (CombinedLoanFragment.COMINED_FUND_LOAN_AMOUNT.equals(mFromFragment)) {
                    tv_title.setText(R.string.caculate_fund_title_price);
                    loanPercentsData = getLoanPercents(9);
                }
            }
        }
        mListView.setAdapter(new QuickAdapter<LoanPercent>(getActivity(), R.layout.item_list_caculate_commom_form, loanPercentsData) {
            @Override
            protected void convert(BaseAdapterHelper helper, LoanPercent item) {
                helper.setText(R.id.tv_common_title, (int) (item.getPercent() * 10) + "成");
                helper.setText(R.id.tv_common_content, item.getPrice() + "万元");
            }
        });
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                LoanPercent item = (LoanPercent) parent.getAdapter().getItem(position);
//                mCallback.onCallBackData(item.getPercent(), item.getPrice());
                fragment4.setPercent(item.getPercent());
                switch (key){
                    case Fragment4.FROMDETAIL_COMMERCIAL_AMOUNT:
                    case Fragment4.FROMDETAIL_COMBINED_COMERCIAL_AMOUNT:
                        fragment4.setTotalPrice(item.getPrice());
                        break;
                    case Fragment4.FROMDETAIL_Fund_AMOUNT:
                    case Fragment4.FROMDETAIL_COMBINED_FUND_AMOUNT:
                        fragment4.setFundTtotalPrice(item.getPrice());
                        break;
                    default:
                        break;
                }

//                if (CombinedLoanFragment.COMINED_COMMERCIAL_LOAN_AMOUNT.equals(mFromFragment)) {
//                    fragment4.setTotalPrice(item.getPrice());
//                } else {
//                    fragment4.setFundTtotalPrice(item.getPrice());
//                }
                fragment4.getCurrentFragment().reFreshView();
                getFragmentManager().popBackStack();
            }
        });
    }

    /**
     * @param hight 带块最高比例
     * @return
     */
    @NonNull
    private ArrayList<LoanPercent> getLoanPercents(int hight) {
        ArrayList<LoanPercent> loanPercents = new ArrayList<>();
        int num = hight;
        float percent = (float) num / 10;
        LoanPercent loanPercent = null;
        for (int i = 0; i < num - 1; i++) {
            loanPercent = new LoanPercent();
            BigDecimal b1 = new BigDecimal(Float.toString(percent));
            BigDecimal b2 = new BigDecimal(Float.toString(0.1f));
            percent = b1.subtract(b2).floatValue();
            int result = (int) (fragment4.getTotalPrice() * percent);
            loanPercent.setPercent(percent);
            loanPercent.setPrice(result);
            loanPercents.add(loanPercent);
        }
        return loanPercents;
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.iv_back) {
            Logger.d(this.getClass().getName() + "返回" + "  stackCount " + getFragmentManager().getBackStackEntryCount());
            getFragmentManager().popBackStack();
        } else if (id == R.id.frame_other_price) {
            Bundle bundle = new Bundle();
//            Bundle arguments = getArguments();
//            if (arguments != null) {
////                String fromCombined = arguments.getString(CombinedLoanFragment.COMINED_COMMERCIAL_LOAN_AMOUNT);//商业贷款
////                String fromCombined = arguments.getString(Fragment4.FROMDETAIL_COMBINED_COMERCIAL_AMOUNT);//商业贷款
//                if (!TextUtils.isEmpty(fromCombined)) {
//                    bundle.putString(Fragment4.KEY, fromCombined);
//                } else {
//                    bundle.putString(Fragment4.FROM_TAG, LoanPriceListFragment.class.getSimpleName());
//                }
//            }
            bundle.putString(Fragment4.FROM_TAG, LoanAmountListFragment.class.getSimpleName());
            bundle.putInt(Fragment4.KEY, Fragment4.FROMDETAIL_COMMERCIAL_AMOUNT);
            bundle.putBoolean(Fragment4.isFromList, true);
            mCallback.onAddFragment(InputNumFragment.class.getName(), bundle);
        }
    }
}
