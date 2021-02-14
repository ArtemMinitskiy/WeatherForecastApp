package com.example.weatherforecastapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.fragment_tabs.view.*

class ItemTabsFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_tabs, container, false)
//        val tab_layout = rootView.findViewById(R.id.tab_layout) as TabLayout
//        val pager = rootView.findViewById(R.id.viewpager) as ViewPager

//        initTab(tab_layout, pager)

        val adapter = context?.let { it -> TabsPagerAdapter(childFragmentManager, it, 4) }
        rootView.viewpager.adapter = adapter
        rootView.tab_layout.setupWithViewPager(rootView.viewpager)

        for (i in 0 until rootView.tab_layout.tabCount) {
            when (i) {
                0 -> rootView.tab_layout.getTabAt(i)!!.text = "Morning"
                1 -> rootView.tab_layout.getTabAt(i)!!.text = "Day"
                2 -> rootView.tab_layout.getTabAt(i)!!.text = "Evening"
                3 -> rootView.tab_layout.getTabAt(i)!!.text = "Night"
            }
        }

        return rootView
    }

    private fun initTab(tab_layout: TabLayout, pager: ViewPager) {
        tab_layout.addTab(tab_layout.newTab().setIcon(R.drawable.ic_launcher_background))
        tab_layout.addTab(tab_layout.newTab().setIcon(R.drawable.ic_launcher_background))
        tab_layout.addTab(tab_layout.newTab().setIcon(R.drawable.ic_launcher_background))
        tab_layout.addTab(tab_layout.newTab().setIcon(R.drawable.ic_launcher_background))

        tab_layout.tabGravity = TabLayout.GRAVITY_FILL

        val adapter = TabsPagerAdapter(requireActivity().supportFragmentManager, requireActivity(), tab_layout.tabCount)
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
