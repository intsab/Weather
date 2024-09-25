package com.intsab.core_domain.dataholders

import com.intsab.code_data.response.CurrentDayWeatherResponse

/**
 * Created by intsabhaider
 * on 24,September,2024
 */

data class CurrentDayWeatherB(
    val cloud: Int,
    val feelslikeC: Double,
    val feelslikeF: Double,
    val humidity: Int,
    val isDay: Int,
    val lastUpdated: String,
    val tempC: Double,
    val tempF: Double,
    val windKph: Double,
    val windMph: Double,
    val country: String,
    val lat: Double,
    val localtime: String,
    val lon: Double,
    val name: String,
    val region: String,
    val code: Int,
    val icon: String,
    val text: String
)

fun CurrentDayWeatherResponse.toUiModel(): CurrentDayWeatherB {
    return CurrentDayWeatherB(
        cloud = this.current.cloud,
        feelslikeC = this.current.feelslikeC,
        feelslikeF = this.current.feelslikeF,
        humidity = this.current.humidity,
        isDay = this.current.isDay,
        lastUpdated = this.current.lastUpdated,
        tempC = this.current.tempC,
        tempF = this.current.tempF,
        windKph = this.current.windKph,
        windMph = this.current.windMph,
        country = this.location.country,
        lat = this.location.lat,
        localtime = this.location.localtime,
        lon = this.location.lon,
        name = this.location.name,
        region = this.location.region,
        code = this.current.condition.code,
        icon = this.current.condition.icon,
        text = this.current.condition.text
    )
}