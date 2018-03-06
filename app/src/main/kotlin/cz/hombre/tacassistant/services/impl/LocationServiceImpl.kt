package cz.hombre.tacassistant.services.impl

import android.content.Context
import android.location.Location
import android.location.LocationManager
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.berico.coords.Coordinates
import cz.hombre.tacassistant.R
import cz.hombre.tacassistant.listener.LocationListener
import cz.hombre.tacassistant.services.DateTimeService
import cz.hombre.tacassistant.services.LocationService
import java.util.*
import kotlin.math.absoluteValue

private const val SEMICOLON = ":"
private const val DEGREES = "°"
private const val MINUTES = "'"
private const val SECONDS = "\""

private const val ACCURACY_BEGIN = "(+-"
private const val ACCURACY_END = " m)"

class LocationServiceImpl(private val applicationContext: Context, private val dateTimeService: DateTimeService) : LocationService {

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

    override fun getLocationPrecision() = if (this.locationListener.gpsLocation.accuracy > 0) {
        "$ACCURACY_BEGIN${this.locationListener.gpsLocation.accuracy}$ACCURACY_END"
    } else {
        String()
    }

    override fun getCurrentGPSLocation() = getGPSFromLocation(this.locationListener.gpsLocation)

    override fun getCurrentMGRSLocation() = getMGRSFromLocation(this.locationListener.gpsLocation)

    private fun getGPSFromLocation(location: Location): String {
        val latitudeSymbol = applicationContext.getString(
                if (location.latitude < 0) {
                    R.string.symbol_south
                } else {
                    R.string.symbol_north
                })

        val longitudeSymbol = applicationContext.getString(
                if (location.longitude < 0) {
                    R.string.symbol_west
                } else {
                    R.string.symbol_east
                })

        val longitude = Location.convert(location.longitude.absoluteValue, Location.FORMAT_SECONDS)
                .replaceFirst(SEMICOLON, DEGREES)
                .replaceFirst(SEMICOLON, MINUTES)
                .plus(SECONDS)

        val latitude = Location.convert(location.latitude.absoluteValue, Location.FORMAT_SECONDS)
                .replaceFirst(SEMICOLON, DEGREES)
                .replaceFirst(SEMICOLON, MINUTES)
                .plus(SECONDS)

        return "$latitudeSymbol $latitude $longitudeSymbol $longitude"
    }

    private fun getMGRSFromLocation(location: Location) = Coordinates.mgrsFromLatLon(location.latitude, location.longitude)

}