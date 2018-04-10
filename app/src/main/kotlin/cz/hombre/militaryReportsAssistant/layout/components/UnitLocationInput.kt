package cz.hombre.militaryReportsAssistant.layout.components

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.View
import android.view.ViewManager
import android.widget.EditText
import android.widget.LinearLayout
import com.google.android.gms.location.places.ui.PlacePicker
import cz.hombre.militaryReportsAssistant.R.string.*
import cz.hombre.militaryReportsAssistant.services.LocationService
import org.jetbrains.anko.*
import org.jetbrains.anko.custom.ankoView

inline fun ViewManager.unitLocationInput(locationService: LocationService, activity: Activity) = unitLocationInput(locationService, activity) {}
inline fun ViewManager.unitLocationInput(locationService: LocationService, activity: Activity, init: UnitLocationInput.() -> Unit) = ankoView({ UnitLocationInput(it, activity, locationService) }, 0, init)

class UnitLocationInput(c: Context, activity: Activity, private val locationService: LocationService) : LinearLayout(c) {

    private lateinit var hideableContent: LinearLayout
    private lateinit var unit: EditText
    private lateinit var callSign: EditText
    private lateinit var location: LocationInput

    init {
        verticalLayout {
            textView(explosive_report_line_2){
                textSize = 20f
            }.setOnClickListener {
                if (hideableContent.visibility == View.VISIBLE) {
                    hideableContent.visibility = View.GONE
                } else {
                    hideableContent.visibility = View.VISIBLE
                }
            }
            hideableContent = verticalLayout {
                linearLayout {
                    unit = editText {
                        hintResource = explosive_report_unit
                    }
                    callSign = editText {
                        hintResource = report_call_sign
                    }
                }
                linearLayout {
                    location = locationInput(report_medevac_line_1_coordinates, report_medevac_line_1_coordinates, locationService, activity)
                }
            }
            setPadding(0,0,0, 30)
        }
    }

    fun onLocationSelected(requestCode: Int, resultCode: Int, data: Intent) {
        location.onLocationSelected(requestCode, resultCode, data)
    }

    fun getValue(): String {
        return "${this.unit.text}, ${this.callSign.text}, ${this.location.getValue()}"
    }

    fun setLocation(location: String) {
        this.location.setValue(location)
    }

    fun setUnit(unit: String) {
        this.unit.setText(unit)
    }

    fun setCallSign(callSign: String) {
        this.callSign.setText(callSign)
    }
}