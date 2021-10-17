package com.example.eventlist.interfaces

import com.example.eventlist.objects.EventSince

interface EventSinceInterface {

    interface EventSinceView {
        fun showEventSince(eventSinceList: MutableList<EventSince>)
    }

    interface EventSincePresenter {
        //PRESENTER - INTERACTOR
        fun uploadEventsSince()

        //INTERACTOR-PRESENTER
        fun uploadEventsSinceSuccessful(eventSinceList: MutableList<EventSince>)
    }

    interface EventSinceInteractor {
        fun uploadEventsSinceFirestore()
    }
}