package com.intsab.intsabwether.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.intsab.core_domain.usecases.CurrentDayWeatherUseCase
import com.intsab.intsabwether.fragments.dashboard.DashboardViewModel
import javax.inject.Inject
import javax.inject.Provider

/**
 * Created by intsabhaider
 * on 25,September,2024
 */
class DashboardViewModelFactory @Inject constructor(
    private val currentDayWeatherUseCase: CurrentDayWeatherUseCase
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DashboardViewModel::class.java)) {
            return DashboardViewModel(currentDayWeatherUseCase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}