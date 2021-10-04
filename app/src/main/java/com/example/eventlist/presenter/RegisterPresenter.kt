package com.example.eventlist.presenter

import android.util.Log
import com.example.eventlist.interfaces.RegisterInterface
import com.example.eventlist.model.RegisterInteractor
import com.example.eventlist.util.Util
import com.example.eventlist.view.fragments.RegisterView

class RegisterPresenter(private var view : RegisterView) : RegisterInterface.RegisterPresenter {

    private var interactor = RegisterInteractor(this)

    override fun registerWithEmailAndPassword(email: String, password: String, confirmPassword: String) {
        if(checkFormatEmailAndPassword(email, password, confirmPassword)){
            if(checkPasswordIndentity(password,confirmPassword)){
                Log.v(Util.TAG_REGISTER, "Presenter comunicando con el Interactor..")
                interactor.registerWithEmailAndPassword(email, password)
            }else{
                view.showMessageErrorRegister("Las contraseñas no son idénticas")
            }
        }else{
            view.showMessageErrorRegister("Campos vacios")
        }
    }

    override fun checkFormatEmailAndPassword(email: String, password: String, confirmPassword: String): Boolean {
        if(email == "" || password == "" || confirmPassword == "" ){
            return false
        }
            return true
        }


    override fun checkPasswordIndentity(password: String, confirmPassword: String) : Boolean {
        if (password == confirmPassword) {
            return true
        }
        return false
    }

    override fun registerSuccesfull() {
        view.goHome()
    }

    override fun registerIncorrect() {
        view.showMessageErrorRegister("Error en la creación del usuario, vuelva a intentarlo más tarde")
    }
}