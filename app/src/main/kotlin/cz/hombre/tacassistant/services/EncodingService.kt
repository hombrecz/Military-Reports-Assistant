package cz.hombre.tacassistant.services

import cz.hombre.tacassistant.dto.ReportData

interface EncodingService {

    fun formatReportText(report: ReportData): String

    fun encodeNumbersWithRAMROD(report: ReportData): ReportData

    fun encodeLettersWithSpellingAlphabet(report: ReportData): ReportData
}