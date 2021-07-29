package com.gekaradchenko.weatherforeveryone.weekweather

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.gekaradchenko.weatherforeveryone.todayweather.TodayWeatherViewModel

class WeekWeatherFactory (private val application: Application) :
    ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(WeekWeatherViewModel::class.java)) {
            return WeekWeatherViewModel(application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}