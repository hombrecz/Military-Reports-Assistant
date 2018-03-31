package cz.hombre.militaryReportsAssistant.listener

import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle

class LocationListener : LocationListener {

    var gpsLocation: Location = Location(LocationManager.GPS_PROVIDER)

    override fun onLocationChanged(location: Location) {
        this.gpsLocation = location
    }

    override fun onStatusChanged(provider: String, status: Int, extras: Bundle) {}
    override fun onProviderEnabled(provider: String) {}
    override fun onProviderDisabled(provider: String) {}
}