package cz.hombre.tacassistant.services

import android.content.Context
import android.location.LocationManager
import android.support.v7.app.AppCompatActivity
import android.util.Log
import cz.hombre.tacassistant.listener.LocationListener

interface LocationService {

    fun getCurrentGPSLocation(): String

    fun getCurrentMGRSLocation(): String
}

class LocationServiceImpl(applicationContext: Context) : LocationService {

    private val locationListener: LocationListener = LocationListener()
    private val locationManager: LocationManager = applicationContext.getSystemService(AppCompatActivity.LOCATION_SERVICE) as LocationManager
    override fun getCurrentGPSLocation(): String {
        return this.locationListener.getGpsLocation()
    }

    override fun getCurrentMGRSLocation(): String {
        return "MGRS TODO"
    }

    init {

        try {
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0L, 0f, locationListener)
        } catch (e: SecurityException) {
            Log.e("Location service", "Fail to request location update", e)
        } catch (e: IllegalArgumentException) {
            Log.e("Location service", "GPS provider does not exist", e)
        }
    }

}