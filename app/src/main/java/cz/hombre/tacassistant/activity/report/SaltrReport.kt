package cz.hombre.tacassistant.activity.report

import android.content.Intent
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.widget.EditText
import cz.hombre.tacassistant.R
import cz.hombre.tacassistant.activity.ReportPreviewActivity
import cz.hombre.tacassistant.dto.ReportData
import cz.hombre.tacassistant.dto.ReportLine
import cz.hombre.tacassistant.services.DateTimeService

import kotlinx.android.synthetic.main.activity_saltr_report.*
import kotlinx.android.synthetic.main.content_saltr_report.*
import org.koin.android.ext.android.inject

class SaltrReport : AppCompatActivity() {

    private val dateTimeService: DateTimeService by inject()

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
        setAutoLocation(saltr_value_location)
        setHidingContent()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun setAutoLocation(textField: EditText?) {
        var locationManager = getSystemService(LOCATION_SERVICE) as LocationManager?


        val locationListener: LocationListener = object : LocationListener {
            override fun onLocationChanged(location: Location) {
                textField!!.setText("${location.longitude}:${location.latitude}");
            }
            override fun onStatusChanged(provider: String, status: Int, extras: Bundle) {}
            override fun onProviderEnabled(provider: String) {}
            override fun onProviderDisabled(provider: String) {}
        }

        try {
            locationManager?.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0L, 0f, locationListener)
        } catch (e: SecurityException) {
            Log.e("SALTR report", "Fail to request location update", e)
        } catch (e: IllegalArgumentException) {
            Log.e("SALTR report", "GPS provider does not exist", e)
        }
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
