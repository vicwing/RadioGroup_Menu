package com.example.cdj.myapplication.mainfunction.function1;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.cdj.myapplication.Bean.SecListBean;
import com.example.cdj.myapplication.R;
import com.example.cdj.myapplication.SecListItemBeanCallback;
import com.example.cdj.myapplication.base.BaseFragment;
import com.example.cdj.myapplication.baseadapter.adapterhelper.BaseAdapterHelper;
import com.example.cdj.myapplication.baseadapter.adapterhelper.QuickAdapter;
import com.example.cdj.myapplication.widget.CircleCornerTextView;
import com.example.cdj.myapplication.widget.CusTextView;
import com.example.cdj.myapplication.utils.PreferencesUtils;
import com.example.cdj.myapplication.utils.ScreenUtil;
import com.jrummyapps.android.util.HtmlBuilder;
import com.orhanobut.logger.Logger;
import com.zhy.http.okhttp.OkHttpUtils;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;

/**
 * Created by cdj onCallBackData 2016/2/1.
 */
public class FragmentA extends BaseFragment {
    private Context mContext;

    @Bind(R.id.width_tv)
    TextView widthTv;
    @Bind(R.id.height_tv)
    TextView heightTv;

//    @Bind(R.id.iv_circleimage)
//    CircleImageView ivCircleimage;

    @Bind(R.id.tv_circle_coner)
    CircleCornerTextView tvCircleConer;

    @Bind(R.id.custextview)
    CusTextView custextview;
    @Bind(R.id.tv_spanble)
    TextView tvSpanble;
    @Bind(R.id.ll_newhousedetail_label)
    LinearLayout mLinearLayout;

    private int screenWidth;
    private int screenHeight;

    SpannableString msp = null;

    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.content_fragment1, null);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);
        mContext = getActivity();
        ButterKnife.bind(this, view);
        printScreenDes();
        String text = "认证田贝四路水工工业区翠北小区海鹏大院x栋301";

        tvCircleConer.setText(text);
        tvCircleConer.setTextSize(60);
        tvCircleConer.setBorderColor(getResources().getColor(R.color.orange));
        Logger.d("哈哈哈哈哈");
        custextview.setText(text);

        addView();
//        // 设置图片
//        Drawable drawable = getResources().getDrawable(R.drawable.trash);
//        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
//        msp.setSpan(new ImageSpan(drawable), 53, 57, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
//
//        // 设置边框
//        Drawable bg = getResources().getDrawable(R.drawable.text_background);
//        msp.setSpan(new ImageSpan(bg) {
//            @Override
//            public void draw(Canvas canvas, CharSequence text, int start, int end, float x, int top, int y,
//                             int bottom, Paint paint) {
//                paint.setTypeface(Typeface.create("normal", Typeface.BOLD));
//                paint.setTextSize(50);
//                int len = Math.round(paint.measureText(text, start, end));
//                getDrawable().setBounds(0, 0, len, 60);
//                super.draw(canvas, text, start, end, x, top, y, bottom, paint);
//                paint.setColor(Color.BLUE);
//                paint.setTypeface(Typeface.create("normal", Typeface.BOLD));
//                paint.setTextSize(40);
//                canvas.drawText(text.subSequence(start, end).toString(), x + 10, y, paint);
//            }
//        }, 57, 59, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
//        mTextView.setText(msp);
//        mTextView.setMovementMethod(LinkMovementMethod.getInstance());


        //html builder 测试
        HtmlBuilder html = new HtmlBuilder();
        html.p("Lorem ipsum dolor sit amet, denique detraxit reprimique quo in. Ius dicat omnes mucius cu.");
        html.font().color("red").face("sans-serif-condensed").text("Red Text").close();
        tvSpanble.setText(html.build());
        Logger.d("htmlbuilder  "+html.toString());
//        TestDataModel.getInstance().setRetainedTextView(tvSpanble);
//        requestUpdate("110");
        ImageView imageView = (ImageView) getActivity().findViewById(R.id.iv_selector);
        imageView.setImageDrawable(getResources().getDrawable(R.drawable.qf_collect_selector));
        imageView.setSelected(true);
        View tv_facility_name = getActivity().findViewById(R.id.tv_facility_name);
        tv_facility_name.setSelected(true);
        tv_facility_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Logger.d("点击时间......................");
            }
        });

//        setRentFacilites();

//        Logger.d("  网络 isMobileConnected   "+ NetWorkUtils.isMobileConnected(mContext));
//        Logger.d("  网络 isNetworkConnected   "+ NetWorkUtils.isNetworkConnected(mContext));
//        Logger.d("  网络 getConnectedType   "+ NetWorkUtils.getConnectedType(mContext));
//        Logger.d("  网络 getAPNType   "+ NetWorkUtils.getAPNType(mContext));
        PreferencesUtils.putDouble(mContext,"test1",24241348.454646545d);
//        PreferencesUtils.putDouble(mContext,"test1",Double.MAX_VALUE);
        double text1 = PreferencesUtils.getDouble(mContext, "test1", 4.5);
        Logger.d("PreferencesUtils  test1 "+text1);
        Logger.d("PreferencesUtils  test1 "+new DecimalFormat("#").format(text1));
        String string = BigDecimal.valueOf(text1).toString();
        Logger.d("PreferencesUtils  str "+string);
//        Logger.d("PreferencesUtils  test1 "+PreferencesUtils.getDouble(mContext, "test1", 4.5));
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
        mLinearLayout.setVisibility(View.VISIBLE);
        mLinearLayout.setOrientation(LinearLayout.HORIZONTAL);

