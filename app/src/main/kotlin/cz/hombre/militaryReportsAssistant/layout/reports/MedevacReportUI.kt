package cz.hombre.militaryReportsAssistant.layout.reports

import android.support.design.widget.FloatingActionButton
import android.view.Gravity
import cz.hombre.militaryReportsAssistant.R
import cz.hombre.militaryReportsAssistant.R.string.*
import cz.hombre.militaryReportsAssistant.activity.reports.MedevacReport
import cz.hombre.militaryReportsAssistant.layout.components.*
import cz.hombre.militaryReportsAssistant.services.LocationService
import org.jetbrains.anko.*
import org.jetbrains.anko.design.coordinatorLayout
import org.jetbrains.anko.design.floatingActionButton

class MedevacReportUI(private val locationService: LocationService) : AnkoComponent<MedevacReport> {

    lateinit var previewButton: FloatingActionButton
    lateinit var line1: LocationInput
    lateinit var line2: DualTextInput
    lateinit var line3: MultipleLineInput
    lateinit var line4: CheckBoxInput
    lateinit var line5: MultipleLineInput
    lateinit var line6: RadioInput
    lateinit var line7: RadioInput
    lateinit var line8: MultipleLineInput
    lateinit var line9: CheckBoxInput

    override fun createView(ui: AnkoContext<MedevacReport>) = with(ui) {
        coordinatorLayout {
            id = R.id.medevac_report
            scrollView {
                verticalLayout {
                    line1 = locationInput(report_medevac_line_1, report_location_button, locationService, ui.owner)
                    line2 = dualTextInput(report_medevac_line_2, report_frequency, report_call_sign)
                    line3 = multipleLineInput(report_medevac_line_3, patientsByPrecedenceOptions())
                    line4 = checkBoxInput(report_medevac_line_4, specialEquipmentOptions())
                    line5 = multipleLineInput(report_medevac_line_5, patientsNumberOptions())
                    line6 = radioInput(report_medevac_line_6, pickupSiteSecurityOptions())
                    line7 = radioInput(report_medevac_line_7, pickupSiteMarkingOptions())
                    line8 = multipleLineInput(report_medevac_line_8, patientNationalityAndStatusOptions())
                    line9 = checkBoxInput(report_medevac_line_9, nbcContaminationOptions())
                }
            }.lparams(
                    width = matchParent,
                    height = matchParent
            )

            previewButton = floatingActionButton {
                imageResource = android.R.drawable.ic_dialog_email
                useCompatPadding = true
            }.lparams {
                gravity = Gravity.BOTTOM or Gravity.END
                margin = dip(16)
            }
        }
    }

    private fun patientsByPrecedenceOptions() = sortedMapOf(
            letter_a to report_medevac_line_3_alpha,
            letter_b to report_medevac_line_3_bravo,
            letter_c to report_medevac_line_3_charlie,
            letter_d to report_medevac_line_3_delta,
            letter_e to report_medevac_line_3_echo)

    private fun specialEquipmentOptions() = sortedMapOf(
            letter_a to report_medevac_line_4_alpha,
            letter_b to report_medevac_line_4_bravo,
            letter_c to report_medevac_line_4_charlie,
            letter_d to report_medevac_line_4_delta)

    private fun patientsNumberOptions() = sortedMapOf(
            letter_a to report_medevac_line_5_alpha,
            letter_l to report_medevac_line_5_lima)

    private fun pickupSiteSecurityOptions() = linkedMapOf(
            letter_n to report_medevac_line_6_alpha,
            letter_p to report_medevac_line_6_bravo,
            letter_e to report_medevac_line_6_charlie,
            letter_x to report_medevac_line_6_delta)

    private fun pickupSiteMarkingOptions() = sortedMapOf(
            letter_a to report_medevac_line_7_alpha,
            letter_b to report_medevac_line_7_bravo,
            letter_c to report_medevac_line_7_charlie,
            letter_d to report_medevac_line_7_delta,
            letter_e to report_medevac_line_7_echo)

    private fun patientNationalityAndStatusOptions() = sortedMapOf(
            letter_a to report_medevac_line_8_alpha,
            letter_b to report_medevac_line_8_bravo,
            letter_c to report_medevac_line_8_charlie,
            letter_d to report_medevac_line_8_delta,
            letter_e to report_medevac_line_8_echo)

    private fun nbcContaminationOptions() = linkedMapOf(
            letter_n to report_medevac_line_9_november,
            letter_b to report_medevac_line_9_biological,
            letter_c to report_medevac_line_9_chemical)
}