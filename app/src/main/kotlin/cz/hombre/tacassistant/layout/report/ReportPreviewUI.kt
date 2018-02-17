package cz.hombre.tacassistant.layout.report

import android.support.compat.R.id.normal
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

    lateinit var ramrodButton: FloatingActionButton
    lateinit var spellingButton: FloatingActionButton
    lateinit var textContent: EditText

    override fun createView(ui: AnkoContext<ReportPreviewActivity>) = with(ui) {
        coordinatorLayout {
            constraintLayout {
                editText {
                    textContent = this
                    hintResource = R.string.report_preview_hint
                }
            }

            floatingActionButton {
                id = R.id.spelling_fab
                imageResource = android.R.drawable.ic_dialog_email
                size = normal
                scaleType = ImageView.ScaleType.CENTER
                useCompatPadding = true
                spellingButton = this
            }.lparams {
                gravity = Gravity.BOTTOM or Gravity.END
                anchorGravity = Gravity.TOP or Gravity.END
                margin = dip(16)
            }

            floatingActionButton {
                imageResource = android.R.drawable.ic_media_play
                size = normal
                scaleType = ImageView.ScaleType.CENTER
                useCompatPadding = true
                ramrodButton = this
            }.lparams {
                gravity = Gravity.TOP or Gravity.END
                anchorId = spellingButton.id
                anchorGravity = Gravity.TOP or Gravity.END
                margin = dip(4)
            }
        }
    }

    fun setContent(content: String) {
        textContent.setText(content)
    }
}