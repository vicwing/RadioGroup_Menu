package com.example.cdj.myapplication.mainfunction.function2;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuffXfermode;
import android.graphics.Shader;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.blankj.utilcode.util.ImageUtils;
import com.example.cdj.myapplication.R;
import com.example.cdj.myapplication.widget.propertyanimator.MyAnimView;
import com.orhanobut.logger.Logger;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by cdj onCallBackData 2016/2/1.
 */
public class Fragment2 extends Fragment implements View.OnClickListener {


    @BindView(R.id.btn_animate)
    Button mBtnAnimate;

    @BindView(R.id.iv_fragment2)
    ImageView iv_fragment2;

    @BindView(R.id.animate_view)
    MyAnimView animateView;

    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        View view = inflater.inflate(R.layout.content_fragment2, null);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mBtnAnimate.setOnClickListener(this);

//        ValueAnimator anim = ValueAnimator.ofFloat(0f, 5f, 3f, 10f);
//        anim.setDuration(5000);
//        anim.start();
//        Context.obtainDrawable();
//        View.performAccessibilityAction();

//        int hasWriteContactsPermission = checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE);
//        if (hasWriteContactsPermission != PackageManager.PERMISSION_GRANTED) {
//            Activity activty=this;
//            ActivityCompat.requestPermissions(activty,new String[] {Manifest.permission.WRITE_EXTERNAL_STORAGE},
//                    CODE_FOR_WRITE_PERMISSION);
//            return;
//        }
//        ContextCompat.checkSelfPermission();
        Bitmap decodeResource = BitmapFactory.decodeResource(getResources(), R.drawable.icon_picture);
        Bitmap addReflection = ImageUtils.addReflection(decodeResource,1000);
        Bitmap fastBlur = ImageUtils.fastBlur(addReflection, 1, 3);
        iv_fragment2.setImageBitmap(fastBlur);
    }

    /**
     * 倒影模糊并且加黑色阴影效果
     * @param src
     * @param reflectionHeight
     * @param recycle
     * @return
     */
    public static Bitmap addReflection(Bitmap src, int reflectionHeight, boolean recycle) {
        if (isEmptyBitmap(src)) return null;
        // 原图与倒影之间的间距
        final int REFLECTION_GAP = 0;
        int srcWidth = src.getWidth();
        int srcHeight = src.getHeight();
        Matrix matrix = new Matrix();
        matrix.preScale(1, -1);
        Bitmap reflectionBitmap = Bitmap.createBitmap(src, 0, srcHeight - reflectionHeight,
                srcWidth, reflectionHeight, matrix, false);
        Bitmap ret = Bitmap.createBitmap(srcWidth, srcHeight + reflectionHeight, src.getConfig());
        Canvas canvas = new Canvas(ret);
        canvas.drawBitmap(src, 0, 0, null);
        canvas.drawBitmap(reflectionBitmap, 0, srcHeight + REFLECTION_GAP, null);
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        LinearGradient shader = new LinearGradient(0, srcHeight,
                0, ret.getHeight() + REFLECTION_GAP,
                0x70FFFFFF, 0x00FFFFFF, Shader.TileMode.MIRROR);
        paint.setShader(shader);
        paint.setXfermode(new PorterDuffXfermode(android.graphics.PorterDuff.Mode.DST_IN));
        canvas.drawRect(0, srcHeight + REFLECTION_GAP,
                srcWidth, ret.getHeight(), paint);
        if (!reflectionBitmap.isRecycled()) reflectionBitmap.recycle();
        if (recycle && !src.isRecycled()) src.recycle();
        return ret;
    }
    private int checkSelfPermission(String storage) {
        return 0;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

//        if (requestCode == CODE_FOR_WRITE_PERMISSION){
//            if (permissions[0].equals(Manifest.permission.WRITE_EXTERNAL_STORAGE)
//                    &&grantResults[0] == PackageManager.PERMISSION_GRANTED){
//                //用户同意使用write
//                startGetImageThread();
//            }else{
//                //用户不同意，自行处理即可
//                finish();
//            }
//        }
    }

    @OnClick(R.id.btn_move1)
    void textviewOnClick1(){
//        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(textview, "translationX",0, 100);
//        objectAnimator.setDuration(300);
////        objectAnimator.setupEndValues();
//        objectAnimator.start();
    }
    @OnClick(R.id.btn_move2)
    void textviewOnClick2(){
//        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(textview, "translationX",0, 400);
//        objectAnimator.setupStartValues();
//        objectAnimator.setDuration(300);
//        objectAnimator.start();
    }

    private void startObjectAnimator() {
//        ObjectAnimator animator = ObjectAnimator.ofFloat(textview, "alpha", 1f, 0f, 1f);
//        ObjectAnimator animator = ObjectAnimator.ofFloat(textview, "rotation", 0f, 360f);
//        float curTranslationX = textview.getTranslationX();
//        ObjectAnimator animator = ObjectAnimator.ofFloat(textview, "translationX", curTranslationX, -500f, curTranslationX);
//        animator.setDuration(5000);
//        animator.start();

        //多个动画同时执行  进入动画结束后  旋转,淡入淡出同时执行
//        ObjectAnimator moveIn = ObjectAnimator.ofFloat(textview, "translationX", -500f, 0f);
//        ObjectAnimator rotate = ObjectAnimator.ofFloat(textview, "rotation", 0f, 360f);
//        ObjectAnimator fadeInOut = ObjectAnimator.ofFloat(textview, "alpha", 1f, 0f, 1f);
//        AnimatorSet animSet = new AnimatorSet();
//        animSet.play(rotate).with(fadeInOut).after(moveIn);
//        animSet.setDuration(5000);
//        animSet.start();
//        animSet.addListener(new AnimatorListenerAdapter() {
//            @Override
//            public void onAnimationStart(Animator animation) {
//                super.onAnimationStart(animation);
//            }
//        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_animate:
                Logger.d("startObjectAnimator");
//                startObjectAnimator();
                animateView.resetPoint();
                break;
            default:
                break;
        }
    }
    /**
     * 判断bitmap对象是否为空
     *
     * @param src 源图片
     * @return {@code true}: 是<br>{@code false}: 否
     */
    private static boolean isEmptyBitmap(Bitmap src) {
        return src == null || src.getWidth() == 0 || src.getHeight() == 0;
    }
}