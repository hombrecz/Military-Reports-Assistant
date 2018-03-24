package cz.hombre.tacassistant.activity


import android.annotation.TargetApi
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Build
import android.os.Bundle
import android.preference.PreferenceActivity
import android.preference.PreferenceFragment
import android.preference.PreferenceManager
import android.support.v7.app.AppCompatDelegate
import android.view.MenuItem
import cz.hombre.tacassistant.R
import cz.hombre.tacassistant.services.LocaleService
import cz.hombre.tacassistant.services.PreferencesService
import cz.hombre.tacassistant.services.impl.LOCALE_CHANGED
import org.jetbrains.anko.toast
import org.koin.android.ext.android.inject

private const val NIGHT_MODE = "preference_night_mode"
private const val LOCALE = "preference_locale"

class SettingsActivity : PreferenceActivity(), SharedPreferences.OnSharedPreferenceChangeListener {

    private val preferencesService: PreferencesService by inject()
    private val localeService: LocaleService by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTitle(R.string.title_activity_settings)

        fragmentManager.beginTransaction()
                .replace(android.R.id.content, SettingsPreferenceFragment())
                .commit()

        val prefs = PreferenceManager.getDefaultSharedPreferences(this)
        prefs.registerOnSharedPreferenceChangeListener(this)
    }

    override fun onSharedPreferenceChanged(sharedPreferences: SharedPreferences?, key: String?) {
        when (key) {
            NIGHT_MODE -> {
                AppCompatDelegate.setDefaultNightMode(preferencesService.getNightMode())
                finish()

            }
            LOCALE -> {
                localeService.setPreferredLocale(baseContext)
                setResult(LOCALE_CHANGED)
                recreate()
                finish()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        toast(localeService.getStringForActualLocale(baseContext, R.string.settings_app_reloaded))
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    class SettingsPreferenceFragment : PreferenceFragment() {
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            addPreferencesFromResource(R.xml.pref_settings)
        }

        override fun onOptionsItemSelected(item: MenuItem): Boolean {
            val id = item.itemId
            if (id == android.R.id.home) {
                startActivity(Intent(activity, SettingsActivity::class.java))
                return true
            }
            return super.onOptionsItemSelected(item)
        }
    }

    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(localeService.setPreferredLocale(base))
    }
}
