package com.chw.utilsx.util

import android.os.Build

/**
 * @author hanwei.chen
 * 2021/7/15 23:31
 */
val isAndroid6
    get() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.M

val isAndroid7
    get() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.N

val isAndroid8
    get() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.O

val isAndroid10
    get() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q

val isAndroid11
    get() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.R