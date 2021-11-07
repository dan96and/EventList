package com.example.eventlist.model

import android.util.Log
import com.example.eventlist.interfaces.EditEventInterface
import com.example.eventlist.objects.Event
import com.example.eventlist.presenter.EditEventPresenter
import com.example.eventlist.util.Util

class EditEventInteractor(val presenter: EditEventPresenter):EditEventInterface.EditEventInteractor {

    override fun uploadChangesEvent(event: Event) {
        Util.db.collection(Util.userId).document(event.idEvent).update("name", event.title, "date", event.date,"typeEvent", event.typeEvent, "notification", event.notification ).addOnSuccessListener {
            Log.v(Util.TAG_NEW_EVENT,"Evento modificado correctamente, comunicando interactor con presenter..")
            presenter.editEventCorrect(event)
        }
            .addOnFailureListener {
                Log.v(Util.TAG_NEW_EVENT,"ERROR al editar evento, comunicando interactor con presenter..")
                presenter.editEventError()
            }
    }

    override fun deleteEventFireStore(idEvent: String) {
        Util.db.collection(Util.userId).document(idEvent).delete()
            .addOnSuccessListener {
                Log.v(Util.TAG_SHOW_DELETEEVENT,"Evento eliminado, comunicando con el presenter..")
                presenter.deleteEventSuccesfull()
            }
            .addOnFailureListener {
                Log.v(Util.TAG_SHOW_DELETEEVENT,"Error al eliminar el evento eliminado, comunicando con el presenter..")
                presenter.deleteEventError()
            }
    }
}