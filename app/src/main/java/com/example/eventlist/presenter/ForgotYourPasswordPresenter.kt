package com.example.eventlist.presenter

import android.util.Log
import com.example.eventlist.interfaces.ForgotYourPasswordInterface
import com.example.eventlist.model.ForgotYourPasswordInteractor
import com.example.eventlist.util.Util
import com.example.eventlist.view.fragments.ForgotYourPasswordView

class ForgotYourPasswordPresenter(private val view: ForgotYourPasswordView) :
    ForgotYourPasswordInterface.ForgotYourPasswordPresenter {

    private val interactor = ForgotYourPasswordInteractor(this)

    //PRESENTER-INTERACTOR
    override fun sendPassword(email: String) {
        if (checkEmptyFields(email)) {
            Log.v(Util.TAG_FORGOTPASSWORD,"Comunicando presenter con la view..")
            view.showMessage("Campo vacio. Completa el campo email.")
        } else {
            Log.v(Util.TAG_FORGOTPASSWORD,"Comunicando presenter con el interactor..")
            interactor.sendEmailWithPasswordFirebase(email)
        }
    }

    override fun sendEmailSuccesfull() {
        Log.v(Util.TAG_FORGOTPASSWORD,"Comunicando presenter con la view..")
        view.showMessageCloseFragment("Te hemos enviado un email para resetear la contraseña. Comprueba tu email!")
    }

    override fun sendEmailError() {
        Log.v(Util.TAG_FORGOTPASSWORD,"Comunicando presenter con la view..")
        view.showMessage("Comprueba que has escrito bien tu correo!")
    }

    //CHECK
    override fun checkEmptyFields(email: String): Boolean {
        return email == ""
    }
}