package com.example.eventlist.model

import android.util.Log
import com.example.eventlist.interfaces.EventUntilInterface
import com.example.eventlist.objects.EventApp
import com.example.eventlist.presenter.EventUntilPresenter
import com.example.eventlist.util.Util
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class EventUntilInteractor (private val presenter : EventUntilPresenter):EventUntilInterface.EventUntilInteractor {

    override fun uploadEventUntilSqlite() {
            CoroutineScope(Dispatchers.Main).launch{
                val eventListUntil = withContext(Dispatchers.IO){ EventApp.getDB().eventDao().showUntilEvents()}

                Log.v(Util.TAG_SHOW_EVENTUNTIL,"Eventos descargados correctamente. Comunicando interactor con el presenter..")
                presenter.uploadEventUntilSuccesfull(eventListUntil)
            }
    }
}