package cz.hombre.militaryReportsAssistant.activity.reports

import android.content.Intent
import android.os.Bundle
import cz.hombre.militaryReportsAssistant.R
import cz.hombre.militaryReportsAssistant.dto.ReportData
import cz.hombre.militaryReportsAssistant.dto.ReportLine
import cz.hombre.militaryReportsAssistant.layout.reports.SituationReportUI
import org.jetbrains.anko.setContentView

class SituationReport : AbstractReport() {

    private var situationReportUI = SituationReportUI(dateTimeService, locationService)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        situationReportUI.setContentView(this)
        setTitle(R.string.title_activity_situation_report)

        situationReportUI.previewButton.setOnClickListener {
            translateReport()
        }
        situationReportUI.time.setValue(dateTimeService.getMilitaryDateTimeGroup())
        situationReportUI.location.setValue(locationService.getCurrentMGRSLocation())
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (data != null) {
            situationReportUI.location.onLocationSelected(requestCode, resultCode, data)
        }
    }

    override fun getReportData(): ReportData {

        val time = ReportLine(situationReportUI.time.getValue())
        val enemy = ReportLine(situationReportUI.enemy.getValue())
        val location = ReportLine(situationReportUI.location.getValue())
        val own = ReportLine(situationReportUI.own.getValue())
        val vehicles = ReportLine(situationReportUI.vehicles.getValue())
        val status = ReportLine(situationReportUI.status.getValue())
        val ammunition = ReportLine(situationReportUI.ammunition.getValue())
        val fuel = ReportLine(situationReportUI.fuel.getValue())
        val follow = ReportLine(situationReportUI.following.getValue())

        return ReportData(getString(R.string.title_activity_situation_report), arrayOf(time, enemy, location, own, vehicles, status, ammunition,
                fuel, follow))
    }
}
