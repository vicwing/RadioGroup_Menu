package com.example.cdj.myapplication;

import android.app.Application;
import android.test.ApplicationTestCase;

import com.orhanobut.logger.Logger;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class ApplicationTest extends ApplicationTestCase<Application> {
    public ApplicationTest() {
        super(Application.class);
        Logger.d("测试.........");
    }
}