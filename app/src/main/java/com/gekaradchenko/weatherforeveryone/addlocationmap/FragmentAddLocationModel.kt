package com.gekaradchenko.weatherforeveryone.addlocationmap

import android.os.Parcelable
import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.gekaradchenko.weatherforeveryone.BR
import kotlinx.android.parcel.Parcelize


@Parcelize
data class FragmentAddLocationModel(
    private var _city: String = "",
) : BaseObservable(), Parcelable {

    @get:Bindable
    var city: String = _city
        set(value) {
            _city = value
            field = value
            notifyPropertyChanged(BR.city)
        }
}