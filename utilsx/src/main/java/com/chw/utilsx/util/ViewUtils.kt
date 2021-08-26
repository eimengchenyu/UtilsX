package com.chw.utilsx.util

import android.view.View

/**
 * @author hanwei.chen
 * 2021/8/26 15:40
 */
fun View?.setVisible() {
    if (this == null || this.visibility == View.VISIBLE) return
    visibility = View.VISIBLE
}

fun View?.setInvisible() {
    if (this == null || this.visibility == View.INVISIBLE) return
    visibility = View.INVISIBLE
}

fun View?.setGone() {
    if (this == null || this.visibility == View.GONE) return
    visibility = View.GONE
}