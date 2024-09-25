package com.intsab.core_domain.dataholders

import com.intsab.code_data.response.CurrentDayWeatherResponse
import com.intsab.common.CommonHelper.toNullEmpty

/**
 * Created by intsabhaider
 * on 24,September,2024
 */

data class CurrentDayWeatherB(
    val isSuccess: Boolean,
    val cloud: String,
    val feelslikeC: String,
    val feelslikeF: String,
    val humidity: String,
    val isDay: String,
    val lastUpdated: String,
    val tempC: String,
    val tempF: String,
    val windKph: String,
    val windMph: String,
    val country: String,
    val lat: String,
    val localtime: String,
    val lon: String,
    val locationName: String,
    val region: String,
    val heatIndex: String,
    val weatherIcon: String,
    val shortDescription: String
)

fun CurrentDayWeatherResponse.toUiModel(): CurrentDayWeatherB {
    return CurrentDayWeatherB(
        isSuccess = this.current != null,
        cloud = this.current?.cloud.toNullEmpty().plus("%"),
        feelslikeC = this.current?.feelslikeC.toNullEmpty()+ " \u2103",
        feelslikeF = this.current?.feelslikeF.toNullEmpty()+ " \u2109",
        humidity = this.current?.humidity.toNullEmpty().plus("%"),
        isDay = this.current?.isDay.toNullEmpty(),
        lastUpdated = this.current?.lastUpdated.toNullEmpty(),
        tempC = this.current?.tempC.toNullEmpty() + " \u2103",
        tempF = this.current?.tempF.toNullEmpty() + " \u2109",
        windKph = this.current?.windKph.toNullEmpty().plus(" KPH"),
        windMph = this.current?.windMph.toNullEmpty(),
        country = this.location?.country.toNullEmpty(),
        lat = this.location?.lat.toNullEmpty(),
        localtime = this.location?.localtime.toNullEmpty(),
        lon = this.location?.lon.toNullEmpty(),
        locationName = this.location?.name.toNullEmpty(),
        region = this.location?.region.toNullEmpty(),
        heatIndex = this.current?.heatindexC + " \u2103",
        weatherIcon = "https:" + this.current?.condition?.icon.toNullEmpty(),
        shortDescription = this.current?.condition?.text.toNullEmpty()
    )
}

