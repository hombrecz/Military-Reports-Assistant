package cz.hombre.tacassistant.activity.reports

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import cz.hombre.tacassistant.R
import cz.hombre.tacassistant.dto.ReportData
import cz.hombre.tacassistant.dto.ReportLine
import cz.hombre.tacassistant.services.DateTimeService
import cz.hombre.tacassistant.services.LocationService
import cz.hombre.tacassistant.services.ReportFormService

import kotlinx.android.synthetic.main.activity_saltr_report.*
import kotlinx.android.synthetic.main.content_saltr_report.*
import org.koin.android.ext.android.inject

class SaltrReport : AppCompatActivity() {

    private val dateTimeService: DateTimeService by inject()
    private val locationService: LocationService by inject()
    private val reportFormService: ReportFormService by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_saltr_report)

        fab.setOnClickListener {
            val report = getReportData()
            val previewIntent = Intent(this, ReportPreviewActivity::class.java)
            previewIntent.putExtra("report", report)
            startActivity(previewIntent)
        }
        saltr_value_time.setText(dateTimeService.getZuluDateTimeGroup())
        saltr_value_location.setText(locationService.getCurrentMGRSLocation())
        setHidingContent()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun setHidingContent() {
        reportFormService.setHideableItem(saltr_label_size, saltr_content_size)
        reportFormService.setHideableItem(saltr_label_activity, saltr_content_activity)
        reportFormService.setHideableItem(saltr_label_location, saltr_content_location)
        reportFormService.setHideableItem(saltr_label_time, saltr_content_time)
        reportFormService.setHideableItem(saltr_label_request, saltr_content_request)
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
