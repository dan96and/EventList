package com.example.eventlist.view.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.eventlist.R
import com.example.eventlist.databinding.ActivityInformationViewBinding

class InformationView : AppCompatActivity() {

    private lateinit var binding: ActivityInformationViewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInformationViewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Navigation
        binding.btnBeforeArrow.setOnClickListener {
            finish()
        }
    }
}