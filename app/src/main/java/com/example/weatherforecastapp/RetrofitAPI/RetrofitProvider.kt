package com.example.weatherforecastapp.RetrofitAPI

import android.util.Log
import com.example.weatherforecastapp.PreferencesProvider
import com.example.weatherforecastapp.RetrofitService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitProvider {

    const val BASE_URL = "http://api.openweathermap.org/"

    fun makeRetrofitService(): RetrofitService {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(RetrofitService::class.java)
    }

    fun getWeatherDetail() {
        val service = makeRetrofitService()
        CoroutineScope(Dispatchers.IO).launch {
            val response = service.getWeather("46.48719996790696", "30.720671684814914")

            try {
                withContext(Dispatchers.Main) {
                    if (response.isSuccessful) {
                        PreferencesProvider.saveWeatherModel(response.body())
                        Log.i("ArtREQUEST", "RESPONSE: getWeatherDetail()  ${response.body()!!.listWeather!![0].date!!}")
                        Log.i("ArtREQUEST", "RESPONSE: getWeatherDetail()  ${response.body()!!}")
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

}