package com.gekaradchenko.weatherforeveryone.locations

import android.Manifest
import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.navigation.NavDirections
import com.gekaradchenko.weatherforeveryone.R
import com.gekaradchenko.weatherforeveryone.database.Location
import com.gekaradchenko.weatherforeveryone.database.LocationDao
import com.gekaradchenko.weatherforeveryone.lifecycle.SingleLiveEvent
import com.gekaradchenko.weatherforeveryone.loadingfragment.LoadingFragmentDirections
import com.gekaradchenko.weatherforeveryone.network.WeatherApi
import com.gekaradchenko.weatherforeveryone.preferences.PreferencesLocations
import com.gekaradchenko.weatherforeveryone.todayweather.APPID_KEY
import com.gekaradchenko.weatherforeveryone.todayweather.EXCLUDE
import com.gekaradchenko.weatherforeveryone.weatherviewpager.WeatherViewPagerFragment
import com.gekaradchenko.weatherforeveryone.weatherviewpager.WeatherViewPagerFragmentDirections
import com.gekaradchenko.weatherforeveryone.weekweather.WeekWeather
import com.karumi.dexter.Dexter
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionDeniedResponse
import com.karumi.dexter.listener.PermissionGrantedResponse
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.single.PermissionListener
import kotlinx.coroutines.*

const val TOMORROW = 24

class LocationsViewModel(data: LocationDao, application: Application) :
    AndroidViewModel(application) {
    val app = application
    val dataBase = data

    private val viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)
    private val shared = PreferencesLocations(app)

    private val _permissionBoolean = MutableLiveData<Boolean>()
    val permissionBoolean: LiveData<Boolean>
        get() = _permissionBoolean

    private val _list = MutableLiveData<List<LocationsForecast>>()
    val list: LiveData<List<LocationsForecast>>
        get() = _list


    private val _toastShow = SingleLiveEvent<String>()
    val toastShow: LiveData<String>
        get() = _toastShow


    val locations = dataBase.getAllLocation()


    fun getLocationsWeatherReal() {
        coroutineScope.launch {
            var locationsArray = arrayListOf<LocationsForecast>()
            locations.value?.let {
                it.forEach {
                    val getWeatherDeferred = WeatherApi.retrofitService.getWeather(
                        it.lat, it.lon,
                        EXCLUDE,
                        APPID_KEY
                    )
                    try {
                        var result = getWeatherDeferred.await()
                        locationsArray.add(LocationsForecast(
                            it.locationId,
                            result.timezone,
                            result.timezone_offset,
                            result.hourly.first().weather.first().id,
                            result.hourly.first().temp,
                            result.hourly.first().humidity,
                            result.hourly[TOMORROW].weather.first().id,
                            result.hourly[TOMORROW].temp,
                            result.hourly[TOMORROW].humidity,
                            result.lat,
                            result.lon
                        ))

                    } catch (t: Throwable) {
                        Log.d("LocationsViewModel", "getLocationsWeatherReal: ${t.message}")
                    }
                }
                _list.value = locationsArray

            }


        }
    }

    fun saveLocationsToShared(id: Long) {
        coroutineScope.launch {
            withContext(Dispatchers.IO) {
                list.value?.let {
                    val loc = it.filter {
                        it.id == id
                    }
                    val lat = loc.first().lat
                    val lon = loc.first().lon
                    shared.saveDefaultLocations(lat, lon)
                }
            }
        }
    }


    fun myCheckPermission() {
        Dexter.withContext(app).withPermission(Manifest.permission.ACCESS_FINE_LOCATION)
            .withListener(object : PermissionListener {
                override fun onPermissionGranted(permissionGrantedResponse: PermissionGrantedResponse) {
                    _permissionBoolean.value = true
                    _toastShow.postValue(app.getString(R.string.toast_permission_granted))
                }

                override fun onPermissionDenied(permissionDeniedResponse: PermissionDeniedResponse) {
                    _permissionBoolean.value = false
                }

                override fun onPermissionRationaleShouldBeShown(
                    permissionRequest: PermissionRequest,
                    permissionToken: PermissionToken,
                ) {
                    permissionToken.continuePermissionRequest()
                }
            }).check()
    }

    private val _navigationEvent = SingleLiveEvent<NavDirections>()
    val navigationEvent: LiveData<NavDirections> = _navigationEvent

    fun onNavigateClick() {
        _navigationEvent.postValue(
            WeatherViewPagerFragmentDirections.actionWeatherViewPagerFragmentToAddLocationMapFragment()
        )
    }


    fun deleteLocationFromBD(id: Long) {
        coroutineScope.launch {

            withContext(Dispatchers.IO) {
                dataBase.deleteLocationById(id)
            }
        }

    }


    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}