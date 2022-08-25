package com.example.weatherforecastapp

import android.content.Context
import com.example.weatherforecastapp.Model.ModelWeather
import com.google.gson.Gson

object PreferencesProvider {

    private val preferences = App.getInstance().getSharedPreferences("art", Context.MODE_PRIVATE)

    private const val WEATHER_LOADED = "weather_loaded"

    fun saveWeatherModel(body: ModelWeather?) {
        val gson = Gson()
        val json = gson.toJson(body)
        preferences.edit().putString(WEATHER_LOADED, json).apply()
    }

    fun getWeatherModel(): ModelWeather {
        val gson = Gson()
        val json: String = preferences.getString(WEATHER_LOADED, "")!!
        val obj: ModelWeather = gson.fromJson(json, ModelWeather::class.java)
        return obj
    }

}