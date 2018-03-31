package cz.hombre.militaryReportsAssistant.layout.reports

import android.support.design.widget.FloatingActionButton
import android.view.Gravity
import cz.hombre.militaryReportsAssistant.R.id.salute_report
import cz.hombre.militaryReportsAssistant.R.string.*
import cz.hombre.militaryReportsAssistant.activity.reports.SaluteReport
import cz.hombre.militaryReportsAssistant.layout.components.*
import cz.hombre.militaryReportsAssistant.services.DateTimeService
import org.jetbrains.anko.*
import org.jetbrains.anko.design.coordinatorLayout
import org.jetbrains.anko.design.floatingActionButton

class SaluteReportUI(private val dateTimeService: DateTimeService) : AnkoComponent<SaluteReport> {

    lateinit var previewButton: FloatingActionButton
    lateinit var size: TextInput
    lateinit var activity: TextInput
    lateinit var location: LocationInput
    lateinit var uniform: TextInput
    lateinit var time: TimeInput
    lateinit var enemy: TextInput

    override fun createView(ui: AnkoContext<SaluteReport>) = with(ui) {
        coordinatorLayout {
            id = salute_report
            scrollView {
                verticalLayout {
                    size = textInput(report_size, report_size_hint)
                    activity = textInput(report_activity, report_activity_hint)
                    location = locationInput(report_location, report_location_hint)
                    uniform = textInput(report_salute_uniform, report_salute_uniform_hint)
                    time = timeInput(report_time, report_time_hint, dateTimeService)
                    enemy = textInput(report_salute_equipment, report_salute_equipment_hint)
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
}