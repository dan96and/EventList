package com.example.eventlist.interfaces

import com.example.eventlist.objects.Event

interface NewEventInterface {

    interface NewEventView {
        fun showMessage(message: String)
    }

    interface NewEventPresenter {
        //Presenter - Interactor
        fun uploadEvent(event: Event)

        //Presenter - View
        fun uploadEventCorrect()
        fun uploadEventError()

        //Presenter check
        fun checkfields(name: String, date: String): Boolean
    }

    interface NewEventInteractor {
        fun uploadEventFireStore(event: Event)
    }
}