package cz.hombre.tacassistant.services

import android.content.Context

interface LocaleService {

    fun setPreferredLocale(context: Context): Context

    fun getStringForActualLocale(context: Context, id: Int): String
}