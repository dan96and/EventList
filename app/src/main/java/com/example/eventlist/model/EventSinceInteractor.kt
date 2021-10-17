package com.example.eventlist.model

import android.util.Log
import com.example.eventlist.interfaces.EventSinceInterface
import com.example.eventlist.objects.EventSince
import com.example.eventlist.presenter.EventSincePresenter
import com.example.eventlist.util.Util

class EventSinceInteractor(private var presenter: EventSincePresenter) :
    EventSinceInterface.EventSinceInteractor {

    private var eventSinceList = mutableListOf<EventSince>()
    private lateinit var name: String
    private lateinit var date: String
    private lateinit var dateCreated: String

    override fun uploadEventsSinceFirestore() {
        Util.db.collection(Util.userId).get().addOnSuccessListener { result ->
            for (document in result) {
                name = document.getString("name")!!
                date = document.getString("date")!!
                dateCreated = document.getString("dateCreated")!!
                eventSinceList.add(EventSince(name, dateCreated, date))
            }
            Log.v(Util.TAG_SHOW_EVENTSINCE,"Eventos descargados correctamente. Comunicando interactor con el presenter..")
            presenter.uploadEventsSinceSuccessful(eventSinceList)
        }
            .addOnFailureListener {
                Log.v(Util.TAG_SHOW_EVENTSINCE,"Fallo al descargar los eventos. Comunicando interactor con el presenter..")
            }
    }
}