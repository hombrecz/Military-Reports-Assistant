package cz.hombre.tacassistant.activity.reports

import android.os.Bundle
import cz.hombre.tacassistant.R
import cz.hombre.tacassistant.dto.ReportData
import cz.hombre.tacassistant.dto.ReportLine
import cz.hombre.tacassistant.layout.reports.SaluteReportUI
import org.jetbrains.anko.setContentView

class SaluteReport : AbstractReport() {

    private var saluteReportUI = SaluteReportUI()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        saluteReportUI.setContentView(this)
        saluteReportUI.previewButton.setOnClickListener {
            translateReport()
        }
        saluteReportUI.time.setValue(dateTimeService.getMilitaryDateTimeGroup())
        saluteReportUI.location.setValue(locationService.getCurrentMGRSLocation())
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
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
