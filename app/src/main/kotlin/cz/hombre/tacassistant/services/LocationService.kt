package cz.hombre.tacassistant.services

interface LocationService {

    fun getCurrentGPSLocation(): String

    fun getCurrentMGRSLocation(): String

    fun getLocationPrecision(): String

    fun getLocationTime(): String

    fun getLocationTimeAgo(): String
}