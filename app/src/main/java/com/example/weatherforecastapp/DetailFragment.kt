package com.example.weatherforecastapp

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs

class DetailFragment : Fragment() {

    val args by navArgs<DetailFragmentArgs>()
    var weatherItem: WeatherItem? = null

    @SuppressLint("SetTextI18n")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_detail, container, false)
        val layout = rootView.findViewById(R.id.linear) as ConstraintLayout

        getBackground(layout, args.tabNumber)
//        rootView.detail_text.text = "Detail view, tab number: ${args.tabNumber}"

        weatherItem = args.weatherItem
        Log.i("ArtREQUEST", "RESPONSE:  ${weatherItem!!.date!!}")
//        rootView.detail_text.text = weatherItems!!.get(0).temperature.toString()

//        rootView.detail_extra_info_text.text = args.someExtraInfo

        return rootView
    }

    private fun getBackground(layout: ConstraintLayout, tabNumber: Int) {
        when(tabNumber) {
            1 -> layout.setBackgroundResource(R.drawable.morning_bg)
            2 -> layout.setBackgroundResource(R.drawable.day_bg)
            3 -> layout.setBackgroundResource(R.drawable.evening_bg)
            4 -> layout.setBackgroundResource(R.drawable.night_bg)
        }
    }
}
