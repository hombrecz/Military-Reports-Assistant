package cz.hombre.militaryReportsAssistant.activity

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.support.v7.app.AppCompatDelegate
import android.view.Menu
import android.view.MenuItem
import android.view.View
import cz.hombre.militaryReportsAssistant.R
import cz.hombre.militaryReportsAssistant.R.string.navigation_drawer_close
import cz.hombre.militaryReportsAssistant.R.string.navigation_drawer_open
import cz.hombre.militaryReportsAssistant.activity.reports.*
import cz.hombre.militaryReportsAssistant.layout.MainUI
import cz.hombre.militaryReportsAssistant.services.DatabaseService
import cz.hombre.militaryReportsAssistant.services.DateTimeService
import cz.hombre.militaryReportsAssistant.services.LocaleService
import cz.hombre.militaryReportsAssistant.services.LocationService
import cz.hombre.militaryReportsAssistant.services.PreferencesService
import cz.hombre.militaryReportsAssistant.services.impl.LOCALE_CHANGED
import kotlinx.android.synthetic.main.nav_header_main.*
import org.jetbrains.anko.alert
import org.jetbrains.anko.appcompat.v7.Appcompat
import org.jetbrains.anko.design.longSnackbar
import org.jetbrains.anko.setContentView
import org.koin.android.ext.android.inject

const val PERMISSION_REQUEST_LOCATION = 0

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener, ActivityCompat.OnRequestPermissionsResultCallback {

    private var mainUI = MainUI()

    private val dateTimeService: DateTimeService by inject()
    private val locationService: LocationService by inject()
    private val preferencesService: PreferencesService by inject()
    private val localeService: LocaleService by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainUI.setContentView(this)
        setSupportActionBar(mainUI.toolbar)

        AppCompatDelegate.setDefaultNightMode(preferencesService.getNightMode())
        localeService.setPreferredLocale(baseContext)

        val toggle = object : ActionBarDrawerToggle(this, mainUI.drawerLayout, mainUI.toolbar, navigation_drawer_open, navigation_drawer_close) {
            override fun onDrawerOpened(drawerView: View) {
                super.onDrawerOpened(drawerView)
                setHeaderValues()
            }
        }

        mainUI.drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        mainUI.navigationView.setNavigationItemSelectedListener(this)

        requestLocationDataPermission()
    }

    private fun requestLocationDataPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                            Manifest.permission.ACCESS_FINE_LOCATION)) {
                longSnackbar(this.currentFocus, R.string.report_preview_ramrod_message)
            } else {
                ActivityCompat.requestPermissions(this,
                        arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                        PERMISSION_REQUEST_LOCATION)
            }
        }

    }

    private fun setHeaderValues() {
        status_callname.text = preferencesService.getCallSign()
        status_frequency.text = preferencesService.getFrequency()

        status_gps.text = locationService.getCurrentGPSLocation()
        status_mgrs.text = locationService.getCurrentMGRSLocation()
        status_precision.text = locationService.getLocationPrecision()
        status_time_position_retrieved.text = locationService.getLocationTime()
        status_duration_position_retrieved.text = locationService.getLocationTimeAgo()

        status_local_date.text = dateTimeService.getLocalDate()
        status_local_time.text = dateTimeService.getLocalTime()
        status_dtg_zulu_time.text = dateTimeService.getMilitaryDateTimeGroup()
    }

    override fun onBackPressed() {
        if (mainUI.drawerLayout.isDrawerOpen(GravityCompat.START)) {
            mainUI.drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_settings -> {
                startActivityForResult(Intent(this, SettingsActivity::class.java), 0)
                true
            }
            R.id.action_about -> {
                showLicensesAlert()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun showLicensesAlert() {
        val licenses = applicationContext.assets.open("licenses.txt")

        alert(Appcompat, licenses.bufferedReader().use { it.readText() }).show()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        when (resultCode) {
            LOCALE_CHANGED -> recreate()
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_medevac -> {
                startActivity(Intent(this, MedevacReport::class.java))
                return true
            }
            R.id.nav_sitrep -> {
                startActivity(Intent(this, SituationReport::class.java))
                return true
            }
            R.id.nav_salute -> {
                startActivity(Intent(this, SaluteReport::class.java))
                return true
            }
            R.id.nav_saltr -> {
                startActivity(Intent(this, SaltrReport::class.java))
                return true
            }
            R.id.nav_ied -> {
                startActivity(Intent(this, ExplosiveReport::class.java))
                return true
            }
            R.id.nav_glossary -> {
                startActivity(Intent(this, Glossary::class.java))
                return true
            }
        }

        mainUI.drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>,
                                            grantResults: IntArray) {
        when (requestCode) {
            PERMISSION_REQUEST_LOCATION -> {
                if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                    longSnackbar(this.currentFocus, R.string.navigation_permission_granted)
                } else {
                    longSnackbar(this.currentFocus, R.string.navigation_permission_denied)
                }
                return
            }
        }

    }

    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(localeService.setPreferredLocale(base))
    }
}
