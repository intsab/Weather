package com.intsab.core_domain.dataholders

import com.intsab.code_data.response.WeatherDetailsByDayResponse

/**
 * Created by intsabhaider
 * on 24,September,2024
 */
data class WeatherDetailsB(
    val humidity: Int
)
fun WeatherDetailsByDayResponse.toUiModel(): WeatherDetailsB {
    return WeatherDetailsB(1)
}
