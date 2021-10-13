package com.example.eventlist.presenter

import android.util.Log
import com.example.eventlist.interfaces.NewEventInterface
import com.example.eventlist.model.NewEventInteractor
import com.example.eventlist.util.Util
import com.example.eventlist.view.activities.NewEventView

class NewEventPresenter(val view: NewEventView) : NewEventInterface.NewEventPresenter {

    private val interactor = NewEventInteractor(this)

    //Presenter - Interactor
    override fun uploadEvent(name: String, date: String, notification: Boolean) {
        if (checkfields(name, date)) {
            Log.v(Util.TAG_NEW_EVENT, "Comunicando presenter con el interactor..")
            interactor.uploadEventFireStore(name, date, notification)
        } else {
            Log.v(Util.TAG_NEW_EVENT, "Comunicando presenter con view..")
            view.showMessage("Campos vacios")
        }
    }

    //Presenter - View
    override fun uploadEventCorrect() {
        Log.v(Util.TAG_NEW_EVENT, "Comunicando presenter con view..")
        view.showMessage("Evento creado correctamente")
    }

    override fun uploadEventError() {
        Log.v(Util.TAG_NEW_EVENT, "Comunicando presenter con view..")
        view.showMessage("Error al crear el evento")
    }

    //Prensenter check
    override fun checkfields(name: String, date: String): Boolean {
        if (name != "" && date != "") {
            return true
        }
        return false
    }
}