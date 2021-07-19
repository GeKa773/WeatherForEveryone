package com.gekaradchenko.weatherforeveryone.languagesetting

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.gekaradchenko.weatherforeveryone.R
import com.gekaradchenko.weatherforeveryone.databinding.FragmentLanguageSettingBinding


class LanguageSettingFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        val binding: FragmentLanguageSettingBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_language_setting, container, false)

        val viewModel = ViewModelProvider(this).get(LanguageSettingViewModel::class.java)
        binding.lifecycleOwner = viewLifecycleOwner


        return binding.root
    }


}