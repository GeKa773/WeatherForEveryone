package com.gekaradchenko.weatherforeveryone.viewpager.pagefragments

import android.app.Application
import android.app.Dialog
import android.content.Intent
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.navigation.NavDirections
import com.gekaradchenko.weatherforeveryone.MainActivity
import com.gekaradchenko.weatherforeveryone.R
import com.gekaradchenko.weatherforeveryone.lifecycle.SingleLiveEvent

class PageViewModel(application: Application) : AndroidViewModel(application) {
    val app = application

    private val _navigationEvent = MutableLiveData<Boolean>()
    val navigationEvent: LiveData<Boolean> = _navigationEvent

    fun onNavigateEvent() {
        _navigationEvent.value = true
    }

    fun onNavigateEventComplete() {
        _navigationEvent.value = false
    }


}
