package com.example.eventlist.presenter

import android.util.Log
import com.example.eventlist.interfaces.MenuInformationInterface
import com.example.eventlist.model.MenuInformationInteractor
import com.example.eventlist.util.Util
import com.example.eventlist.view.fragments.MenuInformationView

class MenuInformationPresenter(private val view: MenuInformationView) : MenuInformationInterface.MenuInformationPresenter {

    private var interactor = MenuInformationInteractor(this)

    override fun logOut() {
        Log.v(Util.TAG_REGISTER, "Presenter llamando al Interactor..")
        interactor.logOut()
    }

    override fun logOutSuccesfull() {
        Log.v(Util.TAG_REGISTER, "Presenter llamando a la Vista..")
        view.logOutGoLogin()
    }

}