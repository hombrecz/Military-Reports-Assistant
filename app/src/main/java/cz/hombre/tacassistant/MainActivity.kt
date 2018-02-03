package cz.hombre.tacassistant

import android.content.Intent
import android.os.Bundle
import android.preference.PreferenceManager
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import cz.hombre.tacassistant.report.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.nav_header_main.*
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        val toggle = object : ActionBarDrawerToggle(this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close) {
            override fun onDrawerOpened(drawerView: View) {
                super.onDrawerOpened(drawerView)
                setHeaderValues()
            }
        }
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)

        PreferenceManager.setDefaultValues(this, R.xml.pref_settings, false)
    }

    private fun setHeaderValues() {
        setUserData()
        setLocationData()
        setTimeData()
    }

    private fun setUserData() {
        val prefs = PreferenceManager.getDefaultSharedPreferences(this)

        val frequency = prefs.getString("preference_frequency", "")
        status_frequency.text = frequency

        val callSign = prefs.getString("preference_callsign", "")
        status_callname.text = callSign
    }

    private fun setLocationData() {
        //TODO OD
    }

    private fun setTimeData() {
        val timeFormat = SimpleDateFormat("HH:mm", Locale.getDefault())
        val dateFormat = SimpleDateFormat("MMMM dd, yyyy", Locale.getDefault())
        val actualDateTime = Calendar.getInstance().time
        status_local_date.text = dateFormat.format(actualDateTime)
        status_local_time.text = timeFormat.format(actualDateTime)

        val zuluFormat = SimpleDateFormat("ddHHmm'Z' MMM yy", Locale.getDefault())
        val zuluTimeValue = Calendar.getInstance(TimeZone.getTimeZone("UTC")).time
        status_dtg_zulu_time.text = zuluFormat.format(zuluTimeValue).toUpperCase()
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> {
                startActivity(Intent(this, SettingsActivity::class.java))
                true
            }
            else -> super.onOptionsItemSelected(item)
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

            }
        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }
}
