// IBookAidlInterface.aidl
package com.czy.server;

// Declare any non-default types here with import statements
import com.czy.server.Book;
import com.czy.server.IOnNewBookArrivedListener;
interface BookController {
    List<Book> getBookList();

    void addBookInOut(inout Book book);

    void addBookIn(in Book book);

    void addBookOut(out Book book);

       void registerListener(IOnNewBookArrivedListener listener);

        void unregisterListener(IOnNewBookArrivedListener listener);
}
