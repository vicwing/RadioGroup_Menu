package com.example.cdj.myapplication.mainfunction.function3;

import android.animation.ValueAnimator;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.cdj.myapplication.R;
import com.example.cdj.myapplication.mainfunction.adapter.MySimpledapter;
import com.example.cdj.myapplication.widget.segmentcontrol.SegmentControl;
import com.orhanobut.logger.Logger;

import java.io.File;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by cdj onCallBackData 2016/5/6.
 */
public class Fragment3 extends Fragment {
    //    public static  String Url = "http://10.251.93.254:8010/appapi/v4_3/room/list?bizType=SALE&dataSource=SHENZHEN&pageSize=20&currentPage=1&s=SHENZHEN";
    // 名字根据实际需求进行更改
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    @BindView(R.id.segment_control)
    SegmentControl mSegmentControl;

    @BindView(R.id.recycleview)
    RecyclerView recycleview;
    Unbinder unbinder;
    @BindView(R.id.button_translate)
    Button buttonTranslate;

    private boolean buttonFlag = true;
    /**
     * 通过工厂方法来创建Fragment实例
     * 同时给Fragment来提供参数来使用
     *
     * @return Master_Fragment的实例.
     */
    public static Fragment3 newInstance(String param1, String param2) {
        Fragment3 fragment = new Fragment3();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public Fragment3() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
//        return inflater.inflate(R.layout.content_fragment3, container, false);
        // set up views
        final View layout = inflater.inflate(R.layout.content_fragment3, null);
        unbinder = ButterKnife.bind(this, layout);
        initSegmentControl(layout);
        return layout;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initRecycleview();

//        buttonScaleAni();

    }

    private void buttonScaleAni(final int startWidth, int endWidth) {
        // 创建动画作用对象：此处以Button为例
// 步骤1：设置属性数值的初始值 & 结束值
        ValueAnimator valueAnimator = ValueAnimator.ofInt(startWidth, endWidth);
        // 初始值 = 当前按钮的宽度，此处在xml文件中设置为150
        // 结束值 = 500
        // ValueAnimator.ofInt()内置了整型估值器,直接采用默认的.不需要设置
        // 即默认设置了如何从初始值150 过渡到 结束值500

// 步骤2：设置动画的播放各种属性
        valueAnimator.setDuration(2000);
        // 设置动画运行时长:1s
// 步骤3：将属性数值手动赋值给对象的属性:此处是将 值 赋给 按钮的宽度
        // 设置更新监听器：即数值每次变化更新都会调用该方法
//        int orgin = buttonTranslate.getWidth();
//        Logger.i("onAnimationUpdate:   " + " orginalW = " + orgin);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animator) {
                int currentValue = (Integer) animator.getAnimatedValue();
                Logger.d("onAnimationUpdate:   " + "currentValue = [" + currentValue + "]" + " orginalW = " + buttonTranslate.getWidth());
                // 获得每次变化后的属性值
                // 输出每次变化后的属性值进行查看
                buttonTranslate.getLayoutParams().width = currentValue;
                // 每次值变化时，将值手动赋值给对象的属性
                // 即将每次变化后的值 赋 给按钮的宽度，这样就实现了按钮宽度属性的动态变化
                // 步骤4：刷新视图，即重新绘制，从而实现动画效果
                buttonTranslate.requestLayout();
            }
        });

        valueAnimator.start();
        // 启动动画
    }

    private void initRecycleview() {
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("A");
        arrayList.add("b");
        arrayList.add("c");
        arrayList.add("d");
        arrayList.add("e");
        arrayList.add("f");
        arrayList.add("h");
        arrayList.add("i");
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL);
        MySimpledapter mySimpledapter = new MySimpledapter(arrayList);
        recycleview.addItemDecoration(dividerItemDecoration);
        recycleview.setLayoutManager(linearLayoutManager);
        recycleview.setAdapter(mySimpledapter);
    }


    private void initSegmentControl(View layout) {
        mSegmentControl.setOnSegmentControlClickListener(new SegmentControl.OnSegmentControlClickListener() {
            @Override
            public void onSegmentControlClick(int index) {
            }
        });
//        mSegmentControl.setBackgroundDrawableColor(getResources().getColor(R.color.black));
//        mSegmentControl.setSelectdTextColor(getResources().getColor(R.color.black));
//        mSegmentControl.setDefaultTextColor(getResources().getColor(R.color.white));
//        mSegmentControl.setSelectedDrawableColor(getResources().getColor(R.color.white));
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.btn_start})
    void submitClick(View v) {
        switch (v.getId()) {
            case R.id.btn_start:
                if (buttonFlag) {
                    buttonScaleAni(buttonTranslate.getWidth(), 500);
                    buttonFlag = false;
                } else {
                    buttonScaleAni(buttonTranslate.getWidth(), 200);
                    buttonFlag = true;
                }
                break;
            default:
                break;
        }
    }
}