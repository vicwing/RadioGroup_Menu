package com.example.cdj.myapplication.Interceptor;

import com.blankj.utilcode.util.NetworkUtils;
import com.orhanobut.logger.Logger;

import java.io.IOException;

import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Description :
 * Created by vicwing
 * Created Time 2018/11/8
 */
public class NetInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        boolean connected = NetworkUtils.isConnected();
        //如果有网络
        if (connected) {
            CacheControl cacheControl1 = request.cacheControl();
            if (cacheControl1 != null) {
                int maxAgeSeconds = cacheControl1.maxAgeSeconds();
                //设置了缓存时间,才缓存response,否则不处理
                if (maxAgeSeconds >= 0) {
                    Logger.d("cacheControl :  maxAgeSeconds  " + maxAgeSeconds);
                    Response response = chain.proceed(request);
                    return response.newBuilder()
                            .removeHeader("Pragma")
                            .header("Cache-Control", "public, max-age=" + maxAgeSeconds)
                            .build();
                }
            }
        }
        //如果没有网络，不做处理，直接返回
        return chain.proceed(request);
    }

}
