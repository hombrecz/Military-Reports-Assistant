package cz.hombre.tacassistant.services.impl

import cz.hombre.tacassistant.SpellingAlphabets
import cz.hombre.tacassistant.Utilities
import cz.hombre.tacassistant.dto.ReportData
import cz.hombre.tacassistant.dto.ReportLine
import cz.hombre.tacassistant.services.EncodingService
import cz.hombre.tacassistant.services.PreferencesService

class EncodingServiceImpl(private val preferencesService: PreferencesService) : EncodingService {

    override fun formatReportText(report: ReportData): String {
        val sb = StringBuilder()
        val lines = report.lines

        for (line in lines) {
            if (line.description.isNotEmpty()) {
                sb.append(line.description)
                sb.append(Utilities.SEPARATOR_DASH)
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
                encoded = encoded.replace("$i", ramrod[i].toString())
            }

        return encoded
    }

    private fun encodeStringWithSpelling(text: String): String {
        val alphabetPreference = preferencesService.getPhoneticAlphabet()
        val alphabet: Map<String, String>

        alphabet = when (alphabetPreference) {
            CZECH_ALPHABET_VALUE -> SpellingAlphabets.CZECH
            NATO_ALPHABET_VALUE -> SpellingAlphabets.NATO
            else -> SpellingAlphabets.NATO
        }

        var encoded = String()

        for ((index, char) in text.withIndex()) {

            val symbol: String = when {
                isCzechLetterChFirstSymbol(alphabetPreference, index, text) -> "ch"
                isCzechLetterChSecondSymbol(alphabetPreference, text, index) -> String()
                else -> char.toLowerCase().toString()
            }

            encoded += alphabet.getOrElse(symbol.toLowerCase()) { symbol }
        }

        return encoded + Utilities.NEW_LINE
    }

    private fun isCzechLetterChFirstSymbol(alphabet: Int, index: Int, text: String) =
            alphabet == CZECH_ALPHABET_VALUE && (index != text.length - 1) && text[index].toLowerCase() == 'c' && text[index + 1].toLowerCase() == 'h'

    private fun isCzechLetterChSecondSymbol(alphabet: Int, text: String, index: Int) =
            alphabet == CZECH_ALPHABET_VALUE && (index != 0) && text[index - 1].toLowerCase() == 'c' && text[index].toLowerCase() == 'h'

}