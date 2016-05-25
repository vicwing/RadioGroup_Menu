package com.example.cdj.myapplication;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.cdj.myapplication.Bean.SecListItemEntity;
import com.example.cdj.myapplication.adapter.adapterhelper.QuickAdapter;
import com.example.cdj.myapplication.cusview.segmentcontrol.SegmentControl;
import com.example.cdj.myapplication.loadmore.LoadMoreListViewContainer;
import com.example.cdj.myapplication.mainfunction.function4.BackHandlerHelper;
import com.example.cdj.myapplication.mainfunction.function4.CommercialLoanFragment;
import com.example.cdj.myapplication.mainfunction.function4.CaculateMainFragment;
import com.example.cdj.myapplication.mainfunction.function4.OnHeadlineSelectedListener;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;

import in.srain.cube.views.ptr.PtrClassicFrameLayout;

/**
 * Created by cdj onCallBackData 2016/5/18.
 */
public class CaculateAcitivity  extends FragmentActivity implements OnHeadlineSelectedListener {


//    public final static String FROM_TAG = "from_tag";
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
    CaculateMainFragment mCaculateMainFragment;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.frame);
//        addFragment(Fragment4.class.getName(),null);
         mCaculateMainFragment = (CaculateMainFragment) Fragment.instantiate(this, CaculateMainFragment.class.getName(), null);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.frame_container, mCaculateMainFragment, CaculateMainFragment.class.getName());
        transaction.addToBackStack(null);
        transaction.commit();
    }

    /**
     * 添加Fragment到容器
     * @param fragmentClassName
     * @param args
     */
    public void replaceFragment(String fragmentClassName, Bundle args) {
        Fragment fragment = Fragment.instantiate(this, fragmentClassName, args);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container, fragment).addToBackStack(null).commit();
    }

    /**
     * 添加fragment到  frame_container ;
     * @param fragmentClassName
     * @param args
     */
    public void addFragment(String fragmentClassName, Bundle args) {
        Fragment fragment = Fragment.instantiate(this, fragmentClassName, args);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.setCustomAnimations(R.anim.push_right_in, R.anim.push_right_out,R.anim.push_right_in, R.anim.push_right_out);
        transaction.add(R.id.frame_container, fragment,fragmentClassName);
        transaction.addToBackStack(null);
        transaction.commit();
        Logger.d("addFragment   "+ fragmentClassName+"\n  stackCount   "+getSupportFragmentManager().getBackStackEntryCount());
    }

    @Override
    public void onCallBackData(String num) {
        Logger.d("activity  oncallBackdata "+ num);
        CommercialLoanFragment commercialLoanFragment = (CommercialLoanFragment) mCaculateMainFragment.getChildFragmentManager().findFragmentByTag(CommercialLoanFragment.class.getName());
        commercialLoanFragment.refreshFragment(num);
//        fragment4.onCallBackData(num);
    }

    @Override
    public void onCallBackData(float percent, int price) {
        mCaculateMainFragment.setCommercialAmount(price);
        mCaculateMainFragment.setPercent(percent);
        mCaculateMainFragment.getCurrentFragment().reFreshView();
    }


    @Override
    public void onAddFragment(String fragmentName, Bundle bundle) {
        addFragment(fragmentName,bundle);
    }



    /**
     * 处理返回键
     */
    @Override
    public void onBackPressed() {
        if (!BackHandlerHelper.handleBackPress(this)) {
            super.onBackPressed();
        }
        if (0==getSupportFragmentManager().getBackStackEntryCount()){
            finish();
        }

    }
}
