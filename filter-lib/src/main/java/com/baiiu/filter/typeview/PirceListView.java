package com.baiiu.filter.typeview;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.baiiu.filter.R;
import com.baiiu.filter.adapter.BaseBaseAdapter;
import com.baiiu.filter.interfaces.OnFilterItemClickListener;

import java.util.List;

/**
 * Created by baiiu on 15/12/17.
 * 双列ListView
 */
public class PirceListView<DATA> extends LinearLayout implements AdapterView.OnItemClickListener {

    private BaseBaseAdapter<DATA> mAdapter;
    private OnFilterItemClickListener<DATA> mOnItemClickListener;
    private ListView listview;

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

        setOrientation(VERTICAL);
        inflate(context, R.layout.filter_singlelist_input_bottom, this);
        listview = (ListView) findViewById(R.id.listview);
        listview.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        listview.setDivider(null);
        listview.  setDividerHeight(0);
        listview. setSelector(new ColorDrawable(Color.TRANSPARENT));

        listview. setOnItemClickListener(this);
    }


    public PirceListView<DATA> adapter(BaseBaseAdapter<DATA> adapter) {
        this.mAdapter = adapter;
        listview. setAdapter(adapter);
        return this;
    }

    public PirceListView<DATA> onItemClick(OnFilterItemClickListener<DATA> onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
        return this;
    }

    public void setList(List<DATA> list, int checkedPositoin) {
        mAdapter.setList(list);

        if (checkedPositoin != -1) {
            listview.   setItemChecked(checkedPositoin, true);
        }
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        DATA item = mAdapter.getItem(position);
        if (mOnItemClickListener != null) {
            mOnItemClickListener.onItemClick(item);
        }
    }


}
