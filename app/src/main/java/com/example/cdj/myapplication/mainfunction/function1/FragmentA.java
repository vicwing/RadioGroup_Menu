package com.example.cdj.myapplication.mainfunction.function1;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Shader;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.telephony.TelephonyManager;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.cdj.myapplication.Bean.SecListBean;
import com.example.cdj.myapplication.Interceptor.LoggerInterceptor;
import com.example.cdj.myapplication.Interceptor.NetInterceptor;
import com.example.cdj.myapplication.Interceptor.NoNetInterceptor;
import com.example.cdj.myapplication.R;
import com.example.cdj.myapplication.SecListItemBeanCallback;
import com.example.cdj.myapplication.activity.LinearIndicatorActivity;
import com.example.cdj.myapplication.activity.SnapHelperActivity;
import com.example.cdj.myapplication.base.BaseFragment;
import com.example.cdj.myapplication.mainfunction.adapter.FragmentAAdapter;
import com.example.cdj.myapplication.utils.NetWorkUtils;
import com.example.cdj.myapplication.utils.ScreenUtil;
import com.google.firebase.iid.FirebaseInstanceId;
import com.orhanobut.logger.Logger;
import com.zhy.http.okhttp.OkHttpUtils;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.io.File;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Call;
import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by cdj onCallBackData 2016/2/1.
 */
public class FragmentA extends BaseFragment {
    private final String rx_binding_test = "RX_binding_test";
    private final String snapheler_recycleview = "snapheler_recycleview";
    private final String LINEAR_PAGE_INDICATOR = "linear_page_indicator";
    //    @BindView(R.id.textView7)
    //    TextView textView7;
    //    @BindView(R.id.textView6)
    //    TextView textView6;
    //    @BindView(R.id.textView5)
    //    TextView textView5;
    //    @BindView(R.id.imageView)
    //    ImageView imageView;
    //    @BindView(R.id.tv_television)
    //    TextView tvTelevision;
    @BindView(R.id.btn_subthread)
    Button btnSubthread;
    @BindView(R.id.tv_texcontent)
    TextView tvTexcontent;
    @BindView(R.id.recycleview)
    RecyclerView recycleview;
    private Context mContext;
    private int screenWidth;
    private int screenHeight;

    private String uritext = "http://www.java2s.com:8080/yourpath/fileName.htm?datasource=shenzhen&path=32&id=4#harvic";

    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.content_fragment1, null);
        ButterKnife.bind(this, view);
        return view;
    }

    @SuppressLint("CheckResult")
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);
        mContext = getActivity();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
        recycleview.setLayoutManager(linearLayoutManager);
        List<String> datas = new ArrayList<>();
        datas.add(snapheler_recycleview);
        datas.add(rx_binding_test);
        datas.add(LINEAR_PAGE_INDICATOR);
        FragmentAAdapter adapter = new FragmentAAdapter(datas);
        recycleview.setAdapter(adapter);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Logger.d("onItemClick:   ");
                List<String> data = adapter.getData();
                if (data != null) {
                    for (int i = 0; i < data.size(); i++) {
                        String resut = data.get(i);
                        Intent intent = null;
                        switch (resut) {
                            case snapheler_recycleview:
                                intent = new Intent(mContext, SnapHelperActivity.class);
                                mContext.startActivity(intent);
                                break;
                            case LINEAR_PAGE_INDICATOR:
                                intent = new Intent(mContext, LinearIndicatorActivity.class);
                                mContext.startActivity(intent);
                                break;
                            default:
                                break;
                        }
                    }
                }
            }
        });
//		testUri();

//        okHttpTest();
//        testCacheControl();

//		String abc=new String("abc");  //1
//		SoftReference<String> abcSoftRef=new SoftReference<String>(abc);  //2
//		WeakReference<String> abcWeakRef = new WeakReference<String>(abc); //3
//		abc=null; //4
//		abcSoftRef.clear();//5


//		testWeakRefrence();
//		testSoftRefrence();

