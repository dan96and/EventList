package com.danieland.eventlist.presenter

import android.util.Log
import com.danieland.eventlist.interfaces.EditEventInterface
import com.danieland.eventlist.model.EditEventInteractor
import com.danieland.eventlist.database.entities.Event
import com.danieland.eventlist.util.Util
import com.danieland.eventlist.view.activities.EditEventView

class EditEventPresenter(val view: EditEventView) : EditEventInterface.EditEventPresenter {

    val interactor = EditEventInteractor(this)

    //PRESENTER-INTERACTOR
    override fun saveChangesEvent(event: Event) {
        if (checkFieldsEmpty(event.name, event.date)) {
            Log.v(Util.TAG_NEW_EVENT,"Comunicando presentador con el interactor..")
            interactor.uploadChangesEvent(event)
        }else{
            Log.v(Util.TAG_NEW_EVENT,"Comunicando presentador con el vista..")
            view.showMessage("Required fields must be completed ")
        }
    }

    override fun deleteEvent(idEvent: Int) {
        interactor.deleteEventSqlite(idEvent)
    }

    //INTERACTOR - PRESENTER
    override fun editEventCorrect() {
        view.showDialogEditEventSuccesfull("Changes made successfully!")
    }

    override fun editEventError() {
        view.showMessage("Error saving changes")
    }

    override fun deleteEventSuccesfull() {
        view.showDialogDeleteEventSuccesfull("Event removed!")
    }

    //CHECKS
    override fun checkFieldsEmpty(title: String, date: String): Boolean {
        return !(title == "" || date == "")
    }
}