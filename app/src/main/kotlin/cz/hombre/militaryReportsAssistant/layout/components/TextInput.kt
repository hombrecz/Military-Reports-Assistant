package cz.hombre.militaryReportsAssistant.layout.components

import android.content.Context
import android.view.View
import android.view.ViewManager
import android.widget.EditText
import android.widget.LinearLayout
import org.jetbrains.anko.*
import org.jetbrains.anko.custom.ankoView

inline fun ViewManager.textInput(label: Int, valueHint: Int) = textInput(label, valueHint) {}
inline fun ViewManager.textInput(label: Int, valueHint: Int, init: TextInput.() -> Unit) = ankoView({ TextInput(it, label, valueHint) }, 0, init)

class TextInput(c: Context, val label: Int, val valueHint: Int) : LinearLayout(c) {

    private lateinit var hideableContent: LinearLayout
    private lateinit var valueEdit: EditText

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
            hideableContent = linearLayout {
                valueEdit = editText {
                    hintResource = valueHint
                }
            }
            setPadding(0,0,0, 30)
        }
    }

    fun getValue(): String {
        return this.valueEdit.text.toString()
    }

    fun setValue(value: String) {
        valueEdit.setText(value)
    }
}