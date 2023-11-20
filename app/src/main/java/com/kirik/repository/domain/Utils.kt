package com.kirik.repository.domain

import java.text.SimpleDateFormat
import java.time.Instant
import java.time.format.DateTimeFormatter
import java.util.Date


fun String.getDate(): Date {
    val timeFormatter = DateTimeFormatter.ISO_DATE_TIME
    val accessor = timeFormatter.parse(this)

    val date: Date = Date.from(Instant.from(accessor))
    return date
}

fun Date.format() = SimpleDateFormat("dd.MM.yyy  HH:mm").format(this)
