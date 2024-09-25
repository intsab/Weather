package com.intsab.intsabwether.fragments.weeklylist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.intsab.core_domain.dataholders.FullWeekWeatherB
import com.intsab.core_domain.params.WeeklyListWeatherParams
import com.intsab.core_domain.usecases.FullWeekWeatherUseCase
import com.intsab.intsabwether.base.BaseViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

class WeeklyListViewModel @Inject constructor(
    private val fullWeekWeatherUseCase: FullWeekWeatherUseCase,
) : BaseViewModel() {

    private val _getFullWeekWeatherLiveData = MutableLiveData<List<FullWeekWeatherB>>()
    val getFullWeekWeatherLiveData: LiveData<List<FullWeekWeatherB>> = _getFullWeekWeatherLiveData

    fun getFullWeekList(param: WeeklyListWeatherParams): WeeklyListViewModel {
        viewModelScope.launch {
            val result = fullWeekWeatherUseCase.run(param)
            if (result.isNotEmpty()) _getFullWeekWeatherLiveData.postValue(result)
            else {
                _error.postValue("Something Went Wrong")
            }
        }
        return this
    }

}