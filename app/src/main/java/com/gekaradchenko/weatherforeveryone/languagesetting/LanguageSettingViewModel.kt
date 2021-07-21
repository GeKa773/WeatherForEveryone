package com.gekaradchenko.weatherforeveryone.languagesetting

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.gekaradchenko.weatherforeveryone.preferences.ENG
import com.gekaradchenko.weatherforeveryone.preferences.PreferencesSetting
import com.gekaradchenko.weatherforeveryone.preferences.RUS
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class LanguageSettingViewModel(application: Application) : AndroidViewModel(application) {

    val app = application
    private val shared = PreferencesSetting(app)
    private val viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.IO)

    fun saveRus() {
        coroutineScope.launch {
            shared.saveDefaultLanguage(RUS)
        }
    }
    fun saveEng() {
        coroutineScope.launch {
            shared.saveDefaultLanguage(ENG)
        }
    }


    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}