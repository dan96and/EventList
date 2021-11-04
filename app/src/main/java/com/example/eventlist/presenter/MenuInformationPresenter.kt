package com.example.eventlist.presenter

import android.util.Log
import com.example.eventlist.interfaces.MenuInformationInterface
import com.example.eventlist.model.MenuInformationInteractor
import com.example.eventlist.util.Util
import com.example.eventlist.view.fragments.MenuInformationView

class MenuInformationPresenter(private val view: MenuInformationView) : MenuInformationInterface.MenuInformationPresenter {

    private var interactor = MenuInformationInteractor(this)

    //PRESENTER-INTERACTOR
    override fun logOut() {
        Log.v(Util.TAG_MENU_LOGOUT, "Presenter llamando al Interactor..")
        interactor.logOutFirebase()
    }

    override fun deleteAccount() {
        Log.v(Util.TAG_DELETEACCOUNT, "Presenter comunicando con el interactor..")
        interactor.deleteAccountFirebase()
    }

    //PRESENTER-VIEW
    override fun logOutSuccesfull() {
        Log.v(Util.TAG_MENU_LOGOUT, "Presenter llamando a la Vista..")
        view.goToHomeLoginScreen()
    }

    override fun deleteAccountSuccesfull() {
        Log.v(Util.TAG_DELETEACCOUNT, "Presenter comunicando con el view..")
        view.showDialogCompleteDeleteAccount()
    }

}