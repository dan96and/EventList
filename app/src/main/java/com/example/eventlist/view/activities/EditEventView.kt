package com.example.eventlist.view.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.widget.addTextChangedListener
import com.example.eventlist.databinding.ActivityEditEventBinding
import com.example.eventlist.interfaces.EditEventInterface
import com.example.eventlist.objects.Event
import com.example.eventlist.presenter.EditEventPresenter
import com.example.eventlist.util.Util
import com.example.eventlist.view.fragments.DataPickerFragment
import com.example.eventlist.view.fragments.DialogDeleteEventFragmentView
import java.util.*

class EditEventView : AppCompatActivity(), EditEventInterface.EditEventView {

    private lateinit var binding: ActivityEditEventBinding

    private val presenter = EditEventPresenter(this)

    lateinit var dateSelect: Date
    val dateCurrent: Date = Util.sdf.parse(Util.currentDate)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityEditEventBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Metodo para insertar los datos del evento que vienen de la pantalla anterior
        setDataFields()

        //Guardar los cambios de las modificaciones del evento
        binding.btnSaveChanges.setOnClickListener {
            Log.v(Util.TAG_NEW_EVENT, "Comunicando vista con el presentador..")
            val event = intent.extras!!.getSerializable("KEY") as Event
            Log.v(Util.TAG_NEW_EVENT, event.idEvent)
            presenter.saveChangesEvent(
                Event(
                    binding.etName.text.toString().trim(),
                    "",
                    binding.etDate.text.toString().trim(),
                    checkTypeEvent(),
                    binding.switchNotification.isChecked,
                    event.idEvent
                )
            )
        }

        //Muestra el Calendario
        binding.etDate.setOnClickListener {
            showDatePicker()
        }

        //Metododo para poder escuchar que tipo de Evento es
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

        binding.btnDeleteEvent.setOnClickListener {
            val event = intent.extras!!.getSerializable("KEY") as Event
            val dialog = DialogDeleteEventFragmentView(event.idEvent)
            dialog.show(supportFragmentManager, "DialogDeleteEvent")
        }

    }

    //Poner la informacion del evento seleccionado en el recyclerview en los campos de la pantalla
    private fun setDataFields() {
        val event = intent.extras!!.getSerializable("KEY") as Event
        binding.etDate.setText(event!!.date)
        binding.etName.setText(event.title)
        binding.switchNotification.isChecked = event.notification
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

    //DATEPICKER
    private fun showDatePicker() {
        val datePicker =
            DataPickerFragment { day, month, year -> setDateSelected(day, month, year) }
        datePicker.show(supportFragmentManager, "DatePicker")
    }

    private fun setDateSelected(day: Int, month: Int, year: Int) {
        binding.etDate.setText("$day/$month/$year")
    }

    //METHODS OVERRIDE
    override fun showMessage(message: String) {
        Util.showToast(this, message)
    }

    override fun closeActivityAndShowMessage(message: String) {
        Util.showToast(this, message)
        finish()
    }
}