package com.intsab.core_domain.dataholders

import com.intsab.code_data.response.CurrentDayWeatherResponse

/**
 * Created by intsabhaider
 * on 24,September,2024
 */
data class CurrentDayWeatherB(
    val humidity: Int
)

 fun CurrentDayWeatherResponse.toUiModel(): CurrentDayWeatherB {
    return CurrentDayWeatherB(1)
}

