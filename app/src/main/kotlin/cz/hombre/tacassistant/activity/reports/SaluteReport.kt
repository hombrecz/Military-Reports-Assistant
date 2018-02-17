package cz.hombre.tacassistant.activity.reports

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import cz.hombre.tacassistant.R
import cz.hombre.tacassistant.dto.ReportData
import cz.hombre.tacassistant.dto.ReportLine
import cz.hombre.tacassistant.services.DateTimeService
import cz.hombre.tacassistant.services.LocationService
import cz.hombre.tacassistant.services.REPORT_PROPERTY
import cz.hombre.tacassistant.services.ReportFormService
import kotlinx.android.synthetic.main.activity_salute_report.*
import kotlinx.android.synthetic.main.content_salute_report.*
import org.koin.android.ext.android.inject

class SaluteReport : AppCompatActivity() {

    private val dateTimeService: DateTimeService by inject()
    private val locationService: LocationService by inject()
    private val reportFormService: ReportFormService by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_salute_report)

        fab.setOnClickListener {
            val report = getReportData()
            val previewIntent = Intent(this, ReportPreviewActivity::class.java)
            previewIntent.putExtra(REPORT_PROPERTY, report)
            startActivity(previewIntent)
        }
        salute_value_time.setText(dateTimeService.getZuluDateTimeGroup())
        salute_value_location.setText(locationService.getCurrentMGRSLocation())
        setHidingContent()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun setHidingContent() {
        reportFormService.setHideableItem(salute_label_size, salute_content_size)
        reportFormService.setHideableItem(salute_label_activity, salute_content_activity)
        reportFormService.setHideableItem(salute_label_location, salute_content_location)
        reportFormService.setHideableItem(salute_label_uniform, salute_content_uniform)
        reportFormService.setHideableItem(salute_label_time, salute_content_time)
        reportFormService.setHideableItem(salute_label_equipment, salute_content_equipment)
    }

    private fun getReportData(): ReportData {
        val size = ReportLine(salute_value_size.text.toString())
        val activity = ReportLine(salute_value_activity.text.toString())
        val location = ReportLine(salute_value_location.text.toString())
        val uniform = ReportLine(salute_uniform_value.text.toString())
        val time = ReportLine(salute_value_time.text.toString())
        val equipment = ReportLine(salute_value_equipment.text.toString())

        return ReportData(getString(R.string.title_activity_salute_report), arrayOf(size, activity, location, uniform, time, equipment))
    }
}
