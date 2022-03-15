package com.danieland.eventlist.model

import android.util.Log
import com.danieland.eventlist.interfaces.DaysSinceInterface
import com.danieland.eventlist.objects.EventApp
import com.danieland.eventlist.presenter.DaysSincePresenter
import com.danieland.eventlist.util.Util
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DaysSinceInteractor(val presenter: DaysSincePresenter) :
    DaysSinceInterface.DaysSinceInteractor {

    override fun getItemsDB(){
        CoroutineScope(Dispatchers.Main).launch {
            val listEvent = withContext(Dispatchers.IO) { EventApp.getDB().eventDao().showSinceEvents() }
            Log.v(Util.TAG_SHOW_EVENTSINCE, "EventsSince obtenidos.. Comunicando con el presenter.")
            presenter.getItemsSuccesfull(listEvent)
        }
    }
}