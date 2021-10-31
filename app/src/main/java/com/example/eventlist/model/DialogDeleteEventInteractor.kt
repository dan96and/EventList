package com.example.eventlist.model

import android.util.Log
import com.example.eventlist.interfaces.DialogDeleteEventInterface
import com.example.eventlist.presenter.DialogDeleteEventPresenter
import com.example.eventlist.util.Util

class DialogDeleteEventInteractor(private val presenter: DialogDeleteEventPresenter) :
    DialogDeleteEventInterface.DialogDeleteEventInteractor {

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