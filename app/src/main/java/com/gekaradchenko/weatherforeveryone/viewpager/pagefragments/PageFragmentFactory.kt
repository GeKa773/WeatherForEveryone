package com.gekaradchenko.weatherforeveryone.viewpager.pagefragments

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class PageFragmentFactory (private val application: Application) :
    ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PageViewModel::class.java)) {
            return PageViewModel(application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}