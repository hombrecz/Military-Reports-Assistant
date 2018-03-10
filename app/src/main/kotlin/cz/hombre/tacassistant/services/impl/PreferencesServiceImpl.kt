package cz.hombre.tacassistant.services.impl

import android.content.Context
import android.preference.PreferenceManager
import cz.hombre.tacassistant.services.PreferencesService

const val NATO_ALPHABET_VALUE = 0
const val CZECH_ALPHABET_VALUE = 1
const val UTC_OFFSET = "0"

private const val CALL_SIGN = "preference_callsign"
private const val FREQUENCY = "preference_frequency"
private const val RAMROD = "preference_ramrod"
private const val PHONETIC_ALPHABET = "preference_phonetic_alphabet"
private const val PREFERRED_OFFSET = "preference_preferred_offset"

class PreferencesServiceImpl(context: Context) : PreferencesService {
    private val preferences = PreferenceManager.getDefaultSharedPreferences(context)

    override fun getCallSign(): String = preferences.getString(CALL_SIGN, "")

    override fun getFrequency(): String = preferences.getString(FREQUENCY, "")

    override fun getRamrod(): String = preferences.getString(RAMROD, "")

    override fun getPhoneticAlphabet() = preferences.getString(PHONETIC_ALPHABET, NATO_ALPHABET_VALUE.toString()).toInt()

    override fun getPreferredOffset() = preferences.getString(PREFERRED_OFFSET, UTC_OFFSET).toInt()
}
