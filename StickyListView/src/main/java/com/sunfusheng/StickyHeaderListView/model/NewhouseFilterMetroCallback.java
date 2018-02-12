package com.sunfusheng.StickyHeaderListView.model;

import com.google.gson.Gson;
import com.orhanobut.logger.Logger;
import com.zhy.http.okhttp.callback.Callback;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Response;

/**
 * 地铁
 * Created by cdj onCallBackData 2016/5/11.
 */
public class NewhouseFilterMetroCallback extends Callback<FilterAreaBean> {
    @Override
    public FilterAreaBean parseNetworkResponse(Response response, int id) throws Exception {
        FilterAreaBean bean = null;
        try {
            String string = response.body().string();
            bean = new Gson().fromJson(string, FilterAreaBean.class);
            Logger.d("地铁  bean  " + bean.getMessage());
            return bean;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void onError(Call call, Exception e, int id) {

    }

    @Override
    public void onResponse(FilterAreaBean response, int id) {

    }
}
