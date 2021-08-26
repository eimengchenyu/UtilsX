package com.chw.utilsx.widget

import android.content.Context
import android.util.AttributeSet
import android.widget.VideoView

/**
 * @author hanwei.chen
 * 2021/8/26 15:32
 */
class FullScreenVideoView(context: Context?, attrs: AttributeSet?) : VideoView(context, attrs) {
    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        val width = getDefaultSize(0, widthMeasureSpec)
        val height = getDefaultSize(0, heightMeasureSpec)
        setMeasuredDimension(width, height)
    }
}