package com.example.weatherforecastapp

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
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

    @SuppressLint("SetTextI18n")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
//        val binding: FragmentMasterBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_master, container, false)
//        val rootView = binding.root

        val rootView = inflater.inflate(R.layout.fragment_master, container, false)

        getWeatherDetail()

//        rootView.master_text.text = "Master view, tab number: ${args.tabNumber}"
//        rootView.detail_navigate_button.setOnClickListener {
//            findNavController().navigate(MasterFragmentDirections.showDetailsFromMaster(args.tabNumber, "I am on a phone"))
//        }
        return rootView
    }

    fun getWeatherDetail() {
        val service = RetrofitFactory.makeRetrofitService()
        CoroutineScope(Dispatchers.IO).launch {
            val response = service.getWeather("46.48719996790696", "30.720671684814914")

            try {
                withContext(Dispatchers.Main) {
                    if (response.isSuccessful) {
                        response.body()?.let { initRecyclerView(it) }

//                        Log.i("ArtREQUEST", "RESPONSE: getWeatherDetail()  ${response.body()!!.listWeather!!.get(0).main!!.temp}")
//                        Log.i("ArtREQUEST", "RESPONSE:  ${response.body().toString()}")
//                        Log.i("ArtREQUEST", "RESPONSE:  ${response.body()!!.city!!.city}" + ", ${response.body()!!.city!!.country}")
//                        Log.i("ArtREQUEST", "RESPONSE:  ${response.body()!!.listWeather?.get(0)!!.weather!!.get(0).description}" +
//                                ", ${FormatTemp(response.body()!!.listWeather?.get(0)!!.main!!.temp.toString()) + " \u00B0 C"}")
//                        binding.weatherData = WeatherItem("", response.body()!!.listWeather!!.get(0).main!!.temp.toString(), "", "", "", "", "")
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

    fun FormatTemp(temp: String): String? {
        val i = temp.toFloat() - 273
        return String.format("%.2f", i)
    }

    private fun initRecyclerView(modelWeather: ModelWeather) {
        city_text.text = modelWeather.city!!.city
        val weatherItems = ArrayList<WeatherItem>()
        for (i in 0 until 8) {
            weatherItems.add(WeatherItem(modelWeather.city.city,
                    modelWeather.listWeather!!.get(i).main!!.temp,
                    modelWeather.listWeather.get(i).weather!!.get(0).description,
                    modelWeather.listWeather.get(i).weather!!.get(0).icon,
                    modelWeather.listWeather.get(i).date,
                    modelWeather.listWeather.get(i).wind!!.speed,
                    modelWeather.listWeather.get(i).clouds!!.clouds,
                    modelWeather.listWeather.get(i).main!!.feels_like,
                    modelWeather.listWeather.get(i).main!!.humidity,
                    "modelWeather.city.listCoordinates!!.get(0).latitude",
                    "modelWeather.city.listCoordinates.get(0).longitude"))
        }
        recyclerAdapter = WeatherRecyclerAdapter(weatherItems, args.tabNumber)

        recyclerview.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(requireActivity().applicationContext, LinearLayoutManager.VERTICAL, false)
            adapter = recyclerAdapter
        }

    }


}
