package com.advagroup.genie.helpers

import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

fun Long.convertTimeMillisLongToString(format: String): String {
    val formatter = SimpleDateFormat(format, Locale.getDefault())
    return formatter.format(Date(this))
}

fun get12HoursTime(hour: Int, minute: Int): String {

    val calendar = Calendar.getInstance().apply {
        set(Calendar.HOUR_OF_DAY, hour)
        set(Calendar.MINUTE, minute)
    }

    val sdf = SimpleDateFormat("hh:mm a", Locale.getDefault())
    return sdf.format(calendar.time)

}