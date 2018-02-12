package com.sunfusheng.StickyHeaderListView.ui;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.view.View;
import android.widget.TextView;

import com.czy.server.Book;
import com.czy.server.BookController;
import com.orhanobut.logger.Logger;
import com.sunfusheng.StickyHeaderListView.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * Description : aidl 客户端测试用
 * Created by vic
 * Created Time 2018/2/11
 */
public class ClientMainActivity extends Activity {

    @BindView(R.id.tv_aidl_test)
    TextView tvAidlTest;

    private BookController iBookManager;

    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
//          com.czy.server.Book
            Logger.d(" 链接服务................. ");
            try {
                iBookManager = BookController.Stub.asInterface(service);
                List<Book> bookList = iBookManager.getBookList();
                Logger.d("booklist  " + bookList.size() + " type " + bookList.getClass().getCanonicalName());

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Logger.d(" ServiceConnection  onServiceDisconnected " + name);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
//        bindService();
    }

    @Override
    protected void onDestroy() {
        unbindService(connection);
        super.onDestroy();
    }

    @OnClick({R.id.tv_aidl_test, R.id.tv_getlist, R.id.tv_start_service})
    public void submit(View view) {
        // TODO submit data to server...
        switch (view.getId()) {
            case R.id.tv_aidl_test:
                Logger.d("hahaahaahahahahahah..................");
                try {
                    Book book = new Book("添加蜀步");
                    iBookManager.addBookIn(book);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
                break;
            case R.id.tv_getlist:
                try {
                    List<Book> bookList = iBookManager.getBookList();
                    Logger.d("booklist  " + bookList.size() + " type " + bookList.getClass().getCanonicalName());
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
                break;
            case R.id.tv_start_service:
                bindService();
                break;
            default:
                break;
        }
    }

    private void bindService() {
        Intent intent = new Intent();
        intent.setPackage("com.cdj.group");
        intent.setAction("com.cdj.group.action");
        bindService(intent, connection, Context.BIND_AUTO_CREATE);


//        action 必须包含包名
//        Intent intent = new Intent();
//        intent.setPackage("com.czy.server");
//        intent.setAction("com.czy.server.action");
//        bindService(intent, connection, Context.BIND_AUTO_CREATE);
    }

}
