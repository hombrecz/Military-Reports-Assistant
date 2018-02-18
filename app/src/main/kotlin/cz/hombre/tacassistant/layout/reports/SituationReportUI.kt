package cz.hombre.tacassistant.layout.reports

import android.support.design.widget.FloatingActionButton
import android.view.Gravity
import cz.hombre.tacassistant.R
import cz.hombre.tacassistant.R.string.*
import cz.hombre.tacassistant.activity.reports.SituationReport
import cz.hombre.tacassistant.layout.components.*
import org.jetbrains.anko.*
import org.jetbrains.anko.design.coordinatorLayout
import org.jetbrains.anko.design.floatingActionButton

class SituationReportUI : AnkoComponent<SituationReport> {

    lateinit var previewButton: FloatingActionButton
    lateinit var time: TimeInput
    lateinit var status: RadioInput
    lateinit var enemy: TextInput
    lateinit var own: TextInput
    lateinit var following: TextInput

    override fun createView(ui: AnkoContext<SituationReport>) = with(ui) {
        coordinatorLayout {
            id = R.id.situation_report
            scrollView {
                verticalLayout {
                    time = timeInput(R.string.report_time, R.string.report_time_hint)
                    status = radioInput(R.string.report_situation_status, statusOptions())
                    enemy = textInput(R.string.report_situation_enemy, R.string.report_situation_enemy_hint)
                    own = textInput(R.string.report_situation_own, R.string.report_situation_own_hint)
                    following = textInput(R.string.report_situation_following, R.string.report_situation_following_hint)
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

    private fun statusOptions() = linkedMapOf(
            report_situation_status_green to report_situation_status_green_label,
            report_situation_status_amber to report_situation_status_amber_label,
            report_situation_status_red to report_situation_status_red_label,
            report_situation_status_black to report_situation_status_black_label)
}