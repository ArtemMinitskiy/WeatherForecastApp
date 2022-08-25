package com.example.weatherforecastapp.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.Navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.airbnb.lottie.LottieAnimationView
import com.example.weatherforecastapp.MasterFragmentDirections
import com.example.weatherforecastapp.R
import com.example.weatherforecastapp.WeatherItem
import com.example.weatherforecastapp.databinding.WeatherItemBinding
import java.util.*

class WeatherRecyclerAdapter(private val weatherItems: ArrayList<WeatherItem>, val tabNumber: Int) : RecyclerView.Adapter<WeatherRecyclerAdapter.ViewHolder>() {
    var navController: NavController? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding: WeatherItemBinding = WeatherItemBinding.inflate(inflater, parent, false)
        return ViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        when(tabNumber) {
            1 -> setView(weatherItems, position, holder, 0)
            2 -> setView(weatherItems, position, holder, 2)
            3 -> setView(weatherItems, position, holder, 4)
            4 -> setView(weatherItems, position, holder, 6)

        }

    }

    override fun getItemCount(): Int {
        return 2
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val cardView: CardView
        var binding: WeatherItemBinding?

        init {
            binding = DataBindingUtil.bind(itemView)
            cardView = itemView.findViewById(R.id.cardview)
        }
    }

    fun setView(weatherItems: ArrayList<WeatherItem>, position: Int, holder: ViewHolder, i: Int) {
        val weatherItem: WeatherItem? = weatherItems[position + i]
        holder.binding!!.weatherData = weatherItem
        holder.cardView.setOnClickListener {
            navController = findNavController(holder.cardView)
            navController!!.navigate(MasterFragmentDirections.showDetailsFromMaster(position, "I am on a phone", weatherItem))
        }
    }

    companion object{
        @BindingAdapter("setCardViewBackground")
        @JvmStatic
        fun setCardViewBackground(cardView: CardView, time: String) {
            when(time) {
                "00:00:00", "03:00:00" -> cardView.setBackgroundResource(R.drawable.night_card_bg)
                "06:00:00", "09:00:00" -> cardView.setBackgroundResource(R.drawable.morning_card_bg)
                "12:00:00", "15:00:00" -> cardView.setBackgroundResource(R.drawable.day_card_bg)
                "18:00:00", "21:00:00" -> cardView.setBackgroundResource(R.drawable.evening_card_bg)

            }
        }

        @BindingAdapter("setAdapterAnimation")
        @JvmStatic
        fun setAdapterAnimation(lottieAnimationView: LottieAnimationView, description: String) {
            when(description) {
                "01d" -> lottieAnimationView.setAnimation(R.raw.weather_day_clear_sky)
                "02d" -> lottieAnimationView.setAnimation(R.raw.weather_day_few_clouds)
                "03d" -> lottieAnimationView.setAnimation(R.raw.weather_day_scattered_clouds)
                "04d" -> lottieAnimationView.setAnimation(R.raw.weather_day_broken_clouds)
                "09d" -> lottieAnimationView.setAnimation(R.raw.weather_day_shower_rains)
                "10d" -> lottieAnimationView.setAnimation(R.raw.weather_day_rain)
                "11d" -> lottieAnimationView.setAnimation(R.raw.weather_day_thunderstorm)
                "13d" -> lottieAnimationView.setAnimation(R.raw.weather_day_snow)
                "50d" -> lottieAnimationView.setAnimation(R.raw.weather_day_mist)
                "01n" -> lottieAnimationView.setAnimation(R.raw.weather_night_clear_sky)
                "02n" -> lottieAnimationView.setAnimation(R.raw.weather_night_few_clouds)
                "03n" -> lottieAnimationView.setAnimation(R.raw.weather_night_scattered_clouds)
                "04n" -> lottieAnimationView.setAnimation(R.raw.weather_night_broken_clouds)
                "09n" -> lottieAnimationView.setAnimation(R.raw.weather_night_shower_rains)
                "10n" -> lottieAnimationView.setAnimation(R.raw.weather_night_rain)
                "11n" -> lottieAnimationView.setAnimation(R.raw.weather_night_thunderstorm)
                "13n" -> lottieAnimationView.setAnimation(R.raw.weather_night_snow)
                "50n" -> lottieAnimationView.setAnimation(R.raw.weather_night_mist)

            }
        }
    }
}
