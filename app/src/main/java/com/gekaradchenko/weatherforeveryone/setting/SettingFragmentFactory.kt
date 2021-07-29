package com.gekaradchenko.weatherforeveryone.setting

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.gekaradchenko.weatherforeveryone.locations.LocationsViewModel
import java.lang.IllegalArgumentException

class SettingFragmentFactory(
    private val application: Application,
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SettingViewModel::class.java)) {
            return SettingViewModel(application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")

    }
}