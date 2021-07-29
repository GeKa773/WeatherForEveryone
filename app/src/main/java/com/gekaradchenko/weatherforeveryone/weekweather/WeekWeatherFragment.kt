package com.gekaradchenko.weatherforeveryone.weekweather

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import com.gekaradchenko.weatherforeveryone.R
import com.gekaradchenko.weatherforeveryone.databinding.FragmentTodayWeatherBinding
import com.gekaradchenko.weatherforeveryone.databinding.FragmentWeekWeatherBinding
import com.gekaradchenko.weatherforeveryone.todayweather.TodayWeatherViewModel


class WeekWeatherFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        val binding: FragmentWeekWeatherBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_week_weather, container, false)

        val application = requireNotNull(this.activity).application
        val viewModelFactory = WeekWeatherFactory(application)
        val viewModel =
            ViewModelProvider(this, viewModelFactory).get(WeekWeatherViewModel::class.java)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        viewModel.navigationEvent.observe(viewLifecycleOwner, ::navigate)

        val adapter = WeekWeatherListAdapter(WeekWeatherListener {
            viewModel.onNavigateClick()
        })

        binding.weekRecyclerView.adapter = adapter

        viewModel.list.observe(viewLifecycleOwner, Observer {
            adapter.submitList(it)
        })

        return binding.root
    }

    private fun navigate(navDirections: NavDirections) {
        findNavController().navigate(navDirections)
    }
}