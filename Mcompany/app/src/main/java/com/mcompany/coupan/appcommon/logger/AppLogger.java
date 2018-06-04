package com.mcompany.coupan.appcommon.logger;

import android.util.Log;

import com.mcompany.coupan.appcommon.utility.Utility;


public class AppLogger {

    private static final boolean DEBUG = true;

    public static void i(String tag, String msg) {
        if(Utility.isStringNullOrEmpty(tag) || Utility.isStringNullOrEmpty(msg)){
            return;
        }
        if (DEBUG ) {
            Log.i(tag, msg);
        }
    }

    public static void i(String tag, String msg, Throwable tr) {
        if(Utility.isStringNullOrEmpty(tag) || Utility.isStringNullOrEmpty(msg)){
            return;
        }
        if (DEBUG) {
            Log.i(tag, msg, tr);
        }
    }

    public static void d(String tag, String msg) {
        if(Utility.isStringNullOrEmpty(tag) || Utility.isStringNullOrEmpty(msg)){
            return;
        }
        if (DEBUG) {
            Log.d(tag, msg);
        }
    }

    public static void d(String tag, String msg, Throwable tr) {
        if(Utility.isStringNullOrEmpty(tag) || Utility.isStringNullOrEmpty(msg)){
            return;
        }
        if (DEBUG) {
            Log.d(tag, msg, tr);
        }
    }

    public static void e(String tag, String msg) {
        if(Utility.isStringNullOrEmpty(tag) || Utility.isStringNullOrEmpty(msg)){
            return;
        }
            Log.e(tag, msg);
    }

    public static void e(String tag, String msg, Throwable tr) {
            Log.e(tag, msg, tr);
    }

}
