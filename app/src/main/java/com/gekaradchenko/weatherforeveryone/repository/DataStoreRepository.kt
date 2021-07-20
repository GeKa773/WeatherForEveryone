package com.gekaradchenko.weatherforeveryone.repository
//
//
//import android.content.Context
//import androidx.datastore.core.DataStore
//import androidx.datastore.dataStore
//import androidx.datastore.preferences.core.Preferences
//import androidx.datastore.preferences.core.doublePreferencesKey
//import androidx.datastore.preferences.core.edit
//import androidx.datastore.preferences.preferencesDataStore
//import kotlinx.coroutines.flow.Flow
//import kotlinx.coroutines.flow.first
//import kotlinx.coroutines.flow.map
//
//
//private const val PREFERENCE_LOCATION = "default_locations"
//private const val PREFERENCE_LAT = "default_lat"
//private const val PREFERENCE_LON = "default_lon"
//
//private const val KIEV_LAT = 50.4547
//private const val KIEV_LON = 30.5238
//
//class DataStoreRepository(context: Context) {
//    val context = context
//
//
//    val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = PREFERENCE_LOCATION)
//
//    private val defaultLat = doublePreferencesKey(PREFERENCE_LAT)
//    private val defaultLon = doublePreferencesKey(PREFERENCE_LON)
//
//    val getDefaultLat: Flow<Double> = context.dataStore.data
//        .map {
//            it[defaultLat] ?: KIEV_LAT
//        }
//
//    suspend fun readLat(): Double {
//        val pref = context.dataStore.data.first()
//        return pref[defaultLat] ?: KIEV_LAT
//    }
//
//    val getDefaultLon: Flow<Double> = context.dataStore.data
//        .map {
//            it[defaultLon] ?: KIEV_LON
//        }
//
//    suspend fun readLon(): Double {
//        val pref = context.dataStore.data.first()
//        return pref[defaultLon] ?: KIEV_LON
//    }
//
//    suspend fun saveDefaultLat(lat: Double) {
//        context.dataStore.edit {
//            it[defaultLat] = lat
//        }
//    }
//
//    suspend fun saveDefaultLon(lon: Double) {
//        context.dataStore.edit {
//            it[defaultLon] = lon
//        }
//    }
//
//
//}
//
