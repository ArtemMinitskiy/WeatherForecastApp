package com.example.weatherforecastapp.Model.city

import com.example.weatherforecastapp.Model.city.coordinates.Coordinates
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

    @SerializedName("coord")
    @Expose
    val listCoordinates: Coordinates? = null

    override fun toString(): String {
        return "City{" +
                "city='" + city + '\'' +
                ", country='" + country + '\'' +
                ", coord='" + listCoordinates + '\'' +
                '}'
    }
}