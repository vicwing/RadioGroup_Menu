package com.baiiu.filter.typeview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.baiiu.filter.R;
import com.baiiu.filter.adapter.BaseBaseAdapter;
import com.baiiu.filter.adapter.SimpleTextAdapter;
import com.baiiu.filter.util.CommonUtil;
import com.orhanobut.logger.Logger;

import java.util.List;

/**
 * Created by baiiu on 15/12/17.
 * 双列ListView
 */
public class ThreeListView<LEFTD, RIGHTD> extends LinearLayout implements AdapterView.OnItemClickListener {

    private BaseBaseAdapter<LEFTD> mLeftAdapter;
    private BaseBaseAdapter<LEFTD> mMidAdapter;
    private BaseBaseAdapter<RIGHTD> mRightAdapter;
    private ListView lv_left;
    private ListView lv_right;
    private ListView lv_mid;


    public ThreeListView(Context context) {
        this(context, null);
    }

    public ThreeListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public ThreeListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        setOrientation(VERTICAL);
        inflate(context, R.layout.merge_filter_list, this);

        lv_left = (ListView) findViewById(R.id.lv_left);
        lv_mid = (ListView) findViewById(R.id.lv_mid);
        lv_right = (ListView) findViewById(R.id.lv_right);

        lv_left.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        lv_right.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        lv_mid.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

