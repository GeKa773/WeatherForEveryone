package com.gekaradchenko.weatherforeveryone.setting

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.navigation.NavDirections
import com.gekaradchenko.weatherforeveryone.lifecycle.SingleLiveEvent
import com.gekaradchenko.weatherforeveryone.preferences.PreferencesSetting
import com.gekaradchenko.weatherforeveryone.weatherviewpager.WeatherViewPagerFragmentDirections
import kotlinx.coroutines.*

class SettingViewModel(application: Application) : AndroidViewModel(application) {

    val app = application
    private val viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)
    private val shared = PreferencesSetting(app)


    private val _navigationEvent = SingleLiveEvent<NavDirections>()
    val navigationEvent: LiveData<NavDirections> = _navigationEvent


    fun onNavigateClick() {
        _navigationEvent.postValue(
            WeatherViewPagerFragmentDirections.actionWeatherViewPagerFragmentToLanguageSettingFragment()
        )
    }

    private val _modeNight = MutableLiveData<Boolean>()
    val modeNight: LiveData<Boolean>
        get() = _modeNight

    init {
        getDefaultMode()
    }

    private fun getDefaultMode() {
        coroutineScope.launch {
            _modeNight.value = shared.getDefaultMode()
        }
    }

    fun savaModeShared(mode: Boolean) {
        coroutineScope.launch {
            shared.saveDefaultMode(mode)
        }

    }


    fun savaTooModeShared() {
        coroutineScope.launch {

            if (_modeNight.value == true) {
                shared.saveDefaultMode(false)
            } else {
                shared.saveDefaultMode(true)
            }
        }


    }

    //Exception here !!!
    fun modeSet(mode: Boolean) {
        if (mode) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)


        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }
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


    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}