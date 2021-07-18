package com.gekaradchenko.weatherforeveryone.loadingfragment


import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavDirections
import com.gekaradchenko.weatherforeveryone.lifecycle.SingleLiveEvent

class LoadingViewModel:ViewModel() {

    private val _navigationEvent = SingleLiveEvent<NavDirections>()
    val navigationEvent: LiveData<NavDirections> = _navigationEvent

    fun onNavigateClick(){
        _navigationEvent.postValue(
            LoadingFragmentDirections.actionLoadingFragment2ToViewPagerFragment()
        )
    }

}