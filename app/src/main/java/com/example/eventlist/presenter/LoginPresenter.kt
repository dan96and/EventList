package com.example.eventlist.presenter

import android.util.Log
import com.example.eventlist.interfaces.LoginInterface
import com.example.eventlist.model.LoginInteractor
import com.example.eventlist.util.Util
import com.example.eventlist.view.fragments.LoginView

class LoginPresenter(private var view: LoginView) : LoginInterface.LoginPresenter {

    private var interactor = LoginInteractor(this)

    override fun checkFormatEmailAndPassword(email: String, password: String): Boolean {
        if (email != "" && password != "") {
            return true
        }
        return false
    }

    override fun signWithEmailAndPassword(email: String, password: String) {
        if (checkFormatEmailAndPassword(email, password)) {
            interactor.signWithEmailAndPassword(email, password)
            Log.v(Util.TAG_LOGIN, "Presenter llamando al Interactor..")
        } else {
            view.showMessageErrorLogin("Campos vacios")
        }

    }

    override fun signSuccesfull() {
        Log.v(Util.TAG_LOGIN, "Presenter llamando a la vista..")
        view.goHomeView()
    }

    override fun signIncorrect() {
        Log.v(Util.TAG_LOGIN, "Presenter llamando a la vista..")
        view.showMessageErrorLogin("Usuario o contraseña incorrectos")

    }
}