package com.gekaradchenko.weatherforeveryone.weatherviewpager


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.gekaradchenko.weatherforeveryone.R
import com.gekaradchenko.weatherforeveryone.databinding.FragmentWeatherViewPagerBinding
import com.google.android.material.tabs.TabLayoutMediator


class WeatherViewPagerFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        val binding: FragmentWeatherViewPagerBinding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_weather_view_pager, container, false)

        binding.weatherViewPager2.adapter =
            WeatherViewPagerAdapter(parentFragmentManager, lifecycle)

        TabLayoutMediator(binding.tableLayout, binding.weatherViewPager2) { tab, position ->
            when (position) {
                0 -> tab.setIcon(R.drawable.locations_list_icon)
                1 -> tab.setIcon(R.drawable.today_weather_icon)
                2 -> tab.setIcon(R.drawable.setting_icon)
            }

            binding.weatherViewPager2.currentItem = 1
        }.attach()

        return binding.root
    }

}