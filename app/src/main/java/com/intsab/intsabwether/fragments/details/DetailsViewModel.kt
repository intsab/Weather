package com.intsab.intsabwether.fragments.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.intsab.core_domain.dataholders.WeatherDetailsB
import com.intsab.core_domain.params.WeatherDetailsByDayParams
import com.intsab.core_domain.usecases.WeatherDetailsByDayUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class DetailsViewModel @Inject constructor(
    private val weatherDetailsByDayUseCase: WeatherDetailsByDayUseCase,
) : ViewModel() {

    private val _getWeatherDetailByDayLiveData = MutableLiveData<WeatherDetailsB>()
    val getWeatherDetailByDayLiveData: LiveData<WeatherDetailsB> = _getWeatherDetailByDayLiveData

    fun getWeatherDetailByDay(param: WeatherDetailsByDayParams): DetailsViewModel {
        viewModelScope.launch {
            val result = weatherDetailsByDayUseCase.run(param)
            _getWeatherDetailByDayLiveData.postValue(result)


        }
        return this
    }

}