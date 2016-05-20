package com.example.cdj.myapplication.mainfunction.function4;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.cdj.myapplication.R;
import com.example.cdj.myapplication.base.BackHandledBaseFragment;
import com.example.cdj.myapplication.cusview.segmentcontrol.SegmentControl;
import com.example.cdj.myapplication.mainfunction.function4.sub.CaculateResultFragment;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;

/**
 * Created by cdj onCallBackData 2016/5/6.
 */
public class Fragment4 extends BackHandledBaseFragment {

    public final static String HOUSE_STYLE = "houseStyle";//房屋名称+类型+面积

    public final static String TOTAL_PRICE = "total_price";
    public final static String INTREST_RATE = "intrest_rate";// 利率
    public final static String LOAN_TERM = "loan_term";

    TextView mTvHouseStyle;
    TextView mTvTotalPrice;
    private SegmentControl mSegmentControl;
    private FragmentActivity mActivity;

    private ArrayList<Fragment> fragmentArrayList;
    private Fragment mCurrentFrgment;
    private int currentIndex = 0;
    private Button btn_do_caculate;

    /**
     * 通过工厂方法来创建Fragment实例
     * 同时给Fragment来提供参数来使用
     *
     * @return Master_Fragment的实例.
     */
    public static Fragment4 newInstance(String param1, String param2) {
        Fragment4 fragment = new Fragment4();
        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public Fragment4() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View layout = inflater.inflate(R.layout.content_main4, null);
        initSegmentControl(layout);
        init(layout);
        initFragment();
        return layout;
    }

    private float percent = 0;//房贷几成
    private int defaultPrice = 100;//默认贷款额
    private int totalPrice = defaultPrice;//贷款总额

    private float mIntrestRate = 4.9f;  //利率默认4.9
    private int mLoanTerm = 30;//贷款期限

    public static String FROM_DETAIL = "from_detail";
    private boolean isFromDetail = true;

