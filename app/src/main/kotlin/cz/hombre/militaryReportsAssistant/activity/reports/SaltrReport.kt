package cz.hombre.militaryReportsAssistant.activity.reports

import android.content.Intent
import android.os.Bundle
import cz.hombre.militaryReportsAssistant.R
import cz.hombre.militaryReportsAssistant.dto.ReportData
import cz.hombre.militaryReportsAssistant.dto.ReportLine
import cz.hombre.militaryReportsAssistant.layout.reports.SaltrReportUI
import org.jetbrains.anko.setContentView

class SaltrReport : AbstractReport() {

    private var saltrReportUI = SaltrReportUI(dateTimeService, locationService)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        saltrReportUI.setContentView(this)
        setTitle(R.string.title_activity_saltr_report)

        saltrReportUI.previewButton.setOnClickListener {
            translateReport()
        }
        saltrReportUI.time.setValue(dateTimeService.getMilitaryDateTimeGroup())
        saltrReportUI.location.setValue(locationService.getCurrentMGRSLocation())
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        saltrReportUI.location.onLocationSelected(requestCode, resultCode, data!!)
    }

    override fun getReportData(): ReportData {
        val size = ReportLine(saltrReportUI.size.getValue())
        val activity = ReportLine(saltrReportUI.activity.getValue())
        val location = ReportLine(saltrReportUI.location.getValue())
        val time = ReportLine(saltrReportUI.time.getValue())
        val request = ReportLine(saltrReportUI.request.getValue())

        return ReportData(getString(R.string.title_activity_saltr_report), arrayOf(size, activity, location, time, request))
    }

}
