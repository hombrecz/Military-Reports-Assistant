package cz.hombre.tacassistant.services

import java.util.*

interface DateTimeService {

    fun getMilitaryDateTimeGroup(): String

    fun getLocalTime(): String

    fun getLocalTime(date: Date): String

    fun getLocalDate(): String

    fun getTimeDifference(from: Date): String
}