//        ArrayList<User> objects = new ArrayList<>();
//        for (int i = 0; i < 3; i++) {
//            User user = new User();
//            objects.add(user);
//        }
//        Observable.fromArray(objects) // 输入类型 String
//                .doOnNext(new Consumer<ArrayList<User>>() {
//                    @Override
//                    public void accept(ArrayList<User> users) throws Exception {
//                        String name = Thread.currentThread().getName();
//                        Logger.d("Consumer  thread name = " + name);
//                    }
//                }).subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Observer<ArrayList<User>>() {
//                    @Override
//                    public void onSubscribe(Disposable d) {
//
//                    }
//
//                    @Override
//                    public void onNext(ArrayList<User> users) {
//                        Logger.d("Observer....onNext......");
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//
//                    }
//
//                    @Override
//                    public void onComplete() {
//
//                    }
//                });
//        Observable.create(new ObservableOnSubscribe<Response>() {
//            @Override
//            public void subscribe(@NonNull ObservableEmitter<Response> e) throws Exception {
////                Response.Builder builder = new Response.Builder()
////                        .url("http://api.avatardata.cn/MobilePlace/LookUp?key=ec47b85086be4dc8b5d941f5abd37a4e&mobileNumber=13021671512")
////                        .get();
////                Request request = builder.build();
////                Call call = new OkHttpClient().newCall(request);
////                Response response = call.execute();
////                e.onNext(response);
//
//
//                Request request = new Request.Builder()
//                        .url("http://api.avatardata.cn/MobilePlace/LookUp?key=ec47b85086be4dc8b5d941f5abd37a4e&mobileNumber=13021671512")
//                        .get()
//                        .build();
//                Call call = new OkHttpClient().newCall(request);
//                Response response = call.execute();
//                e.onNext(response);
//            }
//        }).map(new Function<Response, MobileAddress>() {
//            @Override
//            public MobileAddress apply(@NonNull Response response) throws Exception {
//                if (response.isSuccessful()) {
//                    ResponseBody body = response.body();
//                    if (body != null) {
//                        Logger.e("map:转换前:" + response.body().toString());
//                        return new Gson().fromJson(body.string(), MobileAddress.class);
//                    }
//                }
//                return null;
//            }
//        }).observeOn(AndroidSchedulers.mainThread())
//                .doOnNext(new Consumer<MobileAddress>() {
//                    @Override
//                    public void accept(@NonNull MobileAddress s) throws Exception {
//                        Logger.e("doOnNext: 保存成功：" + s.toString() + "\n");
//                    }
//                }).subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Consumer<MobileAddress>() {
//                    @Override
//                    public void accept(@NonNull MobileAddress data) throws Exception {
//                        Logger.d("成功:" + data.toString() + "\n");
//                    }
//                }, new Consumer<Throwable>() {
//                    @Override
//                    public void accept(@NonNull Throwable throwable) throws Exception {
//                        Logger.d("失败：" + throwable.getMessage() + "\n");
//                    }
//                });

