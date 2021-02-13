package com.example.weatherforecastapp

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import kotlinx.android.synthetic.main.fragment_master.view.*

class MasterFragment : Fragment() {

    val args by navArgs<MasterFragmentArgs>()

    @SuppressLint("SetTextI18n")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_master, container, false)

        rootView.master_text.text = "Master view, tab number: ${args.tabNumber}"
        rootView.detail_navigate_button.setOnClickListener {  findNavController().navigate(
            MasterFragmentDirections.showDetailsFromMaster(args.tabNumber, "I am on a phone")) }
        return rootView
    }

}
