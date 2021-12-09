package com.example.eventlist.interfaces

import com.example.eventlist.database.entities.Event

interface EventUntilInterface {

    interface EventUntilView{
        fun showEventUntil(listEventUntil:MutableList<Event>)
        fun showScreenNoEvents()
    }

    interface EventUntilPresenter{
        fun uploadEventUntilFirestore()

        fun uploadEventUntilSuccesfull(listEventUntil:MutableList<Event>)

        //PRESENTER CHECK
        fun checkEmptyEventList(eventSinceList: MutableList<Event>)
    }

    interface EventUntilInteractor{
        fun uploadEventUntilFirestore()
    }
}