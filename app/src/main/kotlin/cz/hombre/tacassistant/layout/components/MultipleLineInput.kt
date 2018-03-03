package cz.hombre.tacassistant.layout.components

import android.content.Context
import android.view.View
import android.view.ViewManager
import android.widget.EditText
import android.widget.LinearLayout
import cz.hombre.tacassistant.Utilities.Companion.COMMA
import org.jetbrains.anko.custom.ankoView
import org.jetbrains.anko.editText
import org.jetbrains.anko.hintResource
import org.jetbrains.anko.textView
import org.jetbrains.anko.verticalLayout

inline fun ViewManager.multipleLineInput(groupLabel: Int, options: Map<Int, Int>) = multipleLineInput(groupLabel, options) {}
inline fun ViewManager.multipleLineInput(groupLabel: Int, options: Map<Int, Int>, init: MultipleLineInput.() -> Unit) = ankoView({ MultipleLineInput(it, groupLabel, options) }, 0, init)

class MultipleLineInput(val c: Context, val groupLabel: Int, val options: Map<Int, Int>) : LinearLayout(c) {

    private lateinit var hideableContent: LinearLayout
    private lateinit var value: EditText
    private var values: Array<EditText> = emptyArray()

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
                    value = editText {
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
            if (!it.text.isNullOrEmpty() && it.text.toString().toInt() > 0) {
                stringBuilder.append("${c.getString(it.id)} ${it.text}${COMMA}")
            }
        }

        return stringBuilder.toString().removeSuffix(COMMA)
    }
}