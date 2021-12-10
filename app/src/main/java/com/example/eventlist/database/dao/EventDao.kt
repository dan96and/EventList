package com.example.eventlist.database.dao

import androidx.room.*
import com.example.eventlist.database.entities.Event

@Dao
interface EventDao {
    @Insert
    fun addEvent(event: Event)

    @Query("UPDATE Events SET deleteEvent = 1 WHERE idEvent = :id ")
    fun deleteEvent(id:Int)

    @Query("SELECT * FROM Events WHERE typeEvent = 'EventSince' AND deleteEvent = 0 ")
    fun showSinceEvents():MutableList<Event>

    @Query("SELECT * FROM Events WHERE typeEvent = 'EventUntil' AND deleteEvent = 0 ")
    fun showUntilEvents():MutableList<Event>
}