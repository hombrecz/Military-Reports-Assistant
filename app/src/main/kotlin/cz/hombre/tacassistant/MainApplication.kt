package cz.hombre.tacassistant

import android.app.Application
import android.content.Context
import android.preference.PreferenceManager
import android.support.v7.app.AppCompatDelegate
import cz.hombre.tacassistant.modules.myModule
import cz.hombre.tacassistant.services.EncodingService
import cz.hombre.tacassistant.services.PreferencesService
import cz.hombre.tacassistant.services.impl.EncodingServiceImpl
import cz.hombre.tacassistant.services.impl.NATO_ALPHABET_VALUE
import org.koin.android.ext.android.inject
import org.koin.android.ext.android.startKoin

class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin(this, listOf(myModule))
        PreferenceManager.setDefaultValues(this, R.xml.pref_settings, false)
    }
}