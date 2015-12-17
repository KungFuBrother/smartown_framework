package com.example.hellojni;

/**
 * Created by Tiger on 2015-12-17.
 */
public class HelloJni {

    public static native String stringFromJNI();

    static {
        System.loadLibrary("hello-jni");
    }

}
