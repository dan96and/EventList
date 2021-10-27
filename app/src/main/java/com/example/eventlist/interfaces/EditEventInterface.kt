package com.example.eventlist.interfaces

import com.example.eventlist.objects.Event

interface EditEventInterface {
    interface EditEventView {
        fun showMessage(message:String)
        fun closeActivityAndShowMessage(message:String)
    }

    interface EditEventPresenter {
        //VIEW-PRESENTER
        fun saveChangesEvent(event: Event)

        //INTERACTOR-PRESENTER
        fun editEventCorrect(event: Event)
        fun editEventError()

        //CHECK VIEW
        fun checkFieldsEmpty(title: String, date: String): Boolean
    }

    interface EditEventInteractor {
        fun uploadChangesEvent(event: Event)
    }
}