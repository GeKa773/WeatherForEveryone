package com.gekaradchenko.weatherforeveryone.weekweather

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.navigation.NavDirections
import com.gekaradchenko.weatherforeveryone.UnitSome
import com.gekaradchenko.weatherforeveryone.lifecycle.SingleLiveEvent
import com.gekaradchenko.weatherforeveryone.network.WeatherApi
import com.gekaradchenko.weatherforeveryone.todayweather.APPID_KEY
import com.gekaradchenko.weatherforeveryone.todayweather.EXCLUDE
import com.gekaradchenko.weatherforeveryone.todayweather.LAT
import com.gekaradchenko.weatherforeveryone.todayweather.LON
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch


class WeekWeatherViewModel(application: Application) : AndroidViewModel(application) {

    val app = application
    private val viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)


    private val _navigationEvent = SingleLiveEvent<NavDirections>()
    val navigationEvent: LiveData<NavDirections> = _navigationEvent

    fun onNavigateClick() {
        _navigationEvent.postValue(

            WeekWeatherFragmentDirections.actionWeekWeatherFragmentToWeatherViewPagerFragment()
        )
    }

    private val _list = MutableLiveData<List<WeekWeather>>()
    val list: LiveData<List<WeekWeather>>
        get() = _list

    private val _timeZone = MutableLiveData<String>()
    val timeZone: LiveData<String>
        get() = _timeZone

    private val _location = MutableLiveData<String>()
    val location: LiveData<String>
        get() = _location

    init {

        getWeekWeatherReal()
    }

    private fun getWeekWeatherReal() {
        coroutineScope.launch {

            val getWeatherDeferred = WeatherApi.retrofitService.getWeather(
                LAT, LON,
                EXCLUDE,
                APPID_KEY
            )

            try {
                var result = getWeatherDeferred.await()
                val listWeather = ArrayList<WeekWeather>()

                _timeZone.value = result.timezone
                _location.value = "${result.lat}/${result.lon}"
                for (i in result.hourly.indices) {
                    listWeather.add(WeekWeather(
                        i,
                        result.current.weather.first().id,
                        result.timezone_offset,
                        result.hourly[i].temp,
                        result.hourly[i].humidity,
                        result.hourly[i].wind_speed
                    ))
                }
                _list.value = listWeather


            } catch (t: Throwable) {
                Log.d("TodayWeatherViewModel", "getWeatherReal: ${t.message}")
            }
        }
    }


    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}