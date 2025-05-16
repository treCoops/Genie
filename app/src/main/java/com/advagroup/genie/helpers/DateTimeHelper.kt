package com.advagroup.genie.helpers

import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.TextStyle
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

fun getTodayDateForDashboard(): String {
    val today = LocalDate.now()
    val day = today.dayOfMonth
    val daySuffix = getDaySuffix(day)
    val month = today.month.getDisplayName(TextStyle.FULL, Locale.ENGLISH)
    val year = today.year
    return "$day$daySuffix $month, $year"
}

private fun getDaySuffix(day: Int): String {
    return if (day in 11..13) {
        "th"
    } else {
        when (day % 10) {
            1 -> "st"
            2 -> "nd"
            3 -> "rd"
            else -> "th"
        }
    }
}