//        disposable = RxjavaTest.rangeTest(this);
//        RxjavaTest.interval(this);
//        SystemClock.sleep(10000);
        getImEiMethod();

        printdifrentPath();
    }

    public void printdifrentPath() {
        Logger.d("getCacheDir=" + mContext.getCacheDir());
        Logger.d("getFilesDir=" + mContext.getFilesDir());
        Logger.d("getExternalCacheDir=" + mContext.getExternalCacheDir());
        Logger.d("getExternalFilesDir=" + mContext.getExternalFilesDir(null));
        Logger.d("getExternalFilesDir=" + getCacheFilePath(mContext));
    }

    public static String getCacheFilePath(Context context) {
        //缓存目录
        String cachePath;
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())
                || !Environment.isExternalStorageRemovable()) {
            //sd卡缓存目录
            cachePath = context.getExternalCacheDir().getPath();
        } else {
            cachePath = context.getCacheDir().getPath();
        }
        return cachePath;
    }

    /**
     * 获取imei号,有sim卡才能获取.
     */
    private void getImEiMethod() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale((Activity) mContext,
                    Manifest.permission.READ_PHONE_STATE)) {

                // Show an expanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.

            } else {

                // No explanation needed, we can request the permission.

                ActivityCompat.requestPermissions((Activity) mContext,
                        new String[]{Manifest.permission.READ_PHONE_STATE},
                        MY_PERMISSIONS_REQUEST_READ_CONTACTS);

                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.


                String imei = null;
                if (ActivityCompat.checkSelfPermission(mContext, Manifest.permission.READ_PHONE_STATE) == PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    imei = getImei();
                }
                Logger.d("onViewCreated:   " + "imei = " + imei);

                return;
            }
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @SuppressLint("MissingPermission")
    private String getImei() {
        TelephonyManager tm = (TelephonyManager) mContext.getSystemService(Context.TELEPHONY_SERVICE);
        return tm.getImei();
    }

    int MY_PERMISSIONS_REQUEST_READ_CONTACTS = 2001;
    private final Interceptor REWRITE_RESPONSE_INTERCEPTOR = new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            String requestCacheContrl = request.header("Cache-Control");
            if ("only-if-cached".equals(requestCacheContrl)) {
                Response originalResponse = chain.proceed(request);
                String cacheControl = originalResponse.header("Cache-Control");
                if (cacheControl == null || cacheControl.contains("no-store") || cacheControl.contains("no-cache") ||
                        cacheControl.contains("must-revalidate") || cacheControl.contains("max-age=0")) {
                    Response response = originalResponse.newBuilder()
                            .removeHeader("Pragma")
                            .header("Cache-Control", "public, max-age=" + 66)
                            .build();
                    Headers headers = response.headers();
                    if (headers != null) {
                        Logger.d("reponse================" + "\n" + headers.toString());
                    }
                    return response;

                } else {
                    return originalResponse;
                }
            } else {
                return chain.proceed(request);
            }


