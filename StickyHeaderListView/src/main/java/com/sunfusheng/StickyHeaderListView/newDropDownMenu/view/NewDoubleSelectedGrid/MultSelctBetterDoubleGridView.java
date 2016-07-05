package com.sunfusheng.StickyHeaderListView.newDropDownMenu.view.NewDoubleSelectedGrid;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;

import com.apkfuns.logutils.LogUtils;
import com.baiiu.filter.interfaces.OnFilterDoneListener;
import com.orhanobut.logger.Logger;
import com.sunfusheng.StickyHeaderListView.R;
import com.sunfusheng.StickyHeaderListView.view.StickyListView.StickyListHeadersAdapter;
import com.sunfusheng.StickyHeaderListView.view.StickyListView.StickyListHeadersListView;

import java.util.List;

/**
 * auther: baiiu
 * time: 16/6/5 05 23:03
 * description:
 */
public class MultSelctBetterDoubleGridView extends LinearLayout implements View.OnClickListener {


    private List<String> mTopGridData;
    private List<String> mBottomGridList;
    private OnFilterDoneListener mOnFilterDoneListener;
    private View btn;
    private StickyListHeadersListView stickyList;


    public MultSelctBetterDoubleGridView(Context context) {
        this(context, null);
    }

    public MultSelctBetterDoubleGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public MultSelctBetterDoubleGridView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public MultSelctBetterDoubleGridView(Context context, AttributeSet attrs, int defStyleAttr,
                                         int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }

    private void init(Context context) {
        setBackgroundColor(Color.WHITE);
        inflate(context, R.layout.item_multselect_sticklist_gridview, this);
        btn = findViewById(R.id.bt_confirm);
        btn.setOnClickListener(this);

        stickyList = (StickyListHeadersListView) findViewById(R.id.lv_stickyheadlist);
        stickyList.setDrawingListUnderStickyHeader(true);
        stickyList.setAreHeadersSticky(false);
    }


    public MultSelctBetterDoubleGridView setmTopGridData(List<String> mTopGridData) {
        this.mTopGridData = mTopGridData;
        return this;
    }

    public MultSelctBetterDoubleGridView setmBottomGridList(List<String> mBottomGridList) {
        this.mBottomGridList = mBottomGridList;
        return this;
    }

    public MultSelctBetterDoubleGridView build() {
        stickyList.setAdapter(new FilterMoreStickyListGridAdapter(getContext()));
        stickyList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Logger.d("stickyList   itemClick ");
            }
        });

        return this;
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
//        findViewById(R.id.my_gridview);
        if (id == R.id.bt_confirm) {
            LogUtils.d("完成.");
//            // TODO Auto-generated method stub
//            SparseBooleanArray checkArray;
//            checkArray = gridView.getCheckedItemPositions();
//
//            String selectedPos = "Selected positions: ";
//            int count = checkArray.size();
//            for (int i = 0; i < count; i++) {
//                if (checkArray.valueAt(i))
//                    selectedPos += checkArray.keyAt(i) + ",";
//            }
            StickyListHeadersAdapter adapter = stickyList.getAdapter();
//            adapter.
            if (mOnFilterDoneListener != null) {
                mOnFilterDoneListener.onFilterDone(3, "左侧key  ", "右侧  value");
            }
        }
    }

    public MultSelctBetterDoubleGridView setOnFilterDoneListener(OnFilterDoneListener listener) {
        mOnFilterDoneListener = listener;
        return this;
    }

    public interface On {
        void onFilterDone(int positionTitle, String title, String urlValue);
    }
}
