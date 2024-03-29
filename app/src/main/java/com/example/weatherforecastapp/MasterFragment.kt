package com.example.weatherforecastapp

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherforecastapp.Adapter.WeatherRecyclerAdapter
import com.example.weatherforecastapp.Model.ModelWeather
import java.util.*
import kotlin.collections.ArrayList

class MasterFragment : Fragment() {
    lateinit var recyclerAdapter: WeatherRecyclerAdapter
    val args by navArgs<MasterFragmentArgs>()
    var recyclerView: RecyclerView? = null
    var city_text: TextView? = null
    var constraintLayout: ConstraintLayout? = null
    val calendar = Calendar.getInstance()

    @SuppressLint("SetTextI18n")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_master, container, false)
        recyclerView = rootView.findViewById(R.id.recyclerview) as RecyclerView
        constraintLayout = rootView.findViewById(R.id.constraint) as ConstraintLayout
        city_text = rootView.findViewById(R.id.city_text) as TextView
        setBackground(constraintLayout!!)
        initRecyclerView(PreferencesProvider.getWeatherModel())

        return rootView
    }

    private fun initRecyclerView(modelWeather: ModelWeather) {
        city_text!!.text = modelWeather.city!!.city
        val weatherItems = ArrayList<WeatherItem>()
        for (i in 0 until 8) {
            weatherItems.add(WeatherItem(modelWeather.city.city,
                    Utils().FormatTemp(modelWeather.listWeather!!.get(i).main!!.temp.toString()),
                    modelWeather.listWeather.get(i).weather!!.get(0).description!!.capitalize(),
                    modelWeather.listWeather.get(i).weather!!.get(0).icon,
                    modelWeather.listWeather.get(i).date,
                    modelWeather.listWeather.get(i).wind!!.speed + "ms",
                    modelWeather.listWeather.get(i).clouds!!.clouds + "%",
                    Utils().FormatTemp(modelWeather.listWeather.get(i).main!!.feels_like.toString()),
                    modelWeather.listWeather.get(i).main!!.humidity + "%",
                    modelWeather.city.listCoordinates!!.latitude,
                    modelWeather.city.listCoordinates.longitude))
        }
        recyclerAdapter = WeatherRecyclerAdapter(weatherItems, args.tabNumber)

        recyclerView!!.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(requireActivity().applicationContext, LinearLayoutManager.VERTICAL, false)
            adapter = recyclerAdapter
        }

    }

    private fun setBackground(constraint: ConstraintLayout) {
        val hour = calendar.get(Calendar.HOUR_OF_DAY)
        val tabNumber = args.tabNumber
        when (hour) {
            in 0..2 -> {
                when (tabNumber) {
                    1 -> constraint.setBackgroundResource(R.drawable.night_bg)
                    2 -> constraint.setBackgroundResource(R.drawable.morning_bg)
                    3 -> constraint.setBackgroundResource(R.drawable.day_bg)
                    4 -> constraint.setBackgroundResource(R.drawable.evening_bg)
                }
            }
            in 3..5 -> {
                when (tabNumber) {
                    1 -> constraint.setBackgroundResource(R.drawable.night_morning_gradient_gb)
                    2 -> constraint.setBackgroundResource(R.drawable.morning_day_gradient_bg)
                    3 -> constraint.setBackgroundResource(R.drawable.day_evening_gradient_bg)
                    4 -> constraint.setBackgroundResource(R.drawable.evening_night_gradient_bg)
                }
            }
            in 6..8 -> {
                when (tabNumber) {
                    1 -> constraint.setBackgroundResource(R.drawable.morning_bg)
                    2 -> constraint.setBackgroundResource(R.drawable.day_bg)
                    3 -> constraint.setBackgroundResource(R.drawable.evening_bg)
                    4 -> constraint.setBackgroundResource(R.drawable.night_bg)
                }
            }
            in 9..11 -> {
                when (tabNumber) {
                    1 -> constraint.setBackgroundResource(R.drawable.morning_day_gradient_bg)
                    2 -> constraint.setBackgroundResource(R.drawable.day_evening_gradient_bg)
                    3 -> constraint.setBackgroundResource(R.drawable.evening_night_gradient_bg)
                    4 -> constraint.setBackgroundResource(R.drawable.night_morning_gradient_gb)
                }
            }
            in 12..14 -> {
                when (tabNumber) {
                    1 -> constraint.setBackgroundResource(R.drawable.day_bg)
                    2 -> constraint.setBackgroundResource(R.drawable.evening_bg)
                    3 -> constraint.setBackgroundResource(R.drawable.night_bg)
                    4 -> constraint.setBackgroundResource(R.drawable.morning_bg)
                }
            }
            in 15..17 -> {
                when (tabNumber) {
                    1 -> constraint.setBackgroundResource(R.drawable.day_evening_gradient_bg)
                    2 -> constraint.setBackgroundResource(R.drawable.evening_night_gradient_bg)
                    3 -> constraint.setBackgroundResource(R.drawable.night_morning_gradient_gb)
                    4 -> constraint.setBackgroundResource(R.drawable.morning_day_gradient_bg)
                }
            }
            in 18..20 -> {
                when (tabNumber) {
                    1 -> constraint.setBackgroundResource(R.drawable.evening_bg)
                    2 -> constraint.setBackgroundResource(R.drawable.night_bg)
                    3 -> constraint.setBackgroundResource(R.drawable.morning_bg)
                    4 -> constraint.setBackgroundResource(R.drawable.day_bg)
                }
            }
            in 21..23 -> {
                when (tabNumber) {
                    1 -> constraint.setBackgroundResource(R.drawable.evening_night_gradient_bg)
                    2 -> constraint.setBackgroundResource(R.drawable.night_morning_gradient_gb)
                    3 -> constraint.setBackgroundResource(R.drawable.morning_day_gradient_bg)
                    4 -> constraint.setBackgroundResource(R.drawable.day_evening_gradient_bg)
                }
            }
        }

    }

}
