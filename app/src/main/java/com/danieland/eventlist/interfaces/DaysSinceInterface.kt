package com.danieland.eventlist.interfaces

import com.danieland.eventlist.database.entities.Event


interface DaysSinceInterface {

    interface DaysSinceView {
        fun showEvents(listEvent: List<Event>)
        fun showEmptyEvents()
    }

    interface DaysSincePresenter {
        fun getItems()
        fun getItemsSuccesfull(listEvent: List<Event>)
    }

    interface DaysSinceInteractor {
        fun getItemsDB()
    }
}