package cz.hombre.militaryReportsAssistant.activity.reports

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import cz.hombre.militaryReportsAssistant.R
import cz.hombre.militaryReportsAssistant.Utilities.Companion.REPORT_PROPERTY
import cz.hombre.militaryReportsAssistant.dto.ReportData
import cz.hombre.militaryReportsAssistant.services.DateTimeService
import cz.hombre.militaryReportsAssistant.services.LocaleService
import cz.hombre.militaryReportsAssistant.services.LocationService
import cz.hombre.militaryReportsAssistant.services.PreferencesService
import org.jetbrains.anko.alert
import org.jetbrains.anko.noButton
import org.jetbrains.anko.yesButton
import org.koin.android.ext.android.inject

abstract class AbstractReport : AppCompatActivity() {

    protected val dateTimeService: DateTimeService by inject()
    protected val locationService: LocationService by inject()
    protected val preferencesService: PreferencesService by inject()
    private val localeService: LocaleService by inject()

    abstract fun getReportData(): ReportData

    protected fun translateReport() {
        val report = getReportData()
        val previewIntent = Intent(this, ReportPreviewActivity::class.java)
        previewIntent.putExtra(REPORT_PROPERTY, report)
        startActivity(previewIntent)
    }

    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(localeService.setPreferredLocale(base))
    }

    override fun onBackPressed() {
        alert(localeService.getStringForActualLocale(baseContext, R.string.report_leaving_dialog_content),
                localeService.getStringForActualLocale(baseContext, R.string.report_leaving_dialog_label)) {
            yesButton { super.onBackPressed() }
            noButton {}
        }.show()
    }
}