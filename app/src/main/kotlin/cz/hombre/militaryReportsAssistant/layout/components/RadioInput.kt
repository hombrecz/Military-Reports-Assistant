package cz.hombre.militaryReportsAssistant.layout.components

import android.content.Context
import android.view.View
import android.view.ViewManager
import android.widget.LinearLayout
import android.widget.RadioGroup
import org.jetbrains.anko.*
import org.jetbrains.anko.custom.ankoView

inline fun ViewManager.radioInput(groupLabel: Int, options: Map<Int, Int>) = radioInput(groupLabel, options) {}
inline fun ViewManager.radioInput(groupLabel: Int, options: Map<Int, Int>, init: RadioInput.() -> Unit) = ankoView({ RadioInput(it, groupLabel, options) }, 0, init)

class RadioInput(val c: Context, val groupLabel: Int, val options: Map<Int, Int>) : LinearLayout(c) {

    private lateinit var radioGroup: RadioGroup

    init {
        verticalLayout {
            textView(groupLabel).setOnClickListener {
                if (radioGroup.visibility == View.VISIBLE) {
                    radioGroup.visibility = View.GONE
                } else {
                    radioGroup.visibility = View.VISIBLE
                }
            }
            radioGroup = radioGroup {
                options.forEach { entry ->
                    radioButton {
                        id = entry.key
                        textResource = entry.value
                    }
                }
            }
            radioGroup.check(options.keys.first())
        }
    }

    fun getValue(): String {
        return c.getString(radioGroup.checkedRadioButtonId)
    }
}