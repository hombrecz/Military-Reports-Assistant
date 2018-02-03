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

import kotlinx.android.synthetic.main.activity_salute_report.*
import kotlinx.android.synthetic.main.content_salute_report.*
import java.text.SimpleDateFormat
import java.util.*

class SaluteReport : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_salute_report)

        fab.setOnClickListener {
            val report = getReportData()
            val previewIntent = Intent(this, ReportPreviewActivity::class.java)
            previewIntent.putExtra("report", report)
            startActivity(previewIntent)
        }
        setAutoTime(salute_value_time)
        setHidingContent()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun setAutoTime(target: EditText) {
        val zuluFormat = SimpleDateFormat("ddHHmm'Z' MMM yy", Locale.getDefault())
        val zuluTimeValue = Calendar.getInstance(TimeZone.getTimeZone("UTC")).time
        target.setText(zuluFormat.format(zuluTimeValue).toUpperCase())
    }

    private fun setHidingContent() {
        switchVisibility(salute_label_size, salute_content_size)
        switchVisibility(salute_label_activity, salute_content_activity)
        switchVisibility(salute_label_location, salute_content_location)
        switchVisibility(salute_label_uniform, salute_content_uniform)
        switchVisibility(salute_label_time, salute_content_time)
        switchVisibility(salute_label_equipment, salute_content_equipment)
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
        val size = ReportLine(salute_value_size.text.toString())
        val activity = ReportLine(salute_value_activity.text.toString())
        val location = ReportLine(salute_value_location.text.toString())
        val uniform = ReportLine(salute_uniform_value.text.toString())
        val time = ReportLine(salute_value_time.text.toString())
        val equipment = ReportLine(salute_value_equipment.text.toString())

        return ReportData("SALTR report", arrayOf(size, activity, location, uniform, time, equipment))
    }
}
