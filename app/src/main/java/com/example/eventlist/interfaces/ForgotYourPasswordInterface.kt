package com.example.eventlist.interfaces

interface ForgotYourPasswordInterface {

    interface ForgotYourPasswordView {
        fun showMessage(message: String)
        fun showMessageCloseFragment(message: String)
    }

    interface ForgotYourPasswordPresenter {
        //PRESENTER-INTERACTOR
        fun sendPassword(email: String)

        //INTERACTOR-PRESENTER
        fun sendEmailSuccesfull()
        fun sendEmailError()

        //CHECK
        fun checkEmptyFields(email: String): Boolean
    }

    interface ForgotYourPasswordInteractor {
        fun sendEmailWithPasswordFirebase(email: String)
    }
}