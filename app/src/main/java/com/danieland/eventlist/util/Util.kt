package com.danieland.eventlist.util

import java.text.SimpleDateFormat
import java.util.*

object Util {

    //TAGS
    const val TAG_NEW_EVENT = "NewEvent"
    const val TAG_SHOW_EVENTSINCE = "ShowEventSince"
    const val TAG_SHOW_EVENTUNTIL = "ShowEventUntil"
    const val TAG_SHOW_DELETEEVENT = "DeleteEvent"

    //GET CURRENT DATE
    val sdf = SimpleDateFormat("dd/MM/yyyy")
    val currentDate = sdf.format(Date()).toString()

    fun checkValueSwitchNotification(valueSwitch: Boolean): Boolean {
        return valueSwitch
    }
}