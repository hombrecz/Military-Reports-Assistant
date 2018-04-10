package cz.hombre.militaryReportsAssistant.services.impl

import android.content.Context
import android.preference.PreferenceManager
import cz.hombre.militaryReportsAssistant.services.PreferencesService

const val NATO_ALPHABET_VALUE = 0
const val CZECH_ALPHABET_VALUE = 1
const val UTC_OFFSET = "0"
const val DEFAULT_NIGHT_MODE = "0"
const val DEFAULT_LOCALE = "en"
const val DEFAULT_GLOSSARY_UNINITIALISED = 0
const val DEFAULT_GLOSSARY_INITIALISED = 1

private const val CALL_SIGN = "preference_callsign"
private const val FREQUENCY = "preference_frequency"
private const val RAMROD = "preference_ramrod"
private const val PHONETIC_ALPHABET = "preference_phonetic_alphabet"
private const val PREFERRED_OFFSET = "preference_preferred_offset"
private const val NIGHT_MODE = "preference_night_mode"
private const val LOCALE = "preference_locale"
private const val GLOSSARY_INITIALISED = "preference_glossary_initialised"

class PreferencesServiceImpl(context: Context) : PreferencesService {

    private val preferences = PreferenceManager.getDefaultSharedPreferences(context)

    override fun getCallSign(): String = preferences.getString(CALL_SIGN, "")

    override fun getFrequency(): String = preferences.getString(FREQUENCY, "")

    override fun getRamrod(): String = preferences.getString(RAMROD, "")

    override fun getPhoneticAlphabet() = preferences.getString(PHONETIC_ALPHABET, NATO_ALPHABET_VALUE.toString()).toInt()

    override fun getPreferredOffset() = preferences.getString(PREFERRED_OFFSET, UTC_OFFSET).toInt()

    override fun getNightMode(): Int = preferences.getString(NIGHT_MODE, DEFAULT_NIGHT_MODE).toInt()

    override fun getLanguage(): String = preferences.getString(LOCALE, DEFAULT_LOCALE)

    override fun isGlossaryInitialised() = preferences.getInt(GLOSSARY_INITIALISED, DEFAULT_GLOSSARY_UNINITIALISED)

    override fun setGlossaryInitialised(flag: Boolean) {
        with(preferences.edit()) {
            if (flag) {
                putInt(GLOSSARY_INITIALISED, DEFAULT_GLOSSARY_INITIALISED)
            } else {
                putInt(GLOSSARY_INITIALISED, DEFAULT_GLOSSARY_UNINITIALISED)
            }
            apply()
        }
    }
}
