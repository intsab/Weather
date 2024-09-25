package com.intsab.core_domain.dataholders

import com.intsab.code_data.response.FullWeekDaysResponse

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
    this.forecast.forecastday.forEach {
        list.add(
            FullWeekWeatherB(
                date = it.date,
                temperature = it.day.avgtempC.toString(),
                sunRise = it.astro.sunrise,
                sunSet = it.astro.sunset,
                condition = it.day.condition.text,
                icon = it.day.condition.icon
            )
        )
    }
    return list
}
