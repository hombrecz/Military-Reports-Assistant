package cz.hombre.tacassistant.report

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.widget.RadioButton
import cz.hombre.tacassistant.R
import cz.hombre.tacassistant.ReportPreviewActivity
import cz.hombre.tacassistant.dto.ReportData
import cz.hombre.tacassistant.dto.ReportLine

import kotlinx.android.synthetic.main.activity_explosive_report.*
import kotlinx.android.synthetic.main.content_explosive_report.*

class ExplosiveReport : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_medevac_report)

        fab.setOnClickListener { view ->
            val report = getReportData()
            val previewIntent = Intent(this, ReportPreviewActivity::class.java)
            previewIntent.putExtra("report", report)
            startActivity(previewIntent)
        }
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun getReportData(): ReportData {

        val line1 = ReportLine("Line 1", explosive_value_line1.text.toString())
        val line2 = ReportLine("Line 2", getSecondLine())
        val line3 = ReportLine("Line 3", getThirdLine())
        val line4 = ReportLine("Line 4", getFourthLine())
        val line5 = ReportLine("Line 5", getFifthLine())
        val line6 = ReportLine("Line 6", explosive_value_line6.text.toString())
        val line7 = ReportLine("Line 7", explosive_value_line7.text.toString())
        val line8 = ReportLine("Line 8", explosive_value_line8.text.toString())
        val line9 = ReportLine("Line 9", getRadioButtonValue(explosive_content_line9.checkedRadioButtonId))

        val reportData = ReportData("UXO/IED 9-liner", arrayOf(line1, line2, line3, line4, line5, line6, line7, line8, line9))

        return reportData
    }

    private fun getSecondLine(): String {
        val sb = StringBuilder()
        sb.append(explosive_value_line2_unit.text.toString())
        sb.append(", ")
        sb.appendln(explosive_value_line2_callsign.text.toString())
        sb.appendln(explosive_content_line2_location.text.toString())

        return sb.toString()
    }

    private fun getThirdLine() = explosive_value_line3_frequency.text.toString() + ", " + explosive_value_line3_callsign.text.toString()

    private fun getFourthLine(): String {
        val sb = StringBuilder()
        sb.appendln(getRadioButtonValue(explosive_radio_group_line4.id))
        sb.append(explosive_value_line4_details.text.toString())

        return sb.toString()
    }

    private fun getFifthLine(): String {
        var result = "negative"
        if (explosive_value_line5_observed.isChecked) {
            result = explosive_value_line5_details.text.toString()
        }

        return result
    }

    private fun getRadioButtonValue(radioButtonGroupId: Int): String {
        val radioButton = findViewById(radioButtonGroupId) as RadioButton
        return radioButton.text.toString()
    }

}
