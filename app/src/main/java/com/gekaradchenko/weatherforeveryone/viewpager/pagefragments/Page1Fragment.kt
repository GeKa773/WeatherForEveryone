package com.gekaradchenko.weatherforeveryone.viewpager.pagefragments

import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.gekaradchenko.weatherforeveryone.R
import com.gekaradchenko.weatherforeveryone.databinding.FragmentPage1Binding


class Page1Fragment : Fragment() {
    lateinit var viewModel: PageViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        val binding: FragmentPage1Binding =
            DataBindingUtil.inflate(LayoutInflater.from(requireContext()),
                R.layout.fragment_page1,
                container,
                false)

        viewModel = ViewModelProvider(this).get(PageViewModel::class.java)
        binding.lifecycleOwner = viewLifecycleOwner


        val viewPager2 = requireActivity().findViewById<ViewPager2>(R.id.viewPager2)

        binding.page1NextButton.setOnClickListener {
            viewPager2.isUserInputEnabled = false
            viewPager2.currentItem = 1
        }


//        val bindingDialog: CustomDialogBinding =
//            DataBindingUtil.inflate(inflater, R.layout.custom_dialog, container, false)
//        bindingDialog.lifecycleOwner = viewLifecycleOwner
//        bindingDialog.viewModel = viewModel


        binding.page1AndroidTextView.setOnClickListener {
//            viewModel.showCustomDialog(requireContext())
            viewModel.onShowDialog()
        }


        viewModel.dialogShow.observe(viewLifecycleOwner, ::onShowDialog)
        viewModel.toastShow.observe(viewLifecycleOwner, ::showToast)


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