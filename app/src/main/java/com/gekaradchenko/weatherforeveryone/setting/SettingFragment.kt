package com.gekaradchenko.weatherforeveryone.setting

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import com.gekaradchenko.weatherforeveryone.MainActivity
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


        binding.nightModeSettingSwitch.setOnCheckedChangeListener { _, isChecked ->
//            viewModel.savaModeShared(isChecked)
//
            //Exception here !!!
//            viewModel.modeSet(isChecked)

        }

        binding.nightModeSettingSwitch.setOnClickListener {
            viewModel.savaTooModeShared()

            //then do it

            startActivity(Intent(requireContext(), MainActivity::class.java))
            requireActivity().finishAffinity()
        }



        binding.settingSwitch1.setOnCheckedChangeListener { _, isChecked ->
            viewModel.setShowAlerts(isChecked)
        }

        binding.settingSwitch2.setOnCheckedChangeListener { _, isChecked ->
            viewModel.setVeryHot(isChecked)
        }

        binding.settingSwitch3.setOnCheckedChangeListener { _, isChecked ->
            viewModel.setSnow(isChecked)
        }

        binding.settingSwitch4.setOnCheckedChangeListener { _, isChecked ->
            viewModel.setHot(isChecked)
        }




        return binding.root
    }

    private fun navigate(navDirections: NavDirections) {
        findNavController().navigate(navDirections)

    }


}