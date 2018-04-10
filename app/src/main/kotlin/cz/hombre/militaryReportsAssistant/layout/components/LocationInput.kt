package cz.hombre.militaryReportsAssistant.layout.components

import android.app.Activity
import android.app.Activity.RESULT_OK
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.support.v4.app.ActivityCompat.startActivityForResult
import android.view.View
import android.view.ViewManager
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.GoogleApiAvailability
import com.google.android.gms.location.places.ui.PlacePicker
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds
import cz.hombre.militaryReportsAssistant.R
import cz.hombre.militaryReportsAssistant.services.LocationService
import org.jetbrains.anko.alert
import org.jetbrains.anko.appcompat.v7.Appcompat
import org.jetbrains.anko.button
import org.jetbrains.anko.custom.ankoView
import org.jetbrains.anko.editText
import org.jetbrains.anko.hintResource
import org.jetbrains.anko.linearLayout
import org.jetbrains.anko.textResource
import org.jetbrains.anko.textView
import org.jetbrains.anko.verticalLayout


private const val PLACE_PICKER_REQUEST = 1
private const val CORNER_POINT_DISTANCE = 0.1

inline fun ViewManager.locationInput(label: Int, valueHint: Int, locationService: LocationService, activity: Activity) = locationInput(label, valueHint, locationService, activity) {}
inline fun ViewManager.locationInput(label: Int, valueHint: Int, locationService: LocationService, activity: Activity, init: LocationInput.() -> Unit) = ankoView({ LocationInput(it, activity, label, valueHint, locationService) }, 0, init)

class LocationInput(private val c: Context, private val activity: Activity, val label: Int, val valueHint: Int, val locationService: LocationService) : LinearLayout(c) {

    private lateinit var hideableContent: LinearLayout
    private lateinit var valueEdit: EditText
    private lateinit var locationButton: Button

    init {
        verticalLayout {
            textView(label) {
                textResource = label
                textSize = 20f
            }.setOnClickListener {
                if (hideableContent.visibility == View.VISIBLE) {
                    hideableContent.visibility = View.GONE
                } else {
                    hideableContent.visibility = View.VISIBLE
                }
            }
            linearLayout {
                hideableContent = this
                valueEdit = editText {
                    hintResource = valueHint
                }
                locationButton = button(R.string.report_location_button)
                locationButton.setOnClickListener { getLocationFromGMaps() }
            }
            setPadding(0,0,0, 30)

        }
    }

    fun getValue(): String {
        return this.valueEdit.text.toString()
    }

    fun setValue(value: String) {
        return valueEdit.setText(value)
    }

    fun onLocationSelected(requestCode: Int, resultCode: Int, data: Intent) {
        if (requestCode == PLACE_PICKER_REQUEST) {
            if (resultCode == RESULT_OK) {
                val place = PlacePicker.getPlace(c, data)
                setValue(locationService.getMGRSLocation(place))
            }
        }
    }

    private fun getLocationFromGMaps() {
        val api = GoogleApiAvailability.getInstance()
        val code = api.isGooglePlayServicesAvailable(activity)
        if (code == ConnectionResult.SUCCESS) {
            val builder = PlacePicker.IntentBuilder()

            val currentGPS = locationService.getCurrentGPS()
            val bottomLeft = LatLng(currentGPS.latitude - CORNER_POINT_DISTANCE, currentGPS.longitude - CORNER_POINT_DISTANCE)
            val topRight = LatLng(currentGPS.latitude + CORNER_POINT_DISTANCE, currentGPS.longitude + CORNER_POINT_DISTANCE)
            builder.setLatLngBounds(LatLngBounds(bottomLeft, topRight))
            startActivityForResult(activity, builder.build(activity), PLACE_PICKER_REQUEST, null)
        } else {
            activity.alert(Appcompat, c.getString(R.string.report_location_missing_api)).show()
        }
    }

}