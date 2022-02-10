package com.example.eventlist.interfaces

interface MenuInformationInterface {

    interface MenuInformationView {
        fun goToHomeLoginScreen()
    }

    interface MenuInformationPresenter {
        //PRESENTER-INTERACTOR
        fun logOut()
        fun deleteAccount()

        //PRESENTER-VIEW
        fun logOutSuccesfull()
        fun deleteAccountSuccesfull()

    }

    interface MenuInformationInteractor {
        fun logOutFirebase()
        fun deleteAccountFirebase()
    }
}