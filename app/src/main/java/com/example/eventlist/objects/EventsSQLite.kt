package com.example.eventlist.objects

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class EventsSQLite(context: Context?) : SQLiteOpenHelper(context, "Events", null, 1) {

    //Creación de la tabla Eventos
    override fun onCreate(db: SQLiteDatabase?) {
        val orderCreation =
            "CREATE TABLE IF NOT EXISTS Events (ID_EVENT TEXT PRIMARY KEY, NAME TEXT, DATE_CREATION TEXT, DATE TEXT, TYPE_EVENT TEXT, NOTIFICATION INTEGER)"
        db!!.execSQL(orderCreation)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        val orderDelete = "DROP TABLE IF EXISTS Events"
        db!!.execSQL(orderDelete)
        onCreate(db)
    }

    //Función para añadir todos los Eventos
    fun addEvents(eventList: MutableList<Event>) {
        val db = this.writableDatabase
        val contentValues = ContentValues()
        for (event in eventList) {
            contentValues.put("ID_EVENT", event.idEvent)
            contentValues.put("NAME", event.name)
            contentValues.put("DATE_CREATION", event.dateCreation)
            contentValues.put("DATE", event.date)
            contentValues.put("TYPE_EVENT", event.typeEvent)
            contentValues.put(
                "NOTIFICATION", if (event.notification) {
                    1
                } else {
                    0
                }
            )
            db.insert("Events", null, contentValues)
            contentValues.clear()
        }
        db.close()
    }

    //Funcion para actualizar todos los Eventos
    fun saveChanges(eventList: MutableList<Event>) {
        val db = this.writableDatabase
        val contentValues = ContentValues()
        lateinit var args: Array<String>
        for (event in eventList) {
            args = arrayOf(event.idEvent)
            contentValues.put("NAME", event.name)
            contentValues.put("DATE_CREATION", event.dateCreation)
            contentValues.put("DATE", event.date)
            contentValues.put("TYPE_EVENT", event.typeEvent)
            contentValues.put(
                "NOTIFICATION", if (event.notification) {
                    1
                } else {
                    0
                }
            )
            db.update("Events", contentValues, "ID_EVENT=?", args)
            contentValues.clear()
        }
        db.close()
    }
}