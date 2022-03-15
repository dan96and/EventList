package com.danieland.eventlist.presenter

import android.util.Log
import com.danieland.eventlist.database.entities.Event
import com.danieland.eventlist.interfaces.DaysSinceInterface
import com.danieland.eventlist.model.DaysSinceInteractor
import com.danieland.eventlist.util.Util
import com.danieland.eventlist.view.fragments.DaysSinceView

class DaysSincePresenter(private val view: DaysSinceView):DaysSinceInterface.DaysSincePresenter {

    private val interactor = DaysSinceInteractor(this)

    override fun getItems() {
        Log.v(Util.TAG_SHOW_EVENTSINCE, "Accediendo al interactor para hacer la peticion..")
        interactor.getItemsDB()
    }

    override fun getItemsSuccesfull(listEvent: List<Event>) {
        if(listEvent.isEmpty()){
            Log.v(Util.TAG_SHOW_EVENTSINCE, "Sin eventos.. Accediendo a la view.")
            view.showEmptyEvents()
        }else{
            Log.v(Util.TAG_SHOW_EVENTSINCE, "Mostrar eventos.. Accediendo a la view")
            view.showEvents(listEvent)
        }
    }
}