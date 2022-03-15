package com.danieland.eventlist.model

import android.util.Log
import com.danieland.eventlist.interfaces.DaysUntilInterface
import com.danieland.eventlist.objects.EventApp
import com.danieland.eventlist.presenter.DaysUntilPresenter
import com.danieland.eventlist.util.Util
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DaysUntilInteractor(private val presenter: DaysUntilPresenter): DaysUntilInterface.DaysUntilInteractor {
    override fun getItemsDB() {
       CoroutineScope(Dispatchers.Main).launch {
           val lisItem = withContext(Dispatchers.IO){ EventApp.getDB().eventDao().showUntilEvents() }
           Log.v(Util.TAG_SHOW_EVENTUNTIL, "Accediendo al presenter, devolviendo una lista de EventUntil..")
           presenter.getItemsSuccesfull(lisItem)
       }
    }
}