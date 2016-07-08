package com.sunfusheng.StickyHeaderListView.newDropDownMenu.view.NewDoubleSelectedGrid;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.util.AttributeSet;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.baiiu.filter.interfaces.OnFilterDoneListener;
import com.baiiu.filter.interfaces.OnFilterItemClickListener;
import com.orhanobut.logger.Logger;
import com.sunfusheng.StickyHeaderListView.R;
import com.sunfusheng.StickyHeaderListView.model.FilterBean;
import com.sunfusheng.StickyHeaderListView.ui.GridViewCompat;
import com.sunfusheng.StickyHeaderListView.ui.MainDropDownMenuActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * description: 新房列表页筛选菜单,更多界面:提供单选,多选的功能.
 */
public class NewhouseFilterMoreView<DATA> extends LinearLayout implements View.OnClickListener {

    private OnFilterItemClickListener<DATA> mOnItemClickListener;
    private OnFilterDoneListener mOnFilterDoneListener;

    private Map<String, List<FilterBean>>   selectedMap = new HashMap<>(); //存储已经选择的筛选项
    private Map<String, List<FilterBean>> hashMap;
    private LinearLayout linearLayout;
    private Context context;
    public NewhouseFilterMoreView(Context mContext) {
        super(mContext, null);
//        mCallback = callback;
        init(mContext);
    }

    public NewhouseFilterMoreView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public NewhouseFilterMoreView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public NewhouseFilterMoreView(Context context, AttributeSet attrs, int defStyleAttr,
                                  int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }


    private void init(Context context) {
        this.context = context;
        setBackgroundColor(Color.WHITE);
        inflate(context, R.layout.newhouse_filter_more, this);
        findViewById(R.id.btn_clear).setOnClickListener(this);
        findViewById(R.id.bt_confirm).setOnClickListener(this);
        linearLayout = (LinearLayout) findViewById(R.id.ll_filtermore_container);


    }

    public NewhouseFilterMoreView setGidMap(HashMap<String, List<FilterBean>> gridList) {
        this.hashMap = gridList;
        return this;
    }


    public NewhouseFilterMoreView build() {

//        int size = hashMap.entrySet().size();

        // 设置Linearlayout 为垂直方向布局
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        for (Map.Entry<String, List<FilterBean>> entry : hashMap.entrySet()) {
//           LogUtils.d("Key = " + entry.getKey() + ", Value = " + entry.getValue());
            linearLayout.addView(createView(entry.getKey(),entry.getValue()));
        }
        return this;
    }

    @Override
    public void onClick(View v) {

        int id = v.getId();
        if (id == R.id.bt_confirm) {
            if (mOnItemClickListener!=null){
                mOnItemClickListener.onItemClick((DATA) selectedMap);
            }
            if (mOnFilterDoneListener != null) {
                mOnFilterDoneListener.onFilterDone(3, selectedMap);
            }

        } else if (id == R.id.btn_clear) {//清空条件
            int childCount = linearLayout.getChildCount();
            for (int i = 0; i <childCount; i++) {
                LinearLayout childll = (LinearLayout) linearLayout.getChildAt(i);
                for (int j = 0; j <childll.getChildCount() ; j++) {
                    GridViewCompat gridView = (GridViewCompat) childll.getChildAt(1);
                    if (gridView!=null){
                        gridView.clearChoices();
                        gridView.invalidateViews();
                    }
                }
            }
            selectedMap.clear();
        }
    }


    public NewhouseFilterMoreView setOnFilterDoneListener(OnFilterDoneListener listener) {
        mOnFilterDoneListener = listener;
        return this;
    }

    /**
     * 方法描述：创建一个新子View<br>
     * @param title
     * @param data
     * @return
     * ps：这个子View 里面又有两个TextView
     */
    private View createView(final String title, List<FilterBean> data) {
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        View view = LayoutInflater.from(context).inflate(R.layout.newhouse_filter_more_grid, null);//也可以从XML中加载布局
        view.setLayoutParams(lp);//设置布局参数
        TextView textTitle = (TextView) view.findViewById(R.id.tv_title);
        textTitle.setText(title);
        GridViewCompat gridView = (GridViewCompat) view.findViewById(R.id.gv_gridview);
        if (title.equals(MainDropDownMenuActivity.houseProperty)||title.equals(MainDropDownMenuActivity.houseSalestatus)){
            gridView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        }else {
            gridView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        }
        gridView.setAdapter(new FilterMoreGridAdapterBug(context,data));
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> view, View arg1, int pos, long id) {
                // We need to invalidate all views on 4.x versions
                GridViewCompat gridView = (GridViewCompat) view;
                gridView.invalidateViews();
//                gridView.invalidate();
                SparseBooleanArray checkArray = gridView.getCheckedItemPositions();
                if (checkArray.size()!=0){
                    ArrayList<FilterBean> lists = new ArrayList<>();
                    for (int i = 0; i < checkArray.size(); i++) {
                        int checedPosition = checkArray.keyAt(i);
                        lists.add((FilterBean) gridView.getAdapter().getItem(checedPosition));
                    }
                    Logger.d("title"+title +"  pos   "+pos+"  id "+id+"  checkArray "+ checkArray);
                    selectedMap.put(title,lists);
                }
            }
        });
//        GridViewUtils.updateGridViewLayoutParams(gridView, 4);
        return view;
    }
    public NewhouseFilterMoreView<DATA> onItemClick(OnFilterItemClickListener<DATA> onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
        return this;
    }
}
