package com.example.eventlist.presenter

import android.util.Log
import com.example.eventlist.interfaces.DialogDeleteEventInterface
import com.example.eventlist.model.DialogDeleteEventInteractor
import com.example.eventlist.util.Util
import com.example.eventlist.view.fragments.DialogDeleteEventFragmentView

class DialogDeleteEventPresenter(private val view: DialogDeleteEventFragmentView):DialogDeleteEventInterface.DialogDeleteEventPresenter {

    private val interactor = DialogDeleteEventInteractor(this)

    //PRESENTER-INTERACTOR
    override fun deleteEvent(idEvent: String) {
        Log.v(Util.TAG_SHOW_DELETEEVENT,"Presenter comunicando con el interactor..")
        interactor.deleteEventFireStore(idEvent)
    }

    //INTERACTOR-PRESENTER
    override fun deleteEventSuccesfull() {
        Log.v(Util.TAG_SHOW_DELETEEVENT,"Presenter comunicando con la view.. Evento eliminado")
        view.closeActivityShowMessage("Evento eliminado")
    }

    override fun deleteEventError() {
        Log.v(Util.TAG_SHOW_DELETEEVENT,"Presenter comunicando con la view.. Error al eliminar el evento")
        view.closeFragmentShowMessage("Error al eliminar el evento")
    }

}