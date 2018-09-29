package com.example.cdj.myapplication.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.ConvertUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.example.cdj.myapplication.R;
import com.example.cdj.myapplication.base.BaseActivity;
import com.example.cdj.myapplication.widget.ScrollDetailView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Description :详情页滚动的自定义view
 * Created by vicwing
 * Created Time 2018/9/26
 */
public class ScrollDetailActivity extends BaseActivity {

    @BindView(R.id.scrollview_detail)
    ScrollDetailView scrollviewDetail;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scroll_detail);
        ButterKnife.bind(this);
        LinearLayout linearLayout1 = new LinearLayout(this);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ConvertUtils.dp2px(500));
        linearLayout1.setLayoutParams(params);
        linearLayout1.setBackgroundColor(getColor(R.color.red));
        scrollviewDetail.addContainer(linearLayout1);
        LinearLayout linearLayout2 = new LinearLayout(this);
        LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ConvertUtils.dp2px(500));
        linearLayout2.setLayoutParams(layoutParams2);
        linearLayout2.setBackgroundColor(getColor(R.color.black_33333));
        scrollviewDetail.addContainer(linearLayout2);
        scrollviewDetail.setText(R.id.tv_entrust_house_name, "龙苑山庄");
//        scrollviewDetail.setText(R.id.tv_top_price, "1000元");

        scrollviewDetail.setVisibilityView(R.id.tv_top_price, false);


        scrollviewDetail.setOnclickListener(R.id.iv_share, v -> {
            com.orhanobut.logger.Logger.d(" onclick....:   ");

        });


        entrustBottomUi();
//        scrollviewDetail.entrustBottomUi();
    }

    private void entrustBottomUi() {
        scrollviewDetail.setVisibilityView(R.id.btn_collection, false);
        scrollviewDetail.setVisibilityView(R.id.tv_appointment, true);
        ConstraintLayout constraintLayout = scrollviewDetail.getView(R.id.ll_detail_bottom);
        RelativeLayout tvContact = scrollviewDetail.getView(R.id.layout_contact);
        tvContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtils.showShort("联系经纪人");
            }
        });
        TextView tvAppointment = scrollviewDetail.getView(R.id.tv_appointment);
        tvAppointment.setBackground(getDrawable(R.drawable.btn_yellow_bg));
        tvAppointment.setText("操作");
        tvAppointment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtils.showShort("预约带看");
            }
        });
        constainUiChange(constraintLayout, tvContact, tvAppointment);
    }


    private void constainUiChange(ConstraintLayout constraintLayout, RelativeLayout tvContact, TextView appointment) {
        ConstraintSet set = new ConstraintSet();
        set.clone(constraintLayout);

        int[] chainViews = {appointment.getId(), tvContact.getId()};
        float[] chainWeights = {1, 3};
        set.createHorizontalChain(appointment.getId(), ConstraintSet.LEFT, tvContact.getId(),
                ConstraintSet.RIGHT, chainViews, chainWeights, ConstraintSet.CHAIN_SPREAD);
//        set.addToHorizontalChain (tvContact.getId());
//        set.setVerticalChainStyle(appointment.getId(), ConstraintSet.CHAIN_SPREAD_INSIDE);
//        set.setVerticalWeight(appointment.getId(),1);
//        set.setVerticalWeight(tvContact.getId(),5);

        set.centerHorizontally(appointment.getId(), ConstraintSet.PARENT_ID);
        set.centerHorizontally(tvContact.getId(), ConstraintSet.PARENT_ID);
        set.applyTo(constraintLayout);
    }
}
