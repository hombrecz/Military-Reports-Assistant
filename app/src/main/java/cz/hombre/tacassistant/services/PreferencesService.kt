package cz.hombre.tacassistant.services

import android.content.Context
import android.preference.PreferenceManager

interface PreferencesService {

    fun getFrequency(): String

    fun getCallSign(): String

    fun getRamrod(): String

    fun getPhoneticAlphabet(): Int
}

class PreferencesServiceImpl(context: Context) : PreferencesService {

    private val CALL_SIGN = "preference_callsign"
    private val FREQUENCY = "preference_frequency"
    private val RAMROD = "preference_ramrod"
    private val PHONETIC_ALPHABET = "preference_phonetic_alphabet"

    private val preferences = PreferenceManager.getDefaultSharedPreferences(context)

    override fun getCallSign(): String {

        return preferences.getString(CALL_SIGN, "")
    }

    override fun getFrequency(): String {

        return preferences.getString(FREQUENCY, "")
    }

    override fun getRamrod(): String {
        return preferences.getString(RAMROD, "")
    }

    override fun getPhoneticAlphabet(): Int {
        return preferences.getInt(PHONETIC_ALPHABET, 0)
    }
}