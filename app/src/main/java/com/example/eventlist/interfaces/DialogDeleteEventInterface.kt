package com.example.eventlist.interfaces

interface DialogDeleteEventInterface {

    interface DialogDeleteEventView {
        fun closeActivityShowMessage(message: String)
        fun closeFragmentShowMessage(message: String)
    }

    interface DialogDeleteEventPresenter {
        //PRESENTER-INTERACTOR
        fun deleteEvent(idEvent: String)

        //INTERACTOR-PRESENTER
        fun deleteEventSuccesfull()
        fun deleteEventError()
    }

    interface DialogDeleteEventInteractor {
        fun deleteEventFireStore(idEvent: String)
    }
}