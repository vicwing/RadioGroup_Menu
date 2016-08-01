package com.example.cdj.myapplication.base;

import com.squareup.leakcanary.RefWatcher;

/**
 * Created by vic on 2016/7/27.
 */
public class BaseFragment extends android.support.v4.app.Fragment {
    @Override
    public void onDestroy() {
        super.onDestroy();
        RefWatcher refWatcher = BaseApplication.getRefWatcher(getActivity());
        refWatcher.watch(this);
    }
}
