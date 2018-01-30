package cz.hombre.tacassistant.report

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.RadioButton
import cz.hombre.tacassistant.R
import cz.hombre.tacassistant.ReportPreviewActivity
import cz.hombre.tacassistant.dto.ReportData
import cz.hombre.tacassistant.dto.ReportLine

import kotlinx.android.synthetic.main.activity_situation_report.*
import kotlinx.android.synthetic.main.content_situation_report.*

class SituationReport : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_situation_report)

        fab.setOnClickListener { view ->
            val report = getReportData()
            val previewIntent = Intent(this, ReportPreviewActivity::class.java)
            previewIntent.putExtra("report", report)
            startActivity(previewIntent)
        }
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun getReportData(): ReportData {

        val time = ReportLine(sitrep_value_time.text.toString())
        val status = ReportLine(getRadioButtonValue(sitrep_value_status.checkedRadioButtonId))
        val enemy = ReportLine(sitrep_value_enemy.text.toString())
        val own = ReportLine(sitrep_value_own.text.toString())
        val follow = ReportLine(sitrep_value_follow.text.toString())

        val reportData = ReportData("Situation report", arrayOf(time, status, enemy, own, follow))

        return reportData
    }

    private fun getRadioButtonValue(radioButtonGroupId: Int): String {
        val radioButton = findViewById(radioButtonGroupId) as RadioButton
        return radioButton.text.toString()
    }

}
