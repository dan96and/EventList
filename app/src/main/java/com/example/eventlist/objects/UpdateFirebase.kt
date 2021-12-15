package com.example.eventlist.objects

import android.util.Log
import com.example.eventlist.database.entities.Event
import com.example.eventlist.util.Util
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class UpdateFirebase {

    private fun downloadEventsSqliteActive(): MutableList<Event> {

        var mutableListEventsActive = mutableListOf<Event>()
        CoroutineScope(Dispatchers.Main).launch {
            mutableListEventsActive = withContext(Dispatchers.IO) {
                EventApp.getDB().eventDao().downloadAllEventsActives()
            }
        }
        for (i in 0..mutableListEventsActive.size) {
            Log.v("Test",mutableListEventsActive[i].name)
        }

        return mutableListEventsActive
    }

    fun uploadEventsFirebase() {
        val mutableListEventsActive = downloadEventsSqliteActive()

        for (i in 0..mutableListEventsActive.size) {
            val event = hashMapOf(
                "id" to mutableListEventsActive[i].idEvent,
                "name" to mutableListEventsActive[i].name,
                "dateCreation" to mutableListEventsActive[i].dateCreation,
                "date" to mutableListEventsActive[i].date,
                "typeEvent" to mutableListEventsActive[i].typeEvent,
                "notification" to mutableListEventsActive[i].notification,
                "deleteEvent" to mutableListEventsActive[i].deleteEvent
            )

            Util.db.collection("Users").document(Util.userId).collection("Events")
                .document(mutableListEventsActive[1].idEvent.toString()).set(event)
                .addOnSuccessListener {
                    Log.v("Test", "Evento subido")
                }
        }
    }
}