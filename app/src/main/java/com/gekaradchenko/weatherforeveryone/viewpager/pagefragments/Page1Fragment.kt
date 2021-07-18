package com.gekaradchenko.weatherforeveryone.viewpager.pagefragments

import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.gekaradchenko.weatherforeveryone.R
import com.gekaradchenko.weatherforeveryone.databinding.FragmentPage1Binding
import com.gekaradchenko.weatherforeveryone.viewpager.ViewPagerViewModel


class Page1Fragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        val binding: FragmentPage1Binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_page1, container, false)

        val viewModel = ViewModelProvider(this).get(PageViewModel::class.java)



        return binding.root
    }

    private fun showDialog(dialog: Dialog?) {
        dialog?.let {
            it.show()
        }
    }


}