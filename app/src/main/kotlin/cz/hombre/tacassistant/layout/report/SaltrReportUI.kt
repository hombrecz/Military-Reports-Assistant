package cz.hombre.tacassistant.layout.report

import android.support.design.widget.FloatingActionButton
import android.view.Gravity
import cz.hombre.tacassistant.R.id.*
import cz.hombre.tacassistant.R.string.*
import cz.hombre.tacassistant.activity.reports.SaltrReport
import cz.hombre.tacassistant.layout.component.TextInput
import cz.hombre.tacassistant.layout.component.textInput
import org.jetbrains.anko.*
import org.jetbrains.anko.design.coordinatorLayout
import org.jetbrains.anko.design.floatingActionButton

class SaltrReportUI : AnkoComponent<SaltrReport> {

    lateinit var previewButton: FloatingActionButton
    lateinit var size: TextInput
    lateinit var activity: TextInput
    lateinit var location: TextInput
    lateinit var time: TextInput
    lateinit var request: TextInput

    override fun createView(ui: AnkoContext<SaltrReport>) = with(ui) {
        coordinatorLayout {
            id = saltr_report
            scrollView {
                verticalLayout {
                    size = textInput(report_size, report_size_hint)
                    activity = textInput(report_activity, report_activity_hint)
                    location = textInput(report_location, report_location_hint)
                    time = textInput(report_time, report_time_hint)
                    request = textInput(report_saltr_request, report_saltr_request_hint)
                }
            }

            floatingActionButton {
                imageResource = android.R.drawable.ic_dialog_email
                useCompatPadding = true
                previewButton = this
            }.lparams {
                gravity = Gravity.BOTTOM or Gravity.END
                margin = dip(16)
            }
        }
    }
}