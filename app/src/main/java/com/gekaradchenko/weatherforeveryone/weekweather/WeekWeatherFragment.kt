package com.gekaradchenko.weatherforeveryone.weekweather

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import com.gekaradchenko.weatherforeveryone.R
import com.gekaradchenko.weatherforeveryone.databinding.FragmentTodayWeatherBinding
import com.gekaradchenko.weatherforeveryone.databinding.FragmentWeekWeatherBinding
import com.gekaradchenko.weatherforeveryone.todayweather.TodayWeatherViewModel


class WeekWeatherFragment : Fragment() {
    private val viewModel: WeekWeatherViewModel by lazy {
        ViewModelProvider(this).get(WeekWeatherViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        val binding: FragmentWeekWeatherBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_week_weather, container, false)

        viewModel.navigationEvent.observe(viewLifecycleOwner, ::navigate)
        binding.weekWeatherFragment.setOnClickListener {
            viewModel.onNavigateClick()
        }



        return binding.root
    }

    private fun navigate(navDirections: NavDirections) {
        findNavController().navigate(navDirections)
    }


}