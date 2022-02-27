package com.danieland.eventlist.presenter

import android.util.Log
import com.danieland.eventlist.interfaces.NewEventInterface
import com.danieland.eventlist.model.NewEventInteractor
import com.danieland.eventlist.database.entities.Event
import com.danieland.eventlist.util.Util
import com.danieland.eventlist.view.activities.NewEventView

class NewEventPresenter(val view: NewEventView) : NewEventInterface.NewEventPresenter {

    private val interactor = NewEventInteractor(this)

    //Presenter - Interactor
    override fun newEvent(event: Event) {
        if (checkfields(event.name, event.date)) {
            Log.v(Util.TAG_NEW_EVENT, "Comunicando presenter con el interactor..")
            interactor.addEventSqlite(event)
        } else {
            Log.v(Util.TAG_NEW_EVENT, "Comunicando presenter con view..")
            view.showMessage("Required fields must be completed")
        }
    }

    //Presenter - View
    override fun uploadEventCorrect() {
        Log.v(Util.TAG_NEW_EVENT, "Comunicando presenter con view..")
        view.showMessage("Event successfully created")
    }

    override fun uploadEventError() {
        Log.v(Util.TAG_NEW_EVENT, "Comunicando presenter con view..")
        view.showMessage("Error creating the event")
    }

    //Prensenter check
    override fun checkfields(name: String, date: String): Boolean {
        if (name != "" && date != "") {
            return true
        }
        return false
    }
}