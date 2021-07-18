package com.gekaradchenko.weatherforeveryone.todayweather

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.navigation.NavDirections
import com.gekaradchenko.weatherforeveryone.lifecycle.SingleLiveEvent
import com.gekaradchenko.weatherforeveryone.loadingfragment.LoadingFragmentDirections
import com.gekaradchenko.weatherforeveryone.weatherviewpager.WeatherViewPagerFragmentDirections

class TodayWeatherViewModel(application: Application) : AndroidViewModel(application) {

    private val _navigationEvent = SingleLiveEvent<NavDirections>()
    val navigationEvent: LiveData<NavDirections> = _navigationEvent

    fun onNavigateClick(){
        _navigationEvent.postValue(
            WeatherViewPagerFragmentDirections.actionWeatherViewPagerFragmentToWeekWeatherFragment()
        )
    }
}