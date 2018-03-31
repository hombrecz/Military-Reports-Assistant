package cz.hombre.militaryReportsAssistant.activity.reports

import android.os.Bundle
import cz.hombre.militaryReportsAssistant.R
import cz.hombre.militaryReportsAssistant.dto.ReportData
import cz.hombre.militaryReportsAssistant.dto.ReportLine
import cz.hombre.militaryReportsAssistant.layout.reports.SituationReportUI
import org.jetbrains.anko.setContentView

class SituationReport : AbstractReport() {

    private var situationReportUI = SituationReportUI(dateTimeService)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        situationReportUI.setContentView(this)
        setTitle(R.string.title_activity_situation_report)

        situationReportUI.previewButton.setOnClickListener {
            translateReport()
        }
        situationReportUI.time.setValue(dateTimeService.getMilitaryDateTimeGroup())
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun getReportData(): ReportData {

        val time = ReportLine(situationReportUI.time.getValue())
        val status = ReportLine(situationReportUI.status.getValue())
        val enemy = ReportLine(situationReportUI.enemy.getValue())
        val own = ReportLine(situationReportUI.own.getValue())
        val follow = ReportLine(situationReportUI.following.getValue())

        return ReportData(getString(R.string.title_activity_situation_report), arrayOf(time, status, enemy, own, follow))
    }
}
