package com.ajcordenete.eventio.utils

import java.text.ParseException
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.Locale

object DateTimeUtils {

    fun getDateTimeString(time: Long): String {
        time.let {
            return try {
                val outputFormatter = DateTimeFormatter.ofPattern(
                    "MMM dd, yyyy HH:mm",
                    Locale.ENGLISH
                )
                val date = Instant
                    .ofEpochSecond(it / 1000L)
                    .atZone(ZoneId.systemDefault())
                    .toLocalDateTime()

                outputFormatter.format(date)
            } catch (e: ParseException) {
                it.toString()
            }
        }
        return ""
    }
}