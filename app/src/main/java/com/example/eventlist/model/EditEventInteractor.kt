package com.example.eventlist.model

import android.util.Log
import com.example.eventlist.interfaces.EditEventInterface
import com.example.eventlist.database.entities.Event
import com.example.eventlist.objects.EventApp
import com.example.eventlist.presenter.EditEventPresenter
import com.example.eventlist.util.Util
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class EditEventInteractor(val presenter: EditEventPresenter) :
    EditEventInterface.EditEventInteractor {

    override fun uploadChangesEvent(event: Event) {
        CoroutineScope(Dispatchers.Main).launch {
            withContext(Dispatchers.IO) {
                EventApp.getDB().eventDao().editEvent(
                    event.idEvent,
                    event.notification,
                    event.name,
                    event.date,
                    event.typeEvent
                )
            }
            Log.v(
                Util.TAG_NEW_EVENT,
                "Evento modificado correctamente, comunicando interactor con presenter.."
            )
            presenter.editEventCorrect()
        }
    }

    override fun deleteEventSqlite(idEvent: Int) {
        CoroutineScope(Dispatchers.Main).launch {
            withContext(Dispatchers.IO){ EventApp.getDB().eventDao().deleteEvent(idEvent)}
        }
        Log.v(Util.TAG_SHOW_DELETEEVENT, "Evento eliminado, comunicando con el presenter..")
        presenter.deleteEventSuccesfull()

    }
}