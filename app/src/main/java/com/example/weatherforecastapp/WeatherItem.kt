package com.example.weatherforecastapp

import android.os.Parcel
import android.os.Parcelable

class WeatherItem(var city: String?, val temperature: String?, val description: String?, val weather_icon: String?, val date: String?,
                  val wind: String?, val clouds: String?, val feels_like: String?, val humidity: String?,
                  val latitude: String?, val longitude: String?) : Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString()) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(city)
        parcel.writeString(temperature)
        parcel.writeString(description)
        parcel.writeString(weather_icon)
        parcel.writeString(date)
        parcel.writeString(wind)
        parcel.writeString(clouds)
        parcel.writeString(feels_like)
        parcel.writeString(humidity)
        parcel.writeString(latitude)
        parcel.writeString(longitude)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<WeatherItem> {
        override fun createFromParcel(parcel: Parcel): WeatherItem {
            return WeatherItem(parcel)
        }

        override fun newArray(size: Int): Array<WeatherItem?> {
            return arrayOfNulls(size)
        }
    }

}