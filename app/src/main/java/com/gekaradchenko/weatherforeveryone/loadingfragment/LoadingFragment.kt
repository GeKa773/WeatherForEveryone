package com.gekaradchenko.weatherforeveryone.loadingfragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import com.gekaradchenko.weatherforeveryone.R
import com.gekaradchenko.weatherforeveryone.databinding.FragmentLoadingBinding


class LoadingFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        val binding: FragmentLoadingBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_loading, container, false)

        val viewModelFactory = LoadingFragmentFactory()
        val viewModel = ViewModelProvider(this, viewModelFactory).get(LoadingViewModel::class.java)

        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel

        viewModel.navigationEvent.observe(viewLifecycleOwner, ::navigate)

        return binding.root
    }

    private fun navigate(navDirections: NavDirections) {
        findNavController().navigate(navDirections)
    }

}