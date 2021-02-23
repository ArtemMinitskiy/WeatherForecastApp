package com.example.weatherforecastapp

class Utils {

    fun FormatTemp(temp: String): String? {
        val i = temp.toFloat() - 273
        return String.format("%.2f", i) + " \u00B0C"
    }

}