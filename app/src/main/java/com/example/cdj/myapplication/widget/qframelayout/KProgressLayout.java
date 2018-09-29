package com.example.cdj.myapplication.widget.qframelayout;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.cdj.myapplication.R;


/**
 * 基础提示控件类 显示加载进度条,数据错误,网络错误等提示
 */
public class KProgressLayout extends FrameLayout {

    private static final String TAG = "KProgressLayout";
    private Context mContext;
    private View mLoadingView;
    private View mEmptyView;
    private View mErrorView;
    private View mSearchEmptyView;
    private KProgressClickListener kProgressClickListener;
    private TextView tvTipstext;
    private TextView tvSearchRight;
    private TextView tvSearchLeft;
    private ImageView ivSearchImageView;

    public void setKProgressClickListener(KProgressClickListener kProgressClickListener) {
        this.kProgressClickListener = kProgressClickListener;
    }

    public interface State {
        int LOADING = 0, EMPTY = 1, ERROR = 2, CANCEL = 3, SEARCH_EMPTY = 4;
    }

    public KProgressLayout(Context context) {
        this(context, null);
        initKProgress(context);
    }

    public KProgressLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        initKProgress(context);
    }

    public KProgressLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initKProgress(context);
    }


    @Override
    public void addView(View child, int index, ViewGroup.LayoutParams params) {
        if (getChildCount() >= 4 && index < 0) {// 这里的3是initKProgress中的3个view
            index = getChildCount() - 4;
        }
        super.addView(child, index, params);
    }

    public void initKProgress(Context context) {
        mContext = context;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.kprogresslayout_layout, this, true);
        mLoadingView = inflater.inflate(R.layout.qfang_kprogresslistview_loadingview, this, false);
        mErrorView = inflater.inflate(R.layout.qfang_kprogresslistview_errorview, this, false);
        mEmptyView = inflater.inflate(R.layout.qfang_kprogresslistview_emptyview, this, false);
        mSearchEmptyView = inflater.inflate(R.layout.qfang_kprogresslistview_search_emptyview, this, false);

        //解决事件穿透的问题.
        mSearchEmptyView.setClickable(true);

        tvTipstext = (TextView) mSearchEmptyView.findViewById(R.id.tv_tipstext);
        tvSearchLeft = (TextView) mSearchEmptyView.findViewById(R.id.tv_look_all);
        tvSearchRight = (TextView) mSearchEmptyView.findViewById(R.id.tv_changetext);
        ivSearchImageView = (ImageView) mSearchEmptyView.findViewById(R.id.iv_imageview);

        if (mEmptyView != null) {
            this.addView(mEmptyView);
            mEmptyView.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (kProgressClickListener != null) {
                        kProgressClickListener.onEmptyViewClick();
                    }
                }
            });
            mEmptyView.setVisibility(View.GONE);
        }

        if (mErrorView != null) {
            this.addView(mErrorView);
            mErrorView.findViewById(R.id.tv_error_text).setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (kProgressClickListener != null) {
                        kProgressClickListener.onErrorViewClick();
                    }
                }
            });
            mErrorView.setVisibility(View.GONE);
        }

        if (mLoadingView != null) {
            this.addView(mLoadingView);
            mLoadingView.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (kProgressClickListener != null) {
                        kProgressClickListener.onLoadingViewClick();
                    }
                }
            });
            mLoadingView.setVisibility(View.GONE);
        }

        //搜索无结果
        if (mSearchEmptyView != null) {
            this.addView(mSearchEmptyView);
            mSearchEmptyView.findViewById(R.id.tv_look_all).setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (kProgressClickListener != null) {
                        kProgressClickListener.onSearchEmptyViewClick(0);
                    }
                }
            });
            mSearchEmptyView.findViewById(R.id.tv_changetext).setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (kProgressClickListener != null) {
                        kProgressClickListener.onSearchEmptyViewClick(1);
                    }
                }
            });
            mSearchEmptyView.setVisibility(View.GONE);
        }
    }

