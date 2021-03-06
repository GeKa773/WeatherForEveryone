package com.gekaradchenko.weatherforeveryone.network

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Deferred
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

private const val BASE_URL = "https://api.openweathermap.org/data/2.5/"
private const val ONECALL = "onecall"
private const val LAT = "lat"
private const val LON = "lon"
private const val EXCLUDE = "exclude"
private const val APPID = "appid"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .addCallAdapterFactory(CoroutineCallAdapterFactory())
    .baseUrl(BASE_URL)
    .build()

interface WeatherApiService {
    @GET(ONECALL)
    fun getWeather(
        @Query(LAT) lat: Double,
        @Query(LON) lon: Double,
        @Query(EXCLUDE) exclude: String,
        @Query(APPID) appid: String
    ):
            Deferred<WeatherModel>
}

object WeatherApi {
    val retrofitService: WeatherApiService by lazy {
        retrofit.create(WeatherApiService::class.java)
    }
}