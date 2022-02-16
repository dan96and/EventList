package com.example.eventlist.objects

import android.app.Application

class EventApp : Application() {

    companion object {
        private var db: EventListDatabase? = null
        fun getDB(): EventListDatabase {
            return db!!
        }
    }

    override fun onCreate() {
        super.onCreate()
        db = EventListDatabase.getDB(applicationContext)
    }
}