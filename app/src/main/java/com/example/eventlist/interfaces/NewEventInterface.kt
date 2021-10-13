package com.example.eventlist.interfaces

interface NewEventInterface {

    interface NewEventView {
        fun showMessage(message: String)
    }

    interface NewEventPresenter {
        //Presenter - Interactor
        fun uploadEvent(name: String, date: String, notification: Boolean)

        //Presenter - View
        fun uploadEventCorrect()
        fun uploadEventError()

        //Presenter check
        fun checkfields(name: String, date: String): Boolean
    }

    interface NewEventInteractor {
        fun uploadEventFireStore(name: String, date: String, notification: Boolean)
    }
}