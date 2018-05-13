package com.example.android.budgetproject

import java.text.SimpleDateFormat
import java.util.*
import java.util.regex.Pattern

object Util {

    //format 27-10-2017
    @JvmStatic
    fun unixToDateAsNumbers(time: Long): String {
        try {
            val dateFormat = SimpleDateFormat("dd MM yyyy", Locale.getDefault())
            val date = Date(time * 1000)
            return dateFormat.format(date)
        } catch (e: NumberFormatException) {
            return ""
        }

    }

}