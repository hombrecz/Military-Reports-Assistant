package cz.hombre.militaryReportsAssistant.services

import cz.hombre.militaryReportsAssistant.dto.ReportData

interface EncodingService {

    fun formatReportText(report: ReportData): String

    fun encodeNumbersWithRAMROD(report: ReportData): ReportData

    fun encodeLettersWithSpellingAlphabet(report: ReportData): ReportData
}