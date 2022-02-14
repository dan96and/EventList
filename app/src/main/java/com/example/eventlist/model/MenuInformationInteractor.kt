package com.example.eventlist.model

import com.example.eventlist.interfaces.MenuInformationInterface
import com.example.eventlist.presenter.MenuInformationPresenter

class MenuInformationInteractor(private var presenter: MenuInformationPresenter) :
    MenuInformationInterface.MenuInformationInteractor {

}