//            okhttp3.Response originalResponse = chain.proceed(chain.request());
//            String cacheControl = originalResponse.header("Cache-Control");
//            if (cacheControl == null || cacheControl.contains("no-store") || cacheControl.contains("no-cache") ||
//                    cacheControl.contains("must-revalidate") || cacheControl.contains("max-age=0")) {
//                Response response = originalResponse.newBuilder()
//                        .removeHeader("Pragma")
//                        .header("Cache-Control", "public, max-age=" + 66)
//                        .build();
//                Headers headers = response.headers();
//                if (headers != null) {
//                    Logger.d("reponse================" + "\n" + headers.toString());
//                }
//                return response;
//
//            } else {
//                return originalResponse;
//            }
        }
    };

    private final Interceptor REWRITE_RESPONSE_INTERCEPTOR_OFFLINE = new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            if (!NetWorkUtils.isNetworkConnected(getContext())) {
                request = request.newBuilder()
                        .removeHeader("Pragma")
                        .header("Cache-Control", "public, only-if-cached")
                        .build();
            }
            return chain.proceed(request);
        }
    };

    private String getCurrentTime() {
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");// HH:mm:ss
        Date date = new Date(System.currentTimeMillis());
//        time1.setText("Date获取当前日期时间" + simpleDateFormat.format(date));


        final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss z",
                Locale.ENGLISH);
        TimeZone.setDefault(TimeZone.getTimeZone("GMT+8"));

        return simpleDateFormat.format(date);
    }

    private void testCacheControl() {
        //缓存文件夹
        File cacheFile = new File(mContext.getExternalCacheDir().toString(), "cache");
        //缓存大小为10M
        int cacheSize = 10 * 1024 * 1024;
        //创建缓存对象
        final Cache cache = new Cache(cacheFile, cacheSize);

        new Thread(new Runnable() {
            @Override
            public void run() {
                OkHttpClient client = new OkHttpClient.Builder()
                        .cache(cache)
                        .addNetworkInterceptor(new NetInterceptor())
                        .addInterceptor(new NoNetInterceptor())
//                        .addInterceptor(new BaseInterceptor(getContext()))
                        .addInterceptor(new LoggerInterceptor("曹操", true))
                        .build();
                //设置缓存时间为60秒
                CacheControl cacheControl = new CacheControl.Builder()
                        .maxAge(30, TimeUnit.SECONDS)
//                        .onlyIfCached()
//                        .noCache()
                        .build();

                Request request = new Request.Builder()
                        .header("Accept-Datetime", getCurrentTime())
                        .header("abc", "123")
//                        .url("http://api.k780.com/?app=life.time&appkey=10003&sign=b59bc3ef6191eb9f747dd4e83c99f2a4&format=json")
                        .url("http://mapi.qfang.com/appapi/v7_0_3/transaction/list?devId=00000000-0e37-d1e5-5782-fcf90033c587&devModel=9&sign=78b19112a9e2fa8673bbb2f8f3aaac4f&dataSource=SHENZHEN&version=v7_0_3&platform=android&m_channel=qfangMarket&timestamp=1541666168426&bizType=SALE&pageSize=1&currentPage=1")
                        .cacheControl(cacheControl)
//                        .cacheControl(CacheControl.FORCE_CACHE)
                        .build();

                try {
                    Response response1 = client.newCall(request).execute();
                    Logger.i(" response1 :" + response1.toString());
                    String string = response1.body().string();
                    Logger.i(" response1 body :" + string);
                    Logger.i(" response1 缓存数据 :" + response1.cacheResponse());
                    Logger.i(" response1 网络数据 :" + response1.networkResponse());

                    Response cacheResponse = response1.cacheResponse();
//                    if (cacheResponse != null) {
////                        String string1 = response1.body().string();
//                        setTextOnUi("缓存数据" + string);
//                    } else {
//                        Response networkResponse = response1.networkResponse();
//                        if (networkResponse != null) {
////                            String string2 = response1.body().string();
//                            setTextOnUi(" 网络返回" + string);
//                        } else {
//                            ((Activity) mContext).runOnUiThread(new Runnable() {
//                                @Override
//                                public void run() {
//                                    ToastUtils.showShort(" 缓存和网络都有数据.");
//                                }
//                            });
//                        }
//                    }
//                    if (response1.code() != 504) {
//                        // 资源已经缓存了，可以直接使用
//                        Response cacheResponse = response1.cacheResponse();
//                        if (cacheResponse != null) {
//                            String string = response1.body().string();
//                            setTextOnUi("缓存数据" + string);
//                        } else {
////                            ((Activity) mContext).runOnUiThread(new Runnable() {
////                                @Override
////                                public void run() {
////                                    ToastUtils.showShort("缓存获取失败.");
////                                }
////                            });
//                            Response networkResponse = response1.networkResponse();
//                            if (networkResponse != null) {
//                                String string = response1.body().string();
//                                setTextOnUi("!=504 网络返回" + string);
//                            } else {
//                                ((Activity) mContext).runOnUiThread(new Runnable() {
//                                    @Override
//                                    public void run() {
//                                        ToastUtils.showShort(" 缓存和网络都有数据.");
//                                    }
//                                });
//                            }
//                        }
//                    } else {
//                        // 资源没有缓存，或者是缓存不符合条件了。
//                        Request request1 = new Request.Builder()
//                                .header("Accept-Datetime", getCurrentTime())
//                                .url("http://api.k780.com/?app=life.time&appkey=10003&sign=b59bc3ef6191eb9f747dd4e83c99f2a4&format=json")
//                                .cacheControl(CacheControl.FORCE_NETWORK)
//                                .build();
//                        response1 = client.newCall(request1).execute();
//                        String string1 = response1.body().string();
//                        setTextOnUi("网络数据" + string1);
//                    }
                    setTextOnUi("数据\n" + string);
                    response1.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }

    private void setTextOnUi(String string) {
        ((Activity) mContext).runOnUiThread(new Runnable() {
            @Override
            public void run() {
                tvTexcontent.setText(string);
            }
        });
    }

    private void okHttpTest() {
        //缓存文件夹
        File cacheFile = new File(mContext.getExternalCacheDir().toString(), "cache");
        //缓存大小为10M
        int cacheSize = 10 * 1024 * 1024;
        //创建缓存对象
        final Cache cache = new Cache(cacheFile, cacheSize);

        new Thread(new Runnable() {
            @Override
            public void run() {
                OkHttpClient client = new OkHttpClient.Builder()
                        .cache(cache)
                        .build();
                //官方的一个示例的url
                String url = "http://mapi.qfang.com/appapi/v7_0_3/school/area?devId=00000000-0e37-d1e5-5782-fcf90033c587&devModel=9&schoolType=w1&sign=9c25ad8b94fc962f498fe2fb08c960f7&dataSource=SHENZHEN&version=v7_0_3&platform=android&m_channel=qfangMarket&timestamp=1541498207519";

                Request request = new Request.Builder()
                        .url(url)
                        .build();
                Call call1 = client.newCall(request);
                Response response1 = null;
                try {
                    //第一次网络请求
                    response1 = call1.execute();
//                    Logger.i("testCache: response1 :" + response1.body().string());
//                    Logger.i("testCache: response1 cache :" + response1.cacheResponse());
//                    Logger.i("testCache: response1 network :" + response1.networkResponse());
                    response1.body().close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                Call call12 = client.newCall(request);

                try {
                    //第二次网络请求
                    Response response2 = call12.execute();
                    Logger.i("testCache: response2 :" + response2.body().string());
                    Logger.i("testCache: response2 cache :" + response2.cacheResponse());
                    Logger.i("testCache: response2 network :" + response2.networkResponse());
                    Logger.i("testCache: response1 equals response2:" + response2.equals(response1));
                    response2.body().close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
//        disposable.dispose();
        Logger.d("onDestroy:  disposable  ");

    }

    private void observableMethod2() {
        Observable observable = Observable.just("Hello", "Hi", "Aloha");
        observable.subscribe(new Observer() {
            @Override
            public void onSubscribe(Disposable d) {
                Logger.i("onSubscribe:   " + "d = [" + d + "]");

            }

            @Override
            public void onNext(Object o) {
                Logger.d("onNext:   " + "o = [" + o + "]");

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }

    private void observableMethod1() {
        Observable<Object> lambdaObservable1 = Observable.create(emitter -> {
                    emitter.onNext(1);
                    emitter.onNext(2);
                    emitter.onNext(3);
                    emitter.onComplete();
                }
        );
        Observer observer = generateObserver();
        lambdaObservable1.subscribe(observer);


        // RxJava的流式操作
        Observable.create(new ObservableOnSubscribe<Integer>() {
            // 1. 创建被观察者 & 生产事件
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                emitter.onNext(1);
                emitter.onNext(2);
                emitter.onNext(3);
                emitter.onComplete();
            }
        }).subscribe(new Observer<Integer>() {
            // 2. 通过通过订阅（subscribe）连接观察者和被观察者
            // 3. 创建观察者 & 定义响应事件的行为
            @Override
            public void onSubscribe(Disposable d) {
                Logger.d("开始采用subscribe连接");

            }
            // 默认最先调用复写的 onSubscribe（）

            @Override
            public void onNext(Integer value) {
                Logger.d("对Next事件" + value + "作出响应");
            }

            @Override
            public void onError(Throwable e) {
                Logger.d("对Error事件作出响应");
            }

            @Override
            public void onComplete() {
                Logger.d("对Complete事件作出响应");
            }

        });
    }

    /**
     * firebases生成的设备唯一标识,卸载app后会有变化.
     */
    private void firebaseInstanceId() {
        FirebaseInstanceId instance = FirebaseInstanceId.getInstance();
        String id = instance.getId();
        Logger.d(" 设备唯一标识 : firebaseInstanceId:   " + "instanceId = [" + id + "]");
    }

    /**
     * // 标识设备唯一号
     * 这个已经废弃,google
     * implementation 'com.google.android.gms:play-services-gcm:15.0.1' 已经变成firebase
     *
     * @return
     */
//    private String getInstanceId() {
//        String iid = InstanceID.getInstance(getContext()).getId();
//        return iid;
//    }
    @NonNull
    private Observer generateObserver() {
        //观察者方式一
        Observer observer = new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {
                Logger.d("======================onSubscribe");
            }

            @Override
            public void onNext(Integer integer) {
                Logger.d("======================onNext " + integer);
            }

            @Override
            public void onError(Throwable e) {
                Logger.d("======================onError");
            }

            @Override
            public void onComplete() {
                Logger.d("======================onComplete");
            }
        };
//        //观察者方式二
        Subscriber<Object> objectSubscriber = new Subscriber<Object>() {
            @Override
            public void onSubscribe(Subscription s) {

            }

            @Override
            public void onNext(Object integer) {

            }

            @Override
            public void onError(Throwable t) {

            }

            @Override
            public void onComplete() {

            }
        };
        return observer;
    }

    /**
     * 弱引用
     */
    private void testWeakRefrence() {
        String abc = new String("abc");
        WeakReference<String> abcWeakRef = new WeakReference<String>(abc);
        abc = null;
        System.out.println("before gc: " + abcWeakRef.get());
        System.gc();
        System.out.println("after gc: " + abcWeakRef.get());
    }

    private void testSoftRefrence() {


    }

    private void makeRefcltImage() {
        Canvas canvas = new Canvas();
        Paint mPaint = new Paint();
        // 为了突出效果，先绘制一个灰色的画布
        canvas.drawColor(Color.GRAY);

        int x = 200, y = 200;

        // 获取源图
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.icon_picture);

        // 实例化一个矩阵对象
        Matrix matrix = new Matrix();
        matrix.setScale(1F, -1F);
        // 产生和原图大小一样的倒影图
        Bitmap refBitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);

        // 绘制好原图
        canvas.drawBitmap(bitmap, x, y, null);
        // 保存图层。这里保存的图层宽度是原图绘制区域的宽度，高度是原图绘制区域两倍的高度，包含了绘制倒影的区域。
        int sc = canvas.saveLayer(x, y + bitmap.getHeight(), x + bitmap.getWidth(), y + bitmap.getHeight() * 2, null, Canvas.ALL_SAVE_FLAG);

        // 绘制倒影图片，绘制的区域紧贴原图的底部
        canvas.drawBitmap(refBitmap, x, y + bitmap.getHeight(), null);

        // 设置好混合模式
        mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
        /*
         *  设置线性渐变模式。
         *  这里绘制的高度是原图的1/4，也就说倒影最终的区域也就是原图的1/4
         *  颜色是从Color.BLACK到透明，用于和倒影图做混合模式。
         *  模式是边缘拉伸模式，这里没用到
         */
        mPaint.setShader(new LinearGradient(x, y + bitmap.getHeight(),
                x, y + bitmap.getHeight() + bitmap.getHeight() / 4,
                Color.BLACK, Color.TRANSPARENT, Shader.TileMode.CLAMP));
        // 画一个矩形区域，作为目标图片，用来做混合模式
        canvas.drawRect(x, y + bitmap.getHeight(), x + refBitmap.getWidth(), y + bitmap.getHeight() * 2, mPaint);

        mPaint.setXfermode(null);

        // 回复图层
        canvas.restoreToCount(sc);
    }

    private void makeThread() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Looper mainLooper = Looper.getMainLooper();
                Looper.prepare();
                Logger.d("  子线程 looper  " + " loop " + mainLooper.hashCode());
//				MessageQueue mainLooperQueue = Looper.getQueue();
                String threadName = Thread.currentThread().getName();
                Logger.d(" thread name  " + threadName);
                Handler childhandler = new Handler(Looper.myLooper()) {
                    @Override
                    public void dispatchMessage(Message msg) {
                        super.dispatchMessage(msg);
                        switch (msg.what) {
                            case 0:
                                break;
                            case 1:
//                                textView7.setText(" 非ui 线程");
//								textView7.invalidate();
                                break;
                            case 2:
                                break;
                            default:
                                break;
                        }
                    }
                };
                Message message = Message.obtain();
                message.what = 1;
                message.obj = "child  hanlder  obj ";
                childhandler.sendMessage(message);
//
//
                Looper.loop();
//				Logger.e(" 老子后面的就不执行了.....");

//				Looper.prepare();
//				Looper.getMainLooper();
//				SystemClock.sleep(3000);
//				textView7.setText(" new Thread update ui .....");
//				Looper.loop();

            }
        }).start();
    }


    @OnClick({R.id.btn_subthread})
    void btnOnclick(View v) {
        switch (v.getId()) {
            case R.id.btn_subthread:
//                makeThread();
                tvTexcontent.setText("");
                testCacheControl();
                break;
//            case R.id.textView7:
//                Intent intent = new Intent(getContext(), StickyHeaderListViewActivity.class);
//                startActivity(intent);
//                break;
            default:
                break;
        }
    }

    /**
     * Uri 格式测试
     */
    private void testUri() {
//		String newUrl = "openactivity://homepage_adv/entrust?datasource=shenzhen&cityname='深圳'#com.qfang.androidclient.activities.entrust.view.activity.OwnerEntrustActivity";
        String newUrl = "http://m.qfang.com/shenzhen/info/label/15?id=14&name=你好";
        Uri uri = Uri.parse(newUrl);
        Logger.d("getScheme " + uri.getScheme());
        Logger.d("getSchemeSpecificPart " + uri.getSchemeSpecificPart());
        Logger.d("getFragment " + uri.getFragment());
        Logger.d("getAuthority " + uri.getAuthority());
        Logger.d("getPath " + uri.getPath());
        Logger.d("getQuery " + uri.getQuery());
        Logger.d("getHost " + uri.getHost());
        Logger.d("getPost " + uri.getPort());
//		Set<String> stringSet = uri.getQueryParameterNames();
//		for (String key : stringSet) {
//			Logger.d("key "+key +"  value "+uri.getQueryParameter(key));
//		}
    }

    public static String Url = "http://10.251.93.254:8010/appapi/v4_3/room/list?bizType=SALE&dataSource=SHENZHEN&pageSize=10";

    private void requestUpdate(final String currentPageStr) {
        String httpUrl = Url + "&currentPage=" + currentPageStr;
        OkHttpUtils
                .get()//
                .url(httpUrl)//
                .build()//
                .execute(new SecListItemBeanCallback() {

                    @Override
                    public void onError(Call call, Exception e, int id) {
                    }

                    @Override
                    public void onResponse(SecListBean response, int id) {
                    }
                });
    }

    /**
     * 动态添加textview
     */
    private void addView() {
//        mLinearLayout.setVisibility(View.VISIBLE);
//        mLinearLayout.setOrientation(LinearLayout.HORIZONTAL);
//        mLinearLayout.addView(makeTextView());
//        mLinearLayout.addView(makeTextView());
//        mLinearLayout.addView(makeTextView());
//        mLinearLayout.addView(makeTextView());
    }

    private TextView makeTextView() {
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        params.leftMargin = 10;
        TextView textView = new TextView(getActivity());
        textView.setLayoutParams(params);
        textView.setBackgroundResource((R.drawable.shap_round_corner));
        int left = ScreenUtil.Dp2Px(mContext, 10);
        int top = ScreenUtil.Dp2Px(mContext, 5);
        int right = ScreenUtil.Dp2Px(mContext, 10);
        int bottom = ScreenUtil.Dp2Px(mContext, 5);
        textView.setPadding(left, top, right, bottom);
        textView.setGravity(Gravity.CENTER);
        textView.setText("标签一号");
        return textView;
    }

    /**
     * 打印屏幕宽高.
     */
    private void printScreenDes() {
        // 获取屏幕密度（方法3）
        DisplayMetrics dm = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);

        float density = dm.density;      // 屏幕密度（像素比例：0.75/1.0/1.5/2.0）
        int densityDPI = dm.densityDpi;     // 屏幕密度（每寸像素：120/160/240/320）
        float xdpi = dm.xdpi;
        float ydpi = dm.ydpi;

        Logger.d(" DisplayMetrics" + "  xdpi=" + xdpi + " ydpi=" + ydpi);
        Logger.i("  DisplayMetrics" + "   density=" + density + " densityDPI=" + densityDPI);

        int screenWidthDip = dm.widthPixels;        // 屏幕宽（dip，如：320dip）
        int screenHeightDip = dm.heightPixels;      // 屏幕宽（dip，如：533dip）


        Logger.e("  DisplayMetrics(222)" + "   screenWidthDip=" + screenWidthDip + "   screenHeightDip=" + screenHeightDip);

        screenWidth = (int) (dm.widthPixels * density + 0.5f);      // 屏幕宽（px，如：480px）
        screenHeight = (int) (dm.heightPixels * density + 0.5f);     // 屏幕高（px，如：800px）

        Logger.e("  DisplayMetrics(222)" + "  screenWidth=" + screenWidth + "    screenHeight=" + screenHeight);


        // 获取屏幕密度（方法1）
        int screenWidth = getActivity().getWindowManager().getDefaultDisplay().getWidth();       // 屏幕宽（像素，如：480px）
        int screenHeight = getActivity().getWindowManager().getDefaultDisplay().getHeight();      // 屏幕高（像素，如：800p）

        Logger.i("屏幕宽高  getDefaultDisplay" + "  screenWidth=" + screenWidth + " screenHeight=" + screenHeight);
        Logger.i("scale " + screenHeight);
    }


    private SpannableStringBuilder setSpanString() {
        SpannableStringBuilder builder = new SpannableStringBuilder();
        String one = "努力";
        builder.append(one + "  ");
        int start = builder.length();
        String center = "恒心";
        int end = start + center.length();
        builder.append(center);
        builder.setSpan(new ForegroundColorSpan(Color.rgb(32, 178, 170)), start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
//        String end = "耐心";
//        builder.append("  "+end);
        return builder;
    }

    /**
     * Spannable 的使用
     */
    private void spanTest() {
//        final LinearLayout linearLayout = new LinearLayout(this);
//        LayoutParams layoutParams = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT);
//        mLayout.addView(linearLayout, layoutParams);

//        new LinearLayout.LayoutParams(getActivity(), LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT);
//        TextView tv = new TextView(getActivity());
//        tv.setText(getArguments().getString("KEY"));
//        tv.setText("测试内容1111");
//        SpannableStringBuilder spanString = setSpanString();


//        String content = "The quick fox jumps over the lazy dog";
//        int start = content.indexOf('q');
//        int end = content.indexOf('k') + 1;

//        String content = "";
//        Spannable word = new SpannableString(content);
//        word.setSpan(new AbsoluteSizeSpan(ScreenUtil.Dp2Px(getActivity(),16)), content.length()-1, content.length(),
//                Spannable.SPAN_INCLUSIVE_INCLUSIVE);
//        word.setSpan(new StyleSpan(Typeface.BOLD), start, end,
//                Spannable.SPAN_INCLUSIVE_INCLUSIVE);
//        word.setSpan(new ForegroundColorSpan(Color.YELLOW),content.length()-1, content.length(),Spannable.SPAN_INCLUSIVE_INCLUSIVE);
//        String totalPrice ="16250000";
//        float v = Integer.parseInt(totalPrice) / Float.parseFloat("156.7");
//        tv.setText((int) v+"/㎡");
////        tv.setText(spanString);
//        return tv;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }


}