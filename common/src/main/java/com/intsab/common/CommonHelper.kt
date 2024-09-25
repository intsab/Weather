package com.intsab.common

/**
 * Created by intsabhaider
 * on 25,September,2024
 */
object CommonHelper {
    fun String?.toNullEmpty(): String {
        return this ?: ""
    }
}