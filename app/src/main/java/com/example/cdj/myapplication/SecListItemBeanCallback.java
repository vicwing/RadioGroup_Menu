package com.example.cdj.myapplication;

import com.example.cdj.myapplication.Bean.SecListBean;
import com.google.gson.Gson;
import com.zhy.http.okhttp.callback.Callback;

import okhttp3.Response;

/**
 * Created by cdj onCallBackData 2016/5/11.
 */
public abstract class SecListItemBeanCallback extends Callback<SecListBean>{

    @Override
    public SecListBean parseNetworkResponse(Response response) throws Exception {
        String string = response.body().string();
        SecListBean user = new Gson().fromJson(string, SecListBean.class);
        return user;
    }
}
