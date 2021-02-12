package com.example.weatherforecastapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout

class MainFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_main, container, false)
        val tab_layout = view.findViewById(R.id.tab_layout) as TabLayout
        val pager = view.findViewById(R.id.pager) as ViewPager

        initTab(tab_layout, pager)

        return view
    }

    private fun initTab(tab_layout: TabLayout, pager: ViewPager) {
        tab_layout.addTab(tab_layout.newTab().setIcon(R.drawable.ic_launcher_background))
        tab_layout.addTab(tab_layout.newTab().setIcon(R.drawable.ic_launcher_background))
        tab_layout.addTab(tab_layout.newTab().setIcon(R.drawable.ic_launcher_background))
        tab_layout.addTab(tab_layout.newTab().setIcon(R.drawable.ic_launcher_background))

        tab_layout.tabGravity = TabLayout.GRAVITY_FILL

        val adapter = MyPagerAdapter(activity!!.supportFragmentManager, tab_layout.tabCount)
        pager.adapter = adapter

        pager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tab_layout))

        tab_layout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                pager.currentItem = tab.position

            }

            override fun onTabUnselected(tab: TabLayout.Tab) {

            }

            override fun onTabReselected(tab: TabLayout.Tab) {

            }
        })

    }

}