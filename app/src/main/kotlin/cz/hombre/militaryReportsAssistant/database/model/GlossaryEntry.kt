package cz.hombre.militaryReportsAssistant.database.model

data class GlossaryEntry(val id: Int, val name: String, val value: String) {
    companion object {
        val TABLE_NAME = "Glossary"
        val COLUMN_ID = "id"
        val COLUMN_NAME = "name"
        val COLUMN_VALUE = "value"
    }
}