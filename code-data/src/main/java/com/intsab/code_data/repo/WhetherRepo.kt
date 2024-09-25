package com.intsab.code_data.repo

import com.intsab.code_data.response.CurrentDayWeatherResponse
import com.intsab.code_data.response.FullWeekDaysResponse
import com.intsab.code_data.services.WhetherService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 * Created by intsabhaider
 * on 24,September,2024
 */
class WhetherRepo @Inject constructor(
    private val services: WhetherService,
) {
    suspend fun getCurrentDayWeather(url: String): CurrentDayWeatherResponse =
        withContext(Dispatchers.IO) {
            try {
                services.getCurrentDayWeather(url)
            } catch (exp: Exception) {
                CurrentDayWeatherResponse(null, null, false)
            }
        }

    suspend fun getFullWeekWeatherList(url: String): FullWeekDaysResponse =
        withContext(Dispatchers.IO) {
            try {
                services.getFullWeekWeatherList(url)
            } catch (exp: Exception) {
                FullWeekDaysResponse(null, null, null, false)
            }
        }

}