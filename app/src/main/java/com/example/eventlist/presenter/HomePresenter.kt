package com.example.eventlist.presenter

import android.content.Context
import android.util.Log
import com.example.eventlist.interfaces.HomeInterface
import com.example.eventlist.model.HomeInteractor
import com.example.eventlist.database.entities.Event
import com.example.eventlist.util.Util
import com.example.eventlist.view.activities.HomeView

class HomePresenter(val view: HomeView) : HomeInterface.HomePresenter {

    private val interactor = HomeInteractor(this)


}