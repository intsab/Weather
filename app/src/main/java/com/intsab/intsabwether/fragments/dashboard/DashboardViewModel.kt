package com.intsab.intsabwether.fragments.dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.intsab.core_domain.dataholders.CurrentDayWeatherB
import com.intsab.core_domain.params.CurrentDayWeatherParams
import com.intsab.core_domain.usecases.CurrentDayWeatherUseCase
import com.intsab.intsabwether.base.BaseViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

class DashboardViewModel @Inject constructor(
    private val currentDayWeatherUseCase: CurrentDayWeatherUseCase,
) : BaseViewModel() {

    private val _getCurrentWeatherLiveData = MutableLiveData<CurrentDayWeatherB>()
    val getCurrentWeatherLiveData: LiveData<CurrentDayWeatherB> = _getCurrentWeatherLiveData

    fun getCurrentWeather(param: CurrentDayWeatherParams): DashboardViewModel {

        viewModelScope.launch {
            val result = currentDayWeatherUseCase.run(param)
            if (result.isSuccess)
                _getCurrentWeatherLiveData.postValue(result)
            else
                _error.postValue("Something Went Wrong")
        }

        return this
    }

}