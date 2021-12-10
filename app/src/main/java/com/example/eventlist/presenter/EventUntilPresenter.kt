package com.example.eventlist.presenter

import android.util.Log
import com.example.eventlist.interfaces.EventUntilInterface
import com.example.eventlist.model.EventUntilInteractor
import com.example.eventlist.database.entities.Event
import com.example.eventlist.util.Util
import com.example.eventlist.view.fragments.DaysUntilView

class EventUntilPresenter(val view: DaysUntilView) : EventUntilInterface.EventUntilPresenter {

    private var interactor = EventUntilInteractor(this)

    override fun uploadEventUntil() {
        Log.v(Util.TAG_SHOW_EVENTUNTIL, "Comunicando presenter con el interactor..")
        interactor.uploadEventUntilSqlite()
    }

    override fun uploadEventUntilSuccesfull(listEventUntil: MutableList<Event>) {
        checkEmptyEventList(listEventUntil)
    }

    override fun checkEmptyEventList(eventSinceList: MutableList<Event>) {
        Log.v(Util.TAG_SHOW_EVENTUNTIL, "Comunicando presenter con la view..")
        if (eventSinceList.size == 0) {
            view.showScreenNoEvents()
        } else {
            view.showEventUntil(eventSinceList)
        }
    }
}