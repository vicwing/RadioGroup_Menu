package com.example.cdj.myapplication.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.RemoteException;

import com.czy.server.Book;
import com.czy.server.BookController;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

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


    private final Binder mbinder = new BookController.Stub() {


        @Override
        public List<Book> getBookList() throws RemoteException {
//            ToastUtils.showShort("查询书本...");
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
    }
}
