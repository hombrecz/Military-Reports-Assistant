package cz.hombre.militaryReportsAssistant.services.impl

import android.content.Context
import cz.hombre.militaryReportsAssistant.GlossaryDefault
import cz.hombre.militaryReportsAssistant.database.DatabaseHelper
import cz.hombre.militaryReportsAssistant.database.model.GlossaryEntry
import cz.hombre.militaryReportsAssistant.services.DatabaseService
import cz.hombre.militaryReportsAssistant.services.LocaleService
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select

class DatabaseServiceImpl(private val applicationContext: Context, private val localeService: LocaleService) : DatabaseService {

    private val database = DatabaseHelper.getInstance(applicationContext)

    private var maxId = 0

    override fun addGlossaryEntry(entry: GlossaryEntry) {
        database.use {
            insert(GlossaryEntry.TABLE_NAME,
                    GlossaryEntry.COLUMN_ID to entry.id,
                    GlossaryEntry.COLUMN_NAME to entry.name,
                    GlossaryEntry.COLUMN_VALUE to entry.value
            )
        }
        setHighestId(entry.id)
    }

    override fun deleteGlossaryEntry(entry: GlossaryEntry) {
        database.use { delete(GlossaryEntry.TABLE_NAME, whereClause = "${GlossaryEntry.COLUMN_ID} = ${entry.id}") }
        reduceHighestId(entry.id)
    }

    override fun getAllGlossaryEntries(): List<GlossaryEntry> {
        return database.use {
            select(GlossaryEntry.TABLE_NAME)
                    .parseList(classParser())
        }
    }

    override fun addDefaultGlossaryEntries(): List<GlossaryEntry> {
        var i = maxId + 1
        val added = ArrayList<GlossaryEntry>()

        GlossaryDefault.ENTRIES.keys.forEach { key ->
            val description = localeService.getStringForActualLocale(applicationContext, GlossaryDefault.ENTRIES[key]!!)
            val newEntry = GlossaryEntry(i++, key, description)
            addGlossaryEntry(newEntry)
            added.add(newEntry)
        }

        return added
    }

    private fun setHighestId(newId: Int) {
        if (newId > maxId) {
            maxId = newId
        }
    }

    private fun reduceHighestId(removedId: Int) {
        if (removedId == maxId) {
            maxId = removedId - 1
        }
    }

}