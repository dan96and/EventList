package com.example.eventlist.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import com.example.eventlist.database.entities.Event

@Dao
interface EventDao {
    @Insert
    fun addEvent(event: Event)
}