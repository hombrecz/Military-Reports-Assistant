package cz.hombre.tacassistant.services

import cz.hombre.tacassistant.database.model.GlossaryEntry

interface DatabaseService {

    fun addGlossaryEntry(entry : GlossaryEntry)

    fun deleteGlossaryEntry(entry : GlossaryEntry)

    fun getAllGlossaryEntries(): List<GlossaryEntry>

    fun addDefaultGlossaryEntries(): List<GlossaryEntry>
}