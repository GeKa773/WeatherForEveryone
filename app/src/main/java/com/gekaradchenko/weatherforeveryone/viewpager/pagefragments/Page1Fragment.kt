package com.gekaradchenko.weatherforeveryone.viewpager.pagefragments

import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.gekaradchenko.weatherforeveryone.R
import com.gekaradchenko.weatherforeveryone.databinding.CustomDialogBinding
import com.gekaradchenko.weatherforeveryone.databinding.FragmentPage1Binding
import com.gekaradchenko.weatherforeveryone.databinding.FragmentViewPagerBinding


class Page1Fragment : Fragment() {
    private lateinit var viewModel: PageViewModel
    private lateinit var binding: FragmentPage1Binding
    private lateinit var bindingViewPage: FragmentViewPagerBinding

    private lateinit var customDialogBinding: CustomDialogBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_page1, container, false)

        bindingViewPage =
            DataBindingUtil.inflate(inflater, R.layout.fragment_view_pager, container, false)


        val application = requireNotNull(this.activity).application

        val viewModelFactory = PageFragmentFactory(application)
        viewModel = ViewModelProvider(this, viewModelFactory).get(PageViewModel::class.java)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
        bindingViewPage.lifecycleOwner = viewLifecycleOwner


        val viewPager2 = requireActivity().findViewById<ViewPager2>(R.id.viewPager2)

        viewModel.fragmentPageEvent.observe(viewLifecycleOwner) {
//            bindingViewPage.viewPager2.isUserInputEnabled = false
//            bindingViewPage.viewPager2.currentItem = 1
            viewPager2.isUserInputEnabled = false
            viewPager2.currentItem = 1
        }


        viewModel.eventDialog.observe(viewLifecycleOwner, ::onShowDialog)
        viewModel.eventPrivatePolice.observe(viewLifecycleOwner, ::showToast)


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