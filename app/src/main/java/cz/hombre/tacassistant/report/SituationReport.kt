package cz.hombre.tacassistant.report

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import cz.hombre.tacassistant.R
import cz.hombre.tacassistant.ReportPreviewActivity
import cz.hombre.tacassistant.dto.ReportData
import cz.hombre.tacassistant.dto.ReportLine
import cz.hombre.tacassistant.services.DateTimeService
import kotlinx.android.synthetic.main.activity_situation_report.*
import kotlinx.android.synthetic.main.content_situation_report.*
import org.koin.android.ext.android.inject

class SituationReport : AppCompatActivity() {

    private val dateTimeService: DateTimeService by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_situation_report)

        fab.setOnClickListener {
            val report = getReportData()
            val previewIntent = Intent(this, ReportPreviewActivity::class.java)
            previewIntent.putExtra("report", report)
            startActivity(previewIntent)
        }
        sitrep_value_time.setText(dateTimeService.getZuluDateTimeGroup())
        setHidingContent()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun setHidingContent() {
        switchVisibility(sitrep_label_time, sitrep_content_time)
        switchVisibility(sitrep_label_status, sitrep_content_status)
        switchVisibility(sitrep_label_enemy, sitrep_content_enemy)
        switchVisibility(sitrep_label_own, sitrep_content_own)
        switchVisibility(sitrep_label_follow, sitrep_content_follow)
    }

    private fun switchVisibility(initiator: View, target: View) {
        initiator.setOnClickListener {
            if (target.visibility == View.VISIBLE) {
                target.visibility = View.GONE
            } else {
                target.visibility = View.VISIBLE
            }
        }
    }

    private fun getReportData(): ReportData {

        val time = ReportLine(sitrep_value_time.text.toString())
        val status = ReportLine(getStatusValue())
        val enemy = ReportLine(sitrep_value_enemy.text.toString())
        val own = ReportLine(sitrep_value_own.text.toString())
        val follow = ReportLine(sitrep_value_follow.text.toString())

        return ReportData("Situation report", arrayOf(time, status, enemy, own, follow))
    }

    private fun getStatusValue(): String {
        val status: String

        when (sitrep_content_status.checkedRadioButtonId) {
            sitrep_value_status_green.id -> {
                status = "Green"
            }
            sitrep_value_status_amber.id -> {
                status = "Amber"
            }
            sitrep_value_status_red.id -> {
                status = "Red"
            }
            sitrep_value_status_black.id -> {
                status = "Black"
            }
            else -> {
                status = ""
            }
        }

        return status
    }

}
