package com.example.cdj.myapplication.widget.qframelayout;

public interface KProgressClickListener {
	void onLoadingViewClick();

	void onEmptyViewClick();

	void onErrorViewClick();

	/**
	 *
	 * @param flag 0查看全部 1换个词试一下
     */
	void onSearchEmptyViewClick(int flag);
}
