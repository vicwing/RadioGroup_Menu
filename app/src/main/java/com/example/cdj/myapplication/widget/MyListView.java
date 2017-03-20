package com.example.cdj.myapplication.widget;


import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

import com.apkfuns.logutils.LogUtils;

/**
 * @ClassName:[MyListView]
 * @Description:[自定义ListView，解决ScrollView中嵌套ListView显示不正常的问题（1行）]
 * @author liuyongzheng
 * @CreateDate:[2014-4-28 下午8:15:55]
 * @UpdateUser: UpdateUser
 * @UpdateDate: [2014-4-28 下午8:15:55]
 * @UpdateRemark: [说明本次修改内容]
 * @version [V1.0]
 */
public class MyListView extends ListView {

	public MyListView(Context context) {
		// TODO Auto-generated method stub
		super(context);
	}

	public MyListView(Context context, AttributeSet attrs) {
		// TODO Auto-generated method stub
		super(context, attrs);
	}

	public MyListView(Context context, AttributeSet attrs, int defStyle) {
		// TODO Auto-generated method stub
		super(context, attrs, defStyle);
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		// TODO Auto-generated method stub
		int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
		super.onMeasure(widthMeasureSpec, expandSpec);
		LogUtils.d("my Listview     onmeasure  ...........");
	}

//	@Override
//	protected void onScrollChanged(int l, int t, int oldl, int oldt) {
//		super.onScrollChanged(l, t, oldl, oldt);
//	}
//
//	@Override
//	public boolean onTouchEvent(MotionEvent ev) {
//		return super.onTouchEvent(ev);
//	}

//	@Override
//	public boolean onInterceptTouchEvent(MotionEvent ev) {
//		switch (ev.getAction()) {
//			// 当手指触摸listview时，让父控件交出ontouch权限,不能滚动
//			case MotionEvent.ACTION_DOWN:
//				setParentScrollAble(false);
//			case MotionEvent.ACTION_MOVE:
//				break;
//			case MotionEvent.ACTION_UP:
//			case MotionEvent.ACTION_CANCEL:
//				// 当手指松开时，让父控件重新获取onTouch权限
//				setParentScrollAble(true);
//				break;
//
//		}
//		return super.onInterceptTouchEvent(ev);
//
//	}
//
//	// 设置父控件是否可以获取到触摸处理权限
//	private void setParentScrollAble(boolean flag) {
//		getParent().requestDisallowInterceptTouchEvent(!flag);
//	}


}