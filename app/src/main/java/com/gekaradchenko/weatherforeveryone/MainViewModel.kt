package com.gekaradchenko.weatherforeveryone

import android.app.Application
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gekaradchenko.weatherforeveryone.preferences.PreferencesSetting
import kotlinx.coroutines.*
import java.util.*

class MainViewModel(application: Application) : AndroidViewModel(application) {
    val app = application
    private val shared = PreferencesSetting(app)
    private val viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    private val _language = MutableLiveData<String>()
    val language: LiveData<String> = _language

    private val _modeNight = MutableLiveData<Boolean>()
    val modeNight: LiveData<Boolean> = _modeNight

    init {
        setSetting()
    }

    private fun setSetting() {
        coroutineScope.launch {
            _language.value = shared.getDefaultLanguage()
            _modeNight.value = shared.getDefaultMode()
        }
    }

    fun modeSet(mode: Boolean) {
        if (mode) AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        else AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

    }


    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

}