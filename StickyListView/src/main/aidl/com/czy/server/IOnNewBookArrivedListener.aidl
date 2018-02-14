// IOnNewBookArrivedListener.aidl
package com.czy.server;

// Declare any non-default types here with import statements
import com.czy.server.Book;

interface IOnNewBookArrivedListener {
    void onNewBookArrived(in Book newBook);
}
