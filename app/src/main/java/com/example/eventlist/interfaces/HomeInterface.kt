package com.example.eventlist.interfaces

import android.content.Context
import com.example.eventlist.database.entities.Event

interface HomeInterface {

    interface HomeView {
        fun showLog(message: String)
        fun implementsCommunicationWithSQLite(EventList: MutableList<Event>)
    }

    interface HomePresenter {
        //PRESENTER - INTERACTOR
        fun downloadAllEvents()

        //PRESENTER-VIEW
        fun downloadEventSuccessful(EventList: MutableList<Event>)
        fun downloadEventError()

        fun importEvents(EventList: MutableList<Event>, context: Context)
    }

    interface HomeInteractor {
        fun downloadAllEventsFirebase()
        fun importEventsSQLite(EventList: MutableList<Event>, context: Context)
    }
}