package com.intsab.core_domain.dataholders

import com.intsab.code_data.response.FullWeekDaysResponse
import com.intsab.common.CommonHelper.toNullEmpty

/**
 * Created by intsabhaider
 * on 24,September,2024
 */
data class FullWeekWeatherB(
    val date: String,
    val temperature: String,
    val sunRise: String,
    val sunSet: String,
    val condition: String,
    val icon: String,
)

fun FullWeekDaysResponse.toUiModel(): List<FullWeekWeatherB> {
    val list = arrayListOf<FullWeekWeatherB>()
    this.forecast?.forecastday?.forEach {
        list.add(
            FullWeekWeatherB(
                date = it.date.toNullEmpty(),
                temperature = it.day?.avgtempC.toNullEmpty(),
                sunRise = it.astro?.sunrise.toNullEmpty(),
                sunSet = it.astro?.sunset.toNullEmpty(),
                condition = it.day?.condition?.text.toNullEmpty(),
                icon = it.day?.condition?.icon.toNullEmpty()
            )
        )
    }
    return list
}
