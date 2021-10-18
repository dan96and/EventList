package com.example.eventlist.presenter

import android.util.Log
import com.example.eventlist.interfaces.EventSinceInterface
import com.example.eventlist.model.EventSinceInteractor
import com.example.eventlist.objects.Event
import com.example.eventlist.util.Util
import com.example.eventlist.view.fragments.DaysSinceView

class EventSincePresenter(private val view: DaysSinceView) : EventSinceInterface.EventSincePresenter {

    var interactor = EventSinceInteractor(this)

    override fun uploadEventsSince() {
        Log.v(Util.TAG_SHOW_EVENTSINCE," Comunicando presenter con interactor..")
        interactor.uploadEventsSinceFirestore()
    }

    override fun uploadEventsSinceSuccessful(eventSinceList:MutableList<Event>) {
        Log.v(Util.TAG_SHOW_EVENTSINCE,"Transferiendo lista de eventos. Comunicando presenter con view..")
        view.showEventSince(eventSinceList)
    }
}