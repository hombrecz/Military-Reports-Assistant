package cz.hombre.militaryReportsAssistant.layout.reports

import android.support.design.widget.FloatingActionButton
import android.view.Gravity
import cz.hombre.militaryReportsAssistant.R
import cz.hombre.militaryReportsAssistant.R.string.explosive_report_line_1
import cz.hombre.militaryReportsAssistant.R.string.explosive_report_line_3
import cz.hombre.militaryReportsAssistant.R.string.explosive_report_line_4
import cz.hombre.militaryReportsAssistant.R.string.explosive_report_line_4_description
import cz.hombre.militaryReportsAssistant.R.string.explosive_report_line_4_dropped
import cz.hombre.militaryReportsAssistant.R.string.explosive_report_line_4_placed
import cz.hombre.militaryReportsAssistant.R.string.explosive_report_line_4_projected
import cz.hombre.militaryReportsAssistant.R.string.explosive_report_line_4_thrown
import cz.hombre.militaryReportsAssistant.R.string.explosive_report_line_5
import cz.hombre.militaryReportsAssistant.R.string.explosive_report_line_5_checkbox
import cz.hombre.militaryReportsAssistant.R.string.explosive_report_line_5_description_hint
import cz.hombre.militaryReportsAssistant.R.string.explosive_report_line_6
import cz.hombre.militaryReportsAssistant.R.string.explosive_report_line_6_hint
import cz.hombre.militaryReportsAssistant.R.string.explosive_report_line_7
import cz.hombre.militaryReportsAssistant.R.string.explosive_report_line_7_hint
import cz.hombre.militaryReportsAssistant.R.string.explosive_report_line_8
import cz.hombre.militaryReportsAssistant.R.string.explosive_report_line_8_hint
import cz.hombre.militaryReportsAssistant.R.string.explosive_report_line_9
import cz.hombre.militaryReportsAssistant.R.string.explosive_report_line_9_immediate
import cz.hombre.militaryReportsAssistant.R.string.explosive_report_line_9_indirect
import cz.hombre.militaryReportsAssistant.R.string.explosive_report_line_9_minor
import cz.hombre.militaryReportsAssistant.R.string.explosive_report_line_9_no_threat
import cz.hombre.militaryReportsAssistant.R.string.report_call_sign
import cz.hombre.militaryReportsAssistant.R.string.report_frequency
import cz.hombre.militaryReportsAssistant.R.string.report_time_hint
import cz.hombre.militaryReportsAssistant.activity.reports.ExplosiveReport
import cz.hombre.militaryReportsAssistant.layout.components.ConditionTextInput
import cz.hombre.militaryReportsAssistant.layout.components.DualTextInput
import cz.hombre.militaryReportsAssistant.layout.components.RadioInput
import cz.hombre.militaryReportsAssistant.layout.components.RadioInputDetailed
import cz.hombre.militaryReportsAssistant.layout.components.TextInput
import cz.hombre.militaryReportsAssistant.layout.components.TimeInput
import cz.hombre.militaryReportsAssistant.layout.components.UnitLocationInput
import cz.hombre.militaryReportsAssistant.layout.components.conditionTextInput
import cz.hombre.militaryReportsAssistant.layout.components.dualTextInput
import cz.hombre.militaryReportsAssistant.layout.components.radioInput
import cz.hombre.militaryReportsAssistant.layout.components.radioInputDetailed
import cz.hombre.militaryReportsAssistant.layout.components.textInput
import cz.hombre.militaryReportsAssistant.layout.components.timeInput
import cz.hombre.militaryReportsAssistant.layout.components.unitLocationInput
import cz.hombre.militaryReportsAssistant.services.DateTimeService
import cz.hombre.militaryReportsAssistant.services.LocationService
import org.jetbrains.anko.AnkoComponent
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.design.coordinatorLayout
import org.jetbrains.anko.design.floatingActionButton
import org.jetbrains.anko.dip
import org.jetbrains.anko.imageResource
import org.jetbrains.anko.margin
import org.jetbrains.anko.matchParent
import org.jetbrains.anko.padding
import org.jetbrains.anko.scrollView
import org.jetbrains.anko.verticalLayout
import org.jetbrains.anko.verticalPadding

class ExplosiveReportUI(private val dateTimeService: DateTimeService, private val locationService: LocationService) : AnkoComponent<ExplosiveReport> {

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
                    line1 = timeInput(explosive_report_line_1, report_time_hint, dateTimeService)
                    line2 = unitLocationInput(locationService, ui.owner)
                    line3 = dualTextInput(explosive_report_line_3, report_frequency, report_call_sign)
                    line4 = radioInputDetailed(explosive_report_line_4, ammunitionTypeOptions(), explosive_report_line_4_description)
                    line5 = conditionTextInput(explosive_report_line_5, explosive_report_line_5_checkbox, explosive_report_line_5_description_hint)
                    line6 = textInput(explosive_report_line_6, explosive_report_line_6_hint)
                    line7 = textInput(explosive_report_line_7, explosive_report_line_7_hint)
                    line8 = textInput(explosive_report_line_8, explosive_report_line_8_hint)
                    line9 = radioInput(explosive_report_line_9, threatOptions())
                }
                padding = dip(10)
            }.lparams(
                    width = matchParent,
                    height = matchParent
            )

            previewButton = floatingActionButton {
                imageResource = R.drawable.ic_prepare_fab
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