package cz.hombre.tacassistant.services.impl

import android.content.Context
import android.os.Build
import cz.hombre.tacassistant.services.LocaleService
import cz.hombre.tacassistant.services.PreferencesService
import java.util.*

const val LOCALE_CHANGED = 3

class LocaleServiceImpl(private val preferencesService: PreferencesService) : LocaleService {

    override fun setPreferredLocale(context: Context): Context {
        val language = preferencesService.getLanguage()

        return setLocale(context, language)
    }

    override fun getStringForActualLocale(context: Context, id: Int): String {
        val resources = context.resources
        val configuration = resources.configuration
        val savedLocale = configuration.locale

        val language = preferencesService.getLanguage()

        configuration.setLocale(Locale(language))
        resources.updateConfiguration(configuration, null)

        val str = resources.getString(id)

        configuration.locale = savedLocale
        resources.updateConfiguration(configuration, null)

        return str
    }

    private fun setLocale(context: Context, language: String): Context {
        val locale = Locale(language)
        Locale.setDefault(locale)

        val resources = context.resources
        var configuration = resources.configuration
        val displayMetrics = resources.displayMetrics
        configuration.setLocale(locale)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {

            return context.applicationContext.createConfigurationContext(configuration)
        } else {
            resources.updateConfiguration(configuration, displayMetrics)

            return context
        }

    }
}