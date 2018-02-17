package cz.hombre.tacassistant.activity.reports

import android.content.Intent
import android.os.Bundle
import cz.hombre.tacassistant.R
import cz.hombre.tacassistant.dto.ReportData
import cz.hombre.tacassistant.dto.ReportLine
import cz.hombre.tacassistant.services.REPORT_PROPERTY
import kotlinx.android.synthetic.main.activity_situation_report.*
import kotlinx.android.synthetic.main.content_situation_report.*

class SituationReport : AbstractReport() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_situation_report)

        fab.setOnClickListener {
            val report = getReportData()
            val previewIntent = Intent(this, ReportPreviewActivity::class.java)
            previewIntent.putExtra(REPORT_PROPERTY, report)
            startActivity(previewIntent)
        }
        sitrep_value_time.setText(dateTimeService.getZuluDateTimeGroup())
        setHidingContent()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun setHidingContent() {
        reportFormService.setHideableItem(sitrep_label_time, sitrep_content_time)
        reportFormService.setHideableItem(sitrep_label_status, sitrep_content_status)
        reportFormService.setHideableItem(sitrep_label_enemy, sitrep_content_enemy)
        reportFormService.setHideableItem(sitrep_label_own, sitrep_content_own)
        reportFormService.setHideableItem(sitrep_label_follow, sitrep_content_follow)
    }

    override fun getReportData(): ReportData {

        val time = ReportLine(sitrep_value_time.text.toString())
        val status = ReportLine(getStatusValue())
        val enemy = ReportLine(sitrep_value_enemy.text.toString())
        val own = ReportLine(sitrep_value_own.text.toString())
        val follow = ReportLine(sitrep_value_follow.text.toString())

        return ReportData(getString(R.string.title_activity_situation_report), arrayOf(time, status, enemy, own, follow))
    }

    private fun getStatusValue(): String {
        val status: String

        when (sitrep_content_status.checkedRadioButtonId) {
            sitrep_value_status_green.id -> {
                status = getString(R.string.report_situation_status_green)
            }
            sitrep_value_status_amber.id -> {
                status = getString(R.string.report_situation_status_amber)
            }
            sitrep_value_status_red.id -> {
                status = getString(R.string.report_situation_status_red)
            }
            sitrep_value_status_black.id -> {
                status = getString(R.string.report_situation_status_black)
            }
            else -> {
                status = ""
            }
        }

        return status
    }

}
