package cz.hombre.tacassistant.report

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.RadioButton
import cz.hombre.tacassistant.R
import cz.hombre.tacassistant.ReportPreviewActivity
import cz.hombre.tacassistant.dto.ReportData
import cz.hombre.tacassistant.dto.ReportLine
import kotlinx.android.synthetic.main.activity_salute_report.*
import kotlinx.android.synthetic.main.content_medevac_report.*

class MedevacReport : AppCompatActivity() {

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

        val line1 = ReportLine("Line 1", medevac_value_line1.text.toString())
        val line2 = ReportLine("Line 2", medevac_value_line2_frequency.text.toString() + ", " + medevac_value_line2_callsign.text.toString())
        val line3 = ReportLine("Line 3", getThirdLineValue())
        val line4 = ReportLine("Line 4", getFourthLineValue())
        val line5 = ReportLine("Line 5", getFifthLineValue())
        val line6 = ReportLine("Line 6", getRadioButtonValue(medevac_content_line6.checkedRadioButtonId))
        val line7 = ReportLine("Line 7", getRadioButtonValue(medevac_content_line7.checkedRadioButtonId))
        val line8 = ReportLine("Line 8", getEightLineValue())
        val line9 = ReportLine("Line 9", getRadioButtonValue(medevac_content_line9.checkedRadioButtonId))

        val reportData = ReportData("Medevac 9-liner", arrayOf(line1, line2, line3, line4, line5, line6, line7, line8, line9))

        return reportData
    }

    private fun getThirdLineValue(): String {
        val stringBuilder = StringBuilder()
        if (!medevac_value_line3_alpha.text.isNullOrEmpty() && medevac_value_line3_alpha.text.toString().toInt() > 0) {
            stringBuilder.append("a - ")
            stringBuilder.appendln(medevac_value_line3_alpha.text)
        }
        if (!medevac_value_line3_bravo.text.isNullOrEmpty() && medevac_value_line3_bravo.text.toString().toInt() > 0) {
            stringBuilder.append("b - ")
            stringBuilder.appendln(medevac_value_line3_bravo.text)
        }
        if (!medevac_value_line3_charlie.text.isNullOrEmpty() && medevac_value_line3_charlie.text.toString().toInt() > 0) {
            stringBuilder.append("c - ")
            stringBuilder.appendln(medevac_value_line3_charlie.text)
        }
        if (!medevac_value_line3_delta.text.isNullOrEmpty() && medevac_value_line3_delta.text.toString().toInt() > 0) {
            stringBuilder.append("d - ")
            stringBuilder.appendln(medevac_value_line3_delta.text)
        }
        if (!medevac_value_line3_echo.text.isNullOrEmpty() && medevac_value_line3_echo.text.toString().toInt() > 0) {
            stringBuilder.append("e - ")
            stringBuilder.appendln(medevac_value_line3_echo.text)
        }

        return stringBuilder.toString()
    }

    private fun getFourthLineValue(): String {
        val stringBuilder = StringBuilder()
        if (medevac_value_line4_alpha.isChecked) {
            stringBuilder.appendln("a")
        }
        if (medevac_value_line4_bravo.isChecked) {
            stringBuilder.appendln("b")
        }
        if (medevac_value_line4_charlie.isChecked) {
            stringBuilder.appendln("c")
        }
        if (medevac_value_line4_delta.isChecked) {
            stringBuilder.appendln("d")
        }

        return stringBuilder.toString()
    }

    private fun getFifthLineValue(): String {
        val stringBuilder = StringBuilder()
        if (!medevac_value_line5_alpha.text.isNullOrEmpty() && medevac_value_line5_alpha.text.toString().toInt() > 0) {
            stringBuilder.append("a - ")
            stringBuilder.appendln(medevac_value_line5_alpha.text)
        }
        if (!medevac_value_line5_lima.text.isNullOrEmpty() && medevac_value_line5_lima.text.toString().toInt() > 0) {
            stringBuilder.append("l - ")
            stringBuilder.appendln(medevac_value_line5_lima.text)
        }

        return stringBuilder.toString()
    }

    private fun getRadioButtonValue(radioButtonGroupId: Int): String {
        val radioButton = findViewById(radioButtonGroupId) as RadioButton
        return radioButton.text.toString()
    }

    private fun getEightLineValue(): String {
        val stringBuilder = StringBuilder()
        if (!medevac_value_line8_alpha.text.isNullOrEmpty() && medevac_value_line8_alpha.text.toString().toInt() > 0) {
            stringBuilder.append("a - ")
            stringBuilder.appendln(medevac_value_line8_alpha.text)
        }
        if (!medevac_value_line8_bravo.text.isNullOrEmpty() && medevac_value_line8_bravo.text.toString().toInt() > 0) {
            stringBuilder.append("b - ")
            stringBuilder.appendln(medevac_value_line8_bravo.text)
        }
        if (!medevac_value_line8_charlie.text.isNullOrEmpty() && medevac_value_line8_charlie.text.toString().toInt() > 0) {
            stringBuilder.append("c - ")
            stringBuilder.appendln(medevac_value_line8_charlie.text)
        }
        if (!medevac_value_line8_delta.text.isNullOrEmpty() && medevac_value_line8_delta.text.toString().toInt() > 0) {
            stringBuilder.append("d - ")
            stringBuilder.appendln(medevac_value_line8_delta.text)
        }
        if (!medevac_value_line8_echo.text.isNullOrEmpty() && medevac_value_line8_echo.text.toString().toInt() > 0) {
            stringBuilder.append("e - ")
            stringBuilder.appendln(medevac_value_line8_echo.text)
        }

        return stringBuilder.toString()
    }
}
