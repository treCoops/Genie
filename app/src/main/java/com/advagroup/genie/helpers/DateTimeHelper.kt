package com.advagroup.genie.helpers

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun Long.convertTimeMillisLongToString(format: String): String {
    val formatter = SimpleDateFormat(format, Locale.getDefault())
    return formatter.format(Date(this))
}