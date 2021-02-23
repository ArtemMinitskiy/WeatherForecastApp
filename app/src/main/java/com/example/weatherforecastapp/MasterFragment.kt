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
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherforecastapp.Adapter.WeatherRecyclerAdapter
import com.example.weatherforecastapp.Model.ModelWeather
import kotlinx.android.synthetic.main.fragment_master.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException

class MasterFragment : Fragment() {
    lateinit var recyclerAdapter: WeatherRecyclerAdapter
    val args by navArgs<MasterFragmentArgs>()
    var recyclerView: RecyclerView? = null

    @SuppressLint("SetTextI18n")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_master, container, false)
        recyclerView = rootView.findViewById(R.id.recyclerview) as RecyclerView
        getWeatherDetail()
        return rootView
    }

    fun getWeatherDetail() {
        val service = RetrofitFactory.makeRetrofitService()
        CoroutineScope(Dispatchers.IO).launch {
            val response = service.getWeather("46.48719996790696", "30.720671684814914")

            try {
                withContext(Dispatchers.Main) {
                    if (response.isSuccessful) {
                        response.body()?.let {
                            initRecyclerView(it)
//                            setSynteticBackground(it)
                        }

                        Log.i("ArtREQUEST", "RESPONSE: getWeatherDetail()  ${response.body()!!.listWeather!!.get(0).date!!}")

                    } else {
                        Log.e("ArtREQUEST", "Error network operation failed with ${response.code()}")
                    }
                }
            } catch (e: HttpException) {
                Log.e("ArtREQUEST", "Exception ${e.message}")
            } catch (e: Throwable) {
                Log.e("ArtREQUEST", "Ooops: Something else went wrong")
            }
        }
    }

    private fun initRecyclerView(modelWeather: ModelWeather) {
//        city_text.text = modelWeather.city!!.city
        val weatherItems = ArrayList<WeatherItem>()
        for (i in 0 until 8) {
            weatherItems.add(WeatherItem(modelWeather.city!!.city,
                    Utils().FormatTemp(modelWeather.listWeather!!.get(i).main!!.temp.toString()),
                    modelWeather.listWeather.get(i).weather!!.get(0).description!!.capitalize(),
                    modelWeather.listWeather.get(i).weather!!.get(0).icon,
                    modelWeather.listWeather.get(i).date,
                    modelWeather.listWeather.get(i).wind!!.speed,
                    modelWeather.listWeather.get(i).clouds!!.clouds,
                    Utils().FormatTemp(modelWeather.listWeather.get(i).main!!.feels_like.toString()),
                    modelWeather.listWeather.get(i).main!!.humidity,
                    modelWeather.city.listCoordinates!!.latitude,
                    modelWeather.city.listCoordinates.longitude))
        }
        recyclerAdapter = WeatherRecyclerAdapter(weatherItems, args.tabNumber)

        setBackground(modelWeather)
        recyclerView!!.apply {
            setBackgroundResource(setBackground(modelWeather))
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(requireActivity().applicationContext, LinearLayoutManager.VERTICAL, false)
            adapter = recyclerAdapter
        }

    }

    private fun setBackground(modelWeather: ModelWeather): Int {
        val time = modelWeather.listWeather!!.get(0).date!!.substring(11)
        return when (time) {
            "00:00:00" ->  R.drawable.night_bg
            "03:00:00" ->  R.drawable.night_morning_gradient_gb
            "06:00:00" ->  R.drawable.morning_bg
            "09:00:00" ->  R.drawable.morning_day_gradient_bg
            "12:00:00" ->  R.drawable.day_bg
            "15:00:00" ->  R.drawable.day_evening_gradient_bg
            "18:00:00" ->  R.drawable.evening_bg
            "21:00:00" ->  R.drawable.evening_night_gradient_bg
            else ->  R.drawable.night_bg
        }
    }

    private fun setSynteticBackground(modelWeather: ModelWeather) {
        val time = modelWeather.listWeather!!.get(0).date!!.substring(11)
        when (time) {
            "00:00:00" -> recyclerview!!.setBackgroundResource(R.drawable.night_bg)
            "03:00:00" -> recyclerview!!.setBackgroundResource(R.drawable.night_morning_gradient_gb)
            "06:00:00" -> recyclerview!!.setBackgroundResource(R.drawable.morning_bg)
            "09:00:00" -> recyclerview!!.setBackgroundResource(R.drawable.morning_day_gradient_bg)
            "12:00:00" -> recyclerview!!.setBackgroundResource(R.drawable.day_bg)
            "15:00:00" -> recyclerview!!.setBackgroundResource(R.drawable.day_evening_gradient_bg)
            "18:00:00" -> recyclerview!!.setBackgroundResource(R.drawable.evening_bg)
            "21:00:00" -> recyclerview!!.setBackgroundResource(R.drawable.evening_night_gradient_bg)

        }
    }

}
