package com.example.eventlist.presenter

import android.util.Log
import androidx.lifecycle.LiveData
import com.example.eventlist.interfaces.EventSinceInterface
import com.example.eventlist.model.EventSinceInteractor
import com.example.eventlist.database.entities.Event
import com.example.eventlist.util.Util
import com.example.eventlist.view.fragments.DaysSinceView

class EventSincePresenter(private val view: DaysSinceView) :
    EventSinceInterface.EventSincePresenter {

    var interactor = EventSinceInteractor(this)

    override fun uploadEventsSince() {
        Log.v(Util.TAG_SHOW_EVENTSINCE, " Comunicando presenter con interactor..")
        interactor.uploadEventsSinceSqlite()
    }

    override fun uploadEventsSinceSuccessful(eventSinceList: LiveData<List<Event>>) {
        // var eventSinceList2 : List<Event> = Transformations.switchMap(eventSinceList)
    }

    //CheckEmptyEvents
    override fun checkEmptyEventList(eventSinceList: LiveData<List<Event>>) {
        Log.v(Util.TAG_SHOW_EVENTSINCE, "Transferiendo lista de eventos. Comunicando presenter con view..")
        if (0 == 0) {
            view.showScreenNoEvents()
        } else {
            view.showEventSince(eventSinceList)
        }
    }
}