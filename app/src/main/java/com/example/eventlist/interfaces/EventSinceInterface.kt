package com.example.eventlist.interfaces

import com.example.eventlist.database.entities.Event

interface EventSinceInterface {

    interface EventSinceView {
        fun showEventSince(eventSinceList: MutableList<Event>)
        fun showScreenNoEvents()
    }

    interface EventSincePresenter {
        //PRESENTER - INTERACTOR
        fun uploadEventsSince()

        //INTERACTOR-PRESENTER
        fun uploadEventsSinceSuccessful(eventSinceList: MutableList<Event>)

        //PRESENTER CHECK
        fun checkEmptyEventList(eventSinceList: MutableList<Event>)
    }

    interface EventSinceInteractor {
        fun uploadEventsSinceFirestore()
    }
}