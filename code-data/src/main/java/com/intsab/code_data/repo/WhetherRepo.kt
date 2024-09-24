package com.intsab.code_data.repo

import com.intsab.code_data.response.CurrentDayWeatherResponse
import com.intsab.code_data.response.WeatherDetailsByDayResponse
import com.intsab.code_data.response.FullWeekDaysResponse
import com.intsab.code_data.services.WhetherService
import javax.inject.Inject

/**
 * Created by intsabhaider
 * on 24,September,2024
 */
class WhetherRepo @Inject constructor(
    private val services: WhetherService,
) {
    suspend fun getCurrentDayWeather(url: String): CurrentDayWeatherResponse {
        val response = services.getCurrentDayWeather(url)

        return response
    }

    suspend fun getFullWeekWeatherList(url: String): FullWeekDaysResponse {
        val response = services.getFullWeekWeatherList(url)

        return response
    }

    suspend fun getWeatherDetailsByDay(url: String): WeatherDetailsByDayResponse {
        val response = services.getWeatherDetailsByDay(url)

        return response
    }
}