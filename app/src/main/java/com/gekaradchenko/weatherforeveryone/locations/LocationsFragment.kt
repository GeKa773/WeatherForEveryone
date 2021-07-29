package com.gekaradchenko.weatherforeveryone.locations

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.gekaradchenko.weatherforeveryone.R
import com.gekaradchenko.weatherforeveryone.WeatherActivity
import com.gekaradchenko.weatherforeveryone.database.LocationDatabase
import com.gekaradchenko.weatherforeveryone.databinding.FragmentLocationsBinding


private const val PACKAGE = "package"
private const val FRAGMENT_STRING = ""

class LocationsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        val binding: FragmentLocationsBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_locations, container, false)

        val application = requireActivity().application
        val dataSource = LocationDatabase.getInstance(application).locationDao
        val viewModelFactory = LocationsViewModelFactory(dataSource, application)

        val viewModel =
            ViewModelProvider(this, viewModelFactory).get(LocationsViewModel::class.java)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel

        val adapter = LocationsListAdapter(LocationsListener {
            viewModel.saveLocationsToShared(it)
            startActivity(Intent(requireContext(), WeatherActivity::class.java))
        })

        viewModel.navigationEvent.observe(viewLifecycleOwner, ::navigate)
        viewModel.toastShow.observe(viewLifecycleOwner, ::showToast)

        viewModel.permissionBoolean.observe(viewLifecycleOwner, Observer {
            it?.let {
                if (it) {
                    viewModel.onNavigateClick()
                } else {
//                    val intent = Intent()
//                    intent.action = Settings.ACTION_APPLICATION_DETAILS_SETTINGS
//                    intent.data =
//                        Uri.fromParts(PACKAGE, requireActivity().packageName, FRAGMENT_STRING)
//                    startActivity(intent)
                    viewModel.showToastPermissionNotFound()
                }
            }

        })

        binding.locationRecyclerview.adapter = adapter

        viewModel.list.observe(viewLifecycleOwner, Observer {
            adapter.submitList(it)
        })
        viewModel.locations.observe(viewLifecycleOwner, Observer {
            viewModel.getLocationsWeatherReal()
        })

        val itemTouchHelperCallbacks = object :
            ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder,
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                viewModel.list.value?.let {
                    val locationsForecast = it[viewHolder.adapterPosition]
                    val idDelete = locationsForecast.id
                    viewModel.deleteLocationFromBD(idDelete)
                }

            }

        }
        val itemTouchHelper = ItemTouchHelper(itemTouchHelperCallbacks)
        itemTouchHelper.attachToRecyclerView(binding.locationRecyclerview)

        return binding.root
    }

    private fun showToast(massage: String) {
        Toast.makeText(requireContext(), massage, Toast.LENGTH_SHORT).show()
    }

    private fun navigate(navDirections: NavDirections) {
        findNavController().navigate(navDirections)
    }


}