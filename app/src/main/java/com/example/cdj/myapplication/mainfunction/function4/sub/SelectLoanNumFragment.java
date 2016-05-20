package com.example.cdj.myapplication.mainfunction.function4.sub;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
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
import com.example.cdj.myapplication.mainfunction.function4.Fragment4;
import com.orhanobut.logger.Logger;

import java.math.BigDecimal;
import java.util.ArrayList;

/**
 * 商业贷款金额.选择列表
 * Created by cdj on 2016/5/20.
 */
public class SelectLoanNumFragment extends BackHandledBaseFragment implements View.OnClickListener {


    View rootView;

    private ImageView iv_back;
    private CommonFormLayout mCommomEditText;

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

    private TextView tv_title;
    private TextView tv_unit;
    private EditText edt_content;
    private ListView mListView;


    private com.example.cdj.myapplication.cusview.CommonFormLayout commonFormLayout;
    Fragment4 fragment4;
    private void initView() {
        iv_back = (ImageView) rootView.findViewById(R.id.iv_back);
        iv_back.setOnClickListener(this);
        tv_title = (TextView) rootView.findViewById(R.id.tv_title);

        tv_title.setText("商业贷款金额");

        commonFormLayout = (CommonFormLayout) rootView.findViewById(R.id.framen_other_price);
        mListView = (ListView) rootView.findViewById(R.id.lv_listview);

         fragment4 = (Fragment4) getFragmentManager().findFragmentByTag(Fragment4.class.getName());

        Bundle bundle = getArguments();
        if (bundle!=null){
            String price = bundle.getString(Fragment4.TOTAL_PRICE);
            if (!TextUtils.isEmpty(price)) {
//                totalPrice = Integer.parseInt(price);
//                Logger.d(SelectLoanNumFragment.class.getSimpleName() + "price " + totalPrice);
            }
        }

        mListView.setAdapter(new QuickAdapter<LoanPercent>(getActivity(), R.layout.item_list_caculate_commom_form, getLoanPercents()) {
            @Override
            protected void convert(BaseAdapterHelper helper, LoanPercent item) {
                helper.setText(R.id.tv_common_title,(int)(item.getPercent()*10)+"成");
                helper.setText(R.id.tv_common_content,item.getPrice()+"万元");
            }
        });
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                LoanPercent item = (LoanPercent) parent.getAdapter().getItem(position);
                mCallback.onCallBackData(item.getPercent(),item.getPrice());
                getFragmentManager().popBackStack();
            }
        });
//        commonFormLayout.setHasRightArrow(true);
    }

    /**
     * 获取list数据源
     * @return
     */
    @NonNull
    private ArrayList<LoanPercent> getLoanPercents() {
        ArrayList<LoanPercent> loanPercents = new ArrayList<>();
        int num = 8;
        float percent = (float ) num  / 10;
        LoanPercent loanPercent = null;
        for (int i = 0; i < num -1; i++) {
            loanPercent  = new LoanPercent();
            BigDecimal b1 = new BigDecimal(Float.toString(percent));
            BigDecimal b2 = new BigDecimal(Float.toString(0.1f));
            percent = b1.subtract(b2).floatValue();
            int  result = (int) (fragment4.getTotalPrice()*percent);
            loanPercent.setPercent(percent);
            loanPercent.setPrice(result);
            loanPercents.add(loanPercent);
        }
        return loanPercents;
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.iv_back) {
//            getActivity().finish();
            Logger.d(this.getClass().getName() + "返回" + "  stackCount " + getFragmentManager().getBackStackEntryCount());
            getFragmentManager().popBackStack();
        }
    }

}
