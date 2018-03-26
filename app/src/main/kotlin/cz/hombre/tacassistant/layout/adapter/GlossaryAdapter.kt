package cz.hombre.tacassistant.layout.adapter

import android.graphics.Typeface
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.LinearLayout.HORIZONTAL
import cz.hombre.tacassistant.R
import cz.hombre.tacassistant.database.model.GlossaryEntry
import org.jetbrains.anko.*

class GlossaryAdapter(private val glossary: ArrayList<GlossaryEntry> = ArrayList()) : BaseAdapter() {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        return with(parent!!.context) {
            linearLayout {
                id = R.id.glossary_container
                lparams(width = matchParent, height = wrapContent)
                padding = dip(10)
                orientation = HORIZONTAL

                textView {
                    id = R.id.entry_name
                    text = glossary[position].name
                    textSize = 16f
                    typeface = Typeface.DEFAULT_BOLD
                    padding = dip(5)
                }

                textView {
                    id = R.id.entry_description
                    text = glossary[position].value
                    textSize = 16f
                    padding = dip(5)
                }
            }
        }
    }

    override fun getItem(position: Int): GlossaryEntry {
        return glossary[position]
    }

    override fun getItemId(position: Int): Long {
        return glossary[position].id.toLong()
    }

    override fun getCount(): Int {
        return glossary.size
    }

    fun add(entry: GlossaryEntry) {
        glossary.add(entry)
        notifyDataSetChanged()
    }

    fun delete(position: Int) {
        glossary.removeAt(position)
        notifyDataSetChanged()
    }

    fun addAll(entries: List<GlossaryEntry>) {
        glossary.addAll(entries)
        notifyDataSetChanged()
    }

    fun deleteAll() {
        glossary.clear()
        notifyDataSetChanged()
    }
}