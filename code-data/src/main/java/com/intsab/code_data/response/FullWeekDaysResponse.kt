package com.intsab.code_data.response
import com.google.gson.annotations.SerializedName


/**
 * Created by intsabhaider
 * on 24,September,2024
 */

data class FullWeekDaysResponse(
    @SerializedName("current")
    val current: Current?,
    @SerializedName("forecast")
    val forecast: Forecast?,
    @SerializedName("location")
    val location: Location?,
    @SerializedName("isSuccess")
    val isSuccess: Boolean = true
)

data class Forecast(
    @SerializedName("forecastday")
    val forecastday: List<Forecastday>
)

data class AirQuality(
    @SerializedName("co")
    val co: String?,
    @SerializedName("gb-defra-index")
    val gbDefraIndex: String?,
    @SerializedName("no2")
    val no2: String?,
    @SerializedName("o3")
    val o3: String?,
    @SerializedName("pm10")
    val pm10: String?,
    @SerializedName("pm2_5")
    val pm25: String?,
    @SerializedName("so2")
    val so2: String?,
    @SerializedName("us-epa-index")
    val usEpaIndex: String?
)


data class Forecastday(
    @SerializedName("astro")
    val astro: Astro?,
    @SerializedName("date")
    val date: String?,
    @SerializedName("date_epoch")
    val dateEpoch: String?,
    @SerializedName("day")
    val day: Day?,
    @SerializedName("hour")
    val hour: List<Hour>?
)

data class Astro(
    @SerializedName("is_moon_up")
    val isMoonUp: String?,
    @SerializedName("is_sun_up")
    val isSunUp: String?,
    @SerializedName("moon_illumination")
    val moonIllumination: String?,
    @SerializedName("moon_phase")
    val moonPhase: String,
    @SerializedName("moonrise")
    val moonrise: String,
    @SerializedName("moonset")
    val moonset: String,
    @SerializedName("sunrise")
    val sunrise: String,
    @SerializedName("sunset")
    val sunset: String
)

data class Day(
    @SerializedName("air_quality")
    val airQuality: AirQualityX,
    @SerializedName("avghumidity")
    val avghumidity: String?,
    @SerializedName("avgtemp_c")
    val avgtempC: String?,
    @SerializedName("avgtemp_f")
    val avgtempF: String?,
    @SerializedName("avgvis_km")
    val avgvisKm: String?,
    @SerializedName("avgvis_miles")
    val avgvisMiles: String?,
    @SerializedName("condition")
    val condition: Condition,
    @SerializedName("daily_chance_of_rain")
    val dailyChanceOfRain: String?,
    @SerializedName("daily_chance_of_snow")
    val dailyChanceOfSnow: String?,
    @SerializedName("daily_will_it_rain")
    val dailyWillItRain: String?,
    @SerializedName("daily_will_it_snow")
    val dailyWillItSnow: String?,
    @SerializedName("maxtemp_c")
    val maxtempC: String?,
    @SerializedName("maxtemp_f")
    val maxtempF: String?,
    @SerializedName("maxwind_kph")
    val maxwindKph: String?,
    @SerializedName("maxwind_mph")
    val maxwindMph: String?,
    @SerializedName("mintemp_c")
    val mintempC: String?,
    @SerializedName("mintemp_f")
    val mintempF: String?,
    @SerializedName("totalprecip_in")
    val totalprecipIn: String?,
    @SerializedName("totalprecip_mm")
    val totalprecipMm: String?,
    @SerializedName("totalsnow_cm")
    val totalsnowCm: String?,
    @SerializedName("uv")
    val uv: String?
)

data class Hour(
    @SerializedName("air_quality")
    val airQuality: AirQuality,
    @SerializedName("chance_of_rain")
    val chanceOfRain: String?,
    @SerializedName("chance_of_snow")
    val chanceOfSnow: String?,
    @SerializedName("cloud")
    val cloud: String?,
    @SerializedName("condition")
    val condition: Condition,
    @SerializedName("dewpoint_c")
    val dewpointC: String?,
    @SerializedName("dewpoint_f")
    val dewpointF: String?,
    @SerializedName("feelslike_c")
    val feelslikeC: String?,
    @SerializedName("feelslike_f")
    val feelslikeF: String?,
    @SerializedName("gust_kph")
    val gustKph: String?,
    @SerializedName("gust_mph")
    val gustMph: String?,
    @SerializedName("heatindex_c")
    val heatindexC: String?,
    @SerializedName("heatindex_f")
    val heatindexF: String?,
    @SerializedName("humidity")
    val humidity: String?,
    @SerializedName("is_day")
    val isDay: String?,
    @SerializedName("precip_in")
    val precipIn: String?,
    @SerializedName("precip_mm")
    val precipMm: String?,
    @SerializedName("pressure_in")
    val pressureIn: String?,
    @SerializedName("pressure_mb")
    val pressureMb: String?,
    @SerializedName("snow_cm")
    val snowCm: String?,
    @SerializedName("temp_c")
    val tempC: String?,
    @SerializedName("temp_f")
    val tempF: String?,
    @SerializedName("time")
    val time: String,
    @SerializedName("time_epoch")
    val timeEpoch: String?,
    @SerializedName("uv")
    val uv: String?,
    @SerializedName("vis_km")
    val visKm: String?,
    @SerializedName("vis_miles")
    val visMiles: String?,
    @SerializedName("will_it_rain")
    val willItRain: String?,
    @SerializedName("will_it_snow")
    val willItSnow: String?,
    @SerializedName("wind_degree")
    val windDegree: String?,
    @SerializedName("wind_dir")
    val windDir: String,
    @SerializedName("wind_kph")
    val windKph: String?,
    @SerializedName("wind_mph")
    val windMph: String?,
    @SerializedName("windchill_c")
    val windchillC: String?,
    @SerializedName("windchill_f")
    val windchillF: String?
)

data class AirQualityX(
    @SerializedName("aqi_data")
    val aqiData: String,
    @SerializedName("co")
    val co: String?,
    @SerializedName("gb-defra-index")
    val gbDefraIndex: String?,
    @SerializedName("no2")
    val no2: String?,
    @SerializedName("o3")
    val o3: String?,
    @SerializedName("pm10")
    val pm10: String?,
    @SerializedName("pm2_5")
    val pm25: String?,
    @SerializedName("so2")
    val so2: String?,
    @SerializedName("us-epa-index")
    val usEpaIndex: String?
)