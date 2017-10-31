package com.handsomezhou.funnyalgorithm.util;

import android.util.Log;


/**
 * Add by zhoujq.
 */

public class LogUtil {
    private static boolean LOG = true;
    private static boolean LOGV = true;
    private static boolean LOGD = true;
    private static boolean LOGI = true;
    private static boolean LOGW = true;
    private static boolean LOGE = true;

    public static void v(String tag, String msg) {
        if (LOG && LOGV) {
            Log.v(tag, msg);
        }
    }

    public static void d(String tag, String msg) {
        if (LOG && LOGD) {
            Log.d(tag, msg);
        }
    }

    public static void i(String tag, String msg) {
        if (LOG && LOGI) {
            Log.i(tag, msg);
        }
    }

    public static void w(String tag, String msg) {
        if (LOG && LOGW) {
            Log.w(tag, msg);
        }
    }

    public static void e(String tag, String msg) {
        if (LOG && LOGE) {
            Log.e(tag, msg);
        }
    }


}
