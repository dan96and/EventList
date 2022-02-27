package com.danieland.eventlist.objects

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.danieland.eventlist.database.dao.EventDao
import com.danieland.eventlist.database.entities.Event

@Database(entities = [Event::class], version = 1, exportSchema = false)
abstract class EventListDatabase : RoomDatabase() {
    abstract fun eventDao(): EventDao

    companion object {
        private var db: EventListDatabase? = null
        fun getDB(context: Context): EventListDatabase {
            if (db == null) {
                db = Room.databaseBuilder(context, EventListDatabase::class.java, "Events").build()
            }
            return db!!
        }
    }
}