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
import com.example.cdj.myapplication.mainfunction.function4.CaculateMainFragment;
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
    private CaculateMainFragment mCaculateMainFragment;
    private String mFromFragment;
    private int key;
    private ArrayList<LoanPercent> mLoanPercentsData;

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
        mCaculateMainFragment = (CaculateMainFragment) getFragmentManager().findFragmentByTag(CaculateMainFragment.class.getName());

        Bundle bundle = getArguments();
        if (bundle != null) {
            mFromFragment = bundle.getString(CaculateMainFragment.FROM_TAG);
            key = bundle.getInt(CaculateMainFragment.KEY);
            intiContent();
        }
        mListView.setAdapter(new QuickAdapter<LoanPercent>(getActivity(), R.layout.item_list_caculate_commom_form, mLoanPercentsData) {
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
                switch (key){
                    case CaculateMainFragment.FROMDETAIL_COMMERCIAL_AMOUNT:
                    case CaculateMainFragment.FROMDETAIL_COMBINED_COMERCIAL_AMOUNT:
                        mCaculateMainFragment.setCommercialAmount(item.getPrice());
                        break;
                    case CaculateMainFragment.FROMDETAIL_Fund_AMOUNT:
                    case CaculateMainFragment.FROMDETAIL_COMBINED_FUND_AMOUNT:
                        mCaculateMainFragment.setFundAmount(item.getPrice());
                        break;
                    default:
                        break;
                }
                mCaculateMainFragment.setPercent(item.getPercent());
                mCaculateMainFragment.getCurrentFragment().reFreshView();
                getFragmentManager().popBackStack();
            }
        });
    }

    /**
     * 初始化.要显示的内容
     */
    private void intiContent() {

        switch (key) {
            case CaculateMainFragment.FROMDETAIL_COMMERCIAL_AMOUNT:
                tv_title.setText(R.string.caculate_commercial_title_loan_amount);
                mLoanPercentsData = getLoanPercents(8);
                break;
            case CaculateMainFragment.FROMDETAIL_Fund_AMOUNT:
                tv_title.setText(R.string.caculate_fund_title_amount);
                mLoanPercentsData = getLoanPercents(9);
                break;
            case CaculateMainFragment.FROMDETAIL_COMBINED_COMERCIAL_AMOUNT:
                tv_title.setText(R.string.caculate_commercial_title_loan_amount);
                mLoanPercentsData = getLoanPercents(8);
                break;
            case CaculateMainFragment.FROMDETAIL_COMBINED_FUND_AMOUNT:
                tv_title.setText(R.string.caculate_fund_title_amount);
                mLoanPercentsData = getLoanPercents(9);
                break;
            default:
                break;
        }
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
            int result = (int) (mCaculateMainFragment.getCommercialAmount() * percent);
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
            bundle.putInt(CaculateMainFragment.KEY, key);
            bundle.putBoolean(CaculateMainFragment.isFromList, true);
            mCallback.onAddFragment(InputNumFragment.class.getName(), bundle);
        }
    }
}
