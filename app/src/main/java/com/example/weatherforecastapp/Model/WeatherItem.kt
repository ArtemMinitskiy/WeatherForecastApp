package com.example.weatherforecastapp.Model

import com.example.weatherforecastapp.Model.listweather.Clouds
import com.example.weatherforecastapp.Model.listweather.Main
import com.example.weatherforecastapp.Model.listweather.Wind
import com.example.weatherforecastapp.Model.listweather.weather.Weather
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.util.*

class WeatherItem {

    @SerializedName("main")
    @Expose
    var main: Main? = null

    @SerializedName("weather")
    @Expose
    val weather: ArrayList<Weather>? = null

    @SerializedName("wind")
    @Expose
    val wind: Wind? = null

    @SerializedName("dt_txt")
    @Expose
    val dt_txt: String? = null

    @SerializedName("clouds")
    @Expose
    val clouds: Clouds? = null

    override fun toString(): String {
        return "ListWeather{" +
                "main=" + main +
                ", weather=" + weather +
                ", wind=" + wind +
                ", dt_txt='" + dt_txt + '\'' +
                ", clouds=" + clouds +
                '}'
    }
}