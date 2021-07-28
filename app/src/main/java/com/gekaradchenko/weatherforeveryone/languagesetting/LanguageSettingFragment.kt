package com.gekaradchenko.weatherforeveryone.languagesetting

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.gekaradchenko.weatherforeveryone.MainActivity
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

        binding.englishLanguageTextView.setOnClickListener {
            viewModel.saveEng()
            restartApp()
        }
        binding.russianLanguageTextView.setOnClickListener {
            viewModel.saveRus()
            restartApp()
        }

        return binding.root
    }

    private fun restartApp() {
        startActivity(Intent(requireContext(), MainActivity::class.java))
    }

}