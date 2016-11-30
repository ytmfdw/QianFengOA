package oa.qianfeng.com.oa.utils;

import android.util.Log;

/**
 * Created by Administrator on 2016/11/30 0030.
 */
public class L {
    static final boolean DEBUG = true;
    static final String TAG = "ytmfdwTAG";

    public static final void d(String msg) {
        if (DEBUG) {
            Log.d(TAG, msg);
        }
    }

    public static final void d(String tag, String msg) {
        if (DEBUG) {
            Log.d(tag, msg);
        }
    }
}
