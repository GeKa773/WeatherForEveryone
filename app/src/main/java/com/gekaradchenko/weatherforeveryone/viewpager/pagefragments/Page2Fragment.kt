package com.gekaradchenko.weatherforeveryone.viewpager.pagefragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.viewpager2.widget.ViewPager2
import com.gekaradchenko.weatherforeveryone.R
import com.gekaradchenko.weatherforeveryone.databinding.FragmentPage2Binding


class Page2Fragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        val binding: FragmentPage2Binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_page2, container, false)

        val viewPager2 = requireActivity().findViewById<ViewPager2>(R.id.viewPager2)

        binding.page2NextButton.setOnClickListener {
            viewPager2.isUserInputEnabled = false
            viewPager2.currentItem = 2
        }


        return binding.root
    }


}