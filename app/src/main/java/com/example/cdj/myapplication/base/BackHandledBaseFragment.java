package com.example.cdj.myapplication.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.example.cdj.myapplication.mainfunction.caculate.BackHandlerHelper;
import com.example.cdj.myapplication.mainfunction.caculate.impl.FragmentBackHandler;
import com.example.cdj.myapplication.mainfunction.caculate.impl.OnHeadlineSelectedListener;

/**
 * 实现了回调接口FragmentBackHandler,传递数据
 * Created by cdj onCallBackData 2016/5/18.
 */
public abstract class BackHandledBaseFragment extends BaseFragment implements FragmentBackHandler  {

    protected OnHeadlineSelectedListener mCallback;

    @Override
    public boolean onBackPressed() {
        return BackHandlerHelper.handleBackPress(this);
    }


    /**
     * 创建通讯回调接口实例
     *
     * @param context
     */
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        // This makes sure that the container activity has implemented
        // the callback impl. If not, it throws an exception
        try {
            //
            mCallback = (OnHeadlineSelectedListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement OnHeadlineSelectedListener");
        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

}
