package com.intsab.intsabwether.utils

/**
 * Created by intsabhaider
 * on 25,September,2024
 */
class GreetingsHelper {
    fun getGreeting(currentTimeMillis: Long): String {
        val hours = java.util.Calendar.getInstance().apply {
            timeInMillis = currentTimeMillis
        }.get(java.util.Calendar.HOUR_OF_DAY)

        return when {
            hours in 5..11 -> "Hi There! Good Morning"
            hours in 12..17 -> "Hello! Good Evening"
            else -> "Hay Dear! Good Night"
        }
    }
}