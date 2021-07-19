package com.gekaradchenko.weatherforeveryone.setting

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
import com.gekaradchenko.weatherforeveryone.databinding.FragmentSettingBinding


class SettingFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment

        val binding: FragmentSettingBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_setting, container, false)

        val viewModel = ViewModelProvider(this).get(SettingViewModel::class.java)
        binding.lifecycleOwner = viewLifecycleOwner

        viewModel.navigationEvent.observe(viewLifecycleOwner, ::navigate)

        binding.languageSettingTextView.setOnClickListener {
            viewModel.onNavigateClick()
        }
        binding.viewModel = viewModel

        binding.settingSwitch1.setOnCheckedChangeListener { buttonView, isChecked ->
            viewModel.setShowAlerts(isChecked)
        }

        binding.settingSwitch2.setOnCheckedChangeListener { buttonView, isChecked ->
            viewModel.setVeryHot(isChecked)
        }

        binding.settingSwitch3.setOnCheckedChangeListener { buttonView, isChecked ->
            viewModel.setSnow(isChecked)
        }

        binding.settingSwitch4.setOnCheckedChangeListener { buttonView, isChecked ->
            viewModel.setHot(isChecked)
        }




        return binding.root
    }

    private fun navigate(navDirections: NavDirections) {
        findNavController().navigate(navDirections)

    }


}