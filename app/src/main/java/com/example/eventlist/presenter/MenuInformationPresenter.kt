package com.example.eventlist.presenter

import com.example.eventlist.interfaces.MenuInformationInterface
import com.example.eventlist.model.MenuInformationInteractor
import com.example.eventlist.view.fragments.MenuInformationView

class MenuInformationPresenter(private val view: MenuInformationView) : MenuInformationInterface.MenuInformationPresenter {

    private var interactor = MenuInformationInteractor(this)


}