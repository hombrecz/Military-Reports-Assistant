package cz.hombre.tacassistant.services

import android.content.Context
import android.location.LocationManager
import android.support.v7.app.AppCompatActivity
import android.util.Log
import cz.hombre.tacassistant.listener.LocationListener

interface LocationService {

    fun getCurrentGPSLocation(): String

    fun getCurrentMGRSLocation(): String

    fun getLocationPrecision(): String

    fun getLocationTime(): String

    fun getLocationTimeAgo(): String
}

class LocationServiceImpl(applicationContext: Context) : LocationService {

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

    override fun getLocationTime(): String {
        return "time TODO"
    }

    override fun getLocationTimeAgo(): String {
        return "time ago TODO"
    }

    override fun getLocationPrecision(): String {
        return "precision TODO"
    }

    override fun getCurrentGPSLocation(): String {
        return this.locationListener.getGpsLocation()
    }

    override fun getCurrentMGRSLocation(): String {
        //TODO OD - implement MGRS
        return "MGRS TODO"
    }

}