package com.example.eventlist.interfaces

interface MenuInformationInterface {

    interface MenuInformationView{
        fun logOutGoLogin()
    }

    interface MenuInformationPresenter{
        fun logOut()
        fun logOutSuccesfull()
    }

    interface MenuInformationInteractor{
        fun logOut()
    }
}