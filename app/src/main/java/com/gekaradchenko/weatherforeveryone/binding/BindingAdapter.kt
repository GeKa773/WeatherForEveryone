package com.gekaradchenko.weatherforeveryone.binding

import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.Switch
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.gekaradchenko.weatherforeveryone.R
import java.text.SimpleDateFormat
import java.util.*

const val TEMP = 273.15
const val FORMAT = "%.1f"
const val TIME_FORMAT = "HH:mm"

@BindingAdapter("setTemp")
fun TextView.setTemp(temp: Double) {
    val t = temp - TEMP
    val stringTemp = String.format(FORMAT, t)
    text = if (t > 0) "+$stringTemp" else stringTemp
}

@BindingAdapter("setString")
fun TextView.setString(string: String?) {
    string?.let {
        text = "$string"
    }
}

@BindingAdapter("setDataToday")
fun TextView.setDataToday(string: String?) {
    val date = Date()
    val format = SimpleDateFormat("dd.MM.yyyy")
    val dateString = format.format(date)
    val calendar = Calendar.getInstance()
    text = when (calendar.get(Calendar.DAY_OF_WEEK)) {
        Calendar.MONDAY -> "${resources.getString(R.string.monday)},${dateString}"
        Calendar.TUESDAY -> "${resources.getString(R.string.tuesday)},${dateString}"
        Calendar.WEDNESDAY -> "${resources.getString(R.string.wednesday)},${dateString}"
        Calendar.THURSDAY -> "${resources.getString(R.string.thursday)},${dateString}"
        Calendar.FRIDAY -> "${resources.getString(R.string.friday)},${dateString}"
        Calendar.SATURDAY -> "${resources.getString(R.string.saturday)},${dateString}"
        else -> "${resources.getString(R.string.sunday)},${dateString}"
    }
}

@BindingAdapter("setTime")
fun TextView.setTime(string: String?) {
    text = SimpleDateFormat(TIME_FORMAT).format(Date())
}

@BindingAdapter("setHumid")
fun TextView.setHumid(humid: Double) {
    text = "${resources.getString(R.string.humidity)} $humid%"
}

@BindingAdapter("setWindSpeed")
fun TextView.setWindSpeed(windSpeed: Double) {
    text =
        "${resources.getString(R.string.wind_speed)} $windSpeed ${resources.getString(R.string.m_c)}"
}


@BindingAdapter("weatherId", "timeZone", "position", requireAll = false)
fun ImageView.setWeatherImage(id: Int, timeZone: Double, position: Int?) {

    val date = Date()
    val localTime = date.time
    val localOffset = (date.timezoneOffset * 60000).toLong()
    val utc = localTime + localOffset
    val x = (utc + 1000 * timeZone).toLong()
    val timeZoneDate = Date(x)

    val c = Calendar.getInstance()
    c.time = timeZoneDate
    var timeOfDay = c[Calendar.HOUR_OF_DAY]

    position?.let {
        timeOfDay += it
        while (timeOfDay > 24) {
            timeOfDay -= 24
        }
    }

    setImageResource(when (timeOfDay) {

        in 6..20 ->
            when (id) {
                in 0..299 -> R.drawable.rain_with_thunderstorm
                in 300..503 -> R.drawable.rain
                in 504..599 -> R.drawable.rain_with_snow
                in 690..700 -> R.drawable.snow
                in 701..799 -> R.drawable.sun_and_clouds
                800 -> R.drawable.sun
                else -> R.drawable.clounds
            }

        else -> when (id) {
            in 0..299 -> R.drawable.night_rain
            in 300..503 -> R.drawable.night_rain
            in 504..599 -> R.drawable.night_snow
            in 690..700 -> R.drawable.night_snow
            in 701..799 -> R.drawable.night_clouds
            800 -> R.drawable.moon
            else -> R.drawable.night_clouds

        }
    })
}

@BindingAdapter("setHoursPosition")
fun TextView.setHoursPosition(position: Int) {
    text = when (position) {
        0 -> resources.getString(R.string.now)
        1 -> "+1 ${resources.getString(R.string.hour)}"
        else -> "+$position ${resources.getString(R.string.hours)}"
    }
}

@BindingAdapter("forecastTemp", "forecastHumid", "forecastWindSpeed", requireAll = true)
fun TextView.setForecastString(temp: Double, humid: Double, windSpeed: Double) {
    val t = temp - TEMP
    val stringTemp = String.format(FORMAT, t)
    val forecastTemp = if (t > 0) "+$stringTemp" else stringTemp
    text = "$forecastTemp/$humid%/$windSpeed${resources.getString(R.string.m_c)}"
}


@BindingAdapter("locationTempToday", "locationWindSpeedToday", requireAll = true)
fun TextView.setLocationTodayForecastString(temp: Double, windSpeed: Double) {
    val t = temp - TEMP
    val stringTemp = String.format(FORMAT, t)
    val forecastTemp = if (t > 0) "+$stringTemp" else stringTemp
    text = "$forecastTemp/$windSpeed% ${resources.getString(R.string.today)}"
}

@BindingAdapter("locationTempTomorrow", "locationWindSpeedTomorrow", requireAll = true)
fun TextView.setLocationTomorrowForecastString(temp: Double, windSpeed: Double) {
    val t = temp - TEMP
    val stringTemp = String.format(FORMAT, t)
    val forecastTemp = if (t > 0) "+$stringTemp" else stringTemp
    text = "${resources.getString(R.string.tomorrow)} $forecastTemp/$windSpeed%"
}

@BindingAdapter("buttonEnable")
fun Button.setEnable(b: Boolean?) {
    b?.let {
        isEnabled = it
    }
}

@BindingAdapter("app:goneUnless")
fun goneUnless(view: View, visible: Boolean) {
    view.visibility = if (visible) View.VISIBLE else View.GONE
}

@BindingAdapter("app:switchChecked")
fun switchChecked(view: Switch, checked: Boolean) {
    if (!checked) {
        view.isChecked = false
    }
}

@BindingAdapter("app:switchCheckedShowAlert")
fun switchCheckedShowAlert(view: Switch, checked: Boolean) {
    view.isChecked = checked
}




