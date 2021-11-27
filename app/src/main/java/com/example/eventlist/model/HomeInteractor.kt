package com.example.eventlist.model

import android.content.Context
import android.util.Log
import com.example.eventlist.objects.EventsSQLite
import com.example.eventlist.interfaces.HomeInterface
import com.example.eventlist.objects.Event
import com.example.eventlist.presenter.HomePresenter
import com.example.eventlist.util.Util

class HomeInteractor(private val presenter: HomePresenter) : HomeInterface.HomeInteractor {

    private lateinit var title: String
    private lateinit var date: String
    private lateinit var dateCreated: String
    private lateinit var typeEvent: String
    private var notification: Boolean = false
    private lateinit var id: String
    private val eventList = mutableListOf<Event>()

    override fun downloadAllEventsFirebase() {
        Util.db.collection(Util.userId).get().addOnSuccessListener { result ->
            for (document in result) {
                title = document.getString("name")!!
                date = document.getString("date")!!
                dateCreated = document.getString("dateCreated")!!
                typeEvent = document.getString("typeEvent")!!
                notification = document.getBoolean("notification")!!
                id = document.getString("id")!!
                eventList.add(Event(title, dateCreated, date, typeEvent, notification, id))
            }
            Log.v(
                Util.TAG_HOME,
                "Eventos descargados correctamente. Comunicando interactor con el presenter.."
            )
            presenter.downloadEventSuccessful(eventList)
        }
            .addOnFailureListener {
                Log.v(
                    Util.TAG_HOME,
                    "Fallo al descargar los eventos. Comunicando interactor con el presenter.."
                )
            }
    }

    override fun importEventsSQLite(EventList: MutableList<Event>, context: Context) {
        val sqliteEvent = EventsSQLite(context)
        //sqliteEvent.addEvents(EventList)
        sqliteEvent.saveChanges(EventList)
        Log.v(Util.TAG_HOME, "Eventos de Firebase importados en SQLite")
    }
}