package com.example.weatherforecastapp

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class TabsPagerAdapter(manager: FragmentManager, private val context: Context, private val numTabs: Int) : FragmentPagerAdapter(manager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    override fun getItem(position: Int): Fragment {
        when (position) {
            0 -> {
                return MasterHostFragment.newInstance(getReadableTabPosition(position))
            }
            1 -> {
                return MasterHostFragment.newInstance(getReadableTabPosition(position))
            }
            2 -> {
                return MasterHostFragment.newInstance(getReadableTabPosition(position))
            }
            else -> {
                return MasterHostFragment.newInstance(getReadableTabPosition(position))
            }
        }

    }

    override fun getCount(): Int {
        return numTabs
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return String.format(context.getString(R.string.cat_tab_item_label), getReadableTabPosition(position))
    }

    private fun getReadableTabPosition(position: Int): Int {
        return position + 1
    }
}
