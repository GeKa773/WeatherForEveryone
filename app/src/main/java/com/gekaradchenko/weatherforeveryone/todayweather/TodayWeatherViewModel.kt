package com.gekaradchenko.weatherforeveryone.todayweather

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.navigation.NavDirections
import com.gekaradchenko.weatherforeveryone.lifecycle.SingleLiveEvent
import com.gekaradchenko.weatherforeveryone.network.WeatherApi
import com.gekaradchenko.weatherforeveryone.preferences.PreferencesLocations

import com.gekaradchenko.weatherforeveryone.weatherviewpager.WeatherViewPagerFragmentDirections
import kotlinx.coroutines.*

const val EXCLUDE = "daily"
const val APPID_KEY = "a479ae562a94cafa121cb9a3c48684bb"


class TodayWeatherViewModel(application: Application) : AndroidViewModel(application) {

    val app = application
    private val viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    private val shared = PreferencesLocations(app)

    private val _navigationEvent = SingleLiveEvent<NavDirections>()
    val navigationEvent: LiveData<NavDirections> = _navigationEvent


    private val _timeZone = MutableLiveData<String>()
    val timeZone: LiveData<String> = _timeZone

    private val _dayToday = MutableLiveData<String>()
    val dayToday: LiveData<String> = _dayToday

    private val _location = MutableLiveData<String>()
    val location: LiveData<String> = _location

    private val _temp = MutableLiveData<Double>()
    val temp: LiveData<Double> = _temp

    private val _iconId = MutableLiveData<Int>()
    val iconId: LiveData<Int> = _iconId

    private val _iconTimeZone = MutableLiveData<Double>()
    val iconTimeZone: LiveData<Double> = _iconTimeZone

    private val _humid = MutableLiveData<Double>()
    val humid: LiveData<Double> = _humid

    private val _wildSpeed = MutableLiveData<Double>()
    val wildSpeed: LiveData<Double> = _wildSpeed

    private val _timeNow = MutableLiveData<String>()
    val timeNow: LiveData<String> = _timeNow


    fun onNavigateClick() {
        _navigationEvent.postValue(
            WeatherViewPagerFragmentDirections.actionWeatherViewPagerFragmentToWeekWeatherFragment()
        )
    }

    init {

        getWeatherReal()
    }

    private fun getWeatherReal() {
        coroutineScope.launch {
            val lat: Double
            val lon: Double

            withContext(Dispatchers.IO) {
                lat = shared.getDefaultLat()
                lon = shared.getDefaultLon()
            }

            val getWeatherDeferred = WeatherApi.retrofitService.getWeather(
                lat, lon,
                EXCLUDE,
                APPID_KEY
            )

            try {
                var result = getWeatherDeferred.await()
                _timeZone.value = result.timezone
                _location.value = "${result.lat}/${result.lon}"
                _temp.value = result.current.temp
                _iconId.value = result.current.weather.first().id
                _iconTimeZone.value = result.timezone_offset
                _humid.value = result.current.humidity
                _wildSpeed.value = result.current.wind_speed
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
