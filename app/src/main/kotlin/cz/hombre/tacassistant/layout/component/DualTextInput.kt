package cz.hombre.tacassistant.layout.component

import android.content.Context
import android.view.View
import android.view.ViewManager
import android.widget.EditText
import android.widget.LinearLayout
import org.jetbrains.anko.*
import org.jetbrains.anko.custom.ankoView

inline fun ViewManager.dualTextInput(label: Int, firstHint: Int, secondHint: Int) = dualTextInput(label, firstHint, secondHint) {}
inline fun ViewManager.dualTextInput(label: Int, firstHint: Int, secondHint: Int, init: DualTextInput.() -> Unit) = ankoView({ DualTextInput(it, label, firstHint, secondHint) }, 0, init)

class DualTextInput(val c: Context, val label: Int, val firstHint: Int, val secondHint: Int) : LinearLayout(c) {

    private lateinit var hideableContent: LinearLayout
    private lateinit var firstValue: EditText
    private lateinit var secondValue: EditText

    init {
        verticalLayout {
            textView(label).setOnClickListener {
                if (hideableContent.visibility == View.VISIBLE) {
                    hideableContent.visibility = View.GONE
                } else {
                    hideableContent.visibility = View.VISIBLE
                }
            }
            hideableContent = linearLayout {
                firstValue = editText() {
                    hintResource = firstHint
                }
                secondValue = editText() {
                    hintResource = secondHint
                }
            }
        }
    }

    fun getValue(): String {
        return "${this.firstValue.text.toString()}, ${this.secondValue.text.toString()}"
    }

    fun setFirstValue(value: String) {
        this.firstValue.setText(value)
    }

    fun setSecondValue(value: String) {
        this.secondValue.setText(value)
    }
}