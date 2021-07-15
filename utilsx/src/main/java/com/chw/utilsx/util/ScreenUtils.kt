package com.chw.utilsx.util

import android.app.Activity
import android.content.Context
import android.content.pm.ActivityInfo
import android.content.res.Configuration
import android.graphics.Point
import android.os.Build
import android.view.WindowManager

/**
 * @author hanwei.chen
 * 2021/5/29 16:18
 */
fun Activity?.keepScreenOn() {
    this?.window?.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
}

fun Activity?.setFullScreen() {
    this?.window?.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
}

fun Activity?.setNonFullScreen() {
    this?.window?.clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
}

fun Activity?.isFullScreen(): Boolean {
    if (this == null) return false
    val fullScreenFlag = WindowManager.LayoutParams.FLAG_FULLSCREEN
    return window.attributes.flags and fullScreenFlag == fullScreenFlag
}

fun Activity?.setLandscape() {
    this?.apply {
        if (requestedOrientation != ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE) {
            requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
        }
    }
}

fun Activity?.setPortrait() {
    this?.apply {
        if (requestedOrientation != ActivityInfo.SCREEN_ORIENTATION_PORTRAIT) {
            requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        }
    }
}

val isLandscape: Boolean
    get() = ContextUtils.application.resources
        .configuration.orientation == Configuration.ORIENTATION_LANDSCAPE

val isPortrait: Boolean
    get() = ContextUtils.application.resources
        .configuration.orientation == Configuration.ORIENTATION_PORTRAIT

object ScreenUtils {
    val screenWidth: Int
        get() {
            val wm =
                ContextUtils.application.getSystemService(Context.WINDOW_SERVICE) as WindowManager
            val point = Point()
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                wm.defaultDisplay.getRealSize(point)
            } else {
                wm.defaultDisplay.getSize(point)
            }
            return point.x
        }

    val screenHeight: Int
        get() {
            val wm =
                ContextUtils.application.getSystemService(Context.WINDOW_SERVICE) as WindowManager
            val point = Point()
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                wm.defaultDisplay.getRealSize(point)
            } else {
                wm.defaultDisplay.getSize(point)
            }
            return point.y
        }

    val appScreenWidth: Int
        get() {
            val wm =
                ContextUtils.application.getSystemService(Context.WINDOW_SERVICE) as WindowManager
            val point = Point()
            wm.defaultDisplay.getSize(point)
            return point.x
        }

    val appScreenHeight: Int
        get() {
            val wm =
                ContextUtils.application.getSystemService(Context.WINDOW_SERVICE) as WindowManager
            val point = Point()
            wm.defaultDisplay.getSize(point)
            return point.y
        }
}