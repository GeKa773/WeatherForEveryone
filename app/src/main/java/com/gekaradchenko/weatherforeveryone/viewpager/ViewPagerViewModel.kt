package com.gekaradchenko.weatherforeveryone.viewpager

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ViewPagerViewModel() : ViewModel() {


    private val _moveNext = MutableLiveData<Int>()
    val moveNext: LiveData<Int>
        get() = _moveNext

    fun onMoveNext(page: Int) {
        _moveNext.value = page
    }

    fun onMoveNextCompleted() {
        _moveNext.value = 0
    }


    private val _enabled = MutableLiveData<Boolean?>()
    val enabled: MutableLiveData<Boolean?>
        get() = _enabled

    fun setEnabledFalse() {
        _enabled.value = false
    }

    fun endEnabled() {
        _enabled.value = null
    }


}