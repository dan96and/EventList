package com.example.eventlist.model

import android.util.Log
import com.example.eventlist.interfaces.ForgotYourPasswordInterface
import com.example.eventlist.presenter.ForgotYourPasswordPresenter
import com.example.eventlist.util.Util
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class ForgotYourPasswordInteractor(private val presenter: ForgotYourPasswordPresenter) :
    ForgotYourPasswordInterface.ForgotYourPasswordInteractor {

    override fun sendEmailWithPasswordFirebase(email: String) {
        Firebase.auth.sendPasswordResetEmail(email).addOnCompleteListener {
            if (it.isSuccessful) {
                Log.v(Util.TAG_FORGOTPASSWORD,"Email enviado correctamente. Comunicando interactor con el presenter..")
                presenter.sendEmailSuccesfull()
            }else{
                Log.v(Util.TAG_FORGOTPASSWORD,"Fallo al enviar el email. Comunicando interactor con el presenter..")
                presenter.sendEmailError()
            }
        }
    }
}