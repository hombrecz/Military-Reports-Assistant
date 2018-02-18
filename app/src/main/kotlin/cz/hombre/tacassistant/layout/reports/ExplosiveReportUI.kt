package cz.hombre.tacassistant.layout.reports

import android.support.design.widget.FloatingActionButton
import android.view.Gravity
import cz.hombre.tacassistant.R
import cz.hombre.tacassistant.R.string.*
import cz.hombre.tacassistant.activity.reports.ExplosiveReport
import cz.hombre.tacassistant.layout.components.*
import org.jetbrains.anko.*
import org.jetbrains.anko.design.coordinatorLayout
import org.jetbrains.anko.design.floatingActionButton

class ExplosiveReportUI : AnkoComponent<ExplosiveReport> {

    lateinit var previewButton: FloatingActionButton
    lateinit var line1: TimeInput
    lateinit var line2: UnitLocationInput
    lateinit var line3: DualTextInput
    lateinit var line4: RadioInputDetailed
    lateinit var line5: ConditionTextInput
    lateinit var line6: TextInput
    lateinit var line7: TextInput
    lateinit var line8: TextInput
    lateinit var line9: RadioInput

    override fun createView(ui: AnkoContext<ExplosiveReport>) = with(ui) {
        coordinatorLayout {
            id = R.id.explosive_report
            scrollView {
                verticalLayout {
                    line1 = timeInput(explosive_report_line_1, report_time_hint)
                    line2 = unitLocationInput()
                    line3 = dualTextInput(explosive_report_line_3, report_frequency, report_call_sign)
                    line4 = radioInputDetailed(explosive_report_line_4, ammunitionTypeOptions(), explosive_report_line_4_description)
                    line5 = conditionTextInput(explosive_report_line_5, explosive_report_line_5_checkbox, explosive_report_line_5_description_hint)
                    line6 = textInput(explosive_report_line_6, explosive_report_line_6_hint)
                    line7 = textInput(explosive_report_line_7, explosive_report_line_7_hint)
                    line8 = textInput(explosive_report_line_8, explosive_report_line_8_hint)
                    line9 = radioInput(explosive_report_line_9, threatOptions())
                }
            }.lparams(
                    width = matchParent,
                    height = matchParent
            )

            previewButton = floatingActionButton {
                imageResource = android.R.drawable.ic_dialog_email
                useCompatPadding = true
            }.lparams {
                gravity = Gravity.BOTTOM or Gravity.END
                margin = dip(16)
            }
        }
    }

    private fun ammunitionTypeOptions() = linkedMapOf(
            explosive_report_line_4_dropped to explosive_report_line_4_dropped,
            explosive_report_line_4_projected to explosive_report_line_4_projected,
            explosive_report_line_4_placed to explosive_report_line_4_placed,
            explosive_report_line_4_thrown to explosive_report_line_4_thrown)

    private fun threatOptions() = linkedMapOf(
            explosive_report_line_9_immediate to explosive_report_line_9_immediate,
            explosive_report_line_9_indirect to explosive_report_line_9_indirect,
            explosive_report_line_9_minor to explosive_report_line_9_minor,
            explosive_report_line_9_no_threat to explosive_report_line_9_no_threat)
}