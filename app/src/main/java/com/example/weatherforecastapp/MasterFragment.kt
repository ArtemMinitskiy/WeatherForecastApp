package com.example.weatherforecastapp

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import kotlinx.android.synthetic.main.fragment_master.view.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException


class MasterFragment : Fragment() {

    val args by navArgs<MasterFragmentArgs>()

    @SuppressLint("SetTextI18n")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_master, container, false)

        getWeatherDetail()

        rootView.master_text.text = "Master view, tab number: ${args.tabNumber}"
        rootView.detail_navigate_button.setOnClickListener {
            findNavController().navigate(
                MasterFragmentDirections.showDetailsFromMaster(
                    args.tabNumber,
                    "I am on a phone"
                )
            )
        }
        return rootView
    }

    fun getWeatherDetail() {
        val service = RetrofitFactory.makeRetrofitService()
        CoroutineScope(Dispatchers.IO).launch {

            val response = service.getWeather("46.48719996790696", "30.720671684814914")
//            val response = service.getWeather()
            try {
                withContext(Dispatchers.Main) {
                    if (response.isSuccessful) {
//                        Log.i("ArtREQUEST", "RESPONSE:  ${response.body().toString()}")
                        Log.i("ArtREQUEST", "RESPONSE:  ${response.body()!!.city!!.city}" + ", ${response.body()!!.city!!.country}")
                        Log.i("ArtREQUEST", "RESPONSE:  ${response.body()!!.listWeather?.get(0)!!.weather!!.get(0).description}" +
                                ", ${FormatTemp(response.body()!!.listWeather?.get(0)!!.main!!.temp.toString()) + " \u00B0 C"}")

                    } else {
                        Log.e(
                            "ArtREQUEST",
                            "Error network operation failed with ${response.code()}"
                        )
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

}
