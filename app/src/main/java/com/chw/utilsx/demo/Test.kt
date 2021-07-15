package com.chw.utilsx.demo

import com.chw.utilsx.util.resources

/**
 * @author hanwei.chen
 * 2021/6/9 17:55
 */
class Test {
    fun text():String{
        return resources.getString(R.string.app_name)
    }
}