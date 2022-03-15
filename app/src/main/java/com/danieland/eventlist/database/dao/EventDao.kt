package com.danieland.eventlist.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.danieland.eventlist.database.entities.Event

@Dao
interface EventDao {
    @Insert
    fun addEvent(event: Event)

    @Query("UPDATE Events SET deleteEvent = 1 WHERE idEvent = :id ")
    fun deleteEvent(id: Int)

    @Query("SELECT * FROM Events WHERE typeEvent = 'EventSince' AND deleteEvent = 0 ")
    fun showSinceEvents(): List<Event>

    @Query("SELECT * FROM Events WHERE typeEvent = 'EventUntil' AND deleteEvent = 0 ")
    fun showUntilEvents(): List<Event>

    @Query("SELECT * FROM Events")
    fun showAllEvents(): List<Event>

    @Query("UPDATE Events SET notification = :notification, name = :name, date = :date, typeEvent = :eventType, description =:description WHERE idEvent = :id")
    fun editEvent(id: Int, notification: Boolean, name: String, date: String, eventType: String, description: String)

    @Query("UPDATE Events SET deleteEvent = 1 WHERE idEvent =:id")
    fun deleterEvent(id: Int)

    @Query("SELECT * FROM Events WHERE deleteEvent = 0")
    fun downloadAllEventsActives(): MutableList<Event>
}