        lv_left.setOnItemClickListener(this);
        lv_right.setOnItemClickListener(this);
        lv_mid.setOnItemClickListener(this);
    }


    public ThreeListView<LEFTD, RIGHTD> leftAdapter(SimpleTextAdapter<LEFTD> leftAdapter) {
        mLeftAdapter = leftAdapter;
        lv_left.setAdapter(leftAdapter);
        return this;
    }

    public ThreeListView<LEFTD, RIGHTD> rightAdapter(SimpleTextAdapter<RIGHTD> rightAdapter) {
        mRightAdapter = rightAdapter;
        lv_right.setAdapter(rightAdapter);
        return this;
    }

    public ThreeListView<LEFTD, RIGHTD> midAdapter(SimpleTextAdapter<LEFTD> midAdapter) {
        mMidAdapter = midAdapter;
        lv_mid.setAdapter(midAdapter);
        return this;
    }

    public void setLeftList(List<LEFTD> list, int checkedPosition) {
        mLeftAdapter.setList(list);

        if (checkedPosition != -1) {
//            lv_left.performItemClick(mLeftAdapter.getView(checkedPositoin, null, null), checkedPositoin, mLeftAdapter.getItemId(checkedPositoin));//调用此方法相当于点击.第一次进来时会触发重复加载.
            lv_left.setItemChecked(checkedPosition, true);
        }
    }

    public void setRightList(List<RIGHTD> list, int checkedPosition) {
        mRightAdapter.setList(list);
        if (checkedPosition != -1) {
            lv_right.setItemChecked(checkedPosition, true);
        }
    }

    public void setMidList(List<LEFTD> list, int checkedPosition) {
        mMidAdapter.setList(list);
        if (checkedPosition != -1) {
            lv_mid.setItemChecked(checkedPosition, true);
        }
    }

    private OnLeftItemClickListener<LEFTD, RIGHTD> mOnLeftItemClickListener;
    private OnRightItemClickListener<LEFTD, RIGHTD> mOnRightItemClickListener;
    private OnMidItemClickListener<LEFTD, RIGHTD> mOnMidItemClickListener;

    public interface OnLeftItemClickListener<LEFTD, RIGHTD> {
        List<LEFTD> provideMidList(LEFTD leftAdapter, int position);
    }
    public interface OnMidItemClickListener<LEFTD, RIGHTD> {
        List<RIGHTD> provideRightList(LEFTD leftAdapter, int position, int mLeftLastPosition);
    }

    public interface OnRightItemClickListener<LEFTD, RIGHTD> {
        void onRightItemClick(int mLeftLastPosition, LEFTD item, RIGHTD childItem);
    }



    public ThreeListView<LEFTD, RIGHTD> onLeftItemClickListener(OnLeftItemClickListener<LEFTD, RIGHTD> onLeftItemClickListener) {
        this.mOnLeftItemClickListener = onLeftItemClickListener;
        return this;
    }

    public ThreeListView<LEFTD, RIGHTD> onRightItemClickListener(OnRightItemClickListener<LEFTD, RIGHTD> onRightItemClickListener) {
        this.mOnRightItemClickListener = onRightItemClickListener;
        return this;
    }

    public ThreeListView<LEFTD, RIGHTD> onMidItemClickListener(OnMidItemClickListener<LEFTD, RIGHTD> mOnMidItemClickListener) {
        this.mOnMidItemClickListener = mOnMidItemClickListener;
        return this;
    }

    public ListView getLeftListView() {
        return lv_left;
    }

    public ListView getRightListView() {
        return lv_right;
    }
    public ListView getMidListView() {
        return lv_mid;
    }

    //========================点击事件===================================
    private int mRightLastChecked;

    private int mLeftLastPosition;
    private int mLeftLastCheckedPosition;


    private int mMidLastPosition;
    private int mMidLastCheckedPosition;
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (mLeftAdapter == null || mRightAdapter == null||mMidAdapter==null) {
            return;
        }

        if (parent == lv_left) {
//         Logger.d("threelistview","position  " + position + "  mLeftLastPosition  : " + mLeftLastPosition);
            if (position!=mLeftLastPosition){//切换后重置所有得选项.
                mRightLastChecked=0;
                mLeftLastPosition=0;
                mLeftLastCheckedPosition=0;
                mMidLastPosition=0;
                mMidLastCheckedPosition=0;
                //列表页,切换选项后 ,清楚选中的状态
                lv_mid.getCheckedItemPositions().clear();
                lv_right.getCheckedItemPositions().clear();
//                mMidAdapter.clearList();
                mRightAdapter.setList(null);
            }
            mLeftLastPosition = position;

            if (mOnLeftItemClickListener != null) {
                LEFTD item = mLeftAdapter.getItem(position);

                List<LEFTD> mMidds = mOnLeftItemClickListener.provideMidList(item, position);

                mMidAdapter.setList(mMidds);

                if (CommonUtil.isEmpty(mMidds)) {
                    //当前点的就是这个条目
                    mLeftLastCheckedPosition = -1;
                }else {
                    mLeftLastCheckedPosition = mLeftLastPosition;
                }
            }
            lv_mid.setItemChecked(mMidLastCheckedPosition, mLeftLastCheckedPosition == position);
            Logger.d("mMidLastCheckedPosition   " +mMidLastCheckedPosition+  " mLeftLastCheckedPosition  "+mLeftLastCheckedPosition +"  position  " +position );
            Logger.d("lv_mid checked position"+lv_mid.getCheckedItemPositions().toString());
        }else if (parent == lv_mid) {

            mMidLastPosition = position;
            if (mOnMidItemClickListener != null) {
                LEFTD item =  mMidAdapter.getItem(position);

                List<RIGHTD> rightds = mOnMidItemClickListener.provideRightList(item, position,mLeftLastPosition);
                    mRightAdapter.setList(rightds);

                if (CommonUtil.isEmpty(rightds)) {
                    //当前点的就是这个条目
                    mMidLastCheckedPosition = -1;
                }else {
                    mMidLastCheckedPosition = mMidLastPosition;
                }
            }
            lv_right.setItemChecked(mRightLastChecked, mMidLastCheckedPosition == position);
        }  else {
            mRightLastChecked = position;
            if (mOnRightItemClickListener != null) {
                mOnRightItemClickListener.onRightItemClick(mLeftLastPosition,mMidAdapter.getItem(mMidLastCheckedPosition), mRightAdapter.getItem(mRightLastChecked));
            }
        }
    }


}
