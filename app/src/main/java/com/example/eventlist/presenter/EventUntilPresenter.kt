package com.example.eventlist.presenter

import android.util.Log
import com.example.eventlist.interfaces.EventUntilInterface
import com.example.eventlist.model.EventUntilInteractor
import com.example.eventlist.objects.Event
import com.example.eventlist.util.Util
import com.example.eventlist.view.fragments.DaysUntilView

class EventUntilPresenter(val view : DaysUntilView) : EventUntilInterface.EventUntilPresenter {

    private var interactor = EventUntilInteractor(this)

    override fun uploadEventUntilFirestore() {
        Log.v(Util.TAG_SHOW_EVENTUNTIL,"Comunicando presenter con el interactor..")
        interactor.uploadEventUntilFirestore()
    }

    override fun uploadEventUntilSuccesfull(listEventUntil:MutableList<Event>) {
        Log.v(Util.TAG_SHOW_EVENTUNTIL,"Comunicando presenter con la view..")
        view.showEventUntil(listEventUntil)
    }
}