package com.example.certidaopet.utils

import java.sql.Date
import java.sql.Timestamp
import java.text.SimpleDateFormat

class DateUtil {
    companion object {
        fun getTodayDate(): String {
            val dateFormat = SimpleDateFormat("dd/MM/yyy")
            val timestamp = Timestamp(System.currentTimeMillis())
            return dateFormat.format(Date(timestamp.time))
        }
    }

}