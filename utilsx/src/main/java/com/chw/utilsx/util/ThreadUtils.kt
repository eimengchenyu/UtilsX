package com.chw.utilsx.util

import android.os.Looper

/**
 * @author hanwei.chen
 * 2021/6/1 14:37
 */
fun isMainThread() = Looper.myLooper() == Looper.getMainLooper()