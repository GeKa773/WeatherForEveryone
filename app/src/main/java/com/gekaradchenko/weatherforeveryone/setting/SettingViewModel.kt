package com.gekaradchenko.weatherforeveryone.setting

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.navigation.NavDirections
import com.gekaradchenko.weatherforeveryone.R
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

    private val _toastShow = SingleLiveEvent<String>()
    val toastShow: LiveData<String> = _toastShow

    private val _modeNight = MutableLiveData<Boolean>()
    val modeNight: LiveData<Boolean> = _modeNight

    private val _showAlerts = MutableLiveData<Boolean>()
    val showAlerts: LiveData<Boolean> = _showAlerts

    private val _veryHot = MutableLiveData<Boolean>()
    val veryHot: LiveData<Boolean> = _veryHot

    private val _snow = MutableLiveData<Boolean>()
    val snow: LiveData<Boolean> = _snow

    private val _hot = MutableLiveData<Boolean>()
    val hot: LiveData<Boolean> = _hot

    fun onNavigateClick() {
        _navigationEvent.postValue(
            WeatherViewPagerFragmentDirections.actionWeatherViewPagerFragmentToLanguageSettingFragment()
        )
    }

    init {
        getDefaultMode()
    }

    private fun getDefaultMode() {
        coroutineScope.launch {
            _modeNight.value = shared.getDefaultMode()
        }
    }

    fun savaNightModeShared() {
        coroutineScope.launch {
            if (_modeNight.value == true) {
                shared.saveDefaultMode(false)
            } else {
                shared.saveDefaultMode(true)
            }
        }
        _toastShow.postValue(app.getString(R.string.reload_the_app))
    }

    fun isShowOtherSwitch(b: Boolean) {
        _showAlerts.value = b
    }

    fun isEnableVeryHot(b: Boolean) {
        _veryHot.value = b
    }

    fun isEnabledSnow(b: Boolean) {
        _snow.value = b
    }

    fun isEnabledHot(b: Boolean) {
        _hot.value = b
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}