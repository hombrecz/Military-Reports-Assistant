package cz.hombre.tacassistant.activity.reports

import android.support.v7.app.AppCompatActivity
import cz.hombre.tacassistant.dto.ReportData
import cz.hombre.tacassistant.services.DateTimeService
import cz.hombre.tacassistant.services.LocationService
import cz.hombre.tacassistant.services.PreferencesService
import cz.hombre.tacassistant.services.ReportFormService
import org.koin.android.ext.android.inject

abstract class AbstractReport : AppCompatActivity() {

    protected val dateTimeService: DateTimeService by inject()
    protected val locationService: LocationService by inject()
    protected val reportFormService: ReportFormService by inject()
    protected val preferencesService: PreferencesService by inject()

    abstract fun getReportData(): ReportData

}