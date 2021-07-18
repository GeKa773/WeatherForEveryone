package com.gekaradchenko.weatherforeveryone.viewpager

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.gekaradchenko.weatherforeveryone.viewpager.pagefragments.Page1Fragment
import com.gekaradchenko.weatherforeveryone.viewpager.pagefragments.Page2Fragment
import com.gekaradchenko.weatherforeveryone.viewpager.pagefragments.Page3Fragment

class ViewPagerAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(fragmentManager, lifecycle) {


    private val fragmentArray: Array<Fragment> = arrayOf(
        Page1Fragment(),
        Page2Fragment(),
        Page3Fragment()
    )

    override fun getItemCount(): Int {

        return fragmentArray.size
    }

    override fun createFragment(position: Int): Fragment {
        return fragmentArray[position]
    }
    
}