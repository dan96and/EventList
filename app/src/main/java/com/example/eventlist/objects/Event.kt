package com.example.eventlist.objects

abstract class Event ( var title: String, private var dateCreation: String, private var date: String) {

    fun getStringDateCreationAndDate():String{
        return "$dateCreation - $date"
    }
}

class EventUntil (title:String, dateCreation: String, date: String, notification:Boolean): Event(title, dateCreation, date)

class EventSince (title:String, dateCreation: String, date: String): Event(title, dateCreation, date)