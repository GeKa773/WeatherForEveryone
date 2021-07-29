package com.gekaradchenko.weatherforeveryone.setting

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
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
        val application = requireActivity().application

        val viewModelFactory = SettingFragmentFactory(application)

        val viewModel = ViewModelProvider(this, viewModelFactory).get(SettingViewModel::class.java)

        viewModel.navigationEvent.observe(viewLifecycleOwner, ::navigate)

        binding.languageSettingTextView.setOnClickListener {
            viewModel.onNavigateClick()
        }
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel

        viewModel.toastShow.observe(viewLifecycleOwner, ::showToast)

        return binding.root
    }

    private fun navigate(navDirections: NavDirections) {
        findNavController().navigate(navDirections)
    }

    private fun showToast(massage: String) {
        Toast.makeText(requireContext(), massage, Toast.LENGTH_SHORT).show()
    }


}