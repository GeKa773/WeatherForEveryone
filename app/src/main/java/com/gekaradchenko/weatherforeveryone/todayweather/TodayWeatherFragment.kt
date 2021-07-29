package com.gekaradchenko.weatherforeveryone.todayweather

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


class TodayWeatherFragment : Fragment() {
    private lateinit var binding: FragmentTodayWeatherBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment

        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_today_weather, container, false)
        val application = requireNotNull(this.activity).application

        val viewModelFactory = TodayWeatherFactory(application)

        val viewModel =
            ViewModelProvider(this, viewModelFactory).get(TodayWeatherViewModel::class.java)

        viewModel.navigationEvent.observe(viewLifecycleOwner, ::navigate)

        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        return binding.root
    }

    private fun navigate(navDirections: NavDirections) {
        findNavController().navigate(navDirections)
    }


}