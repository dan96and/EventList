package com.example.eventlist.presenter

import android.content.Context
import android.util.Log
import com.example.eventlist.interfaces.HomeInterface
import com.example.eventlist.model.HomeInteractor
import com.example.eventlist.database.entities.Event
import com.example.eventlist.util.Util
import com.example.eventlist.view.activities.HomeView

class HomePresenter(val view: HomeView) : HomeInterface.HomePresenter {

    private val interactor = HomeInteractor(this)

    //PRESENTER - INTERACTOR
    //FIREBASE
    override fun downloadAllEvents() {
        Log.v(Util.TAG_HOME, "Comunicando presenter con interactor..")
        interactor.downloadAllEventsFirebase()
    }

    //SQLITE
    override fun importEvents(EventList: MutableList<Event>, context: Context) {
        Log.v(Util.TAG_HOME, "Comunicando presenter con interactor..")
        interactor.importEventsSQLite(EventList,context)
    }

    //PRESENTER-VIEW
    //FIREBASE
    override fun downloadEventSuccessful(EventList: MutableList<Event>) {
        view.implementsCommunicationWithSQLite(EventList)
    }

    override fun downloadEventError() {
        view.showLog("Error al descargar los evento de Firebase")
    }
}