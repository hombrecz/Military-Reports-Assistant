package cz.hombre.militaryReportsAssistant.services.impl

import cz.hombre.militaryReportsAssistant.services.DateTimeService
import cz.hombre.militaryReportsAssistant.services.PreferencesService
import java.text.SimpleDateFormat
import java.util.*

private const val ZULU_FORMAT = "ddHHmm'Z' MMM yy"
private const val LOCAL_TIME_FORMAT = "HH:mm"
private const val LOCAL_DATE_FORMAT = "MMMM dd, yyyy"
private const val LOCAL_DATE_TIME_FORMAT = "HH:mm dd/MMM/yy"
private const val DIFFERENCE_FORMAT_SECONDS = "ss's'"
private const val DIFFERENCE_FORMAT_MINUTES = "mm'm' ss's'"
private const val DIFFERENCE_FORMAT_HOURS = "HH'h' mm'm' ss's'"

private const val MILLI_PER_SECOND = 1000
private const val MILLI_PER_MINUTE = 60 * MILLI_PER_SECOND
private const val MILLI_PER_HOUR = 60 * MILLI_PER_MINUTE

private const val UTC_ZONE = "UTC"

private const val MILITARY_ZONE_OFFSET = "YXWVUTSRQPONZABCDEFGHIKLM"

class DateTimeServiceImpl(private val preferencesService: PreferencesService) : DateTimeService {

    override fun getMilitaryDateTimeGroup() = getMilitaryDateTimeGroup(Date())

    override fun getMilitaryDateTimeGroup(date: Date): String {
        val preferredOffset = preferencesService.getPreferredOffset()

        val formatWithZoneOffset = ZULU_FORMAT.replace('Z', getMilitaryOffset(preferredOffset))

        val format = SimpleDateFormat(formatWithZoneOffset, Locale.UK)
        format.timeZone = getTimeZone(preferredOffset)

        return format.format(date).toUpperCase()
    }

    override fun getLocalTime() = getLocalTime(Calendar.getInstance().time)

    override fun getLocalTime(date: Date) = getFormattedDateTime(date, LOCAL_TIME_FORMAT)

    override fun getLocalDate() = getFormattedDateTime(Calendar.getInstance().time, LOCAL_DATE_FORMAT)

    override fun getLocalDateTime() = getFormattedDateTime(Calendar.getInstance().time, LOCAL_DATE_TIME_FORMAT)

    private fun getFormattedDateTime(date: Date, formatString: String) = SimpleDateFormat(formatString, Locale.getDefault()).format(date).toUpperCase()

    private fun getFormattedUTCDateTime(date: Date, formatString: String) = getFormattedUTCDateTime(date, formatString, Locale.UK)

    private fun getFormattedUTCDateTime(date: Date, formatString: String, locale: Locale): String {
        val format = SimpleDateFormat(formatString, locale)
        format.timeZone = TimeZone.getTimeZone(UTC_ZONE)

        return format.format(date).toUpperCase()
    }

    override fun getTimeDifference(from: Date): String {
        val difference = Date().time - from.time
        val selectedFormat: String

        selectedFormat = when (difference) {
            in 0 until MILLI_PER_SECOND -> DIFFERENCE_FORMAT_SECONDS
            in MILLI_PER_MINUTE until MILLI_PER_HOUR -> DIFFERENCE_FORMAT_MINUTES
            else -> DIFFERENCE_FORMAT_HOURS
        }

        return getFormattedUTCDateTime(Date(difference), selectedFormat)
    }

    private fun getMilitaryOffset(offset: Int) = MILITARY_ZONE_OFFSET[offset + 12]


    private fun getTimeZone(preferredOffset: Int): TimeZone {
        val timeZone: TimeZone
        if (preferredOffset < 0) {
            timeZone = TimeZone.getTimeZone("GMT$preferredOffset:00")
        } else {
            timeZone = TimeZone.getTimeZone("GMT+$preferredOffset:00")
        }
        return timeZone
    }
}