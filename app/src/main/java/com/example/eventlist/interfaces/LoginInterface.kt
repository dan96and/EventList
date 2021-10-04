package com.example.eventlist.interfaces

interface LoginInterface {

    interface LoginView {
        fun goHomeView()
        fun showMessageErrorLogin(message: String)
    }

    interface LoginPresenter {

        //PRESENTER - VALIDATION
        fun checkFormatEmailAndPassword(email: String, password: String): Boolean

        //PRESENTER - INTERACTOR
        fun signWithEmailAndPassword(email: String, password: String)

        //INTERACTOR - PRESENTER
        fun signSuccesfull()
        fun signIncorrect()
    }

    interface LoginInteractor {
        fun signWithEmailAndPassword(email: String, password: String)
    }
}