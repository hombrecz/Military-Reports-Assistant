package cz.hombre.tacassistant.activity.reports

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.RadioButton
import cz.hombre.tacassistant.R
import cz.hombre.tacassistant.dto.ReportData
import cz.hombre.tacassistant.dto.ReportLine
import cz.hombre.tacassistant.services.COMMA
import cz.hombre.tacassistant.services.REPORT_PROPERTY
import kotlinx.android.synthetic.main.activity_explosive_report.*
import kotlinx.android.synthetic.main.content_explosive_report.*

class ExplosiveReport : AbstractReport() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_explosive_report)

        fab.setOnClickListener {
            val report = getReportData()
            val previewIntent = Intent(this, ReportPreviewActivity::class.java)
            previewIntent.putExtra(REPORT_PROPERTY, report)
            startActivity(previewIntent)
        }

        explosive_value_line3_frequency.setText(preferencesService.getFrequency())
        explosive_value_line2_callsign.setText(preferencesService.getCallSign())
        explosive_value_line3_callsign.setText(preferencesService.getCallSign())
        explosive_value_line1.setText(dateTimeService.getZuluDateTimeGroup())
        explosive_content_line2_location.setText(locationService.getCurrentMGRSLocation())

        setLineFiveCheckboxFunction()
        setHidingContent()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun setLineFiveCheckboxFunction() {
        explosive_value_line5_observed.setOnClickListener {
            if (explosive_value_line5_observed.isChecked) {
                explosive_value_line5_details.visibility = View.VISIBLE
            } else {
                explosive_value_line5_details.visibility = View.GONE
            }
        }
    }

    private fun setHidingContent() {
        reportFormService.setHideableItem(explosive_label_line1, explosive_content_line1)
        reportFormService.setHideableItem(explosive_label_line2, explosive_content_line2)
        reportFormService.setHideableItem(explosive_label_line3, explosive_content_line3)
        reportFormService.setHideableItem(explosive_label_line4, explosive_content_line4)
        reportFormService.setHideableItem(explosive_label_line5, explosive_content_line5)
        reportFormService.setHideableItem(explosive_label_line6, explosive_content_line6)
        reportFormService.setHideableItem(explosive_label_line7, explosive_content_line7)
        reportFormService.setHideableItem(explosive_label_line8, explosive_content_line8)
        reportFormService.setHideableItem(explosive_label_line9, explosive_content_line9)
    }

    override fun getReportData(): ReportData {
        val line1 = ReportLine(getString(R.string.report_line) + " 1", explosive_value_line1.text.toString())
        val line2 = ReportLine(getString(R.string.report_line) + " 2", getSecondLine())
        val line3 = ReportLine(getString(R.string.report_line) + " 3", getThirdLine())
        val line4 = ReportLine(getString(R.string.report_line) + " 4", getFourthLine())
        val line5 = ReportLine(getString(R.string.report_line) + " 5", getFifthLine())
        val line6 = ReportLine(getString(R.string.report_line) + " 6", explosive_value_line6.text.toString())
        val line7 = ReportLine(getString(R.string.report_line) + " 7", explosive_value_line7.text.toString())
        val line8 = ReportLine(getString(R.string.report_line) + " 8", explosive_value_line8.text.toString())
        val line9 = ReportLine(getString(R.string.report_line) + " 9", getNinthLineValue())

        return ReportData(getString(R.string.title_activity_explosive_report), arrayOf(line1, line2, line3, line4, line5, line6, line7, line8, line9))
    }

    private fun getSecondLine(): String {
        val sb = StringBuilder()
        sb.append(explosive_value_line2_unit.text.toString())
        if (explosive_value_line2_unit.text.toString().isNotBlank()) {
            sb.append(COMMA)
        }
        sb.appendln(explosive_value_line2_callsign.text.toString())
        sb.appendln(explosive_content_line2_location.text.toString())

        return sb.toString()
    }

    private fun getThirdLine(): String {
        val sb = StringBuilder()
        sb.append(explosive_value_line3_frequency.text.toString())
        if (explosive_value_line3_frequency.text.toString().isNotBlank()) {
            sb.append(COMMA)
        }
        sb.append(explosive_value_line3_callsign.text.toString())

        return sb.toString()
    }

    private fun getFourthLine(): String {
        val sb = StringBuilder()
        sb.appendln(getRadioButtonValue(explosive_radio_group_line4.checkedRadioButtonId))
        sb.append(explosive_value_line4_details.text.toString())

        return sb.toString()
    }

    private fun getFifthLine(): String {
        var result = getString(R.string.report_negative)
        if (explosive_value_line5_observed.isChecked) {
            result = explosive_value_line5_details.text.toString()
        }

        return result
    }

    private fun getNinthLineValue(): String {
        val radioButton = findViewById<RadioButton>(explosive_content_line9.checkedRadioButtonId)
        return radioButton.text.toString()
    }

    private fun getRadioButtonValue(radioButtonGroupId: Int): String {
        val radioButton = findViewById<RadioButton>(radioButtonGroupId)
        return radioButton.text.toString()
    }

}

