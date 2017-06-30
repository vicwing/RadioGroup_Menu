package com.example.cdj.myapplication.mainfunction.function1;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.cdj.myapplication.Bean.SecListBean;
import com.example.cdj.myapplication.R;
import com.example.cdj.myapplication.SecListItemBeanCallback;
import com.example.cdj.myapplication.base.BaseFragment;
import com.example.cdj.myapplication.utils.ScreenUtil;
import com.orhanobut.logger.Logger;
import com.zhy.http.okhttp.OkHttpUtils;

import butterknife.ButterKnife;
import okhttp3.Call;

/**
 * Created by cdj onCallBackData 2016/2/1.
 */
public class FragmentA extends BaseFragment {
    //    @Bind(R.id.tv_television)
//    TextView tvTelevision;
//    @Bind(R.id.tv_qchat)
//    TextView tvQchat;
    private Context mContext;
    private int screenWidth;
    private int screenHeight;
    private String uritext = "http://www.java2s.com:8080/yourpath/fileName.htm?stove=10&path=32&id=4#harvic";

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
        ButterKnife.bind(this, view);

//        testUri();

//        tvQchat.setVisibility(View.GONE);
//        addView();
//        HtmlBuilder html = new HtmlBuilder();
//        html.p("Lorem ipsum dolor sit amet, denique detraxit reprimique quo in. Ius dicat omnes mucius cu.");
//        html.font().color("red").face("sans-serif-condensed").text("Red Text").close();
//        tvSpanble.setText(html.build());
//        Logger.d("htmlbuilder  "+html.toString());
////        TestDataModel.getInstance().setRetainedTextView(tvSpanble);
////        requestUpdate("110");
//        ImageView imageView = (ImageView) getActivity().findViewById(R.id.iv_selector);
//        imageView.setImageDrawable(getResources().getDrawable(R.drawable.qf_collect_selector));
//        imageView.setSelected(true);
//        View tv_facility_name = getActivity().findViewById(R.id.tv_facility_name);
//        tv_facility_name.setSelected(true);
//        tv_facility_name.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Logger.d("点击时间......................");
//            }
//        });
//
//        PreferencesUtils.putDouble(mContext,"test1",24241348.454646545d);
//        double text1 = PreferencesUtils.getDouble(mContext, "test1", 4.5);
//        Logger.d("PreferencesUtils  test1 "+new DecimalFormat("#").format(text1));
//        String string = BigDecimal.valueOf(text1).toString();
//        Logger.d("PreferencesUtils  str "+string);

    }

    private void testUri() {
        Uri uri = Uri.parse(uritext);
        Logger.d("getScheme " + uri.getScheme());
        Logger.d("getSchemeSpecificPart " + uri.getSchemeSpecificPart());
        Logger.d("getFragment " + uri.getFragment());
        Logger.d("getAuthority " + uri.getAuthority());
        Logger.d("getPath " + uri.getPath());
        Logger.d("getQuery " + uri.getQuery());
        Logger.d("getHost " + uri.getHost());
        Logger.d("getPost " + uri.getPort());
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
        ButterKnife.unbind(this);
    }

}