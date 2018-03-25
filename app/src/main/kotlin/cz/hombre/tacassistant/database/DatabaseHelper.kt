package cz.hombre.tacassistant.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import cz.hombre.tacassistant.database.model.GlossaryEntry
import org.jetbrains.anko.db.*

class DatabaseHelper(ctx: Context) : ManagedSQLiteOpenHelper(ctx, "TacAssistantDatabase", null, 1) {
    companion object {
        private var instance: DatabaseHelper? = null

        @Synchronized
        fun getInstance(context: Context): DatabaseHelper {
            if (instance == null) {
                instance = DatabaseHelper(context.applicationContext)
            }
            return instance!!
        }
    }

    override fun onCreate(database: SQLiteDatabase) {
        database.createTable(GlossaryEntry.TABLE_NAME, true, GlossaryEntry.COLUMN_ID to INTEGER + PRIMARY_KEY, GlossaryEntry.COLUMN_NAME to TEXT, GlossaryEntry.COLUMN_VALUE to TEXT)
    }

    override fun onUpgrade(database: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        database.dropTable(GlossaryEntry.TABLE_NAME, true)
    }
}
