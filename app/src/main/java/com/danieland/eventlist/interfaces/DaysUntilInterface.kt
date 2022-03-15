package com.danieland.eventlist.interfaces

import com.danieland.eventlist.database.entities.Event

interface DaysUntilInterface {

    interface DaysUntilView {
        fun showEvents(listEvents: List<Event>)
        fun showEmptyScreen()
    }

    interface DaysUntilPresenter {
        fun getItems()
        fun getItemsSuccesfull(listEvents:List<Event>)
    }

    interface DaysUntilInteractor {
        fun getItemsDB()
    }

}