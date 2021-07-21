package com.gekaradchenko.weatherforeveryone.preferences

import android.content.Context
import android.content.SharedPreferences

private const val PREFERENCE_LOCATION = "default_locations"
private const val PREFERENCE_LAT = "default_lat"
private const val PREFERENCE_LON = "default_lon"

private const val KIEV_LAT = 50.4547f
private const val KIEV_LON = 30.5238f

class PreferencesLocations(context: Context) {
    private val sharedLocations: SharedPreferences =
        context.getSharedPreferences(PREFERENCE_LOCATION, Context.MODE_PRIVATE)
    private val editor: SharedPreferences.Editor =
        context.getSharedPreferences(PREFERENCE_LOCATION, Context.MODE_PRIVATE).edit()


    suspend fun getDefaultLat(): Double =
        sharedLocations.getFloat(PREFERENCE_LAT, KIEV_LAT).toDouble()


    suspend fun getDefaultLon(): Double =
        sharedLocations.getFloat(PREFERENCE_LON, KIEV_LON).toDouble()

    suspend fun saveDefaultLat(lat: Double) {
        val floatLat = lat.toFloat()
        editor.putFloat(PREFERENCE_LAT, floatLat)
        editor.apply()
    }

    suspend fun saveDefaultLon(lon: Double) {
        val floatLon = lon.toFloat()
        editor.putFloat(PREFERENCE_LON, floatLon)
        editor.apply()
    }

    suspend fun saveDefaultLocations(lat: Double, lon: Double) {
        val floatLat = lat.toFloat()
        val floatLon = lon.toFloat()
        editor.putFloat(PREFERENCE_LAT, floatLat)
        editor.putFloat(PREFERENCE_LON, floatLon)
        editor.apply()
    }
}