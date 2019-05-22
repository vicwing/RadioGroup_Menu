package com.example.cdj.myapplication.Interceptor;

import com.blankj.utilcode.util.NetworkUtils;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Description :
 * Created by vicwing
 * Created Time 2018/11/8
 */
public class Myinter implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        //拦截Request对象
        Request request = chain.request();
        //判断有无网络连接
//        boolean connected = isNetworkConnected();
        if (NetworkUtils.isConnected()) {
            //有网络，缓存时间短,缓存90s
//            String cacheControl = request.cacheControl().toString();
            Response response = chain.proceed(request);
            //这里返回的就是我们获取到的响应头，添加缓存配置返回
            return response.newBuilder()
                    .removeHeader("Pragma")
                    .header("Cache-Control", "public, max-age=90")
                    .build();
        }
//如果没有网络，不做处理，直接返回
        return chain.proceed(request);

    }

//    /**
//     * 判断是否有网络
//     *
//     * @return 返回值
//     */
//    public static boolean isNetworkConnected() {
//        Context context = RxApplication.getContext();
//        if (context != null) {
//            ConnectivityManager mConnectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
//            NetworkInfo mNetworkInfo = mConnectivityManager.getActiveNetworkInfo();
//
//            if (mNetworkInfo != null) {
//                return mNetworkInfo.isAvailable();
//            }
//        }
//        return false;
//    }

}
