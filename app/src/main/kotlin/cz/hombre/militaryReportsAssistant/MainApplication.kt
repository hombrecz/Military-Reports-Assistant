package cz.hombre.militaryReportsAssistant

import android.app.Application
import android.content.Context
import android.preference.PreferenceManager
import android.support.v7.app.AppCompatDelegate
import cz.hombre.militaryReportsAssistant.modules.myModule
import cz.hombre.militaryReportsAssistant.services.EncodingService
import cz.hombre.militaryReportsAssistant.services.PreferencesService
import cz.hombre.militaryReportsAssistant.services.impl.EncodingServiceImpl
import cz.hombre.militaryReportsAssistant.services.impl.NATO_ALPHABET_VALUE
import org.koin.android.ext.android.inject
import org.koin.android.ext.android.startKoin

class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin(this, listOf(myModule))
        PreferenceManager.setDefaultValues(this, R.xml.pref_settings, false)
    }
}