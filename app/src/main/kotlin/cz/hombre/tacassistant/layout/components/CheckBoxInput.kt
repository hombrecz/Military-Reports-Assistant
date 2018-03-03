package cz.hombre.tacassistant.layout.components

import android.content.Context
import android.view.View
import android.view.ViewManager
import android.widget.CheckBox
import android.widget.LinearLayout
import cz.hombre.tacassistant.Utilities.Companion.COMMA
import org.jetbrains.anko.checkBox
import org.jetbrains.anko.custom.ankoView
import org.jetbrains.anko.hintResource
import org.jetbrains.anko.textView
import org.jetbrains.anko.verticalLayout

inline fun ViewManager.checkBoxInput(groupLabel: Int, options: Map<Int, Int>) = checkBoxInput(groupLabel, options) {}
inline fun ViewManager.checkBoxInput(groupLabel: Int, options: Map<Int, Int>, init: CheckBoxInput.() -> Unit) = ankoView({ CheckBoxInput(it, groupLabel, options) }, 0, init)

class CheckBoxInput(val c: Context, private val groupLabel: Int, private val options: Map<Int, Int>) : LinearLayout(c) {

    private lateinit var hideableContent: LinearLayout
    private lateinit var value: CheckBox
    private var values: Array<CheckBox> = emptyArray()

    init {
        verticalLayout {
            textView(groupLabel).setOnClickListener {
                if (hideableContent.visibility == View.VISIBLE) {
                    hideableContent.visibility = View.GONE
                } else {
                    hideableContent.visibility = View.VISIBLE
                }
            }

            hideableContent = verticalLayout {
                options.forEach { entry ->
                    value = checkBox {
                        id = entry.key
                        hintResource = entry.value
                    }
                    values = values.plus(value)
                }
            }
        }
    }

    fun getValue(): String {
        val stringBuilder = StringBuilder()

        this.values.forEach {
            if (it.isChecked) {
                stringBuilder.append("${c.getString(it.id)}$COMMA")
            }
        }

        return stringBuilder.toString().removeSuffix(COMMA)
    }
}