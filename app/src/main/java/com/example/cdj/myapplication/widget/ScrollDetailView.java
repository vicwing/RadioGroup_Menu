package com.example.cdj.myapplication.widget;

import android.animation.ObjectAnimator;
import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.OnLifecycleEvent;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.ConvertUtils;
import com.blankj.utilcode.util.ScreenUtils;
import com.example.cdj.myapplication.R;
import com.example.cdj.myapplication.utils.StatusBarUtil;
import com.example.cdj.myapplication.widget.qframelayout.QfangFrameLayout;
import com.orhanobut.logger.Logger;

import org.jetbrains.annotations.NotNull;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Description :顶部滑动透明过程会透明,底部有滑动动画隐藏显示底部的ui
 * Created by vicwing
 * Created Time 2018/9/26
 */
public class ScrollDetailView extends LinearLayout implements CusNestedScrollView.OnScrollListener ,LifecycleObserver {

    /**
     * 设置透明标题变化高度
     */
    float alphaHeight = ConvertUtils.dp2px(180);

    /**
     * 底部高度
     */
    private int bottomViewHeight = ConvertUtils.dp2px(60);

    private boolean isShow = false;

    @BindView(R.id.qfangframelayout)
    QfangFrameLayout qfangframelayout;
    @BindView(R.id.tv_entrust_house_name)
    TextView tvEntrustHouseName;
    @BindView(R.id.tv_top_price)
    TextView tvTopPrice;
    @BindView(R.id.linearLayout3)
    LinearLayout linearLayout3;
    @BindView(R.id.btn_collection)
    TextView btnCollection;
    @BindView(R.id.tv_appointment)
    TextView tvAppointment;
    @BindView(R.id.tv_specail_car)
    TextView tvSpecailCar;
    @BindView(R.id.tv_contact)
    TextView tvContact;
    @BindView(R.id.layout_contact)
    RelativeLayout layoutContact;

    @BindView(R.id.ll_detail_bottom)
    ConstraintLayout constraintLayout;

    @BindView(R.id.ll_entrust_title)
    View llEntrustTitle;

    @BindView(R.id.ll_entrust_detail)
    LinearLayout container;

    @BindView(R.id.scrollview_entrust_detail)
    CusNestedScrollView cusNestedScrollView;

    @BindView(R.id.iv_scoll_back)
    ImageView ivScollBack;

    @BindView(R.id.iv_share)
    ImageView ivShare;

    private Context context;

    public ScrollDetailView(Context context) {
        super(context);
    }

    public ScrollDetailView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ScrollDetailView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public ScrollDetailView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init() {
        setGravity(Gravity.CENTER_VERTICAL);
        context = getContext();
        LayoutInflater.from(context).inflate(R.layout.activity_detail_scroll_view, this, true);
        ButterKnife.bind(this);
        cusNestedScrollView.setOnScrollListener(this);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        //view加载完成时回调
        container.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                // TODO Auto-generated method stub
                if (Build.VERSION.SDK_INT >= 16) {
                    container.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                } else {
                    container.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                }

                int statusBarHeight = StatusBarUtil.getStatusBarHeight(context);
                int containerHeight = container.getHeight();
                int screenHeight = ScreenUtils.getScreenHeight();
                Logger.d(" screenHeight =" + screenHeight +
                        "   containerHeight = [" + containerHeight + "]" + " statusBarHeight= " + statusBarHeight +
                        "  result=" + (containerHeight + statusBarHeight));
                int bottomHeight = ConvertUtils.dp2px(60);
                int result = screenHeight - (statusBarHeight + containerHeight);
                if (result >= 0 || Math.abs(result) < bottomHeight) {
                    //container的高度小于屏幕一定高度时,直接显示底部控件
                    changeBottom(1);
                }
            }
        });
    }

    public void addContainer(View v) {
        container.addView(v);
    }
    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    public void oncreate(@NotNull LifecycleOwner owner) {
        Logger.e("Lifecycle oncreate:  ...... ");
    }


    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    void onDestroy(@NotNull LifecycleOwner owner){
        Logger.d("Lifecycle ON_START:   "+"v = [" + "" + "]");
    }

    @Override
    public void onScrollChanged(int y, int oldy) {
        Logger.d("onScrollChanged= " + y + " oldy " + oldy);
        changeTitle(y);

        changeBottom(y);
    }

    /**
     * 修改顶部标题栏的透明度
     *
     * @param y
     */
    private void changeTitle(int y) {
        Drawable drawable = llEntrustTitle.getBackground().mutate();
        int alpha;
        if (Math.abs(y) < alphaHeight) {
            float progress = Math.abs(y) / alphaHeight;
            alpha = (int) (progress * 255);
            drawable.setAlpha(alpha);
//            tittleDivideline.setVisibility(View.GONE);
            ivShare.setImageResource(R.mipmap.icon_detail_share_white);
            ivScollBack.setImageResource(R.mipmap.icon_common_return_white);
        } else {
            alpha = 255;
//            tittleDivideline.setVisibility(View.VISIBLE);
//            tvEntrustHouseName.setVisibility(View.VISIBLE);
            ivScollBack.setImageResource(R.mipmap.icon_common_return_black);
            ivShare.setImageResource(R.mipmap.icon_detail_share_black);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                if (drawable.getAlpha() != 255) {
                    drawable.setAlpha(255);
                }
            } else {
                drawable.setAlpha(255);
            }
        }
