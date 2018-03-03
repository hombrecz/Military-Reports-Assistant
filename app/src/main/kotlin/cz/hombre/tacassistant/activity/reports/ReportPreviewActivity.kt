package cz.hombre.tacassistant.activity.reports

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import cz.hombre.tacassistant.R
import cz.hombre.tacassistant.REPORT_PROPERTY
import cz.hombre.tacassistant.dto.ReportData
import cz.hombre.tacassistant.layout.reports.ReportPreviewUI
import cz.hombre.tacassistant.services.EncodingService
import org.jetbrains.anko.design.longSnackbar
import org.jetbrains.anko.setContentView
import org.koin.android.ext.android.inject

class ReportPreviewActivity : AppCompatActivity() {

    private var reportPreviewUI = ReportPreviewUI()
    private var ramrod = false
    private var spelling = false
    private lateinit var report: ReportData

    private val encodingService: EncodingService by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        reportPreviewUI.setContentView(this)

        reportPreviewUI.spellingButton.setOnClickListener { view ->
            longSnackbar(view, R.string.report_preview_ramrod_message)
            switchRamrod()
        }

        reportPreviewUI.ramrodButton.setOnClickListener { view ->
            longSnackbar(view, R.string.report_preview_spelling_message)
            switchSpelling()
        }

        report = intent?.getSerializableExtra(REPORT_PROPERTY) as ReportData

        title = report.name

        reportPreviewUI.setContent(encodingService.formatReportText(report))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun switchRamrod() {
        ramrod = !ramrod
        encode()
    }

    private fun switchSpelling() {
        spelling = !spelling
        encodingService
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
