package com.example.cdj.myapplication.Interceptor;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.example.cdj.myapplication.utils.NetWorkUtils;
import com.orhanobut.logger.Logger;

import java.io.IOException;
import java.util.Map;
import java.util.Set;

import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okio.Buffer;

/**
 * Description :
 * Created by vicwing
 * Created Time 2018/11/7
 */
public class BaseInterceptor implements Interceptor {
    private Map<String, String> headers;
    private Context context;
    private String tag = "Interceptor....";

    public BaseInterceptor(Context context) {
        this.context = context;
    }

    public BaseInterceptor(Map<String, String> headers, Context context) {
        this.headers = headers;
        this.context = context;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request.Builder builder = chain.request()
                .newBuilder();

//        builder.cacheControl(CacheControl.FORCE_CACHE).url(chain.request().url()).build();

        if (!NetWorkUtils.isNetworkConnected(context)) {

            ((Activity) context).runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(context, "当前无网络!", Toast.LENGTH_SHORT).show();
                }
            });
        }
        if (headers != null && headers.size() > 0) {
            Set<String> keys = headers.keySet();
            for (String headerKey : keys) {
                builder.addHeader(headerKey, headers.get(headerKey)).build();
            }
        }

        if (NetWorkUtils.isNetworkConnected(context)) {

            int maxAge = 60; // read from cache for 60 s
            builder
                    .removeHeader("Pragma")
                    .addHeader("Cache-Control", "public, max-age=" + maxAge)
                    .build();
//            Logger.d("Cache-Control  public, max-age=" + maxAge);
//            Logger.d("request " + builder.build().toString());

        } else {
            int maxStale = 60 * 60 * 24 * 14; // tolerate 2-weeks stale
            builder
                    .removeHeader("Pragma")
                    .addHeader("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
                    .build();
            Logger.d("没有连接网络.......");

        }
        Request request = builder.build();
        logForRequest(request);
        Response response = chain.proceed(request);
        logForResponse(response);
        return response;
    }

    private Response logForResponse(Response response) {
        try {
            //===>response log
            Log.e(tag, "========response'log===============");
            Response.Builder builder = response.newBuilder();
            Response clone = builder.build();
            Log.e(tag, "url : " + clone.request().url());
            Log.e(tag, "code : " + clone.code());
            Log.e(tag, "protocol : " + clone.protocol());
            if (!TextUtils.isEmpty(clone.message()))
                Log.e(tag, "message : " + clone.message());

//            if (showResponse) {
//                ResponseBody body = clone.body();
//                if (body != null) {
//                    MediaType mediaType = body.contentType();
//                    if (mediaType != null) {
//                        Log.e(tag, "responseBody's contentType : " + mediaType.toString());
//                        if (isText(mediaType)) {
//                            String resp = body.string();
//                            Log.e(tag, "responseBody's content : " + resp);
//
//                            body = ResponseBody.create(mediaType, resp);
//                            return response.newBuilder().body(body).build();
//                        } else {
//                            Log.e(tag, "responseBody's content : " + " maybe [file part] , too large too print , ignored!");
//                        }
//                    }
//                }
//            }

            Log.e(tag, "========response'log=======end");
        } catch (Exception e) {
//            e.printStackTrace();
        }

        return response;
    }

    private void logForRequest(Request request) {
        try {
            String url = request.url().toString();
            Headers headers = request.headers();

            Log.e(tag, "========request'log=======");
            Log.e(tag, "method : " + request.method());
            Log.e(tag, "url : " + url);
            Log.e(tag, "cache contol : " + request.headers().toString());

            if (headers != null && headers.size() > 0) {
                Log.e(tag, "headers : " + headers.toString());
            }
            RequestBody requestBody = request.body();
            if (requestBody != null) {
                MediaType mediaType = requestBody.contentType();
                if (mediaType != null) {
                    Log.e(tag, "requestBody's contentType : " + mediaType.toString());
                    if (isText(mediaType)) {
                        Log.e(tag, "requestBody's content : " + bodyToString(request));
                    } else {
                        Log.e(tag, "requestBody's content : " + " maybe [file part] , too large too print , ignored!");
                    }
                }
            }
            Log.e(tag, "========request'log=======end");
        } catch (Exception e) {
//            e.printStackTrace();
        }
    }

    private boolean isText(MediaType mediaType) {
        if (mediaType.type() != null && mediaType.type().equals("text")) {
            return true;
        }
        if (mediaType.subtype() != null) {
            if (mediaType.subtype().equals("json") ||
                    mediaType.subtype().equals("xml") ||
                    mediaType.subtype().equals("html") ||
                    mediaType.subtype().equals("webviewhtml")
                    )
                return true;
        }
        return false;
    }

    private String bodyToString(final Request request) {
        try {
            final Request copy = request.newBuilder().build();
            final Buffer buffer = new Buffer();
            copy.body().writeTo(buffer);
            return buffer.readUtf8();
        } catch (final IOException e) {
            return "something error when show requestBody.";
        }
    }
}
