package cz.hombre.tacassistant.activity.reports

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import cz.hombre.tacassistant.R
import cz.hombre.tacassistant.dto.ReportData
import cz.hombre.tacassistant.layout.report.ReportPreviewUI
import cz.hombre.tacassistant.services.EncodingService
import cz.hombre.tacassistant.services.REPORT_PROPERTY
import org.jetbrains.anko.setContentView
import org.koin.android.ext.android.inject

class ReportPreviewActivity : AppCompatActivity() {

    private var ramrod = false
    private var spelling = false
    private lateinit var report: ReportData
    private var previewView = ReportPreviewUI()

    private val encodingService: EncodingService by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        previewView.setContentView(this)

        previewView.spellingButton.setOnClickListener { view ->
            Snackbar.make(view, getString(R.string.report_preview_ramrod_message), Snackbar.LENGTH_LONG)
                    .show()
            switchRamrod()
        }

        previewView.ramrodButton.setOnClickListener { view ->
            Snackbar.make(view, getString(R.string.report_preview_spelling_message), Snackbar.LENGTH_LONG)
                    .show()
            switchSpelling()
        }

        report = intent?.getSerializableExtra(REPORT_PROPERTY) as ReportData

        title = report.name

        previewView.setContent(encodingService.formatReportText(report))
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
        previewView.setContent(encodingService.formatReportText(encodedReport))
    }

}
