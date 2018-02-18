package cz.hombre.tacassistant.activity.reports

import android.os.Bundle
import cz.hombre.tacassistant.R
import cz.hombre.tacassistant.dto.ReportData
import cz.hombre.tacassistant.dto.ReportLine
import cz.hombre.tacassistant.layout.reports.MedevacReportUI
import org.jetbrains.anko.setContentView


class MedevacReport : AbstractReport() {

    private var medevacReportUI = MedevacReportUI()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        medevacReportUI.setContentView(this)
        medevacReportUI.previewButton.setOnClickListener {
            translateReport()
        }
        medevacReportUI.line2.setFirstValue(preferencesService.getFrequency())
        medevacReportUI.line2.setSecondValue(preferencesService.getCallSign())
        medevacReportUI.line1.setValue(locationService.getCurrentMGRSLocation())

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun getReportData(): ReportData {
        val line1 = ReportLine(getString(R.string.report_line) + " 1", medevacReportUI.line1.getValue())
        val line2 = ReportLine(getString(R.string.report_line) + " 2", medevacReportUI.line2.getValue())
        val line3 = ReportLine(getString(R.string.report_line) + " 3", medevacReportUI.line3.getValue())
        val line4 = ReportLine(getString(R.string.report_line) + " 4", medevacReportUI.line4.getValue())
        val line5 = ReportLine(getString(R.string.report_line) + " 5", medevacReportUI.line5.getValue())
        val line6 = ReportLine(getString(R.string.report_line) + " 6", medevacReportUI.line6.getValue())
        val line7 = ReportLine(getString(R.string.report_line) + " 7", medevacReportUI.line7.getValue())
        val line8 = ReportLine(getString(R.string.report_line) + " 8", medevacReportUI.line8.getValue())
        val line9 = ReportLine(getString(R.string.report_line) + " 9", medevacReportUI.line9.getValue())

        return ReportData(getString(R.string.title_activity_medevac_report), arrayOf(line1, line2, line3, line4, line5, line6, line7, line8, line9))
    }
}
