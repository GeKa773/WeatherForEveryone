package com.gekaradchenko.weatherforeveryone.viewpager

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.gekaradchenko.weatherforeveryone.R
import com.gekaradchenko.weatherforeveryone.databinding.FragmentViewPagerBinding


class ViewPagerFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        val binding: FragmentViewPagerBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_view_pager, container, false)

        val adapter = ViewPagerAdapter(parentFragmentManager, lifecycle)

        binding.viewPager2.adapter = adapter

        return binding.root
    }
}