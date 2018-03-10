package cz.hombre.tacassistant.services.impl

import cz.hombre.tacassistant.services.DateTimeService
import cz.hombre.tacassistant.services.PreferencesService
import java.text.SimpleDateFormat
import java.util.*

private const val ZULU_FORMAT = "ddHHmm'Z' MMM yy"
private const val LOCAL_TIME_FORMAT = "HH:mm"
private const val LOCAL_DATE_FORMAT = "MMMM dd, yyyy"
private const val DIFFERENCE_FORMAT_SECONDS = "ss's'"
private const val DIFFERENCE_FORMAT_MINUTES = "mm'm' ss's'"
private const val DIFFERENCE_FORMAT_HOURS = "HH'h' mm'm' ss's'"

private const val MILLI_PER_SECOND = 1000
private const val MILLI_PER_MINUTE = 60 * MILLI_PER_SECOND
private const val MILLI_PER_HOUR = 60 * MILLI_PER_MINUTE

private const val UTC_ZONE = "UTC"

private const val MILITARY_ZONE_OFFSET = "YXWVUTSRQPONZABCDEFGHIKLM"

class DateTimeServiceImpl(private val preferencesService: PreferencesService) : DateTimeService {

    override fun getMilitaryDateTimeGroup(): String {
        val preferredOffset = preferencesService.getPreferredOffset()

        val date = Calendar.getInstance(TimeZone.getTimeZone("GMT$preferredOffset:00")).time

        val formatWithZoneOffset = ZULU_FORMAT.replace('Z', getMilitaryOffset(preferredOffset))

        return getFormattedUTCDateTime(date, formatWithZoneOffset)
    }

    override fun getLocalTime() = getLocalTime(Calendar.getInstance().time)

    override fun getLocalTime(date: Date) = getFormattedDateTime(date, LOCAL_TIME_FORMAT)

    override fun getLocalDate() = getFormattedDateTime(Calendar.getInstance().time, LOCAL_DATE_FORMAT)

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
}