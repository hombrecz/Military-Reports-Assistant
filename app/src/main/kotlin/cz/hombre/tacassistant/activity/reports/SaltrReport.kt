package cz.hombre.tacassistant.activity.reports

import android.content.Intent
import android.os.Bundle
import cz.hombre.tacassistant.R
import cz.hombre.tacassistant.dto.ReportData
import cz.hombre.tacassistant.dto.ReportLine
import cz.hombre.tacassistant.layout.report.SaltrReportUI
import cz.hombre.tacassistant.services.REPORT_PROPERTY
import org.jetbrains.anko.setContentView

class SaltrReport : AbstractReport() {

    private var saltrReportUI = SaltrReportUI()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        saltrReportUI.setContentView(this)
        saltrReportUI.previewButton.setOnClickListener {
            val report = getReportData()
            val previewIntent = Intent(this, ReportPreviewActivity::class.java)
            previewIntent.putExtra(REPORT_PROPERTY, report)
            startActivity(previewIntent)
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
