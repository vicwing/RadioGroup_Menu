package com.zhy.http.okhttp.cookie;

import java.io.IOException;

import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 实现okhttp缓存
 * Created by vic on 2016/7/29.
 */
public class CacheInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Headers requsetHeader = request.headers();
//        for (int i = 0; i <requsetHeader.size() ; i++) {
//            String name = requsetHeader.name(i);
//            String value = requsetHeader.get(name);
//            Logger.d(" response requsetHeader name "+name+"  value "+value);
//        }

        Response response = chain.proceed(request);

        Headers headers = response.headers();
        for (int i = 0; i <headers.size() ; i++) {
            String name = headers.name(i);
            String value = headers.get(name);
//            Logger.d(" response headers name "+name+"  value "+value);
        }
        Response response1 = response.newBuilder()
                .removeHeader("Pragma")
                .removeHeader("Cache-Control")
                //cache for 30 days
//                .header("Cache-Control", "max-age=" + 3600 * 24 * 30)
                .header("Cache-Control", "max-age=" + 60*5 )
                .build();

        Headers headers1 = response1.headers();
        for (int i = 0; i < headers1.size() ; i++) {
            String name = headers1.name(i);
            String value = headers.get(name);

//            Logger.d(" response11  headers name "+name+"  value "+value);
        }
        return response1;
    }
}
