package cz.hombre.tacassistant.activity.reports

import android.os.Bundle
import cz.hombre.tacassistant.R
import cz.hombre.tacassistant.dto.ReportData
import cz.hombre.tacassistant.dto.ReportLine
import cz.hombre.tacassistant.layout.report.SaltrReportUI
import org.jetbrains.anko.setContentView

class SaltrReport : AbstractReport() {

    private var saltrReportUI = SaltrReportUI()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        saltrReportUI.setContentView(this)
        saltrReportUI.previewButton.setOnClickListener {
            translateReport()
        }
        saltrReportUI.time.setValue(dateTimeService.getZuluDateTimeGroup())
        saltrReportUI.location.setValue(locationService.getCurrentMGRSLocation())
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
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
