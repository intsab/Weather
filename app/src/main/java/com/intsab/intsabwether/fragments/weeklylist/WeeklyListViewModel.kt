package com.intsab.intsabwether.fragments.weeklylist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.intsab.core_domain.dataholders.FullWeekWeatherB
import com.intsab.core_domain.params.WeeklyListWeatherParams
import com.intsab.core_domain.usecases.FullWeekWeatherUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class WeeklyListViewModel @Inject constructor(
    private val fullWeekWeatherUseCase: FullWeekWeatherUseCase,
) : ViewModel() {

    private val _getFullWeekWeatherLiveData = MutableLiveData<List<FullWeekWeatherB>>()
    val getFullWeekWeatherLiveData: LiveData<List<FullWeekWeatherB>> = _getFullWeekWeatherLiveData

    fun getFullWeekList(param: WeeklyListWeatherParams): WeeklyListViewModel {
        viewModelScope.launch {
            val result = fullWeekWeatherUseCase.run(param)
            _getFullWeekWeatherLiveData.postValue(result)

        }
        return this
    }

}