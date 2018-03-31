package cz.hombre.militaryReportsAssistant.dto

import java.io.Serializable
import java.util.*

data class ReportData(val name: String, val lines: Array<ReportLine>) : Serializable {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as ReportData

        if (name != other.name) return false
        if (!Arrays.equals(lines, other.lines)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = name.hashCode()
        result = 31 * result + Arrays.hashCode(lines)
        return result
    }
}

data class ReportLine(val description: String, val value: String) : Serializable {
    constructor(value: String) : this("", value)
}