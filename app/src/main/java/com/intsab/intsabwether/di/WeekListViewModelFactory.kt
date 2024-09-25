package com.intsab.intsabwether.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.intsab.core_domain.usecases.FullWeekWeatherUseCase
import com.intsab.intsabwether.fragments.dashboard.DashboardViewModel
import com.intsab.intsabwether.fragments.weeklylist.WeeklyListViewModel
import javax.inject.Inject

/**
 * Created by intsabhaider
 * on 25,September,2024
 */
class WeekListViewModelFactory @Inject constructor(
    private val weekDaysWeatherUseCase: FullWeekWeatherUseCase
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(WeeklyListViewModel::class.java)) {
            return WeeklyListViewModel(weekDaysWeatherUseCase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}