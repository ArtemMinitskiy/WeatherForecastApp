package com.example.weatherforecastapp

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.weatherforecastapp.databinding.FragmentDetailBinding

class DetailFragment : Fragment() {

    val args by navArgs<DetailFragmentArgs>()

    @SuppressLint("SetTextI18n")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding: FragmentDetailBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_detail, container, false)
        val rootView = binding.root
        binding.weatherData = args.weatherItem
//        Log.i("ArtREQUEST", "RESPONSE: DetailFragment()  ${args.weatherItem!!.weather_icon}")
//        rootView.detail_text.text = "Detail view, tab number: ${args.tabNumber}"

        return rootView
    }

    companion object{
        @BindingAdapter("setBackground")
        @JvmStatic
        fun setBackground(constraint: ConstraintLayout, time: String) {
            when(time) {
                "00:00:00", "03:00:00" -> constraint.setBackgroundResource(R.drawable.night_bg)
                "06:00:00", "09:00:00" -> constraint.setBackgroundResource(R.drawable.morning_bg)
                "12:00:00", "15:00:00" -> constraint.setBackgroundResource(R.drawable.day_bg)
                "18:00:00", "21:00:00" -> constraint.setBackgroundResource(R.drawable.evening_bg)

            }
        }
    }


}
