package cz.hombre.tacassistant.report

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import cz.hombre.tacassistant.R
import cz.hombre.tacassistant.ReportPreviewActivity
import cz.hombre.tacassistant.dto.ReportData
import cz.hombre.tacassistant.dto.ReportLine

import kotlinx.android.synthetic.main.activity_saltr_report.*
import kotlinx.android.synthetic.main.content_saltr_report.*

class SaltrReport : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_saltr_report)

        fab.setOnClickListener { view ->
            val report = getReportData()
            val previewIntent = Intent(this, ReportPreviewActivity::class.java)
            previewIntent.putExtra("report", report)
            startActivity(previewIntent)
        }
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun getReportData(): ReportData {
        val size = ReportLine(value_size.text.toString())
        val activity = ReportLine(value_activity.text.toString())
        val location = ReportLine(value_location.text.toString())
        val time = ReportLine(value_time.text.toString())
        val request = ReportLine(value_request.text.toString())

        val reportData = ReportData("SALTR report", arrayOf(size, activity, location, time, request))

        return reportData
    }

}
