package com.example.cdj.myapplication.base;

import android.support.v4.app.Fragment;

import com.example.cdj.myapplication.mainfunction.function4.BackHandlerHelper;
import com.example.cdj.myapplication.mainfunction.function4.FragmentBackHandler;

/**
 * Created by cdj on 2016/5/18.
 */
public abstract  class BackHandledBaseFragment extends Fragment  implements FragmentBackHandler {

    @Override
    public boolean onBackPressed() {
        return BackHandlerHelper.handleBackPress(this);
    }
}