//        TextView view1 = (TextView) getActivity().getLayoutInflater().inflate(R.layout.item_textview, null);
//        View view =  getActivity().getLayoutInflater().inflate(R.layout.item_textview, null);
//        TextView textView = (TextView) view.findViewById(R.id.calculatorBtn);
//        ViewGroup.LayoutParams vlp = new ViewGroup.LayoutParams(
//                ViewGroup.LayoutParams.WRAP_CONTENT,
//                ViewGroup.LayoutParams.WRAP_CONTENT);
//        view.setLayoutParams(vlp);
//        textView.setText("标签1");
//        view1.setText("房贷计算器1");
//        TextView textView1 = makeTextView();

        mLinearLayout.addView(makeTextView());
        mLinearLayout.addView(makeTextView());
        mLinearLayout.addView(makeTextView());
        mLinearLayout.addView(makeTextView());
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

        widthTv.setText("宽度");
        heightTv.setText("高度");

        // 获取屏幕密度（方法1）
        int screenWidth = getActivity().getWindowManager().getDefaultDisplay().getWidth();       // 屏幕宽（像素，如：480px）
        int screenHeight = getActivity().getWindowManager().getDefaultDisplay().getHeight();      // 屏幕高（像素，如：800p）

        Logger.i("屏幕宽高  getDefaultDisplay" + "  screenWidth=" + screenWidth + " screenHeight=" + screenHeight);
        Logger.i("scale " + screenHeight);
    }

    public static int px2dip(Context context, float pxValue) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    public static int dip2px(Context context, float dipValue) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
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

    /**
     * 二手房租房  房屋设置
     */
    private void setRentFacilites() {
        GridView gridView = (GridView) getActivity().findViewById(R.id.gv_bacicinfo_rent);
//床|电视|洗衣机|空调|热水器|冰箱|宽带|家具|独立厨房|阳台|独立卫生间|游泳池|车位|电梯
        List<String> facilitesList = new ArrayList<>();
        final String bed = "床";
        final String television = "电视";
        final String washing = "洗衣机";
        final String heater = "热水器";
        final String refrigerator = "冰箱";
        final String elevator = "电梯";
        final String parking = "车位";
        final String airconditioner = "空调";
        String wlan = "宽带";
        facilitesList.add(television);
        facilitesList.add(bed);
        facilitesList.add(washing);
        facilitesList.add(airconditioner);
        facilitesList.add(refrigerator);
        facilitesList.add(parking);
        facilitesList.add(elevator);
        facilitesList.add(heater);
//        facilitesList.add(wlan);
//        facilitesList.add("家具");
//        facilitesList.add("独立厨房");
//        facilitesList.add("阳台");
//        facilitesList.add("独立卫生间");
//        facilitesList.add("游泳池");

//        if (TextUtils.isEmpty(data.facilites)) {
        gridView.setVisibility(View.VISIBLE);
        final String facilites = "床";
//            String[] facilites = data.facilites.split(",");
        gridView.setEnabled(false);
        gridView.setAdapter(new QuickAdapter<String>(getActivity(), R.layout.item_gridview_detail_basicinfo_rent, facilitesList) {

            @Override
            protected void convert(BaseAdapterHelper helper, String item) {
                helper.setText(R.id.tv_facility_name, item);
//                if (bed.equals(item)) {
//                    helper.setImageDrawable(R.id.iv_icon_detail_facility, context.getResources().getDrawable(R.drawable.selector_detail_baseinfo_bed));
//                }
//                  else if (television.equals(item)) {
//                    helper.setImageDrawable(R.id.iv_icon_detail_facility,  context.getResources().getDrawable(R.drawable.selector_detail_baseinfo_television));
//                } else if (elevator.equals(item)) {
//                    helper.setImageDrawable(R.id.iv_icon_detail_facility,  context.getResources().getDrawable(R.drawable.selector_detail_baseinfo_elevator));
//                } else if (heater.equals(item)) {
//                    helper.setImageDrawable(R.id.iv_icon_detail_facility, context.getResources().getDrawable( R.drawable.selector_detail_baseinfo_heater));
//                } else if (parking.equals(item)) {
//                    helper.setImageDrawable(R.id.iv_icon_detail_facility, context.getResources().getDrawable( R.drawable.selector_detail_baseinfo_parking));
//                } else if (refrigerator.equals(item)) {
//                    helper.setImageDrawable(R.id.iv_icon_detail_facility,  context.getResources().getDrawable(R.drawable.selector_detail_baseinfo_refrigerator));
//                } else if (washing.equals(item)) {
//                    helper.setImageDrawable(R.id.iv_icon_detail_facility,  context.getResources().getDrawable(R.drawable.selector_detail_baseinfo_washing));
//                } else if (airconditioner.equals(item)) {
//                    helper.setImageDrawable(R.id.iv_icon_detail_facility, context.getResources().getDrawable(R.drawable.selector_detail_baseinfo_airconditioner));
//                }
//                }

                if (!TextUtils.isEmpty(facilites)) {
                    if (facilites.contains(item)) {
                        ImageView view1 = helper.getView(R.id.iv_icon_detail_facility);
                        view1.setSelected(true);

                        TextView view = helper.getView(R.id.tv_facility_name);
                        view.setPressed(true);
                        Logger.d("这个设备存在  item " + item);
//                        notifyDataSetChanged();
                    }
                }
            }

        });
    }

}