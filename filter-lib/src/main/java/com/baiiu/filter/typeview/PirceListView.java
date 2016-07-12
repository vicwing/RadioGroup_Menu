package com.baiiu.filter.typeview;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.baiiu.filter.R;
import com.baiiu.filter.adapter.BaseBaseAdapter;
import com.baiiu.filter.interfaces.OnFilterItemClickListener;

import java.util.List;

/**
 * 新房列表.筛选价格列表
 *
 * @param <DATA>
 */
public class PirceListView<DATA> extends LinearLayout implements AdapterView.OnItemClickListener, View.OnClickListener {

    private BaseBaseAdapter<DATA> mAdapter;
    private OnFilterItemClickListener<DATA> mOnItemClickListener;
    private OnCusItemClickListener onCusItemClickListener;
    private ListView listview;
    private EditText edt_min;
    private EditText edt_max;

    private View btn_submit;
    private Context context;


    public PirceListView(Context context) {
        this(context, null);
    }

    public PirceListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public PirceListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        this.context = context;

        setOrientation(VERTICAL);
        inflate(context, R.layout.filter_singlelist_input_bottom, this);
        listview = (ListView) findViewById(R.id.listview);
        listview.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        listview.setDivider(null);
        listview.setDividerHeight(0);
        listview.setSelector(new ColorDrawable(Color.TRANSPARENT));
        listview.setOnItemClickListener(this);


        edt_min = (EditText) findViewById(R.id.edt_min);
        edt_max = (EditText) findViewById(R.id.edt_max);

        btn_submit = findViewById(R.id.btn_submit);

        btn_submit.setOnClickListener(this);
    }


    public PirceListView<DATA> adapter(BaseBaseAdapter<DATA> adapter) {
        this.mAdapter = adapter;
        listview.setAdapter(adapter);
        return this;
    }

    public PirceListView<DATA> onItemClick(OnFilterItemClickListener<DATA> onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
        return this;
    }

    public void setList(List<DATA> list, int checkedPositoin) {
        mAdapter.setList(list);

        if (checkedPositoin != -1) {
            listview.setItemChecked(checkedPositoin, true);
        }
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        DATA item = mAdapter.getItem(position);
        if (mOnItemClickListener != null) {
            mOnItemClickListener.onItemClick(item);
        }
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.btn_submit) {
            String minPrice = edt_min.getText().toString().trim();
            String maxPrice = edt_max.getText().toString().trim();

            if (TextUtils.isEmpty(maxPrice) ) {
                Toast.makeText(context, "请输入最大价格", Toast.LENGTH_SHORT).show();
                return;
            }
            if (TextUtils.isEmpty(minPrice)) {
                Toast.makeText(context, "请输入最小价格", Toast.LENGTH_SHORT).show();
                return;
            }

            if (Integer.parseInt(maxPrice) < Integer.parseInt(minPrice)) {
                Toast.makeText(context, "输入的价格有误", Toast.LENGTH_SHORT).show();
                return;
            }

            if (onCusItemClickListener != null) {
                onCusItemClickListener.onCusItemClick(minPrice,maxPrice);
            }
        }
    }


    public interface OnCusItemClickListener {
        void onCusItemClick( String minPrice,String maxPrice);
    }

    public PirceListView<DATA> setOnCusItemClickListener(OnCusItemClickListener listener) {
        this.onCusItemClickListener = listener;
        return this;
    }

}
