package cz.hombre.tacassistant.activity.reports

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import cz.hombre.tacassistant.R
import cz.hombre.tacassistant.dto.ReportData
import cz.hombre.tacassistant.services.EncodingService
import cz.hombre.tacassistant.services.REPORT_PROPERTY

import kotlinx.android.synthetic.main.activity_report_preview.*
import kotlinx.android.synthetic.main.content_report_preview.*
import org.koin.android.ext.android.inject

class ReportPreviewActivity : AppCompatActivity() {

    private var ramrod = false
    private var spelling = false
    private lateinit var report: ReportData

    private val encodingService: EncodingService by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_report_preview)

        fabTop.setOnClickListener { view ->
            Snackbar.make(view, getString(R.string.report_preview_ramrod_message), Snackbar.LENGTH_LONG)
                    .show()
            switchRamrod()
        }

        fabBottom.setOnClickListener { view ->
            Snackbar.make(view, getString(R.string.report_preview_spelling_message), Snackbar.LENGTH_LONG)
                    .show()
            switchSpelling()
        }

        report = intent?.getSerializableExtra(REPORT_PROPERTY) as ReportData

        title = report.name
        preview_content.setText(encodingService.formatReportText(report))
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
        preview_content.setText(encodingService.formatReportText(encodedReport))
    }

}
