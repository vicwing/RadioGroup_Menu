package com.example.cdj.myapplication.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.RemoteCallbackList;
import android.os.RemoteException;
import android.os.SystemClock;

import com.czy.server.Book;
import com.czy.server.BookController;
import com.czy.server.IOnNewBookArrivedListener;
import com.orhanobut.logger.Logger;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Description : 学习 aidl模拟的服务端
 * Created by vic
 * Created Time 2018/2/11
 */
public class BookManagerService extends Service {
    /**
     * CopyOnWriteArrayList相当于线程安全的ArrayList，通过增加写时复制语义来实现线程安全性。底层也是通过一个可变数组来实现的。但是和ArrayList不同的时，它具有以下特性：
     * 它最适合于具有以下特征的应用程序：List 大小通常保持很小，只读操作远多于可变操作，需要在遍历期间防止线程间的冲突
     * 支持高效率并发且是线程安全的
     * 因为通常需要复制整个基础数组，所以可变操作（add()、set() 和 remove() 等等）的开销很大
     * 迭代器支持hasNext(), next()等不可变操作，但不支持可变 remove()等操作
     * 使用迭代器进行遍历的速度很快，并且不会与其他线程发生冲突。在构造迭代器时，迭代器依赖于不变的数组快照.
     */
    private CopyOnWriteArrayList<Book> bookList = new CopyOnWriteArrayList<>();

    private AtomicBoolean mIsServiceDestroyed = new AtomicBoolean(false);

    /**
     * 监听集合
     */
//    private CopyOnWriteArrayList<IOnNewBookArrivedListener> mListenerList = new CopyOnWriteArrayList<>();

    private RemoteCallbackList<IOnNewBookArrivedListener> mListenerList = new RemoteCallbackList<>();

    private final Binder mbinder = new BookController.Stub() {


        @Override
        public List<Book> getBookList() throws RemoteException {
//            ToastUtils.showShort("查询书本...");
            SystemClock.sleep(5000);
            return bookList;
        }

        @Override
        public void addBookInOut(Book book) throws RemoteException {

        }

        @Override
        public void addBookIn(Book book) throws RemoteException {
            bookList.add(book);
//            ToastUtils.showShort("添加书本...");
        }

        @Override
        public void addBookOut(Book book) throws RemoteException {
        }

        @Override
        public void registerListener(IOnNewBookArrivedListener listener) throws RemoteException {
//            if (!mListenerList.contains(listener)) {
//                mListenerList.add(listener);
//            } else {
//                Logger.d(" already  已经存在");
//            }
            mListenerList.register(listener);
            Logger.d(" 已经注册   list size  " + mListenerList.getRegisteredCallbackCount());
        }

        @Override
        public void unregisterListener(IOnNewBookArrivedListener listener) throws RemoteException {
//            if (mListenerList.contains(listener)) {
//                mListenerList.remove(listener);
//                Logger.d(" 注册成功.......");
//            } else {
//                Logger.d(" 没有  注册.......");
//            }

            mListenerList.unregister(listener);
            Logger.d(" 解除注册   list size  " + mListenerList.getRegisteredCallbackCount());
        }


    };


    @Override
    public IBinder onBind(Intent intent) {
        return mbinder;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        initData();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mIsServiceDestroyed.set(true);
    }

    private void initData() {
        Book book1 = new Book("活着");
        Book book2 = new Book("或者");
        Book book3 = new Book("叶应是叶");
        Book book4 = new Book("https://github.com/leavesC");
        Book book5 = new Book("http://www.jianshu.com/u/9df45b87cfdf");
        Book book6 = new Book("http://blog.csdn.net/new_one_object");
        bookList.add(book1);
        bookList.add(book2);
        bookList.add(book3);
        bookList.add(book4);
        bookList.add(book5);
        bookList.add(book6);
        new Thread(new ServiceWork()).start();
    }

    /**
     * 每隔五秒添加一本书.
     */
    private class ServiceWork implements Runnable {
        @Override
        public void run() {
            //do background
            while (!mIsServiceDestroyed.get()) {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                int bookId = bookList.size() + 1;
                Book book = new Book(("新添加的书籍" + String.valueOf(bookId)));
                try {
                    OnNewBookArrived(book);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }

        }

        private void OnNewBookArrived(Book book) throws RemoteException {
            bookList.add(book);
//            方法一:
//            Logger.d(" list   list size  " + mListenerList.size());
//            for (int i = 0; i < mListenerList.size(); i++) {
//                IOnNewBookArrivedListener iOnNewBookArrivedListener = mListenerList.get(i);
//                Logger.d("background  有注册用户哦 ");
//                iOnNewBookArrivedListener.onNewBookArrived(book);
//            }
//            改进方法一
            int registeredcallbackcount = mListenerList.beginBroadcast();
            for (int i = 0; i < registeredcallbackcount; i++) {
                IOnNewBookArrivedListener listener = mListenerList.getBroadcastItem(i);
                if (listener != null) {
                    listener.onNewBookArrived(book);
                }
            }
            //必须和 beginBroadcast 配对使用
            mListenerList.finishBroadcast();
        }
    }
}
