package com.example.eventlist.interfaces

import androidx.lifecycle.LiveData
import com.example.eventlist.database.entities.Event

interface EventSinceInterface {

    interface EventSinceView {
        fun showEventSince(eventSinceList: LiveData<List<Event>>)
        fun showScreenNoEvents()
    }

    interface EventSincePresenter {
        //PRESENTER - INTERACTOR
        fun uploadEventsSince()

        //INTERACTOR-PRESENTER
        fun uploadEventsSinceSuccessful(eventSinceList: LiveData<List<Event>>)

        //PRESENTER CHECK
        fun checkEmptyEventList(eventSinceList: LiveData<List<Event>>)
    }

    interface EventSinceInteractor {
        fun uploadEventsSinceSqlite()
    }
}