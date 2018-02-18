package cz.hombre.tacassistant.services

import android.content.Context
import cz.hombre.tacassistant.SEPARATOR_DASH
import cz.hombre.tacassistant.dto.ReportData
import cz.hombre.tacassistant.dto.ReportLine

interface EncodingService {

    fun formatReportText(report: ReportData): String

    fun encodeNumbersWithRAMROD(report: ReportData): ReportData

    fun encodeLettersWithSpellingAlphabet(report: ReportData): ReportData
}

class EncodingServiceImpl(applicationContext: Context, private val preferencesService: PreferencesService) : EncodingService {

    override fun formatReportText(report: ReportData): String {
        val sb = StringBuilder()
        val lines = report.lines

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

    override fun encodeNumbersWithRAMROD(report: ReportData): ReportData {
        var encodedLines: Array<ReportLine> = emptyArray()

        report.lines.forEach {
            encodedLines = encodedLines.plus(ReportLine(it.description, encodeStringWithRAMROD(it.value)))
        }

        return ReportData(report.name, encodedLines)
    }

    override fun encodeLettersWithSpellingAlphabet(report: ReportData): ReportData {
        var encodedLines: Array<ReportLine> = emptyArray()

        report.lines.forEach {
            encodedLines = encodedLines.plus(ReportLine(it.description, encodeStringWithSpelling(it.value)))
        }

        return ReportData(report.name, encodedLines)
    }

    private fun encodeStringWithRAMROD(text: String): String {
        val ramrod = preferencesService.getRamrod()
        var encoded: String = text

        if (ramrod.length == 10)
            for (i in 0..9) {
                encoded = encoded.replace("${i}", ramrod[i].toString())
            }

        return encoded
    }


    private fun encodeStringWithSpelling(text: String): String {
        //TODO - jak budu kódovat
        return text
    }

}