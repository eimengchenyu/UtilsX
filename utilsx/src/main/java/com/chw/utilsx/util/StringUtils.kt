package com.chw.utilsx.util

/**
 * @author hanwei.chen
 * 2021/5/7 12:19
 */
fun String?.available(): Boolean = !this.isNullOrEmpty() && "null" != this