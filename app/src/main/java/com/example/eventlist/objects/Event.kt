package com.example.eventlist.objects

class Event(var title: String, var dateCreation: String, var date: String, var typeEvent: String, var notification: Boolean) {

    fun getStringDateCreationAndDate(): String {
        return "$dateCreation - $date"
    }
}