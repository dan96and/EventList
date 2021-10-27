package com.example.eventlist.presenter

import android.util.Log
import com.example.eventlist.interfaces.EditEventInterface
import com.example.eventlist.model.EditEventInteractor
import com.example.eventlist.objects.Event
import com.example.eventlist.util.Util
import com.example.eventlist.view.activities.EditEventView

class EditEventPresenter(val view: EditEventView) : EditEventInterface.EditEventPresenter {

    val interactor = EditEventInteractor(this)

    //PRESENTER-INTERACTOR
    override fun saveChangesEvent(event: Event) {
        if (checkFieldsEmpty(event.title, event.date)) {
            Log.v(Util.TAG_NEW_EVENT,"Comunicando presentador con el interactor..")
            interactor.uploadChangesEvent(event)
        }else{
            Log.v(Util.TAG_NEW_EVENT,"Comunicando presentador con el vista..")
            view.showMessage("Hay campos vacios")
        }
    }
    //INTERACTOR - PRESENTER
    override fun editEventCorrect(event: Event) {
        view.closeActivityAndShowMessage("Cambios realizados correctamente")
    }

    override fun editEventError() {
        view.showMessage("Error al guardar los cambios")
    }

    //CHECKS
    override fun checkFieldsEmpty(title: String, date: String): Boolean {
        return !(title == "" || date == "")
    }
}