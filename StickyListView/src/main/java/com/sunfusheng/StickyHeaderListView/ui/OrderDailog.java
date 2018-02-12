package com.sunfusheng.StickyHeaderListView.ui;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import com.sunfusheng.StickyHeaderListView.R;
import com.sunfusheng.StickyHeaderListView.base.quick.BaseAdapterHelper;
import com.sunfusheng.StickyHeaderListView.base.quick.QuickAdapter;
import com.sunfusheng.StickyHeaderListView.model.FilterBean;
import com.sunfusheng.StickyHeaderListView.newhouseui.NewhouseHomeListActivity;

import java.util.List;

/**
 * 自定义的Dialog,排序的
 */
public class OrderDailog extends Dialog {
    private Context context;
    private List<FilterBean> orderByData;
    private ListView listView;
    private OnBtnClickListener listener;
    private ImageView iv_close;
    private View layout;


    public OrderDailog(Context context) {
        super(context, R.style.ExitDialog);
        this.context = context;
    }

    public OrderDailog(Context context, OnBtnClickListener listener) {
        super(context, R.style.ExitDialog);
        this.context = context;
        this.listener = listener;
    }

    public OrderDailog(NewhouseHomeListActivity context, List<FilterBean> orderByData) {
        super(context, R.style.ExitDialog);
        this.context = context;
        this.orderByData = orderByData;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        layout = inflater.inflate(R.layout.dialog_orderby_list, null);
//        setContentView(layout, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        setContentView(layout);
        setparams();
        initViews();
    }


    private void setparams() {


//        DisplayMetrics dm = context.getResources().getDisplayMetrics();
//        int w_screen = dm.widthPixels;
//        int h_screen = dm.heightPixels;

        Window window = getWindow();

        WindowManager.LayoutParams layoutParams = window.getAttributes();
        window.getDecorView().setPadding(0, 0, 0, 0);
        layoutParams.width =  ViewGroup.LayoutParams.MATCH_PARENT;
        layoutParams.height =  ViewGroup.LayoutParams.WRAP_CONTENT;
        layoutParams.gravity = Gravity.BOTTOM;
        window.setWindowAnimations(R.style.DialogWindowAnim);
        window.setAttributes(layoutParams);
    }
    public void showDialog(){
        show();
    }
    private void initViews() {
        // TODO Auto-generated method stub

        listView = (ListView) findViewById(R.id.lv_listview);
        listView.setAdapter(new QuickAdapter<FilterBean>(context, R.layout.item_newhouse_dialog_orderby, orderByData) {
            @Override
            protected void convert(BaseAdapterHelper helper, FilterBean item) {
                helper.setText(R.id.tv_text, item.getDesc());
            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                FilterBean item = (FilterBean) parent.getAdapter().getItem(position);
                if (isShowing())
                    dismiss();
                if (listener != null)
                    listener.onBtnClick(item.getValue());
            }
        });
    }

    public void setOnclickListener(OnBtnClickListener listener) {
        this.listener = listener;
    }

    public interface OnBtnClickListener {
        void onBtnClick(String value);
    }
}
