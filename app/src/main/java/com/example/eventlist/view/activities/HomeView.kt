package com.example.eventlist.view.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.eventlist.R
import com.example.eventlist.databinding.ActivityHomeViewBinding
import com.example.eventlist.util.Util

class HomeView : AppCompatActivity() {

    private lateinit var binding: ActivityHomeViewBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityHomeViewBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        setupBottomNavMenu()

        binding.btnAddEvent.setOnClickListener{
            Util.openActivity(this,NewEventView()::class.java)
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