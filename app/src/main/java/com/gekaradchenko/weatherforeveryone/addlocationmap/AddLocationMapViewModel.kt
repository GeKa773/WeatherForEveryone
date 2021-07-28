package com.gekaradchenko.weatherforeveryone.addlocationmap

import android.app.Application
import android.location.Address
import android.location.Geocoder
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.navigation.NavDirections
import com.gekaradchenko.weatherforeveryone.database.Location
import com.gekaradchenko.weatherforeveryone.database.LocationDao
import com.gekaradchenko.weatherforeveryone.lifecycle.SingleLiveEvent
import com.gekaradchenko.weatherforeveryone.weatherviewpager.WeatherViewPagerFragmentDirections
import kotlinx.coroutines.*
import java.util.*

class AddLocationMapViewModel(val data: LocationDao, application: Application) :
    AndroidViewModel(application) {
    val app = application
    private val viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.IO)

    val model = FragmentAddLocationModel()

    private val _navigationEvent = SingleLiveEvent<NavDirections>()
    val navigationEvent: LiveData<NavDirections> = _navigationEvent
    private val _addLocationEvent = SingleLiveEvent<String>()
    val addLocationEvent: LiveData<String> = _addLocationEvent

    private fun onNavigateClick() {
        _navigationEvent.postValue(
            AddLocationMapFragmentDirections.actionAddLocationMapFragmentToWeatherViewPagerFragment()
        )
    }

    fun onClickButtonEvent() {
        _addLocationEvent.value = model.city
    }

    fun getLatLng() {

        addLocationEvent.value?.let {
            coroutineScope.launch {
                try {
                    val addressList = getAddressList(it)
                    insertDatabase(addressList.first().latitude, addressList.first().longitude)
                    onNavigateClick()
                } catch (t: Throwable) {
                    Log.d("AddLocationMapViewModel", "getLatLng problem: ${t.message}")
                }
            }
        }
    }

    private suspend fun getAddressList(city: String): List<Address> {
        return withContext(Dispatchers.IO) {
            Geocoder(app.applicationContext, Locale.getDefault()).getFromLocationName(city, 1)
        }
    }

    private suspend fun insertDatabase(lat: Double, lon: Double) {
        withContext(Dispatchers.IO) {
            data.insertLocation(Location(lat = lat, lon = lon))
        }
    }


    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}