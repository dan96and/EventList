package com.example.eventlist.model

import android.util.Log
import com.example.eventlist.interfaces.EventSinceInterface
import com.example.eventlist.database.entities.Event
import com.example.eventlist.presenter.EventSincePresenter
import com.example.eventlist.util.Util

class EventSinceInteractor(private var presenter: EventSincePresenter) :
    EventSinceInterface.EventSinceInteractor {

    private var eventSinceList = mutableListOf<Event>()
    private lateinit var name: String
    private lateinit var date: String
    private lateinit var dateCreated: String
    private lateinit var typeEvent: String
    private var notification: Boolean = false
    private var id: Int = 0

    override fun uploadEventsSinceFirestore() {
        Util.db.collection(Util.userId).get().addOnSuccessListener { result ->
            for (document in result) {
                if(document.getString("typeEvent")=="EventSince"){
                    name = document.getString("name")!!
                    date = document.getString("date")!!
                    dateCreated = document.getString("dateCreated")!!
                    typeEvent = document.getString("typeEvent")!!
                    notification = document.getBoolean("notification")!!
                    id = (document.get("id") as Int?)!!
                    eventSinceList.add(Event(name, dateCreated, date, typeEvent, notification, id))
                }else{
                    continue
                }
            }
            Log.v(Util.TAG_SHOW_EVENTSINCE,"Eventos descargados correctamente. Comunicando interactor con el presenter..")
            presenter.uploadEventsSinceSuccessful(eventSinceList)
        }
            .addOnFailureListener {
                Log.v(Util.TAG_SHOW_EVENTSINCE,"Fallo al descargar los eventos. Comunicando interactor con el presenter..")
            }
    }
}