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
import com.example.cdj.myapplication.mainfunction.function4.Fragment4;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;

/**
 * 贷款年限
 * Created by cdj on 2016/5/23.
 */
public class LoanTermListFragment extends BackHandledBaseFragment implements View.OnClickListener {

    private View rootView;
    private ImageView iv_back;
    private CommonFormLayout framen_other_price;

    private TextView tv_title;
    private TextView tv_unit;
    private EditText edt_content;
    private ListView mListView;
    private Fragment4 fragment4;

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
        tv_title.setText("贷款年限");

        framen_other_price = (CommonFormLayout) rootView.findViewById(R.id.frame_other_price);
        framen_other_price.setOnClickListener(this);
        framen_other_price.setTitleText("其他年限");

        mListView = (ListView) rootView.findViewById(R.id.lv_listview);


        fragment4 = (Fragment4) getFragmentManager().findFragmentByTag(Fragment4.class.getName());

        mListView.setAdapter(new QuickAdapter<InterestRateListBean>(getActivity(), R.layout.item_list_caculate_commom_form, getLoanTermList()) {
            @Override
            protected void convert(BaseAdapterHelper helper, InterestRateListBean item) {
                helper.setText(R.id.tv_common_title,item.getDescription()+"年");
//                helper.setText(R.id.tv_common_content,item.getInterestRate()+"%");
            }
        });
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                InterestRateListBean item = (InterestRateListBean) parent.getAdapter().getItem(position);
                fragment4.setLoanTerm(Integer.parseInt(item.getDescription()));
                fragment4.getCurrentFragment().reFreshView();
                getFragmentManager().popBackStack();
            }
        });
    }

    /**
     * 日期年限
     * @return
     */
    @NonNull
    private ArrayList<InterestRateListBean> getLoanTermList() {
        ArrayList<InterestRateListBean> interestRateListBeen = new ArrayList<>();
        interestRateListBeen.add(new InterestRateListBean("5",null));
        interestRateListBeen.add(new InterestRateListBean("10",null));
        interestRateListBeen.add(new InterestRateListBean("15",null));
        interestRateListBeen.add(new InterestRateListBean("20",null));
        interestRateListBeen.add(new InterestRateListBean("25",null));
        interestRateListBeen.add(new InterestRateListBean("30",null));
        return interestRateListBeen;
    }

    @Override
    public void onClick(View v) {
        int id  =  v.getId();
        if (id == R.id.iv_back) {
            Logger.d(this.getClass().getName() + "返回" + "  stackCount " + getFragmentManager().getBackStackEntryCount());
            getFragmentManager().popBackStack();
        }else if (id==R.id.frame_other_price){
            Bundle bundle = new Bundle();
            bundle.putString(Fragment4.FROM_TAG,LoanTermListFragment.class.getSimpleName());
            bundle.putInt(Fragment4.KEY,Fragment4.FROMDETAIL_COMMERCIAL_LOAN_TERM);
            bundle.putBoolean(Fragment4.isFromList, true);
            mCallback.onAddFragment(InputNumFragment.class.getName(),bundle);
        }
    }
}
