package com.danieland.eventlist.view.activities

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import danieland.eventlist.R
import com.danieland.eventlist.database.entities.Event
import com.danieland.eventlist.extensions.toast
import com.danieland.eventlist.interfaces.EditEventInterface
import com.danieland.eventlist.presenter.EditEventPresenter
import com.danieland.eventlist.util.Util
import com.danieland.eventlist.view.fragments.DataPickerFragment
import danieland.eventlist.databinding.ActivityEditEventBinding
import java.util.*

class EditEventView : AppCompatActivity(), EditEventInterface.EditEventView {

    private lateinit var binding: ActivityEditEventBinding

    private val presenter = EditEventPresenter(this)

    private lateinit var dateSelect: Date
    private val dateCurrent: Date = Util.sdf.parse(Util.currentDate)

    private lateinit var dialog : Dialog
    private lateinit var btnCancel : Button
    private lateinit var btnDeleteEvent : Button
    private lateinit var event : Event

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
            presenter.saveChangesEvent(
                Event(
                    name = binding.etName.text.toString().trim(),
                    description = binding.etDescription.text.toString().trim(),
                    date= binding.etDate.text.toString().trim(),
                    typeEvent = checkTypeEvent(),
                    notification = Util.checkValueSwitchNotification(binding.switchNotification.isChecked),
                    idEvent = event.idEvent
                )
            )
        }

        //Mostrar el calendario
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

        //Botón Cancelar de la pantalla
        binding.btnCancel.setOnClickListener {
            finish()
        }

        //Eliminar un evento
        binding.btnDeleteEvent.setOnClickListener {
           showDialog()
        }
    }

    //Poner la informacion del evento seleccionado en el recyclerview en los campos de la pantalla
    private fun setDataFields() {
        val event = intent.extras!!.getSerializable("KEY") as Event
        binding.etDate.setText(event!!.date)
        binding.etName.setText(event.name)
        binding.etDescription.setText(event.description)
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
        toast(message)
    }

    override fun showDialogEditEventSuccesfull(message: String) {
        toast(message)
    }

    override fun showDialogDeleteEventSuccesfull(message: String) {
        toast(message)
        finish()
    }

    //Metodos para mostrar el alertDialog
    private fun showDialog(){
        dialog = Dialog(this)
        dialog.setContentView(R.layout.fragment_dialog_delete_event_view)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        btnCancel = dialog.findViewById(R.id.btnCancel)
        btnDeleteEvent = dialog.findViewById(R.id.btnDeleteEvent)

        btnCancel.setOnClickListener {
            dialog.dismiss()
        }

        btnDeleteEvent.setOnClickListener {
            event = intent.extras!!.getSerializable("KEY") as Event
            presenter.deleteEvent(event.idEvent)
        }
        dialog.show()
    }
}