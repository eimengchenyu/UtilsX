package com.chw.utilsx.util

import android.os.SystemClock

/**
 * 函数执行状态工具类
 * @author hanwei.chen
 * 2021/6/8 19:19
 */
class FunctionProcessUtils {
    private var processFinishTime: Long = 0

    /**
     * 是否处于执行任务状态，若不是，则设接下来[minTime]ms 为执行任务状态
     *
     * @param minTime 任务执行时长
     * @return 是否处于执行任务状态
     */
    fun isProcessing(minTime: Long): Boolean {
        val newTime = newEffecttiveTime(minTime, processFinishTime)
        return if (newTime == processFinishTime) {
            true
        } else {
            processFinishTime = newTime
            false
        }
    }

    /**
     * 以上次任务完成时间戳为基准，计算下次任务执行到的时间戳，若上次时间戳未到，则直接返回上次时间戳
     *
     * @param minTime  任务执行时长
     * @param lastTime 上次完成时间戳，以包括休眠时间的系统运行时长计时
     * @return 下次任务执行到的时间戳
     */
    private fun newEffecttiveTime(minTime: Long, lastTime: Long): Long {
        val time = SystemClock.elapsedRealtime()
        return if (time >= lastTime) time + minTime else lastTime
    }
}