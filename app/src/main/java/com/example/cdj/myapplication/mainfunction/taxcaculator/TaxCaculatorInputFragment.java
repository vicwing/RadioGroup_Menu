package com.example.cdj.myapplication.mainfunction.taxcaculator;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.InputFilter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.cdj.myapplication.R;
import com.example.cdj.myapplication.base.BackHandledBaseFragment;
import com.example.cdj.myapplication.cusview.CommomEditText;
import com.example.cdj.myapplication.mainfunction.caculate.CaculateMainFragment;
import com.example.cdj.myapplication.utils.TestDataModel;
import com.orhanobut.logger.Logger;

/**
 * 税费计算器.输入框计算界面.
 * Created by cdj onCallBackData 2016/5/18.
 */
public class TaxCaculatorInputFragment extends BackHandledBaseFragment implements View.OnClickListener {

    View rootView;
    private ImageView iv_back;
    private CommomEditText mCommomEditText;
    private int key;
    private int mMin =1;
    private int mMax = 99999;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        rootView = inflater.inflate(R.layout.fragment_caculator_sub_input, null);
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        Logger.d(" stackCount   "+getActivity().getSupportFragmentManager().getBackStackEntryCount());
    }

    private TextView tv_title;
    private EditText edt_content;

    private void initView() {
        rootView.setClickable(true);

        iv_back = (ImageView) rootView.findViewById(R.id.iv_back);
        iv_back.setOnClickListener(this);

        tv_title = (TextView) rootView.findViewById(R.id.tv_title);


        edt_content = (EditText) rootView.findViewById(R.id.edt_content);
        mCommomEditText = (CommomEditText) rootView.findViewById(R.id.custom_edt_loan);
        edt_content.setFilters(new InputFilter[]{new InputFilterMinMax(mMin,mMax)});
        Bundle bundle = getArguments();
        if (bundle != null) {
            key = bundle.getInt(CaculateMainFragment.KEY);
            if (key == TaxMainFragment.KEY_TAX_HOUSE_AREA) {
                tv_title.setText("房屋面积");
                mCommomEditText.setTextUnit("㎡");
                mCommomEditText.setEditHint("请输入房屋的面积");
            } else if (key == TaxMainFragment.KEY_TAX_HOUSE_PRICE) {
                tv_title.setText("房屋总价");
                mCommomEditText.setTextUnit("万元");
                mCommomEditText.setEditHint("请输入房屋总价");

            } else if (key == TaxMainFragment.KEY_TAX_DIFFERENCE_PRICE) {
                tv_title.setText("买卖差价");
                mCommomEditText.setTextUnit("万元");
                mCommomEditText.setEditHint("请输入房屋本次卖出与上次买入的差价");
            }
        }
        mCommomEditText.setOnCommitListener(new CommomEditText.OnCommitClickListener() {
            @Override
            public void onClick(String num) {
                TaxMainFragment taxMainFragment = (TaxMainFragment) getFragmentManager().findFragmentByTag(TaxMainFragment.class.getName());
                if (key == TaxMainFragment.KEY_TAX_HOUSE_AREA) {
                    taxMainFragment.setHouseArea(Integer.parseInt(num));
                } else if (key == TaxMainFragment.KEY_TAX_HOUSE_PRICE) {
                    taxMainFragment.setHousePrice(Integer.parseInt(num));
                } else if (key == TaxMainFragment.KEY_TAX_DIFFERENCE_PRICE) {//差价
                      taxMainFragment.setDifferencePrice(Integer.parseInt(num));
                }
                //直接跳到顶层
                getFragmentManager().popBackStack(TaxMainFragment.class.getName(), 0);
            }
        });

        openInputMethod();

        TestDataModel.getInstance().setRetainedTextView(tv_title);
    }

    /**
     * 打开输入法
     */
    private void openInputMethod() {
        edt_content.setFocusable(true);
        edt_content.requestFocus();
        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        // 接受软键盘输入的编辑文本或其它视图
        imm.showSoftInput(edt_content, InputMethodManager.SHOW_FORCED);
    }


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.iv_back) {
            getFragmentManager().popBackStack();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        InputMethodManager imm = (InputMethodManager)getActivity(). getSystemService(Activity.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput( InputMethodManager.HIDE_IMPLICIT_ONLY, 0);
    }
}
