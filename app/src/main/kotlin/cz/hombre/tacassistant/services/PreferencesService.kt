package cz.hombre.tacassistant.services

import android.content.Context
import android.preference.PreferenceManager

const val NATO_ALPHABET_VALUE = 0
const val CZECH_ALPHABET_VALUE = 1

interface PreferencesService {

    fun getFrequency(): String

    fun getCallSign(): String

    fun getRamrod(): String

    fun getPhoneticAlphabet(): Int

}

private const val CALL_SIGN = "preference_callsign"
private const val FREQUENCY = "preference_frequency"
private const val RAMROD = "preference_ramrod"

private const val PHONETIC_ALPHABET = "preference_phonetic_alphabet"

class PreferencesServiceImpl(context: Context) : PreferencesService {

    private val preferences = PreferenceManager.getDefaultSharedPreferences(context)

    override fun getCallSign(): String = preferences.getString(CALL_SIGN, "")

    override fun getFrequency(): String = preferences.getString(FREQUENCY, "")

    override fun getRamrod(): String = preferences.getString(RAMROD, "")

    override fun getPhoneticAlphabet() = preferences.getString(PHONETIC_ALPHABET, NATO_ALPHABET_VALUE.toString()).toInt()
}