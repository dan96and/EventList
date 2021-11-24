package com.example.eventlist.objects

import com.example.eventlist.util.Util
import java.io.Serializable
import java.text.SimpleDateFormat
import java.util.*


class Event(
    var name: String = "",
    var dateCreation: String = "",
    var date: String = "",
    var typeEvent: String = "",
    var notification: Boolean = false,
    val idEvent: String = ""
) : Serializable {

    fun getStringDateCreationAndDate(): String {
        return date
    }

    fun getTimeDifferentBetweenDates(): String {
        val sdf = SimpleDateFormat("dd/MM/yyyy")
        val diffMiliseconds: Long

        val dateSelect: Date = sdf.parse(date)
        val dateCurrent: Date = sdf.parse(Util.currentDate)

        if (typeEvent == "EventUntil") {
            diffMiliseconds = dateSelect.time - dateCurrent.time
        } else {
            diffMiliseconds = dateCurrent.time - dateSelect.time
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
            1 -> "• $years año "
            0 -> ""
            else -> {
                "• $years años "
            }
        }

        result += when (months) {
            1 -> "• $months mes "
            0 -> ""
            else -> {
                "• $months meses "
            }
        }

        result += when (weeks) {
            1 -> "• $weeks semana "
            0 -> ""
            else -> {
                "• $weeks semanas "
            }
        }

        result += when (days) {
            1 -> "• $days día "
            0 -> ""
            else -> {
                "• $days días "
            }
        }
        return result
    }
}