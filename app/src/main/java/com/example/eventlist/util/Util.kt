package com.example.eventlist.util

import android.content.Context
import android.content.Intent
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

    //FUNCTIONS
    fun openActivity(context: Context?, clase: Class<*>) {
        val intent = Intent(context, clase)
        context?.startActivity(intent)
    }

    fun checkValueSwitchNotification(valueSwitch: Boolean): Boolean {
        return valueSwitch
    }
}