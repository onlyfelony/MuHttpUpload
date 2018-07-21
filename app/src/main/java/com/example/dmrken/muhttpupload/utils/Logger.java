package com.example.dmrken.muhttpupload.utils;

import android.util.Log;

/**
 * Created by dmrken on 2018/7/21.
 */

/**
 * 打印调试信息
 */
public class Logger {

    public static final String TAG = "MYLOG";

    //判断是否打印
    public static final boolean DEBUG = true;


    //转对象为string (因为对象可能为null 所以不能直接toString())

    private static String getMsg(Object o) {
        return o == null ? "null" : o.toString();
    }

    public static void i(Object msg) {
        if (DEBUG) {
            Log.i(TAG, getMsg(msg));
        }
    }

    public static void e(Object msg) {
        if (DEBUG) {
            Log.e(TAG, getMsg(msg));
        }
    }

    public static void d(Object msg) {
        if (DEBUG) {
            Log.d(TAG, getMsg(msg));
        }
    }

    /**
     * 打印警告信息
     *
     * @param msg
     */
    public static void w(Object msg) {
        if (DEBUG) {
            Log.w(TAG, getMsg(msg));
        }
    }
}
