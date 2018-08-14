package com.example.cdj.myapplication.mainfunction.function1;

import android.content.Context;
import android.content.Intent;
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
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.cdj.myapplication.Bean.SecListBean;
import com.example.cdj.myapplication.R;
import com.example.cdj.myapplication.SecListItemBeanCallback;
import com.example.cdj.myapplication.activity.webview.StickyHeaderListViewActivity;
import com.example.cdj.myapplication.base.BaseFragment;
import com.example.cdj.myapplication.utils.ScreenUtil;
import com.google.android.gms.tasks.Task;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
import com.orhanobut.logger.Logger;
import com.zhy.http.okhttp.OkHttpUtils;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.lang.ref.WeakReference;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import okhttp3.Call;

/**
 * Created by cdj onCallBackData 2016/2/1.
 */
public class FragmentA extends BaseFragment {
    @BindView(R.id.textView7)
    TextView textView7;
    @BindView(R.id.textView6)
    TextView textView6;
    @BindView(R.id.textView5)
    TextView textView5;
    @BindView(R.id.imageView)
    ImageView imageView;
    //    @BindView(R.id.tv_television)
//    TextView tvTelevision;
//    @BindView(R.id.tv_qchat)
//    TextView tvQchat;
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

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);
        mContext = getActivity();
//		testUri();


//		String abc=new String("abc");  //1
//		SoftReference<String> abcSoftRef=new SoftReference<String>(abc);  //2
//		WeakReference<String> abcWeakRef = new WeakReference<String>(abc); //3
//		abc=null; //4
//		abcSoftRef.clear();//5


//		testWeakRefrence();
//		testSoftRefrence();

//        Observable.create(n -> System.out.println("2222222222"));
//        Observable observable = Observable.create(new ObservableOnSubscribe<Integer>() {
//            @Override
//            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
//                Logger.d("=========================currentThread name: " + Thread.currentThread().getName());
//                e.onNext(1);
//                e.onNext(2);
//                e.onNext(3);
//                e.onComplete();
//            }
//        });


        //lambda 方式调用
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
     *
     * @param instanceId
     */
    private void firebaseInstanceId(Task<InstanceIdResult> instanceId) {
        String id = FirebaseInstanceId.getInstance().getId();
        Logger.d("firebaseInstanceId:   " + "instanceId = [" + id + "]");
        InstanceIdResult instanceIdResult = instanceId.getResult();
        Logger.d("firebaseInstanceId:   " + "instanceId = [" + instanceIdResult.getId() + "]" + " token =" + instanceIdResult.getToken());
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
                                textView7.setText(" 非ui 线程");
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


    @OnClick({R.id.btn_subthread, R.id.textView7})
    void btnOnclick(View v) {
        switch (v.getId()) {
            case R.id.btn_subthread:
                makeThread();
                break;
            case R.id.textView7:
                Intent intent = new Intent(getContext(), StickyHeaderListViewActivity.class);
                startActivity(intent);
                break;
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

    int count = 0;
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