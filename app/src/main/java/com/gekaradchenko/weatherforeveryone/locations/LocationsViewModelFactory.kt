package com.gekaradchenko.weatherforeveryone.locations

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.gekaradchenko.weatherforeveryone.addlocationmap.AddLocationMapViewModel
import com.gekaradchenko.weatherforeveryone.database.LocationDao
import java.lang.IllegalArgumentException

class LocationsViewModelFactory(
    private val data: LocationDao,
    private val application: Application
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LocationsViewModel::class.java)) {
            return LocationsViewModel(data, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")

    }
}