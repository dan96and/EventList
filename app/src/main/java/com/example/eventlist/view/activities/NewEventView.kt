package com.example.eventlist.view.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import com.example.eventlist.databinding.ActivityNewEventViewBinding
import com.example.eventlist.view.fragments.DataPickerFragment
import com.google.android.material.datepicker.MaterialDatePicker

class NewEventView : AppCompatActivity() {

    private lateinit var binding: ActivityNewEventViewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityNewEventViewBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.btnCancel.setOnClickListener {
            this.finish()
        }

        binding.etDate.setOnClickListener {
            showDatePicker()
        }
    }

    //Mostrar DatePicker
    private fun showDatePicker() {
        val datePicker = DataPickerFragment { day, month, year -> setDateSelected(day,month,year) }
        datePicker.show(supportFragmentManager, "DatePicker")
    }
    //Mostrar la fecha seleccionada en el EditText
    private fun setDateSelected(day: Int, month: Int, year: Int) {
        binding.etDate.setText("$day/$month/$year")
    }

}