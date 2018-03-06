package cz.hombre.tacassistant.layout.reports

import android.support.design.widget.FloatingActionButton
import android.view.Gravity
import android.widget.EditText
import android.widget.ImageView
import cz.hombre.tacassistant.R
import cz.hombre.tacassistant.activity.reports.ReportPreviewActivity
import org.jetbrains.anko.*
import org.jetbrains.anko.constraint.layout.constraintLayout
import org.jetbrains.anko.design.coordinatorLayout
import org.jetbrains.anko.design.floatingActionButton

class ReportPreviewUI : AnkoComponent<ReportPreviewActivity> {

    private lateinit var textContent: EditText
    lateinit var ramrodButton: FloatingActionButton
    lateinit var spellingButton: FloatingActionButton
    lateinit var shareButton: FloatingActionButton

    override fun createView(ui: AnkoContext<ReportPreviewActivity>) = with(ui) {
        coordinatorLayout {
            id = R.id.report_preview
            constraintLayout {
                editText {
                    textContent = this
                    hintResource = R.string.report_preview_hint
                }
            }

            shareButton = floatingActionButton {
                id = R.id.share_fab
                imageResource = android.R.drawable.ic_menu_share
                useCompatPadding = true
                scaleType = ImageView.ScaleType.CENTER
            }.lparams {
                gravity = Gravity.BOTTOM or Gravity.END
                anchorGravity = Gravity.TOP or Gravity.END
                margin = dip(16)
            }

            ramrodButton = floatingActionButton {
                id = R.id.ramrod_fab
                imageResource = android.R.drawable.ic_media_play
                useCompatPadding = true
                scaleType = ImageView.ScaleType.CENTER
            }.lparams {
                gravity = Gravity.TOP or Gravity.END
                anchorId = shareButton.id
                anchorGravity = Gravity.TOP or Gravity.END
                margin = dip(4)
            }

            spellingButton = floatingActionButton {
                id = R.id.spelling_fab
                imageResource = android.R.drawable.ic_dialog_email
                useCompatPadding = true
                scaleType = ImageView.ScaleType.CENTER
            }.lparams {
                gravity = Gravity.TOP or Gravity.END
                anchorId = ramrodButton.id
                anchorGravity = Gravity.TOP or Gravity.END
                margin = dip(4)
            }
        }
    }

    fun setContent(content: String) {
        textContent.setText(content)
    }
}