package cz.hombre.militaryReportsAssistant.activity.reports

import android.content.Intent
import android.os.Bundle
import cz.hombre.militaryReportsAssistant.R
import cz.hombre.militaryReportsAssistant.dto.ReportData
import cz.hombre.militaryReportsAssistant.dto.ReportLine
import cz.hombre.militaryReportsAssistant.layout.reports.SaluteReportUI
import org.jetbrains.anko.setContentView

class SaluteReport : AbstractReport() {

    private var saluteReportUI = SaluteReportUI(dateTimeService, locationService)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        saluteReportUI.setContentView(this)
        setTitle(R.string.title_activity_salute_report)

        saluteReportUI.previewButton.setOnClickListener {
            translateReport()
        }
        saluteReportUI.time.setValue(dateTimeService.getMilitaryDateTimeGroup())
        saluteReportUI.location.setValue(locationService.getCurrentMGRSLocation())
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        saluteReportUI.location.onLocationSelected(requestCode, resultCode, data!!)
    }

    override fun getReportData(): ReportData {
        val size = ReportLine(saluteReportUI.size.getValue())
        val activity = ReportLine(saluteReportUI.activity.getValue())
        val location = ReportLine(saluteReportUI.location.getValue())
        val uniform = ReportLine(saluteReportUI.uniform.getValue())
        val time = ReportLine(saluteReportUI.time.getValue())
        val equipment = ReportLine(saluteReportUI.enemy.getValue())

        return ReportData(getString(R.string.title_activity_salute_report), arrayOf(size, activity, location, uniform, time, equipment))
    }
}
