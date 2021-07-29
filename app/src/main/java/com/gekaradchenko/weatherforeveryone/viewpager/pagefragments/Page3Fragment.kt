package com.gekaradchenko.weatherforeveryone.viewpager.pagefragments

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.gekaradchenko.weatherforeveryone.R
import com.gekaradchenko.weatherforeveryone.WeatherActivity
import com.gekaradchenko.weatherforeveryone.databinding.CustomDialogBinding
import com.gekaradchenko.weatherforeveryone.databinding.FragmentPage3Binding


class Page3Fragment : Fragment() {
    private lateinit var viewModel: PageViewModel
    private lateinit var binding: FragmentPage3Binding
    private lateinit var customDialogBinding: CustomDialogBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_page3, container, false)

        val application = requireNotNull(this.activity).application
        val viewModelFactory = PageFragmentFactory(application)

        viewModel = ViewModelProvider(this, viewModelFactory).get(PageViewModel::class.java)

        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel

        viewModel.navigationEvent.observe(viewLifecycleOwner) {
            if (it) {
                startActivity(Intent(requireContext(), WeatherActivity::class.java))
                viewModel.onNavigateEventComplete()
            }
        }

        viewModel.eventPrivatePolice.observe(viewLifecycleOwner, ::showToast)
        viewModel.eventDialog.observe(viewLifecycleOwner, ::onShowDialog)

        return binding.root
    }

    private fun onShowDialog(b: Boolean) {

        customDialogBinding =
            DataBindingUtil.inflate(layoutInflater, R.layout.custom_dialog, null, false)
        customDialogBinding.lifecycleOwner = viewLifecycleOwner
        customDialogBinding.viewModel = viewModel
        val dialog = Dialog(requireContext())
        dialog.setContentView(customDialogBinding.root)
        dialog.window!!.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        dialog.setCancelable(true)
        customDialogBinding.dialogPrivateButton.setOnClickListener {
            viewModel.showToastPrivetPolicy()
            dialog.cancel()
        }

        customDialogBinding.dialogTermButton.setOnClickListener {
            viewModel.showToastTermOfUsing()
            dialog.cancel()
        }

        dialog.show()
    }

    private fun showToast(s: String) {
        Toast.makeText(requireContext(), s, Toast.LENGTH_SHORT).show()
    }


}