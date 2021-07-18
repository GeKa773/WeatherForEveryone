package com.gekaradchenko.weatherforeveryone.viewpager.pagefragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.gekaradchenko.weatherforeveryone.MainActivity
import com.gekaradchenko.weatherforeveryone.R
import com.gekaradchenko.weatherforeveryone.WeatherActivity
import com.gekaradchenko.weatherforeveryone.databinding.FragmentPage3Binding


class Page3Fragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        val binding: FragmentPage3Binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_page3, container, false)

        val viewModel = ViewModelProvider(this).get(PageViewModel::class.java)

        viewModel.navigationEvent.observe(viewLifecycleOwner, Observer {
            if (it) {
                startActivity(Intent(requireContext(),WeatherActivity::class.java))
                viewModel.onNavigateEventComplete()
            }
        })
        binding.page3NextButton.setOnClickListener {
            viewModel.onNavigateEvent()
        }



        return binding.root
    }


}