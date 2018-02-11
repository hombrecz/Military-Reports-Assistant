package cz.hombre.tacassistant.services

import java.text.SimpleDateFormat
import java.util.*

interface DateTimeService {

    fun getZuluDateTimeGroup(): String

    fun getLocalTime(): String

    fun getLocalDate(): String
}

class DateTimeServiceImpl : DateTimeService {

    private val ZULU_FORMAT = "ddHHmm'Z' MMM yy"
    private val LOCAL_TIME_FORMAT = "HH:mm"
    private val LOCAL_DATE_FORMAT = "MMMM dd, yyyy"

    override fun getZuluDateTimeGroup(): String {
        val date = Calendar.getInstance(TimeZone.getTimeZone("UTC")).time

        return getFormattedDateTime(date, ZULU_FORMAT)
    }

    override fun getLocalTime(): String {
        val actualDateTime = Calendar.getInstance().time

        return getFormattedDateTime(actualDateTime, LOCAL_TIME_FORMAT)
    }

    override fun getLocalDate(): String {
        val actualDateTime = Calendar.getInstance().time
        return getFormattedDateTime(actualDateTime, LOCAL_DATE_FORMAT)
    }

    private fun getFormattedDateTime(date: Date, format: String): String {
        val zuluFormat = SimpleDateFormat(format, Locale.getDefault())

        return zuluFormat.format(date).toUpperCase()
    }
}