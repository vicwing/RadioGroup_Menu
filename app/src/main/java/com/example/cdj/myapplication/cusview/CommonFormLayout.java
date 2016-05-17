package com.example.cdj.myapplication.cusview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.cdj.myapplication.R;


/**
 * 基础表单控件
 * 这是一个基础表单控件,提供设置标题和内容文本,文本颜色,标题左侧图标,是否有向右箭头等方法
 * Created by shidong on 16/5/12.
 */
public class CommonFormLayout extends RelativeLayout {

    private final TextView tv_common_title;
    private final TextView tv_common_content;
    private final View view_top_line;

    public CommonFormLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CommonFormLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

        LayoutInflater.from(context).inflate(R.layout.layout_common_form, this, true);
        tv_common_title = (TextView) findViewById(R.id.tv_common_title);
        tv_common_content = (TextView) findViewById(R.id.tv_common_content);
        view_top_line = findViewById(R.id.view_top_line);

        //获取属性并解析
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CommonFormLayout);
        int count = typedArray.getIndexCount();
        for (int i = 0; i < count; i++) {
            int itemId = typedArray.getIndex(i);
            if (itemId == R.styleable.CommonFormLayout_titleText) {
                tv_common_title.setText(typedArray.getText(itemId));
            } else if (itemId == R.styleable.CommonFormLayout_contentText) {
                tv_common_content.setText(typedArray.getText(itemId));
            } else if (itemId == R.styleable.CommonFormLayout_hasRightArrow) {
                if (typedArray.getBoolean(itemId, false)) {
                    tv_common_content.setCompoundDrawablesWithIntrinsicBounds(null, null, getResources().getDrawable(R.mipmap.qf_right_arrow), null);
                }
            } else if (itemId == R.styleable.CommonFormLayout_leftImage) {
                tv_common_title.setCompoundDrawablesWithIntrinsicBounds(typedArray.getDrawable(itemId), null, null, null);
            } else if (itemId == R.styleable.CommonFormLayout_CommonFormLayout_titleTextColor) {
                tv_common_title.setTextColor(typedArray.getColor(itemId, R.color.grey_333333));
            } else if (itemId == R.styleable.CommonFormLayout_contentTextColor) {
                tv_common_content.setTextColor(typedArray.getColor(itemId, R.color.grey_888888));
            } else if (itemId == R.styleable.CommonFormLayout_hasTopLine) {
                if (typedArray.getBoolean(itemId, false)) {
                    view_top_line.setVisibility(VISIBLE);
                }
            }
        }
    }


    /**
     * 设置左侧标题图标
     *
     * @param resId
     */
    public void setLeftImage(int resId) {
        setLeftImage(getResources().getDrawable(resId));
    }

    /**
     * 设置左侧标题图标
     *
     * @param drawable
     */
    public void setLeftImage(Drawable drawable) {
        tv_common_title.setCompoundDrawablesWithIntrinsicBounds(drawable, null, null, null);
    }

    /**
     * 设置是否有向右箭头
     *
     * @param hasRightArrow
     */
    public void setHasRightArrow(boolean hasRightArrow) {
        if (hasRightArrow) {
            tv_common_content.setCompoundDrawablesWithIntrinsicBounds(null, null, getResources().getDrawable(R.mipmap.qf_right_arrow), null);
        } else {
            tv_common_content.setCompoundDrawablesWithIntrinsicBounds(null, null, null, null);
        }
    }

    /**
     * 设置标题文本
     *
     * @param resID
     */
    public void setTitleText(int resID) {
        setTitleText(getResources().getString(resID));
    }

    /**
     * 设置标题文本
     *
     * @param text
     */
    public void setTitleText(String text) {
        tv_common_title.setText(text);
    }

    /**
     * 返回标题文本
     */
    public CharSequence getTitleText() {
        return tv_common_title.getText();
    }

    /**
     * 设置内容文本
     *
     * @param resId
     */
    public void setContentText(int resId) {
        setContentText(getResources().getString(resId));
    }

    /**
     * 设置内容文本
     *
     * @param text
     */
    public void setContentText(String text) {
        tv_common_content.setText(text);
    }

    /**
     * 返回内容文本
     */
    public CharSequence getContentText() {
        return tv_common_content.getText();
    }

    /**
     * 设置标题文本颜色
     *
     * @param resId
     */
    public void setTitleTextColor(int resId) {
        tv_common_title.setTextColor(resId);
    }

    /**
     * 设置内容文本颜色
     *
     * @param resId
     */
    public void setContentTextColor(int resId) {
        tv_common_content.setTextColor(resId);
    }

}
