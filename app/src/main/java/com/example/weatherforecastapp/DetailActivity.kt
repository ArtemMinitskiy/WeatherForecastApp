package com.example.weatherforecastapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.navArgs

class DetailActivity : AppCompatActivity() {
    val args by navArgs<DetailActivityArgs>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val detail = supportFragmentManager.findFragmentById(R.id.detail_nav_fragment) as NavHostFragment?
        if (detail != null) {
            val navController = detail.navController
            val navInflater = navController.navInflater
            val graph = navInflater.inflate(R.navigation.detail)

            detail.navController.setGraph(graph, args.toBundle())
        }
    }
}