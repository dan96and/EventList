package com.example.eventlist.view.activities

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.eventlist.R
import com.example.eventlist.databinding.ActivityHomeViewBinding
import com.example.eventlist.interfaces.HomeInterface
import com.example.eventlist.database.entities.Event
import com.example.eventlist.presenter.HomePresenter
import com.example.eventlist.util.Util

class HomeView : AppCompatActivity(), HomeInterface.HomeView {

    private lateinit var binding: ActivityHomeViewBinding
    private val presenter = HomePresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityHomeViewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Log.v(Util.TAG_HOME, "Descargar eventos. Comunicando view con presenter..")
        presenter.downloadAllEvents()

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

    override fun showLog(message: String) {
        Log.v(Util.TAG_HOME, message)
    }

    override fun implementsCommunicationWithSQLite(EventList: MutableList<Event>) {
        presenter.importEvents(EventList, this)
    }
}