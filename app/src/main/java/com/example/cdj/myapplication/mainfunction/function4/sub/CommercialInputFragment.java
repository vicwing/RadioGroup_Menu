package com.example.cdj.myapplication.mainfunction.function4.sub;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.cdj.myapplication.R;
import com.example.cdj.myapplication.base.BackHandledBaseFragment;
import com.example.cdj.myapplication.cusview.CommomEditText;
import com.orhanobut.logger.Logger;

/**
 * 商业贷款输入金额
 * Created by cdj onCallBackData 2016/5/18.
 */
public class CommercialInputFragment extends BackHandledBaseFragment implements View.OnClickListener{

    View rootView;

    private ImageView iv_back;
    private CommomEditText mCommomEditText;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        if (rootView==null)
            rootView = inflater.inflate(R.layout.fragment_caculate_sub_commercialloan, null);
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
    private Button btn_commit;
    private void initView() {
        iv_back  = (ImageView) rootView.findViewById(R.id.iv_back);
        iv_back.setOnClickListener(this);
        tv_title = (TextView) rootView.findViewById(R.id.tv_title);

        tv_title.setText("商业贷款金额");

        edt_content= (EditText) rootView.findViewById(R.id.edt_content);

        tv_unit = (TextView) rootView.findViewById(R.id.tv_unit);

        tv_unit = (Button) rootView.findViewById(R.id.btn_commit);


        mCommomEditText = (CommomEditText) rootView.findViewById(R.id.custom_edt_loan);
        mCommomEditText.setOnCommitListener(new CommomEditText.OnCommitClickListener() {
            @Override
            public void onClick(String num) {
//                Logger.d("提交的数字是 "+ num);
                mCallback.onCallBackData(num);
                getFragmentManager().popBackStack();
            }
        });
    }


    @Override
    public void onClick(View v) {
        if (v.getId()==R.id.iv_back){
//            getActivity().finish();
            Logger.d(this.getClass().getName()+"返回"+"  stackCount "+getFragmentManager().getBackStackEntryCount());
            getFragmentManager().popBackStack();
        }
    }
}
