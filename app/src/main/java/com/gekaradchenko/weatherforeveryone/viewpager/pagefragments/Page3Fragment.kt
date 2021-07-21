package com.gekaradchenko.weatherforeveryone.viewpager.pagefragments

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.gekaradchenko.weatherforeveryone.MainActivity
import com.gekaradchenko.weatherforeveryone.R
import com.gekaradchenko.weatherforeveryone.WeatherActivity
import com.gekaradchenko.weatherforeveryone.databinding.FragmentPage3Binding


class Page3Fragment : Fragment() {
    lateinit var viewModel: PageViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        val binding: FragmentPage3Binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_page3, container, false)

        viewModel = ViewModelProvider(this).get(PageViewModel::class.java)

        viewModel.navigationEvent.observe(viewLifecycleOwner, Observer {
            if (it) {
                startActivity(Intent(requireContext(), WeatherActivity::class.java))
                viewModel.onNavigateEventComplete()
            }
        })
        binding.page3NextButton.setOnClickListener {
            viewModel.onNavigateEvent()
        }
        binding.page3AndroidTextView.setOnClickListener {
//            viewModel.showCustomDialog(requireContext())
            viewModel.onShowDialog()
        }

        viewModel.toastShow.observe(viewLifecycleOwner, ::showToast)
        viewModel.dialogShow.observe(viewLifecycleOwner, ::onShowDialog)





        return binding.root
    }


    // ???
    private fun onShowDialog(b: Boolean) {
        val dialog = Dialog(requireContext())
        dialog.setContentView(R.layout.custom_dialog)
        dialog.window!!.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        dialog.setCancelable(true)
        val dialogPrivateButton = dialog.findViewById<Button>(R.id.dialogPrivateButton)
        val dialogTermButton = dialog.findViewById<Button>(R.id.dialogTermButton)
        dialogPrivateButton.setOnClickListener {
            viewModel.showToastPrivetPolicy()
            dialog.cancel()
        }

        dialogTermButton.setOnClickListener {
            viewModel.showToastTermOfUsing()
            dialog.cancel()
        }

        dialog.show()
    }

    private fun showToast(s: String) {
        Toast.makeText(requireContext(), s, Toast.LENGTH_SHORT).show()
    }


}