package com.example.eventlist.view.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import com.example.eventlist.databinding.ActivityNewEventViewBinding

class NewEventView : AppCompatActivity() {

    private lateinit var binding: ActivityNewEventViewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityNewEventViewBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.btnCancel.setOnClickListener{
            this.finish()
        }
    }


}