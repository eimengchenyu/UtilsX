package com.chw.utilsx.util

import android.app.Activity
import android.app.ActivityManager
import android.app.Application
import android.content.Context
import android.os.Build

/**
 * @author hanwei.chen
 * 2021/5/31 9:25
 */
object ContextUtils {
    lateinit var application: Application
    val context: Context
        get() = application.applicationContext
}

/**
 * 判断context是否合法
 */
fun Context?.isContextValid(): Boolean {
    if (this == null) {
        return false
    }
    if (this is Activity) {
        val activity = this as Activity?
        val isDestroyed = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1)
            !activity!!.isDestroyed else true
        return !activity!!.isFinishing && isDestroyed
    }
    return true
}

/**
 * 判断当前进程是否在前台
 */
fun Context?.isApplicationForeground(): Boolean {
    if (this == null) return false
    val appProcesses =
        (getSystemService(Context.ACTIVITY_SERVICE) as? ActivityManager)?.runningAppProcesses
    if (appProcesses.isNullOrEmpty()) return false
    for (appProcess in appProcesses) {
        if (appProcess.processName == packageName) {
            return appProcess.importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND
        }
    }
    return false
}