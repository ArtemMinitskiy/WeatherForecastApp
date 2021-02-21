package com.example.weatherforecastapp.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.Navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherforecastapp.MasterFragmentDirections
import com.example.weatherforecastapp.R
import com.example.weatherforecastapp.WeatherItem
import com.example.weatherforecastapp.databinding.WeatherItemBinding
import java.util.*

class WeatherRecyclerAdapter(weatherItems: ArrayList<WeatherItem>, tabNumber: Int) : RecyclerView.Adapter<WeatherRecyclerAdapter.ViewHolder>() {
    private val weatherItems: ArrayList<WeatherItem>
    var navController: NavController? = null
    val tabNumber: Int
    init {
        this.weatherItems = weatherItems
        this.tabNumber = tabNumber
    }

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
}
