package com.gekaradchenko.weatherforeveryone.setting

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavDirections
import com.gekaradchenko.weatherforeveryone.lifecycle.SingleLiveEvent
import com.gekaradchenko.weatherforeveryone.loadingfragment.LoadingFragmentDirections
import com.gekaradchenko.weatherforeveryone.weatherviewpager.WeatherViewPagerFragmentDirections

class SettingViewModel(application: Application) : AndroidViewModel(application) {
    private val _navigationEvent = SingleLiveEvent<NavDirections>()
    val navigationEvent: LiveData<NavDirections> = _navigationEvent

    fun onNavigateClick() {
        _navigationEvent.postValue(
            WeatherViewPagerFragmentDirections.actionWeatherViewPagerFragmentToLanguageSettingFragment()
        )
    }


    private val _showAlerts = MutableLiveData<Boolean>()
    val showAlerts: LiveData<Boolean>
        get() = _showAlerts

    fun setShowAlerts(b: Boolean) {
        _showAlerts.value = b
    }

    private val _veryHot = MutableLiveData<Boolean>()
    val veryHot: LiveData<Boolean>
        get() = _veryHot

    fun setVeryHot(b: Boolean) {
        _veryHot.value = b
    }

    private val _snow = MutableLiveData<Boolean>()
    val snow: LiveData<Boolean>
        get() = _snow

    fun setSnow(b: Boolean) {
        _snow.value = b
    }

    private val _hot = MutableLiveData<Boolean>()
    val hot: LiveData<Boolean>
        get() = _hot

    fun setHot(b: Boolean) {
        _hot.value = b
    }

}