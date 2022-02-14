package com.example.eventlist.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.eventlist.util.Util
import java.io.Serializable
import java.text.SimpleDateFormat
import java.util.*

@Entity(tableName = "Events")
class Event(
    var name: String = "",
    var description: String = "",
    var dateCreation: String = "",
    var date: String = "",
    var typeEvent: String = "",
    var notification: Boolean = false,
    var deleteEvent: Boolean = false,
    @PrimaryKey(autoGenerate = true)
    var idEvent: Int = 0
) : Serializable {

    fun getStringDateCreationAndDate(): String {
        return date
    }

    fun getTimeDifferentBetweenDates(): String {
        val sdf = SimpleDateFormat("dd/MM/yyyy")
        val diffMiliseconds: Long

        val dateSelect: Date = sdf.parse(date)
        val dateCurrent: Date = sdf.parse(Util.currentDate)

        if (typeEvent != "EventUntil") {
            diffMiliseconds = dateCurrent.time - dateSelect.time
        } else {
            diffMiliseconds = dateSelect.time - dateCurrent.time
        }

        val diffDays: Long = ((((diffMiliseconds / 1000) / 60) / 60) / 24)
        val years: Int = (diffDays / 365).toInt()
        val months = ((diffDays % 365) / 30).toInt()
        val weeks = ((diffDays % 365) % 30 / 7).toInt()
        val days = ((diffDays % 365) % 30 % 7).toInt()

        return getStringResult(years, months, weeks, days)
    }

    private fun getStringResult(years: Int, months: Int, weeks: Int, days: Int): String {
        var result: String = ""

        result += when (years) {
            1 -> "• $years year "
            0 -> ""
            else -> {
                "• $years years "
            }
        }

        result += when (months) {
            1 -> "• $months month "
            0 -> ""
            else -> {
                "• $months months "
            }
        }

        result += when (weeks) {
            1 -> "• $weeks week "
            0 -> ""
            else -> {
                "• $weeks weeks "
            }
        }

        result += when (days) {
            1 -> "• $days day "
            0 -> ""
            else -> {
                "• $days days "
            }
        }
        
        //Si los dias, meses, semanas y años son igual a 0, mostrar el siguiente mensaje
        if (days == 0 && months == 0 && weeks == 0 && years == 0){
            result = "TODAY IS THE DAY"
        }

        return result
    }
}