//        tvEntrustHouseName.setTextColor(tvEntrustHouseName.getTextColors().withAlpha(alpha));
    }

    /**
     * 隐藏显示底部试图
     *
     * @param y
     */
    private void changeBottom(int y) {
        if (y <= 0) {//到达顶部
            if (isShow) {//隐藏
                startPropertyAnimaIn(constraintLayout, 0, bottomViewHeight);
                isShow = false;
            }
        } else {//上滑
            if (!isShow) {
                constraintLayout.setVisibility(View.VISIBLE);
                startPropertyAnimaIn(constraintLayout, bottomViewHeight, 0);
                isShow = true;
            }
        }
    }

    /**
     * 属性动画进入
     */
    private void startPropertyAnimaIn(View bottomView, int startHeight, int endHeight) {
        ObjectAnimator anim = ObjectAnimator.ofFloat(bottomView, "translationY", startHeight, endHeight);
        anim.setDuration(200);
        anim.start();
    }

    public void setVisibilityView(int resId, boolean visible) {
        findViewById(resId).setVisibility(visible ? VISIBLE : GONE);
    }

    public void setText(int resId, String string) {
        ((TextView) findViewById(resId)).setText(string);
    }

    public void setOnclickListener(int resId, View.OnClickListener listener) {
        findViewById(resId).setOnClickListener(listener);
    }

    public <T extends View> T getView(int resId) {
        return findViewById(resId);
    }

    /**
     * 房源委托界面条用的方法.
     */
    public void entrustBottomUi() {
        btnCollection.setVisibility(GONE);
        tvAppointment.setVisibility(VISIBLE);
        tvAppointment.setBackground(context.getResources().getDrawable(R.drawable.btn_yellow_bg));
        tvAppointment.setText("操作");
        constainUiChange(constraintLayout, layoutContact, tvAppointment);
    }

    public void constainUiChange(ConstraintLayout constraintLayout, RelativeLayout tvContact, TextView appointment) {
        ConstraintSet set = new ConstraintSet();
        set.clone(constraintLayout);

        int[] chainViews = {appointment.getId(), tvContact.getId()};
        float[] chainWeights = {1, 3};
        set.createHorizontalChain(appointment.getId(), ConstraintSet.LEFT, tvContact.getId(),
                ConstraintSet.RIGHT, chainViews, chainWeights, ConstraintSet.CHAIN_SPREAD);
        set.centerHorizontally(appointment.getId(), ConstraintSet.PARENT_ID);
        set.centerHorizontally(tvContact.getId(), ConstraintSet.PARENT_ID);
        set.applyTo(constraintLayout);
    }
}
