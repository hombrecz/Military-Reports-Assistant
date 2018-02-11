package cz.hombre.tacassistant.services

import android.content.Context
import android.location.Location
import android.location.LocationManager
import android.support.v7.app.AppCompatActivity
import android.util.Log
import cz.hombre.tacassistant.listener.LocationListener
import java.util.*
import kotlin.math.absoluteValue

interface LocationService {

    fun getCurrentGPSLocation(): String

    fun getCurrentMGRSLocation(): String

    fun getLocationPrecision(): String

    fun getLocationTime(): String

    fun getLocationTimeAgo(): String
}

class LocationServiceImpl(applicationContext: Context, private val dateTimeService: DateTimeService) : LocationService {

    private val locationListener: LocationListener = LocationListener()
    private val locationManager: LocationManager = applicationContext.getSystemService(AppCompatActivity.LOCATION_SERVICE) as LocationManager

    init {
        try {
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0L, 0f, locationListener)
        } catch (e: SecurityException) {
            Log.e("Location service", "Fail to request location update", e)
        } catch (e: IllegalArgumentException) {
            Log.e("Location service", "GPS provider does not exist", e)
        }
    }

    override fun getLocationTime() = dateTimeService.getLocalTime(Date(this.locationListener.gpsLocation.time))

    override fun getLocationTimeAgo() = dateTimeService.getTimeDifference(Date(this.locationListener.gpsLocation.time))

    override fun getLocationPrecision(): String {
        if (this.locationListener.gpsLocation.accuracy > 0) {
            return "(+- ${this.locationListener.gpsLocation.accuracy} m)"
        } else {
            return ""
        }
    }

    override fun getCurrentGPSLocation() = getGPSFromLocation(this.locationListener.gpsLocation)

    override fun getCurrentMGRSLocation() = getMGRSFromLocation(this.locationListener.gpsLocation)

    private fun getGPSFromLocation(location: Location): String {
        val latitudeSymbol: String
        if (location.latitude < 0) {
            latitudeSymbol = "S"
        } else {
            latitudeSymbol = "N"
        }

        val longitudeSymbol: String
        if (location.longitude < 0) {
            longitudeSymbol = "W"
        } else {
            longitudeSymbol = "E"
        }

        var longitude = Location.convert(location.longitude.absoluteValue, Location.FORMAT_SECONDS)
                .replaceFirst(":", "°")
                .replaceFirst(":", "'")
                .plus("\"")

        var latitude = Location.convert(location.latitude.absoluteValue, Location.FORMAT_SECONDS)
                .replaceFirst(":", "°")
                .replaceFirst(":", "'")
                .plus("\"")

        return " ${latitudeSymbol}${latitude} ${longitudeSymbol}${longitude}"
    }

    private fun getMGRSFromLocation(location : Location): String {
        //TODO OD - implement MGRS
        return "MGRS TODO"
    }

}