package cz.hombre.militaryReportsAssistant.services

import cz.hombre.militaryReportsAssistant.database.model.GlossaryEntry

interface DatabaseService {

    fun addGlossaryEntry(entry : GlossaryEntry)

    fun deleteGlossaryEntry(entry : GlossaryEntry)

    fun getAllGlossaryEntries(): List<GlossaryEntry>

    fun addDefaultGlossaryEntries(): List<GlossaryEntry>
}