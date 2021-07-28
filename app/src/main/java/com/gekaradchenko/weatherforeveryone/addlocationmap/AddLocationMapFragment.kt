package com.gekaradchenko.weatherforeveryone.addlocationmap

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
import com.gekaradchenko.weatherforeveryone.database.LocationDatabase
import com.gekaradchenko.weatherforeveryone.databinding.FragmentAddLocationMapBinding
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.material.snackbar.Snackbar


class AddLocationMapFragment : Fragment(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        val binding: FragmentAddLocationMapBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_add_location_map, container, false)

        binding.lifecycleOwner = viewLifecycleOwner
        val application = requireNotNull(this.activity).application
        val dataSource = LocationDatabase.getInstance(application).locationDao

        val viewModelFactory = AddLocationMapViewModelFactory(dataSource, application)

        val viewModel =
            ViewModelProvider(this, viewModelFactory).get(AddLocationMapViewModel::class.java)

        binding.viewModel = viewModel

        val mapFragment =
            childFragmentManager.findFragmentById(R.id.containerMap) as SupportMapFragment
        mapFragment.getMapAsync(this)

        viewModel.navigationEvent.observe(viewLifecycleOwner, ::navigate)

        viewModel.addLocationEvent.observe(viewLifecycleOwner) {
            viewModel.getLatLng()
            showSnackBar(viewModel.addLocationEvent.value.toString())
        }

        return binding.root
    }


    private fun navigate(navDirections: NavDirections) {
        findNavController().navigate(navDirections)

    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

    }

    private fun showSnackBar(message: String) {
        Snackbar.make(view ?: return, "Your city is : $message", Snackbar.LENGTH_SHORT)
            .show()
    }


}