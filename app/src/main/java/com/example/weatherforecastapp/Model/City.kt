package com.example.weatherforecastapp.Model

import com.example.weatherforecastapp.Model.listweather.ListWeather
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.util.ArrayList

class City {
    @SerializedName("name")
    @Expose
    val city: String? = null

    @SerializedName("country")
    @Expose
    val country: String? = null

//    @SerializedName("coord")
//    @Expose
//    val listCoordinates: ArrayList<Coordinates>? = null

    override fun toString(): String {
        return "City{" +
                "city='" + city + '\'' +
                ", country='" + country + '\'' +
//                ", coord='" + listCoordinates + '\'' +
                '}'
    }
}