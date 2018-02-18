package cz.hombre.tacassistant.layout.component

import android.content.Context
import android.view.View
import android.view.ViewManager
import android.widget.EditText
import android.widget.LinearLayout
import cz.hombre.tacassistant.R.string.*
import org.jetbrains.anko.*
import org.jetbrains.anko.custom.ankoView

inline fun ViewManager.unitLocationInput() = unitLocationInput() {}
inline fun ViewManager.unitLocationInput(init: UnitLocationInput.() -> Unit) = ankoView({ UnitLocationInput(it) }, 0, init)

class UnitLocationInput(c: Context) : LinearLayout(c) {

    private lateinit var hideableContent: LinearLayout
    private lateinit var unit: EditText
    private lateinit var callSign: EditText
    private lateinit var location: LocationInput

    init {
        verticalLayout {
            textView(explosive_report_line_2).setOnClickListener {
                if (hideableContent.visibility == View.VISIBLE) {
                    hideableContent.visibility = View.GONE
                } else {
                    hideableContent.visibility = View.VISIBLE
                }
            }
            hideableContent = verticalLayout {
                linearLayout {
                    unit = editText() {
                        hintResource = explosive_report_unit
                    }
                    callSign = editText() {
                        hintResource = report_call_sign
                    }
                }
                linearLayout {
                    location = locationInput(report_medevac_line_1_coordinates, report_medevac_line_1_coordinates)
                }
            }
        }
    }

    fun getValue(): String {
        return "${this.unit.text.toString()}, ${this.callSign.text.toString()}, ${this.location.getValue()}"
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