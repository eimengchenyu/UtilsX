package com.chw.utilsx.util

import android.os.Build
import android.webkit.WebSettings

/**
 * @author hanwei.chen
 * 2021/8/23 10:31
 */
object DeviceUtils {
    val deviceUserAgent: String
        get() {
            val userAgent = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                try {
                    WebSettings.getDefaultUserAgent(ContextUtils.application) ?: ""
                } catch (e: Exception) {
                    System.getProperty("http.agent") ?: ""
                }
            } else {
                System.getProperty("http.agent") ?: ""
            }
            val sb = StringBuffer()
            var i = 0
            val length = userAgent.length
            while (i < length) {
                val c = userAgent[i]
                if (c <= '\u001f' || c >= '\u007f') {
                    sb.append(String.format("\\u%04x", c.toInt()))
                } else {
                    sb.append(c)
                }
                i++
            }
            return sb.toString()
        }
}