package com.gekaradchenko.weatherforeveryone.weatherviewpager

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.gekaradchenko.weatherforeveryone.locations.LocationsFragment
import com.gekaradchenko.weatherforeveryone.setting.SettingFragment
import com.gekaradchenko.weatherforeveryone.todayweather.TodayWeatherFragment
import com.gekaradchenko.weatherforeveryone.viewpager.pagefragments.Page1Fragment
import com.gekaradchenko.weatherforeveryone.viewpager.pagefragments.Page2Fragment
import com.gekaradchenko.weatherforeveryone.viewpager.pagefragments.Page3Fragment

class WeatherViewPagerAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(fragmentManager, lifecycle) {
    private val fragmentArray: Array<Fragment> = arrayOf(
        LocationsFragment(),
        TodayWeatherFragment(),
        SettingFragment()
    )


    override fun getItemCount(): Int {
        return fragmentArray.size

    }

    override fun createFragment(position: Int): Fragment {
        return fragmentArray[position]
    }
}