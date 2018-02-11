package cz.hombre.tacassistant.services

import java.text.SimpleDateFormat
import java.util.*

interface DateTimeService {

    fun getZuluDateTimeGroup(): String

    fun getLocalTime(): String

    fun getLocalTime(date: Date): String

    fun getLocalDate(): String

    fun getTimeDifference(from: Date): String
}

class DateTimeServiceImpl : DateTimeService {
    private val ZULU_FORMAT = "ddHHmm'Z' MMM yy"
    private val LOCAL_TIME_FORMAT = "HH:mm"
    private val LOCAL_DATE_FORMAT = "MMMM dd, yyyy"
    private val DIFFERENCE_FORMAT_SECONDS = "ss's'"
    private val DIFFERENCE_FORMAT_MINUTES = "mm'm' ss's'"
    private val DIFFERENCE_FORMAT_HOURS = "HH'h' mm'm' ss's'"

    private val MILLI_PER_SECOND = 1000
    private val MILLI_PER_MINUTE = 60 * MILLI_PER_SECOND
    private val MILLI_PER_HOUR = 60 * MILLI_PER_MINUTE

    override fun getZuluDateTimeGroup(): String {
        val date = Calendar.getInstance(TimeZone.getTimeZone("UTC")).time

        return getFormattedUTCDateTime(date, ZULU_FORMAT)
    }

    override fun getLocalTime() = getLocalTime(Calendar.getInstance().time)

    override fun getLocalTime(date: Date) = getFormattedDateTime(date, LOCAL_TIME_FORMAT)

    override fun getLocalDate() = getFormattedDateTime(Calendar.getInstance().time, LOCAL_DATE_FORMAT)

    private fun getFormattedDateTime(date: Date, formatString: String) = SimpleDateFormat(formatString, Locale.getDefault()).format(date).toUpperCase()

    private fun getFormattedUTCDateTime(date: Date, formatString: String): String {
        val format = SimpleDateFormat(formatString, Locale.getDefault())
        format.timeZone = TimeZone.getTimeZone("UTC")

        return format.format(date).toUpperCase()
    }

    override fun getTimeDifference(from: Date): String {
        val difference = Date().time - from.time
        val selectedFormat: String

        when (difference) {
            in 0 until MILLI_PER_SECOND -> selectedFormat = DIFFERENCE_FORMAT_SECONDS
            in MILLI_PER_MINUTE until MILLI_PER_HOUR -> selectedFormat = DIFFERENCE_FORMAT_MINUTES
            else -> selectedFormat = DIFFERENCE_FORMAT_HOURS
        }

        return getFormattedUTCDateTime(Date(difference), selectedFormat)
    }
}