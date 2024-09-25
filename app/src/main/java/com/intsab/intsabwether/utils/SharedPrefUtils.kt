package com.intsab.intsabwether.utils

import android.content.Context
import android.content.SharedPreferences

/**
 * Created by intsabhaider
 * on 25,September,2024
 */
object SharedPrefUtils {
    private val prefName = "MyPrefs"
    private val cityPrefKey = "city_name"
    fun saveCityToPreferences(context: Context, value: String) {
        val sharedPreferences: SharedPreferences =
            context.getSharedPreferences(prefName, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString(cityPrefKey, value)
        editor.apply()
    }

    fun getCityFromPreferences(context: Context): String {
        val sharedPreferences: SharedPreferences =
            context.getSharedPreferences(prefName, Context.MODE_PRIVATE)
        return sharedPreferences.getString(cityPrefKey, "Dubai") ?: "Dubai"
    }
}