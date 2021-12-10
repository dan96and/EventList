package com.example.eventlist.model

import android.util.Log
import com.example.eventlist.interfaces.EventUntilInterface
import com.example.eventlist.database.entities.Event
import com.example.eventlist.presenter.EventUntilPresenter
import com.example.eventlist.util.Util

class EventUntilInteractor (private val presenter : EventUntilPresenter):EventUntilInterface.EventUntilInteractor {

    private var eventSinceList = mutableListOf<Event>()
    private lateinit var name: String
    private lateinit var date: String
    private lateinit var dateCreated: String
    private lateinit var typeEvent: String
    private var notification: Boolean = false
    private lateinit var id: String

    override fun uploadEventUntilFirestore() {
        Util.db.collection(Util.userId).get().addOnSuccessListener { result ->
            for (document in result) {
                if(document.getString("typeEvent")=="EventUntil"){
                    name = document.getString("name")!!
                    date = document.getString("date")!!
                    dateCreated = document.getString("dateCreated")!!
                    typeEvent = document.getString("typeEvent")!!
                    notification = document.getBoolean("notification")!!
                    id = document.getString("id")!!
                    //eventSinceList.add(Event(name, dateCreated, date, typeEvent, notification, id.toInt()))
                }else{
                    continue
                }
            }
            Log.v(Util.TAG_SHOW_EVENTUNTIL,"Eventos descargados correctamente. Comunicando interactor con el presenter..")
            presenter.uploadEventUntilSuccesfull(eventSinceList)
        }
            .addOnFailureListener {
                Log.v(Util.TAG_SHOW_EVENTUNTIL,"Fallo al descargar los eventos. Comunicando interactor con el presenter..")
            }
    }
}