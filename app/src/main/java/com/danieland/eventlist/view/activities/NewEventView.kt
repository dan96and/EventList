package com.danieland.eventlist.view.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.lifecycleScope
import com.danieland.eventlist.interfaces.NewEventInterface
import com.danieland.eventlist.database.entities.Event
import com.danieland.eventlist.extensions.openActivity
import com.danieland.eventlist.extensions.toast
import com.danieland.eventlist.presenter.NewEventPresenter
import com.danieland.eventlist.util.Util
import com.danieland.eventlist.view.fragments.DataPickerFragment
import danieland.eventlist.databinding.ActivityNewEventViewBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.*

class NewEventView : AppCompatActivity(), NewEventInterface.NewEventView {

    private lateinit var binding: ActivityNewEventViewBinding
    private var presenter = NewEventPresenter(this)

    private lateinit var dateSelect: Date
    private val dateCurrent: Date = Util.sdf.parse(Util.currentDate)

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
            openActivity(HomeView::class.java)
            finish()
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
        if (binding.etDate.text.toString() != "") {
            dateSelect = Util.sdf.parse(binding.etDate.text.toString())
            return if (dateSelect.before(dateCurrent)) {
                "EventSince"
            } else {
                "EventUntil"
            }
        }
        return ""
    }

    //OVERRIDE METHODS VIEW
    override fun showMessage(message: String) {
        this.runOnUiThread(Runnable {
            toast(message)
            binding.etName.text.clear()
            binding.etDescription.text.clear()
            binding.etDate.text.clear()
        })
    }

    override fun onBackPressed() {
        super.onBackPressed()
        openActivity(HomeView::class.java)
        finish()
    }
}