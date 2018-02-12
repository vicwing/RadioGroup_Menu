package com.sunfusheng.StickyHeaderListView.model;

import com.apkfuns.logutils.LogUtils;
import com.google.gson.Gson;
import com.zhy.http.okhttp.callback.Callback;

import java.util.List;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by cdj onCallBackData 2016/5/11.
 */
public  class SecondHandFilterListCallback extends Callback<SecondHandFilterBean>{


    @Override
    public SecondHandFilterBean parseNetworkResponse(Response response, int id) throws Exception {
        String string = response.body().string();
        SecondHandFilterBean bean = new Gson().fromJson(string, SecondHandFilterBean.class);
        return bean;
    }

    @Override
    public void onError(Call call, Exception e, int id) {

    }

    @Override
    public void onResponse(SecondHandFilterBean response, int id) {

        LogUtils.d("返回结果");
        LogUtils.d(response);
        String status = response.getStatus();
        if (status.equals("C0000")) {
            SecondHandFilterBean.ResultBean result = response.getResult();
            List<FilterBean> area = result.getArea();
            List<FilterBean> decorationBeanList = result.getDecoration();
            List<FilterBean> lableBeanList = result.getLable();
            List<FilterBean> ageBeanList = result.getAge();


//            hashMap = new HashMap<>();
//            hashMap.put(houseArea, area);
//            hashMap.put(houseDecorate, decorationBeanList);
//            hashMap.put(houseLabel, lableBeanList);
//            hashMap.put(houseAge, ageBeanList);
//
////                            dropMenuAdapter.setBetterDoubleGridData(hashMap);
//            initFilterDropDownView();
        } else {//返回错误message
//            Toast.makeText(MainDropDownMenuActivity1.this, "message  " + response.getMessage() + "\n statsus  " + response.getStatus(), Toast.LENGTH_SHORT);
        }
    }
}
