package com.example.eventlist.util

import android.content.Context
import android.content.Intent
import android.widget.Toast
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import java.text.SimpleDateFormat
import java.util.*

object Util {

    //TAGS
    const val TAG_LOGIN = "Login"
    const val TAG_REGISTER = "Register"
    const val TAG_MENU_LOGOUT = "LogOut"
    const val TAG_NEW_EVENT = "NewEvent"
    const val TAG_SHOW_EVENTSINCE = "ShowEventSince"
    const val TAG_SHOW_EVENTUNTIL = "ShowEventUntil"
    const val TAG_SHOW_DELETEEVENT = "DeleteEvent"

    //GET CURRENT DATE
    val sdf = SimpleDateFormat("dd/MM/yyyy")
    val currentDate = sdf.format(Date()).toString()

    //FIREBASE
    val userId = Firebase.auth.currentUser!!.uid
    val db = Firebase.firestore

    //FUNCTIONS
    fun openActivity(context: Context?, clase: Class<*>) {
        val intent = Intent(context, clase)
        context?.startActivity(intent)
    }

    fun showToast(context: Context?, message: String) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
    }

    fun checkValueSwitchNotification(valueSwitch: Boolean): Boolean {
        if (valueSwitch) {
            return true
        } else {
            return false
        }
    }
}