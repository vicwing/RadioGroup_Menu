package com.example.cdj.myapplication.mainfunction.taxcaculator;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.widget.ListView;
import android.widget.TextView;

import com.example.cdj.myapplication.Bean.SecListItemEntity;
import com.example.cdj.myapplication.R;
import com.example.cdj.myapplication.adapter.adapterhelper.QuickAdapter;
import com.example.cdj.myapplication.cusview.segmentcontrol.SegmentControl;
import com.example.cdj.myapplication.loadmore.LoadMoreListViewContainer;
import com.example.cdj.myapplication.mainfunction.caculate.BackHandlerHelper;
import com.example.cdj.myapplication.mainfunction.caculate.impl.OnHeadlineSelectedListener;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;

import in.srain.cube.views.ptr.PtrClassicFrameLayout;

/**
 * Created by cdj on 2016/5/26.
 */
public class TaxCaculatorActivity  extends FragmentActivity  implements OnHeadlineSelectedListener {


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


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.frame);


//        TaxMainFragment taxMainFragment = (TaxMainFragment) Fragment.instantiate(this, TaxMainFragment.class.getName(), null);
//        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
//        transaction.add(R.id.frame_container, taxMainFragment, TaxMainFragment.class.getName());
//        transaction.addToBackStack(TaxMainFragment.class.getName());
//        transaction.commit();

        addFragment(TaxMainFragment.class.getName(),null);
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
        transaction.addToBackStack(fragmentClassName);
        transaction.commit();
        Logger.d("addFragment   "+ fragmentClassName+"\n  stackCount   "+getSupportFragmentManager().getBackStackEntryCount());
    }

    @Override
    public void onCallBackData(String num) {

    }



    @Override
    public void onAddFragment(String fragmentName, Bundle bundle) {
        addFragment(fragmentName,bundle);
    }

    @Override
    public void onCallBackData(float percent, int price) {

    }

    /**
     * 处理返回键
     */
    @Override
    public void onBackPressed() {
        if (!BackHandlerHelper.handleBackPress(this)) {
            super.onBackPressed();
        }
        if (1==getSupportFragmentManager().getBackStackEntryCount()){
            finish();
        }
    }
}
