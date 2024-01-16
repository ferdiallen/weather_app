package com.ferdi.weatherapp.core

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter


fun getCurrentDate(): String {
    return DateTimeFormatter.ofPattern("dd MMM yyyy")
        .format(ZonedDateTime.now())
}


fun getCurrentTime(): String {
    return DateTimeFormatter.ofPattern("HH:mm")
        .format(ZonedDateTime.now())

}