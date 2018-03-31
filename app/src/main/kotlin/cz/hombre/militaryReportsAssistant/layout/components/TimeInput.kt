package cz.hombre.militaryReportsAssistant.layout.components

import android.content.Context
import android.view.View
import android.view.ViewManager
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import com.github.florent37.singledateandtimepicker.dialog.SingleDateAndTimePickerDialog
import cz.hombre.militaryReportsAssistant.R
import cz.hombre.militaryReportsAssistant.services.DateTimeService
import org.jetbrains.anko.button
import org.jetbrains.anko.custom.ankoView
import org.jetbrains.anko.editText
import org.jetbrains.anko.hintResource
import org.jetbrains.anko.linearLayout
import org.jetbrains.anko.textResource
import org.jetbrains.anko.textView
import org.jetbrains.anko.verticalLayout


inline fun ViewManager.timeInput(label: Int, valueHint: Int, dateTimeService: DateTimeService) = timeInput(label, valueHint, dateTimeService) {}
inline fun ViewManager.timeInput(label: Int, valueHint: Int, dateTimeService: DateTimeService, init: TimeInput.() -> Unit) = ankoView({ TimeInput(it, label, valueHint, dateTimeService) }, 0, init)

class TimeInput(private val c: Context, val label: Int, val valueHint: Int, val dateTimeService: DateTimeService) : LinearLayout(c) {

//    private val dateTimeService: DateTimeService by inject()

    private lateinit var hideableContent: LinearLayout
    private lateinit var valueEdit: EditText
    private lateinit var timeButton: Button

    init {
        verticalLayout {
            textView(label) {
                textResource = label
            }.setOnClickListener {
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
                timeButton = button(R.string.report_datetime_button)
                timeButton.setOnClickListener { openDateTimePicker() }
            }
        }
    }

    fun getValue(): String {
        return this.valueEdit.text.toString()
    }

    fun setValue(value: String) {
        return valueEdit.setText(value)
    }

    private fun openDateTimePicker() {
        SingleDateAndTimePickerDialog.Builder(context)
                .backgroundColor(c.resources.getColor(R.color.background))
                .mainColor(c.resources.getColor(R.color.primaryLightColor))
                .titleTextColor(c.resources.getColor(R.color.secondaryColor))
                .displayHours(true)
                .displayMinutes(true)
                .displayListener {}
                .title(c.getString(R.string.report_datetime_dialog))
                .listener(SingleDateAndTimePickerDialog.Listener() {
                    setValue(dateTimeService.getMilitaryDateTimeGroup(it))
                })
                .display()
    }
}