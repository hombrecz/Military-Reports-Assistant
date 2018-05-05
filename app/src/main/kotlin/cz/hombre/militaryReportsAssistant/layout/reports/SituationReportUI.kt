package cz.hombre.militaryReportsAssistant.layout.reports

import android.support.design.widget.FloatingActionButton
import android.view.Gravity
import cz.hombre.militaryReportsAssistant.R
import cz.hombre.militaryReportsAssistant.R.string.*
import cz.hombre.militaryReportsAssistant.activity.reports.SituationReport
import cz.hombre.militaryReportsAssistant.layout.components.*
import cz.hombre.militaryReportsAssistant.services.DateTimeService
import cz.hombre.militaryReportsAssistant.services.LocationService
import org.jetbrains.anko.*
import org.jetbrains.anko.design.coordinatorLayout
import org.jetbrains.anko.design.floatingActionButton

class SituationReportUI(private val dateTimeService: DateTimeService, private val locationService: LocationService) : AnkoComponent<SituationReport> {

    lateinit var previewButton: FloatingActionButton
    lateinit var time: TimeInput
    lateinit var enemy: TextInput
    lateinit var location: LocationInput
    lateinit var own: TextInput
    lateinit var vehicles: TextInput
    lateinit var status: RadioInput
    lateinit var ammunition: RadioInput
    lateinit var fuel: RadioInput
    lateinit var following: TextInput

    override fun createView(ui: AnkoContext<SituationReport>) = with(ui) {
        coordinatorLayout {
            id = R.id.situation_report
            scrollView {
                verticalLayout {
                    time = timeInput(R.string.report_time, R.string.report_time_hint, dateTimeService)
                    enemy = textInput(R.string.report_situation_enemy, R.string.report_situation_enemy_hint)
                    location = locationInput(report_situation_location, report_situation_location_hint, locationService, ui.owner)
                    own = textInput(R.string.report_situation_own, R.string.report_situation_own_hint)
                    vehicles = textInput(report_situation_vehicles, report_situation_vehicles_hint)
                    status = radioInput(R.string.report_situation_status, unitStatusOptions())
                    ammunition = radioInput(report_situation_ammunition, statusOptions())
                    fuel = radioInput(report_situation_fuel, statusOptions())
                    following = textInput(R.string.report_situation_following, R.string.report_situation_following_hint)
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

    private fun unitStatusOptions() = linkedMapOf(
            report_situation_status_green to report_situation_unit_status_green_label,
            report_situation_status_amber to report_situation_unit_status_amber_label,
            report_situation_status_red to report_situation_unit_status_red_label,
            report_situation_status_black to report_situation_unit_status_black_label)

    private fun statusOptions() = linkedMapOf(
            report_situation_status_green to report_situation_status_green_label,
            report_situation_status_amber to report_situation_status_amber_label,
            report_situation_status_red to report_situation_status_red_label,
            report_situation_status_black to report_situation_status_black_label)
}