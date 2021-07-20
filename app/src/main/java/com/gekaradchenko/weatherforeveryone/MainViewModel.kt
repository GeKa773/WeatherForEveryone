package com.gekaradchenko.weatherforeveryone

import android.app.Application
import android.content.res.Configuration
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
    val language: LiveData<String>
        get() = _language

    init {
        setLanguage()
    }

    private fun setLanguage() {
        coroutineScope.launch {
                _language.value = shared.getDefaultLanguage()
        }
    }



    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

}