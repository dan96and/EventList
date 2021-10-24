package com.example.eventlist.view.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.eventlist.databinding.ActivityEditEventBinding
import com.example.eventlist.objects.Event

class EditEventView : AppCompatActivity() {

    private lateinit var binding: ActivityEditEventBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityEditEventBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setDataFields()
    }

    //Poner la informacion del evento seleccionado en el recyclerview en los campos de la pantalla
    private fun setDataFields(){
        val event = intent.extras?.getSerializable("KEY") as Event?
        binding.etDate.setText(event?.date.toString())
        binding.etName.setText(event?.title.toString())
        binding.switchNotification.isChecked = event!!.notification
    }
}