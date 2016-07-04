package com.sunfusheng.StickyHeaderListView.model;

import com.google.gson.Gson;
import com.zhy.http.okhttp.callback.Callback;

import okhttp3.Response;

/**
 * Created by cdj onCallBackData 2016/5/11.
 */
public abstract class SecondHandFilterListCallback extends Callback<SecondHandFilterBean>{

    @Override
    public SecondHandFilterBean parseNetworkResponse(Response response) throws Exception {
        String string = response.body().string();
        SecondHandFilterBean bean = new Gson().fromJson(string, SecondHandFilterBean.class);
        return bean;
    }
}
