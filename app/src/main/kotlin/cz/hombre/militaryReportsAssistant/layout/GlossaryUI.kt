package cz.hombre.militaryReportsAssistant.layout

import android.support.design.widget.FloatingActionButton
import android.view.Gravity
import android.view.View
import android.widget.ListView
import android.widget.TextView
import cz.hombre.militaryReportsAssistant.R
import cz.hombre.militaryReportsAssistant.activity.Glossary
import cz.hombre.militaryReportsAssistant.layout.adapter.GlossaryAdapter
import org.jetbrains.anko.*
import org.jetbrains.anko.design.coordinatorLayout
import org.jetbrains.anko.design.floatingActionButton

class GlossaryUI(private val glossaryAdapter: GlossaryAdapter) : AnkoComponent<Glossary> {

    lateinit var addButton: FloatingActionButton
    lateinit var hintView: TextView
    lateinit var glossaryList: ListView

    override fun createView(ui: AnkoContext<Glossary>): View = with(ui) {
        return coordinatorLayout {
            glossaryList = listView()
            hintView = textView(R.string.glossary_hint) {
                textSize = 20f

            }.lparams {
                margin = dip(16)
            }

            verticalLayout {
                glossaryList = listView {
                    adapter = glossaryAdapter
                }
            }.lparams {
                margin = dip(5)
            }

            addButton = floatingActionButton {
                imageResource = R.drawable.ic_add_fab
                useCompatPadding = true
            }.lparams {
                gravity = Gravity.BOTTOM or Gravity.END
                margin = dip(16)
            }
        }
    }
}