package cz.hombre.tacassistant.activity.reports

import android.support.v7.app.AppCompatActivity
import cz.hombre.tacassistant.dto.ReportData

abstract class AbstractReport : AppCompatActivity() {

    abstract fun getReportData(): ReportData

}