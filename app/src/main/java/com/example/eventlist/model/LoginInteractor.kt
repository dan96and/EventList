package com.example.eventlist.model

import android.util.Log
import com.example.eventlist.interfaces.LoginInterface
import com.example.eventlist.presenter.LoginPresenter
import com.example.eventlist.util.Util
import com.google.firebase.auth.FirebaseAuth

class LoginInteractor(private var presenter: LoginPresenter) : LoginInterface.LoginInteractor {

    private var auth = FirebaseAuth.getInstance()

    //LOGIN
    override fun signWithEmailAndPassword(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                Log.v(Util.TAG_LOGIN, "Login satisfactorio, llamando al Presentador..")
                presenter.signSuccesfull()
            }else{
                Log.v(Util.TAG_LOGIN, "Login incorrecto, llamando al Presentador..")
                presenter.signIncorrect()
            }
        }
    }

    //AUTO-LOGIN
    override fun autoLogin() {
        if(auth.currentUser != null){
            presenter.autoLoginSuccesfull()
        }
    }
}