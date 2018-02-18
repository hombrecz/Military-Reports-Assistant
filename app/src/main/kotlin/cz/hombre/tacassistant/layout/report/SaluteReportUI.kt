package cz.hombre.tacassistant.layout.report

import android.support.design.widget.FloatingActionButton
import android.view.Gravity
import cz.hombre.tacassistant.R.id.salute_report
import cz.hombre.tacassistant.R.string.*
import cz.hombre.tacassistant.activity.reports.SaluteReport
import cz.hombre.tacassistant.layout.component.TextInput
import cz.hombre.tacassistant.layout.component.textInput
import org.jetbrains.anko.*
import org.jetbrains.anko.design.coordinatorLayout
import org.jetbrains.anko.design.floatingActionButton

class SaluteReportUI : AnkoComponent<SaluteReport> {

    lateinit var previewButton: FloatingActionButton
    lateinit var size: TextInput
    lateinit var activity: TextInput
    lateinit var location: TextInput
    lateinit var uniform: TextInput
    lateinit var time: TextInput
    lateinit var enemy: TextInput

    override fun createView(ui: AnkoContext<SaluteReport>) = with(ui) {
        coordinatorLayout {
            id = salute_report
            scrollView {
                verticalLayout {
                    size = textInput(report_size, report_size_hint)
                    activity = textInput(report_activity, report_activity_hint)
                    location = textInput(report_location, report_location_hint)
                    uniform = textInput(report_salute_uniform, report_salute_uniform_hint)
                    time = textInput(report_time, report_time_hint)
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