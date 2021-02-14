package com.example.weatherforecastapp

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import kotlinx.android.synthetic.main.fragment_detail.view.*

class DetailFragment : Fragment() {

    val args by navArgs<DetailFragmentArgs>()

    @SuppressLint("SetTextI18n")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_detail, container, false)
        val layout = rootView.findViewById(R.id.linear) as LinearLayout

        getBackground(layout, args.tabNumber)

        rootView.detail_text.text = "Detail view, tab number: ${args.tabNumber}"
        rootView.detail_extra_info_text.text = args.someExtraInfo
        return rootView
    }

    private fun getBackground(layout: LinearLayout, tabNumber: Int) {
        when(tabNumber) {
            1 -> layout.setBackgroundResource(R.drawable.morning_bg)
            2 -> layout.setBackgroundResource(R.drawable.day_bg)
            3 -> layout.setBackgroundResource(R.drawable.evening_bg)
            4 -> layout.setBackgroundResource(R.drawable.night_bg)
        }
    }
}
