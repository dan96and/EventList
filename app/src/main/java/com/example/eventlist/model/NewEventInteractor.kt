package com.example.eventlist.model

import android.util.Log
import com.example.eventlist.interfaces.NewEventInterface
import com.example.eventlist.database.entities.Event
import com.example.eventlist.objects.EventApp
import com.example.eventlist.presenter.NewEventPresenter
import com.example.eventlist.util.Util
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class NewEventInteractor(private var presenter: NewEventPresenter) :
    NewEventInterface.NewEventInteractor {

    override fun addEventSqlite(event: Event) {

        CoroutineScope(Dispatchers.Main).launch {

            withContext(Dispatchers.IO) { EventApp.getDB().eventDao().addEvent(event) }

            Log.v(Util.TAG_NEW_EVENT, "Eventos creado correctamente. Comunicando interactor con el presenter..")
            presenter.uploadEventCorrect()
        }
    }
}