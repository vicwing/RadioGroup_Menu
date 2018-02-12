// IBookAidlInterface.aidl
package com.czy.server;

// Declare any non-default types here with import statements
import com.czy.server.Book;

interface BookController {
    List<Book> getBookList();

    void addBookInOut(inout Book book);

    void addBookIn(in Book book);

    void addBookOut(out Book book);
}
