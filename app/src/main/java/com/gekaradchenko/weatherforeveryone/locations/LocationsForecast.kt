package com.gekaradchenko.weatherforeveryone.locations

class LocationsForecast(
    var id: Long,
    var locationCity: String,
    var timeZone: Double,
    var weatherIdToday: Int,
    var tempToday: Double,
    var windSpeedToday: Double,
    var weatherIdTomorrow: Int,
    var tempTomorrow: Double,
    var windSpeedTomorrow: Double,
    var lat: Double,
    var lon: Double,
)