package cz.hombre.tacassistant.layout.components

import android.content.Context
import android.view.View
import android.view.ViewManager
import android.widget.CheckBox
import android.widget.EditText
import android.widget.LinearLayout
import cz.hombre.tacassistant.R.string.report_negative
import org.jetbrains.anko.*
import org.jetbrains.anko.custom.ankoView

inline fun ViewManager.conditionTextInput(label: Int, conditionLabel: Int, hint: Int) = conditionTextInput(label, conditionLabel, hint) {}
inline fun ViewManager.conditionTextInput(label: Int, conditionLabel: Int, hint: Int, init: ConditionTextInput.() -> Unit) = ankoView({ ConditionTextInput(it, label, conditionLabel, hint) }, 0, init)

class ConditionTextInput(val c: Context, val label: Int, val conditionLabel: Int, val editHint: Int) : LinearLayout(c) {

    private lateinit var hideableContent: LinearLayout
    private lateinit var checkBox: CheckBox
    private lateinit var valueEdit: EditText

    init {
        verticalLayout {
            textView(label).setOnClickListener {
                if (hideableContent.visibility == View.VISIBLE) {
                    hideableContent.visibility = View.GONE
                } else {
                    hideableContent.visibility = View.VISIBLE
                }
            }
            hideableContent = verticalLayout {
                checkBox(conditionLabel) {
                    checkBox = this
                }.setOnClickListener {
                    if (valueEdit.visibility == View.VISIBLE) {
                        valueEdit.visibility = View.GONE
                    } else {
                        valueEdit.visibility = View.VISIBLE
                    }
                }
                valueEdit = editText {
                    hintResource = editHint
                    visibility = View.GONE
                }
            }
        }
    }

    fun getValue(): String {
        var value = c.getString(report_negative)
        if (checkBox.isChecked) {
            value = valueEdit.text.toString()
        }

        return value
    }
}