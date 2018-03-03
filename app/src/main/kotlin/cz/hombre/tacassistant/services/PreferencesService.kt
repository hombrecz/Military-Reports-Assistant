package cz.hombre.tacassistant.services

import android.content.Context
import android.preference.PreferenceManager

val NATO_ALPHABET_VALUE = 0
val CZECH_ALPHABET_VALUE = 1

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

    override fun getCallSign() = preferences.getString(CALL_SIGN, "")

    override fun getFrequency() = preferences.getString(FREQUENCY, "")

    override fun getRamrod() = preferences.getString(RAMROD, "")

    override fun getPhoneticAlphabet() = preferences.getString(PHONETIC_ALPHABET, NATO_ALPHABET_VALUE.toString()).toInt()
}