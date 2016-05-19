package com.example.cdj.myapplication.cusview;

import android.content.Context;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.cdj.myapplication.R;

/**
 * Created by cdj onCallBackData 2016/5/19.
 */
public class CommomEditText  extends LinearLayout implements TextWatcher ,View.OnClickListener{


    private TextView tv_unit;
    private EditText edt_content;
    private Button btn_commit;
    public CommomEditText(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CommomEditText(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);


        View rootView = LayoutInflater.from(context).inflate(R.layout.layout_commom_edittext, this, true);

        edt_content= (EditText) rootView.findViewById(R.id.edt_content);
        edt_content.addTextChangedListener(this);

        tv_unit = (TextView) rootView.findViewById(R.id.tv_unit);

        btn_commit = (Button) rootView.findViewById(R.id.btn_commit);
        btn_commit.setOnClickListener(this);
        btn_commit.setEnabled(false);
        //获取属性并解析
//        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CommonFormLayout);
//        int count = typedArray.getIndexCount();
//        for (int i = 0; i < count; i++) {
//            int itemId = typedArray.getIndex(i);
//            if (itemId == R.styleable.CommonFormLayout_titleText) {
//                tv_common_title.setText(typedArray.getText(itemId));
//            } else if (itemId == R.styleable.CommonFormLayout_contentText) {
//                tv_common_content.setText(typedArray.getText(itemId));
//            } else if (itemId == R.styleable.CommonFormLayout_hasRightArrow) {
//                if (typedArray.getBoolean(itemId, false)) {
//                    tv_common_content.setCompoundDrawablesWithIntrinsicBounds(null, null, getResources().getDrawable(R.mipmap.qf_right_arrow), null);
//                }
//            } else if (itemId == R.styleable.CommonFormLayout_leftImage) {
//                tv_common_title.setCompoundDrawablesWithIntrinsicBounds(typedArray.getDrawable(itemId), null, null, null);
//            } else if (itemId == R.styleable.CommonFormLayout_CommonFormLayout_titleTextColor) {
//                tv_common_title.setTextColor(typedArray.getColor(itemId, R.color.grey_333333));
//            } else if (itemId == R.styleable.CommonFormLayout_contentTextColor) {
//                tv_common_content.setTextColor(typedArray.getColor(itemId, R.color.grey_888888));
//            } else if (itemId == R.styleable.CommonFormLayout_hasTopLine) {
//                if (typedArray.getBoolean(itemId, false)) {
//                    view_top_line.setVisibility(VISIBLE);
//                }
//            }
//        }
//        typedArray.recycle();
    }

    public void setTextUnit(String text){
        tv_unit.setText(text);
    }
    public void setTextUnit(int resId){
        tv_unit.setText(resId);
    }


    public void setInputType(int type){
        edt_content.setInputType(type);
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//        Logger.d("beforeTextChanged     "+s);
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
//        Logger.d("onTextChanged     "+s);
        if (!TextUtils.isEmpty(s)){
            btn_commit.setEnabled(true);
        }else{
            btn_commit.setEnabled(false);
        }
    }
    @Override
    public void afterTextChanged(Editable s) {
//        Logger.d("afterTextChanged     "+s);
    }

    @Override
    public void onClick(View v) {
            listener.onClick(edt_content.getText().toString().trim());
    }
    private OnCommitClickListener listener;
    public void setOnCommitListener(OnCommitClickListener  listener){
        this.listener=listener;
    }

    public interface  OnCommitClickListener{
        void onClick(String num);
    }
}
