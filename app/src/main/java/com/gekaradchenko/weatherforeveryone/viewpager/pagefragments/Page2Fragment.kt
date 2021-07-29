package com.gekaradchenko.weatherforeveryone.viewpager.pagefragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.gekaradchenko.weatherforeveryone.R
import com.gekaradchenko.weatherforeveryone.databinding.FragmentPage2Binding
import com.gekaradchenko.weatherforeveryone.databinding.FragmentViewPagerBinding


class Page2Fragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        val binding: FragmentPage2Binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_page2, container, false)

        val bindingViewPager: FragmentViewPagerBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_view_pager, container, false)

        val application = requireNotNull(this.activity).application
        val viewModelFactory = PageFragmentFactory(application)
        val viewModel: PageViewModel =
            ViewModelProvider(this, viewModelFactory).get(PageViewModel::class.java)

        bindingViewPager.lifecycleOwner = this.requireActivity()
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel

        val viewPager2 = requireActivity().findViewById<ViewPager2>(R.id.viewPager2)

        viewModel.fragmentPageEvent.observe(viewLifecycleOwner) {
//            bindingViewPager.viewPager2.isUserInputEnabled = false
//            bindingViewPager.viewPager2.currentItem = 2

            viewPager2.isUserInputEnabled = false
            viewPager2.currentItem = 2
        }

        return binding.root
    }
}