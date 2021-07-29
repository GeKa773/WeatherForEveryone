package com.gekaradchenko.weatherforeveryone.viewpager.pagefragments

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.gekaradchenko.weatherforeveryone.R
import com.gekaradchenko.weatherforeveryone.lifecycle.SingleLiveEvent

class PageViewModel(application: Application) : AndroidViewModel(application) {
    val app = application

    private val _navigationEvent = MutableLiveData<Boolean>()
    val navigationEvent: LiveData<Boolean> = _navigationEvent

    private val _fragmentPageEvent = SingleLiveEvent<Boolean>()
    val fragmentPageEvent: LiveData<Boolean> = _fragmentPageEvent

    private val _eventPrivatePolicy = SingleLiveEvent<String>()
    val eventPrivatePolice: LiveData<String> = _eventPrivatePolicy

    private val _eventDialog = SingleLiveEvent<Boolean>()
    val eventDialog: LiveData<Boolean> = _eventDialog

    fun onNavigateEvent() {
        _navigationEvent.value = true
    }

    fun onNavigateEventComplete() {
        _navigationEvent.value = false
    }
    fun onPageEvent() {
        _fragmentPageEvent.value = true
    }

    fun onShowDialog() {
        _eventDialog.postValue(true)
    }

     fun showToastPrivetPolicy() {
        _eventPrivatePolicy.postValue(app.getString(R.string.privacy_policy))
    }

     fun showToastTermOfUsing() {
        _eventPrivatePolicy.postValue(app.getString(R.string.term_of_using))
    }


}
