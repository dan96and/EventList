package com.danieland.eventlist.presenter

import android.util.Log
import com.danieland.eventlist.database.entities.Event
import com.danieland.eventlist.interfaces.DaysUntilInterface
import com.danieland.eventlist.model.DaysUntilInteractor
import com.danieland.eventlist.util.Util
import com.danieland.eventlist.view.fragments.DaysUntilView

class DaysUntilPresenter(private val view: DaysUntilView) : DaysUntilInterface.DaysUntilPresenter {

    private val interactor = DaysUntilInteractor(this)

    override fun getItems() {
        Log.v(Util.TAG_SHOW_EVENTUNTIL, "Accediendo al interactor, solicitando datos de Eventos Until..")
        interactor.getItemsDB()
    }

    override fun getItemsSuccesfull(listEvents: List<Event>) {
        if (listEvents.isEmpty()) {
            Log.v(Util.TAG_SHOW_EVENTUNTIL, "Accediendo a la view. No hay eventos que mostrar..")
            view.showEmptyScreen()
        } else {
            Log.v(Util.TAG_SHOW_EVENTUNTIL, "Accediendo al presenter. Mostrando eventos..")
            view.showEvents(listEvents)
        }
    }
}