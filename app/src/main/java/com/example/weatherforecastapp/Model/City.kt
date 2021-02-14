package com.example.weatherforecastapp.Model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class City {
    @SerializedName("name")
    @Expose
    val city: String? = null

    @SerializedName("country")
    @Expose
    val country: String? = null

    override fun toString(): String {
        return "City{" +
                "city='" + city + '\'' +
                ", country='" + country + '\'' +
                '}'
    }
}