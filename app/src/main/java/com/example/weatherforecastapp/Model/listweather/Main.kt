package com.example.weatherforecastapp.Model.listweather

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Main {
    @SerializedName("temp")
    @Expose
    val temp: String? = null

    override fun toString(): String {
        return "Main{" +
                "temp='" + temp + '\'' +
                '}'
    }
}