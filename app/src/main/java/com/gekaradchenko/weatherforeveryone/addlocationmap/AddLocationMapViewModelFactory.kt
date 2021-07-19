package com.gekaradchenko.weatherforeveryone.addlocationmap

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.gekaradchenko.weatherforeveryone.database.LocationDao
import java.lang.IllegalArgumentException

class AddLocationMapViewModelFactory(
private val data:LocationDao,
private val application: Application
) :ViewModelProvider.Factory{

    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AddLocationMapViewModel::class.java)) {
            return AddLocationMapViewModel(data, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}