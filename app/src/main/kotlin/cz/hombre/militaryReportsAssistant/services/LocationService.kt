package cz.hombre.militaryReportsAssistant.services

import android.location.Location
import com.google.android.gms.location.places.Place

interface LocationService {

    fun getCurrentGPS(): Location

    fun getCurrentGPSLocation(): String

    fun getCurrentMGRSLocation(): String

    fun getMGRSLocation(place: Place): String

    fun getLocationPrecision(): String

    fun getLocationTime(): String

    fun getLocationTimeAgo(): String
}