package com.intsab.core_domain.usecases

import com.intsab.code_data.repo.WhetherRepo
import com.intsab.common.UseCase
import com.intsab.core_domain.dataholders.WeatherDetailsB
import com.intsab.core_domain.dataholders.toUiModel
import com.intsab.core_domain.params.WeatherDetailsByDayParams
import javax.inject.Inject

/**
 * Created by intsabhaider
 * on 24,September,2024
 */
class WeatherDetailsByDayUseCase @Inject constructor(
    private val repo: WhetherRepo
) : UseCase<WeatherDetailsB, WeatherDetailsByDayParams>() {
    private val TAG = this::class.java.simpleName

    override suspend fun run(params: WeatherDetailsByDayParams?): WeatherDetailsB {
        return repo.getWeatherDetailsByDay("").toUiModel()
    }
}