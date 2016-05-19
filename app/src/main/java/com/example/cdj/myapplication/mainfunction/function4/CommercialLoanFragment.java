package com.example.cdj.myapplication.mainfunction.function4;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.cdj.myapplication.R;
import com.example.cdj.myapplication.base.BackHandledBaseFragment;
import com.example.cdj.myapplication.cusview.CommonFormLayout;
import com.example.cdj.myapplication.mainfunction.function4.sub.CommercialInputFragment;
import com.orhanobut.logger.Logger;

/**
 * 商业贷款界面
 * Created by cdj onCallBackData 2016/5/17.
 */
public class CommercialLoanFragment extends BackHandledBaseFragment{

    // 名字根据实际需求进行更改
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // 这里的参数只是一个举例可以根据需求更改
    private String mParam1;
    private String mParam2;

    /**
     * 通过工厂方法来创建Fragment实例
     * 同时给Fragment来提供参数来使用
     *
     * @return Master_Fragment的实例.
     */
    public static CommercialLoanFragment newInstance(String param1, String param2) {
        CommercialLoanFragment fragment = new CommercialLoanFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public CommercialLoanFragment() {
        // Required empty public constructor
    }

    CommonFormLayout mFrameLoan;//贷款金额

    CommonFormLayout mFrameInterestRate;//房贷利率

    CommonFormLayout mFrameLoanYear;//贷款年限

    private Button btn_do_caculate;

    private int defaultPrice = 100;//默认贷款额
    private  int totalPrice = defaultPrice;//贷款总额

    private float mIntrestRate = 4.9f;
    private int  mLoanTerm = 30;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View layout = inflater.inflate(R.layout.fragment_caculate_commercialloan, null);
        init(layout);
        return layout;
    }

    private void init(View layout) {

        mFrameLoan = (CommonFormLayout) layout.findViewById(R.id.frame_loan);
        mFrameInterestRate = (CommonFormLayout) layout.findViewById(R.id.frame_interest_rate);
        mFrameLoanYear = (CommonFormLayout) layout.findViewById(R.id.frame_loan_year);
        btn_do_caculate = (Button) layout.findViewById(R.id.btn_do_caculate);

        mFrameLoan.setTitleText("贷款金额");
        mFrameLoan.setHasRightArrow(true);
        mFrameLoan.setContentText("7成"+mParam1);
        mFrameLoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(getContext(), CaculateSubActivity.class);
//                intent.putExtra()
//                startActivity(intent);

//                replaceFragment(CommercialInputFragment.class.getName(),null);

                mCallback.onReplaceFragment(CommercialInputFragment.class.getName(),null);

            }
        });


        mFrameInterestRate.setTitleText("贷款利率");
        mFrameInterestRate.setContentText("最新基准利率"+mIntrestRate+"%");
        mFrameInterestRate.setHasRightArrow(true);

        mFrameLoanYear.setTitleText("贷款年限");
        mFrameLoanYear.setContentText(mLoanTerm+"年");
        mFrameLoanYear.setHasRightArrow(true);


    }

//    OnHeadlineSelectedListener mCallback;
//
//    @Override
//    public void onAttach(Context context) {
//        super.onAttach(context);
//        // This makes sure that the container activity has implemented
//        // the callback interface. If not, it throws an exception
//        try {
//            //
//            mCallback = (OnHeadlineSelectedListener) context;
//        } catch (ClassCastException e) {
//            throw new ClassCastException(context.toString()
//                    + " must implement OnHeadlineSelectedListener");
//        }
//    }

    /**
     * 进入子布局选择数据后确认提交后 ,返回刷新fragment
     * @param text
     */
    public void refreshFragment(String text){
        Logger.d(" 商业贷款界面  "+text);
        mFrameLoan.setContentText(text);
    }

    /**
     * 获取贷款总额
     * @return
     */
    public String getTotalPrice(){
      return mFrameLoan.getContentText().toString().trim();
    }

    /**
     * 获取利率
     * @return
     */
    public String getIntrestRate(){
        return mFrameInterestRate.getContentText().toString().trim();
    }

    /**
     * 贷款年限
     * @return
     */
    public String getLoanTerm(){
        return mFrameLoanYear.getContentText().toString().trim();
    }
}
