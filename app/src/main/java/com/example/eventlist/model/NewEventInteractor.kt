package com.example.eventlist.model

import android.util.Log
import com.example.eventlist.interfaces.NewEventInterface
import com.example.eventlist.presenter.NewEventPresenter
import com.example.eventlist.util.Util
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class NewEventInteractor(private var presenter: NewEventPresenter) : NewEventInterface.NewEventInteractor {

    private val db = Firebase.firestore
    private val userId = Firebase.auth.currentUser!!.uid

    override fun uploadEventFireStore(name: String, date: String, notification: Boolean) {
        val prueba = hashMapOf(
            "name" to name,
            "date" to date,
            "notification" to notification
        )

        db.collection(userId).add(prueba).addOnSuccessListener { documentReference ->
            Log.v(Util.TAG_NEW_EVENT,"Evento creado correctamente, comunicando interactor con presenter..")
            presenter.uploadEventCorrect()
        }
            .addOnFailureListener { e ->
                Log.v(Util.TAG_NEW_EVENT,"ERROR al crear evento, comunicando interactor con presenter..")
                presenter.uploadEventError()
            }
    }
}