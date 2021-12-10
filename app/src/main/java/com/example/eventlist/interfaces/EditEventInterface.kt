package com.example.eventlist.interfaces

import com.example.eventlist.database.entities.Event

interface EditEventInterface {
    interface EditEventView {
        fun showMessage(message:String)
        fun showDialogEditEventSuccesfull(message: String)
        fun showDialogDeleteEventSuccesfull(message: String)
    }

    interface EditEventPresenter {
        //VIEW-PRESENTER
        fun saveChangesEvent(event: Event)
        fun deleteEvent(idEvent: String)

        //INTERACTOR-PRESENTER
        fun editEventCorrect()
        fun editEventError()

        fun deleteEventSuccesfull()
        fun deleteEventError()

        //CHECK VIEW
        fun checkFieldsEmpty(title: String, date: String): Boolean
    }

    interface EditEventInteractor {
        fun uploadChangesEvent(event: Event)
        fun deleteEventFireStore(idEvent: String)
    }
}