package com.intsab.common

import com.google.gson.Gson
import com.google.gson.GsonBuilder

/**
 * Created by intsabhaider
 * on 24,September,2024
 */
object GsonExt {
    fun toJson(clz: Any): String {
        val gson = GsonBuilder().create()
        return gson.toJson(clz)
    }

    fun Any.anyToJsonString(): String {
        return Gson().toJsonTree(this).asJsonObject.toString()
    }

    fun <T> fromJson(json: String, clazz: Class<T>): T {
        return Gson().fromJson(json, clazz) as T
    }
}