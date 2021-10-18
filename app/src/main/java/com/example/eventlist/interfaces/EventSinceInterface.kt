package com.example.eventlist.interfaces

import com.example.eventlist.objects.Event

interface EventSinceInterface {

    interface EventSinceView {
        fun showEventSince(eventSinceList: MutableList<Event>)
    }

    interface EventSincePresenter {
        //PRESENTER - INTERACTOR
        fun uploadEventsSince()

        //INTERACTOR-PRESENTER
        fun uploadEventsSinceSuccessful(eventSinceList: MutableList<Event>)
    }

    interface EventSinceInteractor {
        fun uploadEventsSinceFirestore()
    }
}