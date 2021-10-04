package com.example.eventlist.model

import android.util.Log
import com.example.eventlist.interfaces.RegisterInterface
import com.example.eventlist.presenter.RegisterPresenter
import com.example.eventlist.util.Util
import com.google.firebase.auth.FirebaseAuth

class RegisterInteractor(private var presenter: RegisterPresenter) : RegisterInterface.RegisterInteractor {

    private var auth = FirebaseAuth.getInstance()

    override fun registerWithEmailAndPassword(email: String, password: String) {
        auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener { task ->
            if(task.isSuccessful){
                Log.v(Util.TAG_REGISTER, "Usuario registrado correctamente, comunicando con el Presenter..")
                presenter.registerSuccesfull()
            }else{
                Log.v(Util.TAG_REGISTER, "Usuario NO registrado, comunicando con el Presenter..")
                presenter.registerIncorrect()
            }
        }
    }
}