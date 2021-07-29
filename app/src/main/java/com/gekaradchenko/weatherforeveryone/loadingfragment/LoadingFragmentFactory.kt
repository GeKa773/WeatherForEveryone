package com.gekaradchenko.weatherforeveryone.loadingfragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class LoadingFragmentFactory() :
    ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LoadingViewModel::class.java)) {
            return LoadingViewModel() as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
