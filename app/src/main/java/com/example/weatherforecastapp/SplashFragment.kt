package com.example.weatherforecastapp

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.example.weatherforecastapp.RetrofitAPI.RetrofitProvider
import java.util.*

class SplashFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        RetrofitProvider.getWeatherDetail()
        Handler().postDelayed({
            findNavController().navigate(R.id.action_splashFragment_to_tabs_fragment, null, NavOptions.Builder().setPopUpTo(R.id.splashFragment, true).build())
        }, 3000)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_splash, container, false)
        return view
    }

/*    private fun setBackground(splashFrame: FrameLayout) {
        val rightNow: Calendar = Calendar.getInstance()
        val currentHourIn24Format = rightNow.get(Calendar.HOUR_OF_DAY)
        when (currentHourIn24Format) {
            in 0..2 -> return splashFrame.setBackgroundResource(R.drawable.night_bg)

            in 3..5 -> return splashFrame.setBackgroundResource(R.drawable.night_morning_gradient_gb)

            in 6..8 -> return splashFrame.setBackgroundResource(R.drawable.morning_bg)

            in 9..11 -> return splashFrame.setBackgroundResource(R.drawable.morning_day_gradient_bg)

            in 12..14 -> return splashFrame.setBackgroundResource(R.drawable.day_bg)

            in 15..17 -> return splashFrame.setBackgroundResource(R.drawable.day_evening_gradient_bg)

            in 18..20 -> return splashFrame.setBackgroundResource(R.drawable.evening_bg)

            in 21..23 -> return splashFrame.setBackgroundResource(R.drawable.evening_night_gradient_bg)

        }

    }*/

    override fun onResume() {
        super.onResume()
        (activity as AppCompatActivity?)!!.supportActionBar!!.hide()
    }

    override fun onStop() {
        super.onStop()
        (activity as AppCompatActivity?)!!.supportActionBar!!.show()
    }

}