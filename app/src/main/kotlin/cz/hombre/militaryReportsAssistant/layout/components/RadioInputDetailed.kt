package cz.hombre.militaryReportsAssistant.layout.components

import android.content.Context
import android.view.View
import android.view.ViewManager
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.RadioGroup
import org.jetbrains.anko.*
import org.jetbrains.anko.custom.ankoView

inline fun ViewManager.radioInputDetailed(groupLabel: Int, options: Map<Int, Int>, detailHint: Int) = radioInputDetailed(groupLabel, options, detailHint) {}
inline fun ViewManager.radioInputDetailed(groupLabel: Int, options: Map<Int, Int>, detailHint: Int, init: RadioInputDetailed.() -> Unit) = ankoView({ RadioInputDetailed(it, groupLabel, options, detailHint) }, 0, init)

class RadioInputDetailed(val c: Context, val groupLabel: Int, val options: Map<Int, Int>, val detailHint: Int) : LinearLayout(c) {

    private lateinit var hideableContent: LinearLayout
    private lateinit var radioGroup: RadioGroup
    private lateinit var detail: EditText

    init {
        verticalLayout {
            textView(groupLabel){
                textSize = 20f
            }.setOnClickListener {
                if (hideableContent.visibility == View.VISIBLE) {
                    hideableContent.visibility = View.GONE
                } else {
                    hideableContent.visibility = View.VISIBLE
                }
            }
            hideableContent = verticalLayout {
                radioGroup = radioGroup {
                    options.forEach { entry ->
                        radioButton {
                            id = entry.key
                            textResource = entry.value
                        }
                    }
                }
                radioGroup.check(options.keys.first())
                detail = editText {
                    hintResource = detailHint
                }
            }
            setPadding(0,0,0, 30)
        }
    }

    fun getValue(): String {
        return "${c.getString(radioGroup.checkedRadioButtonId)}, ${detail.text}"
    }
}