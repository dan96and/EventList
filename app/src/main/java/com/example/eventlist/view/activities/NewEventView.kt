package com.example.eventlist.view.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

import com.example.eventlist.databinding.ActivityNewEventViewBinding
import com.example.eventlist.interfaces.NewEventInterface
import com.example.eventlist.presenter.NewEventPresenter
import com.example.eventlist.util.Util
import com.example.eventlist.view.fragments.DataPickerFragment

class NewEventView : AppCompatActivity(), NewEventInterface.NewEventView {

    private lateinit var binding: ActivityNewEventViewBinding
    private var presenter = NewEventPresenter(this)

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

        binding.btnAddEvent.setOnClickListener {
            Log.v(Util.TAG_NEW_EVENT,"Comunicando view con el presenter..")
            presenter.uploadEvent(binding.etName.text.toString().trim(),binding.etDate.text.toString().trim(), checkSwitchNotification())
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

    private fun checkSwitchNotification():Boolean{
        if(binding.switchNotification.isChecked){
            return true
        }
        return false
    }

    override fun showMessage(message: String) {
        Util.showToast(this,message)
    }

}