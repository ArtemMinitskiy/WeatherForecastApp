package com.example.weatherforecastapp

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.fragment.app.FragmentStatePagerAdapter

class MyPagerAdapter(fm: FragmentManager, internal var mNumOfTabs: Int) : FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    override fun getItem(position: Int): Fragment {

        when (position) {
            0 -> {
                return ForecastFragment()
            }
            1 -> {
                return ForecastFragment()
            }
            2 -> {
                return ForecastFragment()
            }
            else -> {
                return ForecastFragment()
            }
        }
    }

    override fun getCount(): Int {
        return mNumOfTabs
    }
}
