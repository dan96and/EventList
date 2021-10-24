package com.example.eventlist.objects

import java.io.Serializable

class Event(var title: String, var dateCreation: String, var date: String, var typeEvent: String, var notification: Boolean) : Serializable {

    fun getStringDateCreationAndDate(): String {
        return "$dateCreation - $date"
    }
}