package com.example.weatherforecastapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button

class ForecastFragment() : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_forecast, container, false)
        val btn = view.findViewById(R.id.btn) as Button

        val bundle = Bundle().apply {
            putString("Hell", "Hell")
        }

        btn.setOnClickListener {
            navController?.navigate(R.id.detailForecastFragment, bundle)
        }

        return view
    }

}