package com.example.eventlist.model

import android.util.Log
import com.example.eventlist.interfaces.EventSinceInterface
import com.example.eventlist.objects.EventApp
import com.example.eventlist.presenter.EventSincePresenter
import com.example.eventlist.util.Util
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class EventSinceInteractor(private var presenter: EventSincePresenter) :
    EventSinceInterface.EventSinceInteractor {

    override fun uploadEventsSinceSqlite() {
        CoroutineScope(Dispatchers.Main).launch {
           val eventListSince = withContext(Dispatchers.IO){ EventApp.getDB().eventDao().showSinceEvents()}

            Log.v(Util.TAG_SHOW_EVENTSINCE, "Eventos descargados correctamente. Comunicando interactor con el presenter..")
            presenter.uploadEventsSinceSuccessful(eventListSince)
        }
    }
}