// 可以解决事件穿透的问题.
//    @Override
//    public boolean onTouchEvent(MotionEvent event) {
//        if (mSearchEmptyView!=null&&mSearchEmptyView.getVisibility()==VISIBLE){
//            Logger.e("KProgressLayout    onInterceptTouchEvent:   拦截事件不允许滑动");
//            Logger.e("KProgressLayout    onInterceptTouchEvent:   消费事件");
//            return true;
//        }
//        return super.onTouchEvent(event);
//    }

    public void showView(int state) {

        boolean showLoadingView = false;
        boolean showEmptyView = false;
        boolean showErrorView = false;
        boolean showSearchEmpty = false;

        switch (state) {
            case State.LOADING:
                showLoadingView = true;
                break;
            case State.EMPTY:
                showEmptyView = true;
                break;
            case State.ERROR:
                showErrorView = true;
                break;
            case State.SEARCH_EMPTY:
                showSearchEmpty = true;
                break;
            case State.CANCEL:
                // 显示主界面
                break;
        }
        showProgress(showLoadingView, showEmptyView, showErrorView, showSearchEmpty);
    }

    public void showProgress(final boolean showLoadingView, boolean showEmptyView, boolean showErrorView, boolean showSearchEmpty) {

        if (mLoadingView != null) {
//            Logger.i( "调用showProgress");
//            int shortAnimTime = 500;
            mLoadingView.setVisibility(showLoadingView ? View.VISIBLE : View.GONE);
//            mLoadingView.animate().setDuration(shortAnimTime)
//                    .alpha(showLoadingView ? 1 : 1)
//                    .setListener(new AnimatorListenerAdapter() {
//
//                        @Override
//                        public void onAnimationStart(Animator animation) {
//                            mLoadingView.setVisibility(VISIBLE);
//                        }
//
//                        @Override
//                        public void onAnimationEnd(Animator animation) {
//                            mLoadingView.setVisibility(GONE);
//                        }
//                    });
        }

        if (mEmptyView != null) {
            mEmptyView.setVisibility(showEmptyView ? View.VISIBLE : View.GONE);
        }

        if (mErrorView != null) {
            mErrorView.setVisibility(showErrorView ? View.VISIBLE : View.GONE);
        }

        if (mSearchEmptyView != null) {
            mSearchEmptyView.setVisibility(showSearchEmpty ? View.VISIBLE : View.GONE);
        }
    }

    public void showLoadingView() {
        showView(State.LOADING);
    }


    public void cancelAll() {
        showView(State.CANCEL);
    }

    public void showSearchEmpty() {
        showView(State.SEARCH_EMPTY);
    }

    public void showErrorView() {
        showView(State.ERROR);
    }

    public void showErrorViewText(String text) {
        showErrorView();
        showErrorViewText(text, null);
    }

    /**
     * 修改错误情况的时候,提示语文案
     *
     * @param descText
     * @param btnText
     */
    public void showErrorViewText(String descText, String btnText) {
        showErrorView();
        if (mErrorView != null) {
            if (!TextUtils.isEmpty(descText)) {
                ((TextView) mErrorView.findViewById(R.id.tv_error_desc)).setText(descText);
            }
            if (!TextUtils.isEmpty(btnText)) {
                ((TextView) mErrorView.findViewById(R.id.tv_error_text)).setText(btnText);
            }
        }
    }

    public void showEmptyView() {
        showEmptyView("暂无数据");
    }

    /**
     * 显示数据位空
     *
     * @param text
     */
    public void showEmptyView(String text) {
        this.showEmptyView(text, 0);
    }

    /**
     * 显示错误提示,并设置相应图片
     */
    public void showEmptyView(String text, int imgResId) {
        this.showEmptyView(text, imgResId, null, null);
    }


    /**
     * 显示错误提示,并设置相应图片
     */
    public void showEmptyView(String text, int imgResId, String operateText) {
        this.showEmptyView(text, imgResId, operateText, null);
    }

    /**
     * 显示错误提示,并设置相应图片
     */
    public void showEmptyView(String text, int imgResId, String operateText, final OnEmptyButtonClickListener onEmptyButtonClickListener) {
        showView(State.EMPTY);
        if (mEmptyView != null) {
            TextView textView = (TextView) mEmptyView.findViewById(R.id.tv_temp_has_nodata);
            textView.setText(text);
            ImageView iv_imageview = (ImageView) mEmptyView.findViewById(R.id.iv_imageview);
            if (imgResId != 0) {
                iv_imageview.setImageResource(imgResId);
            }
            if (imgResId == -1) {
                iv_imageview.setVisibility(INVISIBLE);
            }
            //如果设置空数据时点击监听,回调到页面处理点击监听
            if (onEmptyButtonClickListener != null) {
                Button btn_other_operation = (Button) mEmptyView.findViewById(R.id.btn_other_operation);
                btn_other_operation.setVisibility(VISIBLE);
                btn_other_operation.setText(operateText);
                btn_other_operation.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onEmptyButtonClickListener.onEmptyButtonClick();
                    }
                });
            }
        }
    }

    /**
     * 搜索页提示语
     *
     * @param text
     */
    public void setTextSearchText(String text) {
        tvTipstext.setText(text);
    }

    /**
     * 搜索页 右侧 提示语
     *
     * @param text
     */
    public void setSearchRightText(String text) {
        tvSearchRight.setText(text);
    }

    /**
     * 搜索页左侧提示语
     *
     * @param text
     */
    public void setSearchLeftText(String text) {
        tvSearchLeft.setText(text);
    }

    public void setSearchImageView(int resId) {
        ivSearchImageView.setImageResource(resId);
    }


    public void showSingleButton(final Activity activity, String text, String btnText, final Intent intent) {
        showSingleButton(text, btnText);
        tvSearchLeft.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.startActivity(intent);
//				activity.finish();
            }
        });
    }

    /**
     * @param text   按钮文本
     * @param intent
     */
    public void showSingleButton(String text, String btnText, final Intent intent) {
        showSingleButton(text, btnText);
        if (null != intent) {
            tvSearchLeft.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    mContext.startActivity(intent);
                }
            });
        }
    }

    /**
     * @param text   按钮文本
     * @param intent
     */
    public void showSingleButton(String text, String btnText, final Intent intent, final Activity activity, final int requestCode) {
        showSingleButton(text, btnText);
        if (null != intent) {
            tvSearchLeft.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    activity.startActivityForResult(intent, requestCode);
                }
            });
        }
    }

    /**
     * 显示一个按钮
     *
     * @param text
     * @param btnText
     * @param onClickListener
     */
    public void showSingleButton(String text, String btnText, OnClickListener onClickListener) {
        showSingleButton(text, btnText);
        tvSearchLeft.setOnClickListener(onClickListener);
    }

    /**
     * 显示一个按钮
     *
     * @param text    错误描述
     * @param btnText button 文案
     */
    public void showSingleButton(String text, String btnText) {
        tvSearchLeft.setText(btnText);
        ivSearchImageView.setImageResource(R.drawable.qf_not_data);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        lp.setMargins(40, 5, 40, 5);
        tvSearchLeft.setLayoutParams(lp);
        showSearchEmpty();
        setTextSearchText(text);
        tvSearchRight.setVisibility(View.GONE);
    }

    /**
     * 数据为空时的点击操作监听接口
     */
    public interface OnEmptyButtonClickListener {
        void onEmptyButtonClick();
    }

}
