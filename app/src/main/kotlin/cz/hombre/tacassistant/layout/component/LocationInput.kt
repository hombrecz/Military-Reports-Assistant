package cz.hombre.tacassistant.layout.component

import android.content.Context
import android.view.View
import android.view.ViewManager
import android.widget.EditText
import android.widget.LinearLayout
import org.jetbrains.anko.*
import org.jetbrains.anko.custom.ankoView

inline fun ViewManager.locationInput(label: Int, valueHint: Int) = locationInput(label, valueHint) {}
inline fun ViewManager.locationInput(label: Int, valueHint: Int, init: LocationInput.() -> Unit) = ankoView({ LocationInput(it, label, valueHint) }, 0, init)

class LocationInput(c: Context, val label: Int, val valueHint: Int) : LinearLayout(c) {
    private lateinit var hideableContent: LinearLayout
    private lateinit var valueEdit: EditText

    init {
        verticalLayout {
            textView(label) {
                textResource = label
            }
                    .setOnClickListener {
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
            }
        }
    }

    fun getValue(): String {
        return this.valueEdit.text.toString()
    }

    fun setValue(value: String){
        return valueEdit.setText(value)
    }
}