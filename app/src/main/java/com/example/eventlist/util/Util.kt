package com.example.eventlist.util

import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity

object Util {

    //TAGS
    const val TAG_LOGIN = "Login"
    const val TAG_REGISTER = "Register"
    const val TAG_MENU_LOGOUT = "LogOut"
    const val TAG_NEW_EVENT = "NewEvent"

    //FUNCTIONS
    fun openActivity(context: Context?, clase: Class<*>) {
        val intent = Intent(context, clase)
        context?.startActivity(intent)
    }

    fun showToast(context: Context?, message: String) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
    }
}