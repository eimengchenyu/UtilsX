package com.chw.utilsx.util

import android.graphics.Rect
import android.view.TouchDelegate
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

/**
 * 扩大View的点击区域
 */
fun View?.expandTouchArea(size: Int) {
    this?.let { view ->
        (view.parent as? View)?.let { parent ->
            parent.post {
                parent.touchDelegate = TouchDelegate(Rect().apply {
                    view.isEnabled = true
                    view.getHitRect(this)
                    top -= size
                    bottom += size
                    left -= size
                    right += size
                }, view)
            }
        }
    }
}