package cz.hombre.militaryReportsAssistant.layout.reports

import android.support.design.widget.FloatingActionButton
import android.view.Gravity
import cz.hombre.militaryReportsAssistant.R
import cz.hombre.militaryReportsAssistant.R.id.saltr_report
import cz.hombre.militaryReportsAssistant.R.string.*
import cz.hombre.militaryReportsAssistant.activity.reports.SaltrReport
import cz.hombre.militaryReportsAssistant.layout.components.*
import cz.hombre.militaryReportsAssistant.services.DateTimeService
import cz.hombre.militaryReportsAssistant.services.LocationService
import org.jetbrains.anko.*
import org.jetbrains.anko.design.coordinatorLayout
import org.jetbrains.anko.design.floatingActionButton

class SaltrReportUI(private val dateTimeService: DateTimeService, private val locationService: LocationService) : AnkoComponent<SaltrReport> {

    lateinit var previewButton: FloatingActionButton
    lateinit var size: TextInput
    lateinit var activity: TextInput
    lateinit var location: LocationInput
    lateinit var time: TimeInput
    lateinit var request: TextInput

    override fun createView(ui: AnkoContext<SaltrReport>) = with(ui) {
        coordinatorLayout {
            id = saltr_report
            scrollView {
                verticalLayout {
                    size = textInput(report_size, report_size_hint)
                    activity = textInput(report_activity, report_activity_hint)
                    location = locationInput(report_location, report_location_hint, locationService, ui.owner)
                    time = timeInput(report_time, report_time_hint, dateTimeService)
                    request = textInput(report_saltr_request, report_saltr_request_hint)
                }
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
}