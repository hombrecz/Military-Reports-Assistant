package cz.hombre.tacassistant.report

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import cz.hombre.tacassistant.R
import cz.hombre.tacassistant.ReportPreviewActivity
import cz.hombre.tacassistant.dto.ReportData
import cz.hombre.tacassistant.dto.ReportLine

import kotlinx.android.synthetic.main.activity_salute_report.*
import kotlinx.android.synthetic.main.content_salute_report.*

class SaluteReport : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_salute_report)

        fab.setOnClickListener { view ->
            val report = getReportData()
            val previewIntent = Intent(this, ReportPreviewActivity::class.java)
            previewIntent.putExtra("report", report)
            startActivity(previewIntent)
        }
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun getReportData(): ReportData {
        val size = ReportLine(salute_value_size.text.toString())
        val activity = ReportLine(salute_value_activity.text.toString())
        val location = ReportLine(salute_value_location.text.toString())
        val uniform = ReportLine(salute_uniform_value.text.toString())
        val time = ReportLine(salute_value_time.text.toString())
        val equipment = ReportLine(salute_value_equipment.text.toString())

        val reportData = ReportData("SALTR report", arrayOf(size, activity, location, uniform, time, equipment))

        return reportData
    }
}
