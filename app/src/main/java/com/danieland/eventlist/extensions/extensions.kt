package com.danieland.eventlist.extensions

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.fragment.app.Fragment

fun Context.toast(message: String, duration: Int = Toast.LENGTH_LONG) {
    Toast.makeText(this, message, duration).show()
}

fun Activity.openActivity(activity: Class<*>) {
    this.startActivity(Intent(this, activity))
}

fun Fragment.openActivitySinceFragment(activity: Class<*>) {
    this.startActivity(Intent(this.context, activity))
}