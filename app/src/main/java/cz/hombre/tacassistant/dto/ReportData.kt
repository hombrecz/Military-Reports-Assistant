package cz.hombre.tacassistant.dto

import java.io.Serializable
import java.util.*

data class ReportData(val name: String, private val lines: Array<ReportLine>) : Serializable {

    private val SEPARATOR_DASH = " - "

    fun formattedReport(): String {
        val sb = StringBuilder()

        for (line in lines) {
            if (line.description.isNotEmpty()) {
                sb.append(line.description)
                sb.append(SEPARATOR_DASH)
            }
            if (line.value.isNotEmpty()) {
                sb.append(line.value)
            }
            if ((line.description.isNotEmpty() || line.value.isNotEmpty()) && (line != lines[lines.lastIndex])) {
                sb.appendln()
            }
        }

        return sb.toString()
    }

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