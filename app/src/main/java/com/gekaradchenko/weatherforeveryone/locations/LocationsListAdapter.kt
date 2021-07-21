package com.gekaradchenko.weatherforeveryone.locations

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.gekaradchenko.weatherforeveryone.databinding.LocationsRecyclerViewItemBinding
import com.gekaradchenko.weatherforeveryone.weekweather.WeekWeather
import com.gekaradchenko.weatherforeveryone.weekweather.WeekWeatherListener

class LocationsListAdapter(private val clickListener: LocationsListener) :
    ListAdapter<LocationsForecast, LocationsListAdapter.LocationsViewHolder>(DiffCallback) {

    class LocationsViewHolder(private var binding: LocationsRecyclerViewItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(locationsForecast: LocationsForecast, clickListener: LocationsListener) {
            binding.locationsForecast = locationsForecast
            binding.clickListener = clickListener
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LocationsViewHolder {

        return LocationsViewHolder(LocationsRecyclerViewItemBinding.inflate(LayoutInflater.from(
            parent.context
        )))
    }

    override fun onBindViewHolder(holder: LocationsViewHolder, position: Int) {
        holder.bind(getItem(position), clickListener)
    }


    private companion object DiffCallback : DiffUtil.ItemCallback<LocationsForecast>() {
        override fun areItemsTheSame(
            oldItem: LocationsForecast,
            newItem: LocationsForecast,
        ): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(
            oldItem: LocationsForecast,
            newItem: LocationsForecast,
        ): Boolean {
            return oldItem.id == newItem.id
        }

    }

}

class LocationsListener(val clickListener: (locationId: Long) -> Unit) {
    fun onClick(locationsForecast: LocationsForecast) = clickListener(locationsForecast.id)

}