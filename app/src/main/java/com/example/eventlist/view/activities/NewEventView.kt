package com.example.eventlist.view.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.lifecycleScope
import com.example.eventlist.databinding.ActivityNewEventViewBinding
import com.example.eventlist.interfaces.NewEventInterface
import com.example.eventlist.database.entities.Event
import com.example.eventlist.presenter.NewEventPresenter
import com.example.eventlist.util.Util
import com.example.eventlist.view.fragments.DataPickerFragment
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.*

class NewEventView : AppCompatActivity(), NewEventInterface.NewEventView {

    private lateinit var binding: ActivityNewEventViewBinding
    private var presenter = NewEventPresenter(this)

    lateinit var dateSelect: Date
    val dateCurrent: Date = Util.sdf.parse(Util.currentDate)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityNewEventViewBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.btnSaveChanges.setOnClickListener {
            Log.v(Util.TAG_NEW_EVENT, "Comunicando view con el presenter..")
            lifecycleScope.launch {
                withContext(Dispatchers.IO) {
                    presenter.newEvent(
                        Event(
                            name = binding.etName.text.toString().trim(),
                            dateCreation = Util.currentDate,
                            date = binding.etDate.text.toString().trim(),
                            typeEvent = checkTypeEvent(),
                            notification = binding.switchNotification.isChecked,
                            description = binding.etDescription.text.toString().trim()
                        )
                    )
                }
            }
        }

        binding.etDate.addTextChangedListener {
            if (checkTypeEvent() == "EventSince") {
                binding.switchNotification.visibility = View.GONE
                binding.switchNotification.isChecked = false
            } else {
                binding.switchNotification.visibility = View.VISIBLE
            }
        }

        binding.btnCancel.setOnClickListener {
            this.finish()
        }

        binding.etDate.setOnClickListener {
            showDatePicker()
        }
    }

    //DATEPICKER
    private fun showDatePicker() {
        val datePicker =
            DataPickerFragment { day, month, year -> setDateSelected(day, month, year) }
        datePicker.show(supportFragmentManager, "DatePicker")
    }

    private fun setDateSelected(day: Int, month: Int, year: Int) {
        binding.etDate.setText("$day/$month/$year")
    }

    //CHECKS
    private fun checkTypeEvent(): String {
        dateSelect = Util.sdf.parse(binding.etDate.text.toString())
        return if (dateSelect.before(dateCurrent)) {
            "EventSince"
        } else {
            "EventUntil"
        }
    }

    //OVERRIDE METHODS VIEW
    override fun showMessage(message: String) {
        this.runOnUiThread(Runnable {
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        })
    }
}