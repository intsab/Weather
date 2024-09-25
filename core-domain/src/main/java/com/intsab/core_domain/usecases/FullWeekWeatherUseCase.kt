package com.intsab.core_domain.usecases

import com.intsab.code_data.repo.WhetherRepo
import com.intsab.common.Constants
import com.intsab.common.UseCase
import com.intsab.core_domain.dataholders.FullWeekWeatherB
import com.intsab.core_domain.dataholders.toUiModel
import com.intsab.core_domain.params.WeeklyListWeatherParams
import javax.inject.Inject

/**
 * Created by intsabhaider
 * on 24,September,2024
 */
class FullWeekWeatherUseCase @Inject constructor(
    private val repo: WhetherRepo
) : UseCase<List<FullWeekWeatherB>, WeeklyListWeatherParams>() {
    private val TAG = this::class.java.simpleName

    override suspend fun run(params: WeeklyListWeatherParams?): List<FullWeekWeatherB> {
        return repo.getFullWeekWeatherList(
            Constants.WHETHER_FORECAST_URL.plus("key=").plus(Constants.API_TOKEN).plus("&q=")
                .plus(params?.city ?: "Dubai").plus("&days=7")
        ).toUiModel()
    }
}