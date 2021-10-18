package com.example.eventlist.model

import android.util.Log
import com.example.eventlist.interfaces.NewEventInterface
import com.example.eventlist.objects.Event
import com.example.eventlist.presenter.NewEventPresenter
import com.example.eventlist.util.Util

class NewEventInteractor(private var presenter: NewEventPresenter) : NewEventInterface.NewEventInteractor {

    override fun uploadEventFireStore(event:Event) {
        val eventSince = hashMapOf(
            "name" to event.title,
            "date" to event.date,
            "dateCreated" to event.dateCreation,
            "typeEvent" to event.typeEvent,
            "notification" to event.notification
        )

        Util.db.collection(Util.userId).document().set(eventSince).addOnSuccessListener {
            Log.v(Util.TAG_NEW_EVENT,"Evento creado correctamente, comunicando interactor con presenter..")
            presenter.uploadEventCorrect()
        }
            .addOnFailureListener {
                Log.v(Util.TAG_NEW_EVENT,"ERROR al crear evento, comunicando interactor con presenter..")
                presenter.uploadEventError()
            }
    }
}