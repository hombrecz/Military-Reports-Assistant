package cz.hombre.militaryReportsAssistant.activity.reports

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import cz.hombre.militaryReportsAssistant.R
import cz.hombre.militaryReportsAssistant.Utilities
import cz.hombre.militaryReportsAssistant.Utilities.Companion.REPORT_PROPERTY
import cz.hombre.militaryReportsAssistant.dto.ReportData
import cz.hombre.militaryReportsAssistant.layout.reports.ReportPreviewUI
import cz.hombre.militaryReportsAssistant.services.DateTimeService
import cz.hombre.militaryReportsAssistant.services.EncodingService
import cz.hombre.militaryReportsAssistant.services.LocaleService
import org.jetbrains.anko.design.longSnackbar
import org.jetbrains.anko.setContentView
import org.jetbrains.anko.share
import org.koin.android.ext.android.inject

class ReportPreviewActivity : AppCompatActivity() {

    private var reportPreviewUI = ReportPreviewUI()
    private var ramrod = false
    private var spelling = false
    private lateinit var report: ReportData

    private val encodingService: EncodingService by inject()
    private val dateTimeService: DateTimeService by inject()
    private val localeService: LocaleService by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        reportPreviewUI.setContentView(this)
        setTitle(R.string.title_activity_report_preview)


        reportPreviewUI.spellingButton.setOnClickListener { view ->
            longSnackbar(view, localeService.getStringForActualLocale(baseContext, R.string.report_preview_spelling_message))
            switchSpelling()
        }

        reportPreviewUI.ramrodButton.setOnClickListener { view ->
            longSnackbar(view, R.string.report_preview_ramrod_message)
            longSnackbar(view, localeService.getStringForActualLocale(baseContext, R.string.report_preview_ramrod_message))
            switchRamrod()
        }

        reportPreviewUI.shareButton.setOnClickListener {
            share(this.title.toString() + Utilities.NEW_LINE + encodingService.formatReportText(report), this.title.toString())
        }

        report = intent?.getSerializableExtra(REPORT_PROPERTY) as ReportData

        title = report.name + Utilities.SEPARATOR_DASH + dateTimeService.getLocalDateTime()

        reportPreviewUI.setContent(encodingService.formatReportText(report))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun switchRamrod() {
        ramrod = !ramrod
        encode()
    }

    private fun switchSpelling() {
        spelling = !spelling
        encode()
    }

    private fun encode() {
        var encodedReport = report.copy()
        if (ramrod) {
            encodedReport = encodingService.encodeNumbersWithRAMROD(encodedReport)
        }
        if (spelling) {
            encodedReport = encodingService.encodeLettersWithSpellingAlphabet(encodedReport)
        }
        reportPreviewUI.setContent(encodingService.formatReportText(encodedReport))
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }

}
