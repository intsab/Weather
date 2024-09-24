package com.intsab.code_data.services

import com.intsab.code_data.response.CurrentDayWeatherResponse
import com.intsab.code_data.response.FullWeekDaysResponse
import com.intsab.code_data.response.WeatherDetailsByDayResponse
import retrofit2.http.GET
import retrofit2.http.Url

/**
 * Created by intsabhaider
 * on 24,September,2024
 */
interface WhetherService {
    @GET
    suspend fun getCurrentDayWeather(
        @Url url: String
    ): CurrentDayWeatherResponse

    @GET
    suspend fun getWeatherDetailsByDay(
        @Url url: String
    ): WeatherDetailsByDayResponse

    @GET
    suspend fun getFullWeekWeatherList(
        @Url url: String
    ): FullWeekDaysResponse
}