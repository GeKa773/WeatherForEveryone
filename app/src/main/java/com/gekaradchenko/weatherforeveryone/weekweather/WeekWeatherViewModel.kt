package com.gekaradchenko.weatherforeveryone.weekweather

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.navigation.NavDirections
import com.gekaradchenko.weatherforeveryone.lifecycle.SingleLiveEvent


class WeekWeatherViewModel(application: Application) : AndroidViewModel(application) {

    private val _navigationEvent = SingleLiveEvent<NavDirections>()
    val navigationEvent: LiveData<NavDirections> = _navigationEvent

    fun onNavigateClick() {
        _navigationEvent.postValue(

            WeekWeatherFragmentDirections.actionWeekWeatherFragmentToWeatherViewPagerFragment()
        )
    }
}