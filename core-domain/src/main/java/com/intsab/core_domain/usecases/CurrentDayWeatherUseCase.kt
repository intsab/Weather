package com.intsab.core_domain.usecases

import com.intsab.code_data.repo.WhetherRepo
import com.intsab.common.Constants
import com.intsab.common.UseCase
import com.intsab.core_domain.dataholders.CurrentDayWeatherB
import com.intsab.core_domain.dataholders.toUiModel
import com.intsab.core_domain.params.CurrentDayWeatherParams
import javax.inject.Inject

/**
 * Created by intsabhaider
 * on 24,September,2024
 */
class CurrentDayWeatherUseCase @Inject constructor(
    private val repo: WhetherRepo
) : UseCase<CurrentDayWeatherB, CurrentDayWeatherParams>() {
    private val TAG = this::class.java.simpleName

    override suspend fun run(params: CurrentDayWeatherParams?): CurrentDayWeatherB {
        return repo.getCurrentDayWeather(
            Constants.CURRENT_WHETHER_URL.plus("key=").plus(Constants.API_TOKEN).plus("&q=")
                .plus(params?.city ?: "Dubai")
        ).toUiModel()
    }
}

