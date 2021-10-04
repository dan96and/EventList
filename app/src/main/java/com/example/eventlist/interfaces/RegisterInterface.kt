package com.example.eventlist.interfaces

interface RegisterInterface {

    interface RegisterView {
        fun goHome()
        fun showMessageErrorRegister(message: String)
    }

    interface RegisterPresenter {
        //PRESENTER - INTERACTOR
        fun registerWithEmailAndPassword(email: String, password: String, confirmPassword: String)

        //INTERACTOR - PRESENTER
        fun registerSuccesfull()
        fun registerIncorrect()

        //PRESENTER
        fun checkFormatEmailAndPassword(email: String, password: String, confirmPassword: String): Boolean
        fun checkPasswordIndentity(password: String, confirmPassword: String): Boolean
    }

    interface RegisterInteractor {
        fun registerWithEmailAndPassword(email: String, password: String)
    }
}