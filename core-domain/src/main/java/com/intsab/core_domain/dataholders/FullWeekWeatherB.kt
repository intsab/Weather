package com.intsab.core_domain.dataholders

import com.intsab.code_data.response.FullWeekDaysResponse

/**
 * Created by intsabhaider
 * on 24,September,2024
 */
data class FullWeekWeatherB(
    val humidity: Int
)

fun FullWeekDaysResponse.toUiModel(): FullWeekWeatherB {
    return FullWeekWeatherB(1)
}
