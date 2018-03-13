package cz.hombre.tacassistant.services

interface PreferencesService {

    fun getFrequency(): String

    fun getCallSign(): String

    fun getRamrod(): String

    fun getPhoneticAlphabet(): Int

    fun getPreferredOffset(): Int

    fun getNightMode(): Int
}