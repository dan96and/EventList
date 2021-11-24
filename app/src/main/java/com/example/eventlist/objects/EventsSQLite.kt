package com.example.eventlist.objects

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class EventsSQLite(context: Context?) : SQLiteOpenHelper(context, "Events", null, 1) {

    //Creación de la tabla Eventos
    override fun onCreate(db: SQLiteDatabase?) {
        val orderCreation = "CREATE TABLE IF NOT EXISTS Events (ID_EVENT TEXT PRIMARY KEY, NAME TEXT, DATE_CREATION TEXT, DATE TEXT, TYPE_EVENT TEXT, NOTIFICATION TEXT)"
        db!!.execSQL(orderCreation)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        val orderDelete = "DROP TABLE IF EXISTS Events"
        db!!.execSQL(orderDelete)
        onCreate(db)
    }

    //Función para añadir todos los eventos a SQLite
    fun addEvents(eventList: MutableList<Event>) {
        val db = this.writableDatabase
        for (event in eventList) {
            val values = ContentValues()

            values.put("ID_EVENT", event.idEvent)
            values.put("NAME", event.name)
            values.put("DATE_CREATION", event.dateCreation)
            values.put("DATE", event.date)
            values.put("TYPE_EVENT", event.typeEvent)
            values.put("NOTIFICATION", event.notification.toString())

            db.insert("Events", null, values)
        }
        db.close()
    }
}