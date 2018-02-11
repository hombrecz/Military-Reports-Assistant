package cz.hombre.tacassistant.services

import android.view.View

interface ReportFormService {
    fun setHideableItem(trigger: View, content: View)
}

class ReportFormServiceImpl() : ReportFormService {

    override fun setHideableItem(trigger: View, content: View) {
        trigger.setOnClickListener {
            if (content.visibility == View.VISIBLE) {
                content.visibility = View.GONE
            } else {
                content.visibility = View.VISIBLE
            }
        }
    }

}