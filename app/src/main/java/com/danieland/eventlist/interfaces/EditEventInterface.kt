package com.danieland.eventlist.interfaces

import com.danieland.eventlist.database.entities.Event

interface EditEventInterface {
    interface EditEventView {
        fun showMessage(message:String)
        fun showDialogEditEventSuccesfull(message: String)
        fun showDialogDeleteEventSuccesfull(message: String)
    }

    interface EditEventPresenter {
        //VIEW-PRESENTER
        fun saveChangesEvent(event: Event)
        fun deleteEvent(idEvent: Int)

        //INTERACTOR-PRESENTER
        fun editEventCorrect()
        fun editEventError()

        fun deleteEventSuccesfull()

        //CHECK VIEW
        fun checkFieldsEmpty(title: String, date: String): Boolean
    }

    interface EditEventInteractor {
        fun uploadChangesEvent(event: Event)
        fun deleteEventSqlite(idEvent: Int)
    }
}