    private void init(View layout) {
        mActivity = getActivity();

        if (getArguments() != null) {
            String  mHouseSytle = getArguments().getString(HOUSE_STYLE);
            if (!TextUtils.isEmpty(mHouseSytle)) {
                isFromDetail = true;//来自详情页
                layout.findViewById(R.id.rl_houses_style).setVisibility(View.VISIBLE);
                ((TextView) layout.findViewById(R.id.tv_house_style)).setText(mHouseSytle+"xx大厦机房急停");
            } else {
                layout.findViewById(R.id.rl_houses_style).setVisibility(View.GONE);
            }
//            ((TextView) layout.findViewById(R.id.tv_house_style)).setText(TextUtils.isEmpty(mHouseSytle)? "": mHouseSytle);
            String price = getArguments().getString(TOTAL_PRICE);
            if (!TextUtils.isEmpty(price))
                totalPrice = Integer.parseInt(price);

            String intrestRate = getArguments().getString(INTREST_RATE);
            if (!TextUtils.isEmpty(intrestRate))
                mIntrestRate = Float.parseFloat(intrestRate);
        }else{

        }

        btn_do_caculate = (Button) layout.findViewById(R.id.btn_do_caculate);
        btn_do_caculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                caculateData();
            }
        });

        layout.findViewById(R.id.iv_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });
    }

    /**
     * 计算房贷结果
     */
    private void caculateData() {
        Logger.d("贷款总额  " + "  利率  " + " 公积金利率 " + "贷款期限  " + " stack" + getChildFragmentManager().getBackStackEntryCount());
        Bundle bundle = new Bundle();
        bundle.putInt(TOTAL_PRICE,totalPrice);
        bundle.putFloat(INTREST_RATE,mIntrestRate);
        bundle.putInt(LOAN_TERM,mLoanTerm);
        mCallback.onReplaceFragment(CaculateResultFragment.class.getName(),bundle);
    }


    private void initSegmentControl(View layout) {
        mSegmentControl = (SegmentControl) layout.findViewById(R.id.segment_control);
        mSegmentControl.setOnSegmentControlClickListener(new SegmentControl.OnSegmentControlClickListener() {
            @Override
            public void onSegmentControlClick(int index) {
                Logger.d("index" + index + "   fragmentStackCount  " + getFragmentManager().getBackStackEntryCount());
                changeFragment(index);
                getCurrentFragment().reFreshView();
            }
        });
//        mSegmentControl.setBackgroundDrawableColor(getResources().getColor(R.color.black));
        mSegmentControl.setSelectdTextColor(getResources().getColor(R.color.black_33333));
        mSegmentControl.setDefaultTextColor(getResources().getColor(R.color.white));
//        mSegmentControl.setSelectedDrawableColor(getResources().getColor(R.color.white));

    }


    private void initFragment() {
        mSegmentControl.setSelectedIndex(0);
        Bundle bundle = new Bundle();
        bundle.putInt(TOTAL_PRICE,totalPrice);
        bundle.putFloat(INTREST_RATE,mIntrestRate);
        bundle.putBoolean(FROM_DETAIL,isFromDetail);
        fragmentArrayList = new ArrayList<Fragment>();
//        fragmentArrayList.add(CommercialLoanFragment.newInstance(String.valueOf(totalPrice), String.valueOf(mIntrestRate)));
        fragmentArrayList.add(CommercialLoanFragment.newInstance(bundle));
        fragmentArrayList.add(AccumulationFunLoanFragment.newInstance("", null));
        fragmentArrayList.add(CombinedLoanFragment.newInstance("", null));
        changeFragment(0);
    }

    /**
     * 切换fragment
     * @param index
     */
    private void changeFragment(int index) {
        currentIndex = index;
        FragmentTransaction ft = getChildFragmentManager().beginTransaction();
        //判断当前的Fragment是否为空，不为空则隐藏
        if (null != mCurrentFrgment) {
            ft.hide(mCurrentFrgment);
        }
        //先根据Tag从FragmentTransaction事物获取之前添加的Fragment
        Fragment fragment = getChildFragmentManager().findFragmentByTag(fragmentArrayList.get(currentIndex).getClass().getName());

        if (null == fragment) {
            //如fragment为空，则之前未添加此Fragment。便从集合中取出
            fragment = fragmentArrayList.get(currentIndex);
        }
        mCurrentFrgment = fragment;
        //判断此Fragment是否已经添加到FragmentTransaction事物中
        if (!fragment.isAdded()) {
            ft.add(R.id.frame_caculate, fragment, fragment.getClass().getName());
        } else {
            ft.show(fragment);
        }
        ft.commit();
    }

//    OnHeadlineSelectedListener mCallback;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        // This makes sure that the container activity has implemented
        // the callback interface. If not, it throws an exception
        try {
            //
            mCallback = (OnHeadlineSelectedListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement OnHeadlineSelectedListener");
        }
    }

    public boolean isFromDetail(){
        return  isFromDetail;
    }

    /**
     * 贷款几成数
     * @return
     */
    public void setPercent(float percent){
        this.percent= percent;
    }

    public float getPercent(){
        return  percent;
    }

    public int getTotalPrice(){
        return  totalPrice;
    }
    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public void setIntrestRate(float intrestRate) {
        mIntrestRate = intrestRate;
    }

    public float getIntrestRate(){
        return  mIntrestRate;
    }
    public int getLoanTerm(){
        return mLoanTerm;
    }

    public void setLoanTerm(int loanTerm) {
        mLoanTerm = loanTerm;
    }

    public void setFromDetail(boolean fromDetail) {
        isFromDetail = fromDetail;
    }

    public void refresth(float percent, int price) {
//       / currentIndex
    }

    /**
     * 获取当前显示的fragment实例.
     * @return
     */
    public  SubRefreshListener  getCurrentFragment(){
        return (SubRefreshListener) mCurrentFrgment;
    }
}