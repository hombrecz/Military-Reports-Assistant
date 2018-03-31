package cz.hombre.militaryReportsAssistant.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import cz.hombre.militaryReportsAssistant.database.model.GlossaryEntry
import org.jetbrains.anko.db.INTEGER
import org.jetbrains.anko.db.ManagedSQLiteOpenHelper
import org.jetbrains.anko.db.PRIMARY_KEY
import org.jetbrains.anko.db.TEXT
import org.jetbrains.anko.db.createTable
import org.jetbrains.anko.db.dropTable

class DatabaseHelper(ctx: Context) : ManagedSQLiteOpenHelper(ctx, "MilitaryReportsAssistantDatabase", null, 1) {
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
