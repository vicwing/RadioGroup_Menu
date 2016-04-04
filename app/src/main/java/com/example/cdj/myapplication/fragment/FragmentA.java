package com.example.cdj.myapplication.fragment;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.cdj.myapplication.R;
import com.example.cdj.myapplication.cusview.CircleCornerTextView;
import com.example.cdj.myapplication.cusview.CusTextView;
import com.orhanobut.logger.Logger;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by cdj on 2016/2/1.
 */
public class FragmentA extends Fragment {

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

    private int screenWidth;
    private int screenHeight;

    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_content, null);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        printScreenDes();
//        Logger.clear();
        Logger.init("circlecorner").setMethodCount(1).hideThreadInfo();
        String text = "认证田贝四路水工工业区翠北小区海鹏大院x栋301";
        tvCircleConer.setText(text);
        tvCircleConer.setTextSize(60);
//        tvCircleConer.setTextColor(getResources().getColor(R.color.orange));
        tvCircleConer.setBorderColor(getResources().getColor(R.color.orange));
        Logger.d("哈哈哈哈哈");

        custextview.setText(text);
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
//        tv.setText(getArguments().getString("key"));
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