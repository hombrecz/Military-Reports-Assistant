package cz.hombre.tacassistant.report

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.EditText
import cz.hombre.tacassistant.R
import cz.hombre.tacassistant.ReportPreviewActivity
import cz.hombre.tacassistant.dto.ReportData
import cz.hombre.tacassistant.dto.ReportLine

import kotlinx.android.synthetic.main.activity_saltr_report.*
import kotlinx.android.synthetic.main.content_saltr_report.*
import java.text.SimpleDateFormat
import java.util.*

class SaltrReport : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_saltr_report)

        fab.setOnClickListener {
            val report = getReportData()
            val previewIntent = Intent(this, ReportPreviewActivity::class.java)
            previewIntent.putExtra("report", report)
            startActivity(previewIntent)
        }
        setAutoTime(saltr_value_time)
        setHidingContent()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun setAutoTime(target: EditText) {
        val zuluFormat = SimpleDateFormat("ddHHmm'Z' MMM yy", Locale.getDefault())
        val zuluTimeValue = Calendar.getInstance(TimeZone.getTimeZone("UTC")).time
        target.setText(zuluFormat.format(zuluTimeValue).toUpperCase())
    }

    private fun setHidingContent() {
        switchVisibility(saltr_label_size, saltr_content_size)
        switchVisibility(saltr_label_activity, saltr_content_activity)
        switchVisibility(saltr_label_location, saltr_content_location)
        switchVisibility(saltr_label_time, saltr_content_time)
        switchVisibility(saltr_label_request, saltr_content_request)
    }

    private fun switchVisibility(initiator: View, target: View) {
        initiator.setOnClickListener {
            if (target.visibility == View.VISIBLE) {
                target.visibility = View.GONE
            } else {
                target.visibility = View.VISIBLE
            }
        }
    }

    private fun getReportData(): ReportData {
        val size = ReportLine(saltr_value_size.text.toString())
        val activity = ReportLine(saltr_value_activity.text.toString())
        val location = ReportLine(saltr_value_location.text.toString())
        val time = ReportLine(saltr_value_time.text.toString())
        val request = ReportLine(saltr_value_request.text.toString())

        return ReportData("SALTR report", arrayOf(size, activity, location, time, request))
    }

}
