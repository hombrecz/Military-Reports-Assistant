package cz.hombre.tacassistant.services.impl

import android.content.Context
import cz.hombre.tacassistant.database.DatabaseHelper
import cz.hombre.tacassistant.database.model.GlossaryEntry
import cz.hombre.tacassistant.services.DatabaseService
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select

class DatabaseServiceImpl(applicationContext: Context) : DatabaseService {

    private val database = DatabaseHelper.getInstance(applicationContext)

    override fun addGlossaryEntry(entry: GlossaryEntry) {
        database.use {
            insert(GlossaryEntry.TABLE_NAME,
                    GlossaryEntry.COLUMN_ID to entry.id,
                    GlossaryEntry.COLUMN_NAME to entry.name,
                    GlossaryEntry.COLUMN_VALUE to entry.value
            )
        }
    }

    override fun deleteGlossaryEntry(entry: GlossaryEntry) {
        database.use { delete(GlossaryEntry.TABLE_NAME, whereClause = "${GlossaryEntry.COLUMN_ID} = ${entry.id}") }
    }

    override fun getAllGlossaryEntries(): List<GlossaryEntry> {
        return database.use {
            select(GlossaryEntry.TABLE_NAME)
                    .parseList(classParser<GlossaryEntry>())
        }
    }
}