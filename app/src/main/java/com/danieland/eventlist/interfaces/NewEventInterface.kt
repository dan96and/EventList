package com.danieland.eventlist.interfaces

import com.danieland.eventlist.database.entities.Event

interface NewEventInterface {

    interface NewEventView {
        fun showMessage(message: String)
    }

    interface NewEventPresenter {
        //Presenter - Interactor
        fun newEvent(event: Event)

        //Presenter - View
        fun uploadEventCorrect()
        fun uploadEventError()

        //Presenter check
        fun checkfields(name: String, date: String): Boolean
    }

    interface NewEventInteractor {
        fun addEventSqlite(event: Event)
    }
}