package com.intsab.intsabwether.utils

import org.junit.Test
import org.junit.jupiter.api.Assertions.*

/**
 * Created by intsabhaider
 * on 26,September,2024
 */
class GreetingsHelperTest {

    private val greetingUtil = GreetingsHelper()

    @Test
    fun testGetGreeting_Morning() {
        // Time for 8 AM
        val currentTimeMillis = (8 * 60 * 60 * 1000).toLong() // 8 AM in milliseconds
        assertEquals("Hi There! Good Morning", greetingUtil.getGreeting(currentTimeMillis))
    }

    @Test
    fun testGetGreeting_Evening() {
        // Time for 3 PM
        val currentTimeMillis = (15 * 60 * 60 * 1000).toLong() // 3 PM in milliseconds
        assertEquals("Hello! Good Evening", greetingUtil.getGreeting(currentTimeMillis))
    }

    @Test
    fun testGetGreeting_Night() {
        // Time for 10 PM
        val currentTimeMillis = (22 * 60 * 60 * 1000).toLong() // 10 PM in milliseconds
        assertEquals("Hay Dear! Good Night", greetingUtil.getGreeting(currentTimeMillis))
    }

    @Test
    fun testGetGreeting_BeforeMorning() {
        // Time for 2 AM
        val currentTimeMillis = (2 * 60 * 60 * 1000).toLong() // 2 AM in milliseconds
        assertEquals("Hay Dear! Good Night", greetingUtil.getGreeting(currentTimeMillis))
    }

    @Test
    fun testGetGreeting_AfterEvening() {
        // Time for 7 PM
        val currentTimeMillis = (19 * 60 * 60 * 1000).toLong() // 7 PM in milliseconds
        assertEquals("Hay Dear! Good Night", greetingUtil.getGreeting(currentTimeMillis))
    }
}