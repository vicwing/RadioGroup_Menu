package com.example.cdj.myapplication.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.cdj.myapplication.R;
import com.orhanobut.logger.Logger;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Description :自定义一个EditText,字数显示
 * Created by vicwing
 * Created Time 2018/10/18
 */
public class CusEditText extends LinearLayout implements TextWatcher {

    @BindView(R.id.editText_content)
    EditText editTextContent;
    @BindView(R.id.tv_count_num)
    TextView tvCountNum;
    private Context context;
    private int contentMaxLength = Integer.MAX_VALUE;

    public CusEditText(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public CusEditText(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    private void init(AttributeSet attrs) {
        setGravity(Gravity.CENTER_VERTICAL);
        context = getContext();
        LayoutInflater.from(context).inflate(R.layout.view_cus_edit_text, this, true);
        ButterKnife.bind(this);

        //获取属性并解析
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.custom_edittext);
        int count = typedArray.getIndexCount();
        for (int i = 0; i < count; i++) {
            int itemId = typedArray.getIndex(i);
            if (itemId == R.styleable.custom_edittext_edittext_text_size) {
                float dimension = typedArray.getDimension(itemId, 12);
                Logger.d("textsize :   " + dimension);
                editTextContent.setTextSize(TypedValue.COMPLEX_UNIT_PX,dimension);
            } else if (itemId == R.styleable.custom_edittext_edittext_text_length) {
                contentMaxLength = typedArray.getInteger(itemId, Integer.MAX_VALUE);
            } else if (itemId == R.styleable.custom_edittext_edittext_hint) {
                String texhint = typedArray.getText(itemId).toString();
                editTextContent.setHint(texhint);
            } else if (itemId == R.styleable.custom_edittext_edittext_hint_color) {
                int hintColor = typedArray.getColor(itemId, ContextCompat.getColor(context, R.color.black_33333));
                editTextContent.setHintTextColor(hintColor);
            }
        }
        if (typedArray != null) {
            typedArray.recycle();
        }
        editTextContent.addTextChangedListener(this);
        InputFilter[] filters = {new InputFilter.LengthFilter(contentMaxLength)};
        editTextContent.setFilters(filters);
        tvCountNum.setText("0/" + contentMaxLength);
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        int length = s.length();
        tvCountNum.setText(length + "/" + contentMaxLength);
    }

    /**
     * 获取edittext文字
     *
     * @return
     */
    public String getText() {
        return editTextContent.getText().toString().trim();
    }
    public void setText(String string){
        editTextContent.setText(string);
    }
}
