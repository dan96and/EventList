package com.example.eventlist.model

import android.util.Log
import com.example.eventlist.interfaces.MenuInformationInterface
import com.example.eventlist.presenter.MenuInformationPresenter
import com.example.eventlist.util.Util
import com.google.firebase.auth.FirebaseAuth

class MenuInformationInteractor (private var presenter: MenuInformationPresenter): MenuInformationInterface.MenuInformationInteractor {

    var auth = FirebaseAuth.getInstance()

    override fun logOut() {
        auth.signOut()
        Log.v(Util.TAG_REGISTER, "Interactor llamando al Presenter..")
        presenter.logOutSuccesfull()
    }
}