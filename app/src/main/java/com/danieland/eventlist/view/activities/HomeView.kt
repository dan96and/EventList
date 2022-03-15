package com.danieland.eventlist.view.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.danieland.eventlist.extensions.openActivity
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.MobileAds
import danieland.eventlist.R
import danieland.eventlist.databinding.ActivityHomeViewBinding

class HomeView : AppCompatActivity() {

    private lateinit var binding: ActivityHomeViewBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityHomeViewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Banner anuncios
        MobileAds.initialize(this) {
            val adRequest = AdRequest.Builder().build()
            binding.bannerAd.loadAd(adRequest)
        }

        //BottomNavMenu
        setupBottomNavMenu()
        binding.bottomNavigationView.setOnItemReselectedListener { }
        binding.btnAddEvent.setOnClickListener {
            openActivity(NewEventView()::class.java)
            finish()
        }
        binding.btnInformation.setOnClickListener {
            openActivity(InformationView::class.java)
        }
    }

    private fun setupBottomNavMenu() {
        val navController = findNavController(R.id.fragmentContainerView)
        binding.bottomNavigationView.setupWithNavController(navController)
    }
}