package com.danieland.eventlist.view.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.danieland.eventlist.util.Util
import danieland.eventlist.R
import danieland.eventlist.databinding.ActivityHomeViewBinding

class HomeView : AppCompatActivity() {

    private lateinit var binding: ActivityHomeViewBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityHomeViewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //BottomNavMenu
        setupBottomNavMenu()
        binding.bottomNavigationView.setOnItemReselectedListener { }
        binding.btnAddEvent.setOnClickListener {
            Util.openActivity(this, NewEventView()::class.java)
        }
        binding.btnInformation.setOnClickListener {
            Util.openActivity(this, InformationView::class.java)
        }
    }

    private fun setupBottomNavMenu() {
        val navController = findNavController(R.id.fragmentContainerView)
        binding.bottomNavigationView.setupWithNavController(navController)
    }
}