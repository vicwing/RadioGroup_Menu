package com.example.cdj.myapplication;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.cdj.myapplication.Bean.SecListItemEntity;
import com.example.cdj.myapplication.adapter.adapterhelper.QuickAdapter;
import com.example.cdj.myapplication.cusview.segmentcontrol.SegmentControl;
import com.example.cdj.myapplication.loadmore.LoadMoreListViewContainer;
import com.example.cdj.myapplication.mainfunction.function4.AccumulationFunLoanFragment;
import com.example.cdj.myapplication.mainfunction.function4.BackHandlerHelper;
import com.example.cdj.myapplication.mainfunction.function4.CombinedLoanFragment;
import com.example.cdj.myapplication.mainfunction.function4.CommercialLoanFragment;
import com.example.cdj.myapplication.mainfunction.function4.Fragment4;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;

import in.srain.cube.views.ptr.PtrClassicFrameLayout;

/**
 * Created by cdj on 2016/5/18.
 */
public class CaculateAcitivity  extends FragmentActivity implements  CommercialLoanFragment.OnHeadlineSelectedListener  {


    public final static String HOUSE_STYLE = "houseStyle";
    public final static String TOTAL_PRICE = "totalPrice";

    TextView mTvHouseStyle;
    TextView mTvTotalPrice;

    // 这里的参数只是一个举例可以根据需求更改
    private String mParam1;
    private String mParam2;

    private PtrClassicFrameLayout mPtrFrameLayout;
    private ListView mListView;
    private QuickAdapter<SecListItemEntity> mAdapter;
    private List<SecListItemEntity> mSecListItemEntities = new ArrayList<SecListItemEntity>();
    private LoadMoreListViewContainer loadMoreListViewContainer;
    private SegmentControl mSegmentControl;

    private int defaultPrice = 100;//默认贷款额
    private int totalPrice = defaultPrice;//贷款总额

    private float mIntrestRate = 4.9f;
    private int mLoanTerm = 30;

    private ArrayList<Fragment> fragmentArrayList;
    private Fragment mCurrentFrgment;
    private int currentIndex = 0;
    private Button btn_do_caculate;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.frame);
        replaceFragment(Fragment4.class.getName(),null);
//        setContentView(R.layout.content_main4);
//        initSegmentControl();
//        init();
//        initFragment();
    }




    private void init() {
        String houseStyleStr = getIntent().getStringExtra(HOUSE_STYLE);
        if (!TextUtils.isEmpty(houseStyleStr)) {

            findViewById(R.id.rl_houses_style).setVisibility(View.VISIBLE);

            totalPrice = getIntent().getIntExtra(TOTAL_PRICE, defaultPrice);

            mTvHouseStyle = (TextView) findViewById(R.id.tv_house_style);

            mTvHouseStyle.setText("宏宏大厦  3房1厅 112m2");

            mTvTotalPrice = (TextView) findViewById(R.id.tv_total_price);
            mTvTotalPrice.setText(totalPrice);
        } else {
            findViewById(R.id.rl_houses_style).setVisibility(View.GONE);
        }

        btn_do_caculate = (Button) findViewById(R.id.btn_do_caculate);
        btn_do_caculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                caculateData();
            }
        });
    }

    /**
     * 计算房贷结果
     */
    private void caculateData() {
        Logger.d("贷款总额  "+"  利率  "+" 公积金利率 "+ "贷款期限  ");
    }


    private void initSegmentControl() {
        mSegmentControl = (SegmentControl) findViewById(R.id.segment_control);
        mSegmentControl.setOnSegmentControlClickListener(new SegmentControl.OnSegmentControlClickListener() {
            @Override
            public void onSegmentControlClick(int index) {
                changeFragment(index);
                Logger.d( "index" + index+"   fragmentStackCount  "+  getFragmentManager().getBackStackEntryCount());
            }
        });
//        mSegmentControl.setBackgroundDrawableColor(getResources().getColor(R.color.black));
        mSegmentControl.setSelectdTextColor(getResources().getColor(R.color.black_33333));
        mSegmentControl.setDefaultTextColor(getResources().getColor(R.color.white));
//        mSegmentControl.setSelectedDrawableColor(getResources().getColor(R.color.white));

    }


    private void initFragment() {
        mSegmentControl.setSelectedIndex(0);
        fragmentArrayList = new ArrayList<Fragment>();
        fragmentArrayList.add(CommercialLoanFragment.newInstance("新添加",null));
        fragmentArrayList.add(AccumulationFunLoanFragment.newInstance("新添加",null));
        fragmentArrayList.add(CombinedLoanFragment.newInstance("新添加",null));
        changeFragment(0);
    }


    /**
     * 添加Fragment到容器
     * @param fragmentClassName
     * @param args
     */
    public void replaceFragment(String fragmentClassName, Bundle args) {
        Fragment fragment = Fragment.instantiate(this, fragmentClassName, args);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container, fragment).commit();
    }

    /**
     * 添加fragment到  frame_container ;
     * @param fragmentClassName
     * @param args
     */
    public void addFragment(String fragmentClassName, Bundle args) {
        Logger.d("addFragment   "+ fragmentClassName);
        Fragment fragment = Fragment.instantiate(this, fragmentClassName, args);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.frame_container, fragment,fragmentClassName);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    private  void changeFragment(int index){
        currentIndex = index;
//        FragmentTransaction ft = getChildFragmentManager().beginTransaction();
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        //判断当前的Fragment是否为空，不为空则隐藏
        if (null != mCurrentFrgment) {
            ft.hide(mCurrentFrgment);
        }
        //先根据Tag从FragmentTransaction事物获取之前添加的Fragment
        Fragment fragment = getSupportFragmentManager().findFragmentByTag(fragmentArrayList.get(currentIndex).getClass().getName());

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

    @Override
    public void onArticleSelected(int position) {
        Logger.d("fragment 跳转到........." +position);
    }


    @Override
    public void onReplaceFragment(String fragmentName) {
        Logger.d("o");
        addFragment(fragmentName,null);
    }



    @Override
    public void onBackPressed() {
        Logger.d("popBack...........");
        if (!BackHandlerHelper.handleBackPress(this)) {
            super.onBackPressed();
        }
    }
}
