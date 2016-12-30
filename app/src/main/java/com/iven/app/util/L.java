package com.iven.app.util;

import android.text.TextUtils;
import android.util.Log;

import java.util.Map;

/**
 * @auth Iven
 * 2016/11/22 22:10
 * @desc 控制台打印工具
 */

public class L {
    /**
     * 只有Debug模式才开启日志打印
     */
    private static final boolean DEBUG = true;

    //Verbose
    public static void v(Object obj, String msg) {
        if (DEBUG && obj != null && !TextUtils.isEmpty(msg)) {
            if (obj instanceof String) {
                Log.v(obj.toString(), msg);
            } else {
                Log.v(obj.getClass().getSimpleName(), msg);
            }
        }
    }

    //Debug
    public static void d(Object obj, String msg) {
        if (DEBUG && obj != null && !TextUtils.isEmpty(msg)) {
            if (obj instanceof String) {
                Log.d(obj.toString(), msg);
            } else {
                Log.d(obj.getClass().getSimpleName(), msg);
            }
        }
    }

    //Info
    public static void i(Object obj, String msg) {
        if (DEBUG && obj != null && !TextUtils.isEmpty(msg)) {
            if (obj instanceof String) {
                Log.i(obj.toString(), msg);
            } else {
                Log.i(obj.getClass().getSimpleName(), msg);
            }
        }
    }

    //Warn
    public static void w(Object obj, String msg) {
        if (DEBUG && obj != null && !TextUtils.isEmpty(msg)) {
            if (obj instanceof String) {
                Log.w(obj.toString(), msg);
            } else {
                Log.w(obj.getClass().getSimpleName(), msg);
            }
        }
    }

    //Error
    public static void e(Object obj, String msg) {
        if (DEBUG && obj != null && !TextUtils.isEmpty(msg)) {
            if (obj instanceof String) {
                Log.e(obj.toString(), msg);
            } else {
                Log.e(obj.getClass().getSimpleName(), msg);
            }
        }
    }

    public static void showMap(Object obj, Map map) {
        if (DEBUG && obj != null && map != null && map.size() > 0) {

            StringBuilder sb = new StringBuilder("请求参数:---------> ");
            for (Object key : map.keySet()) {
                if (key != null && map.get(key) != null) {
                    sb.append(key.toString() + "=" + map.get(key).toString() + ",");
                }
            }
            String result = sb.substring(0, sb.length() - 1) + "<----------------";

            if (obj instanceof String) {
                Log.i(obj.toString(), result);
            } else {
                Log.i(obj.getClass().getSimpleName(), result);
            }
        }
    }

}
