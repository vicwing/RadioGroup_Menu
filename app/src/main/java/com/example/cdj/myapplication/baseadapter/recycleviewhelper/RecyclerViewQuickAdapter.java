package com.example.cdj.myapplication.baseadapter.recycleviewhelper;

import android.content.Context;

import java.util.List;


/**
 * 支持 RecyclerView 的 RecyclerViewQuickAdapter
 * Created by HanHailong on 15/9/6.
 */
public abstract class RecyclerViewQuickAdapter<T> extends BaseQuickAdapter<T, BaseAdapterHelper> {

    /**
     * Create a RecyclerViewQuickAdapter.
     *
     * @param context     The context.
     * @param layoutResId The layout resource id of each item.
     */
    public RecyclerViewQuickAdapter(Context context, int layoutResId) {
        super(context, layoutResId);
    }

    /**
     * Same as RecyclerViewQuickAdapter#RecyclerViewQuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param context     The context.
     * @param layoutResId The layout resource id of each item.
     * @param data        A new list is created out of this one to avoid mutable list
     */
    public RecyclerViewQuickAdapter(Context context, int layoutResId, List<T> data) {
        super(context, layoutResId, data);
    }

    /**
     * Create a multi-view-type RecyclerViewQuickAdapter
     *
     * @param context              The context
     * @param multiItemTypeSupport multiitemtypesupport
     */
    protected RecyclerViewQuickAdapter(Context context, MultiItemTypeSupport<T> multiItemTypeSupport) {
        super(context, multiItemTypeSupport);
    }

    /**
     * Same as RecyclerViewQuickAdapter#RecyclerViewQuickAdapter(Context,MultiItemTypeSupport) but with
     * some initialization data
     *
     * @param context
     * @param multiItemTypeSupport
     * @param data
     */
    protected RecyclerViewQuickAdapter(Context context, MultiItemTypeSupport<T> multiItemTypeSupport, List<T> data) {
        super(context, multiItemTypeSupport, data);
    }

}
