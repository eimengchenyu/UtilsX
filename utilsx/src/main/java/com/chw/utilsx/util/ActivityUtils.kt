package com.chw.utilsx.util

import android.app.Activity
import android.content.Intent
import android.os.Bundle

/**
 * @author hanwei.chen
 * 2021/8/26 15:03
 */
inline fun <reified A : Activity> Activity?.launchActivity(
    finishCurrent: Boolean = false,
    outBundle: Bundle? = null
) {
    if (!isContextValid()) return
    this!!.startActivity(Intent(this, A::class.java).apply {
        if (outBundle != null) putExtras(outBundle)
    })
    if (finishCurrent) this.finish()
}

inline fun <reified A : Activity> Activity?.launchActivityForResult(
    finishCurrent: Boolean = false,
    outBundle: Bundle? = null,
    requestCode: Int
) {
    if (!isContextValid()) return
    this!!.startActivityForResult(Intent(this, A::class.java).apply {
        if (outBundle != null) putExtras(outBundle)
    }, requestCode)
    if (finishCurrent) this.finish()
}