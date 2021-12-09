package com.example.eventlist.model

import com.example.eventlist.interfaces.NewEventInterface
import com.example.eventlist.database.entities.Event
import com.example.eventlist.objects.EventApp
import com.example.eventlist.presenter.NewEventPresenter

class NewEventInteractor(private var presenter: NewEventPresenter) :
    NewEventInterface.NewEventInteractor {

    override fun addEventSqlite(event: Event) {
        EventApp.getDB().eventDao().addEvent(event)
        presenter.uploadEventCorrect()
    }
}