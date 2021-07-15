package com.chw.utilsx.util

import android.app.Service
import android.content.Context
import android.content.Intent

/**
 * @author hanwei.chen
 * 2021/7/15 23:07
 */
inline fun <reified S : Service> Context.launchForegroundService() {
    Intent(this, S::class.java).apply {
        if (isAndroid8) startForegroundService(this) else startService(this)
    }
}