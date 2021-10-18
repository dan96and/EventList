package com.example.eventlist.interfaces

import com.example.eventlist.objects.Event

interface EventUntilInterface {

    interface EventUntilView{
        fun showEventUntil(listEventUntil:MutableList<Event>)
    }

    interface EventUntilPresenter{
        fun uploadEventUntilFirestore()

        fun uploadEventUntilSuccesfull(listEventUntil:MutableList<Event>)
    }

    interface EventUntilInteractor{
        fun uploadEventUntilFirestore()
    }
}