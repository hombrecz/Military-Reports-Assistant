package cz.hombre.militaryReportsAssistant.layout

import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.widget.Toolbar
import android.view.Gravity
import android.widget.LinearLayout
import cz.hombre.militaryReportsAssistant.R
import cz.hombre.militaryReportsAssistant.activity.MainActivity
import org.jetbrains.anko.AnkoComponent
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.AnkoViewDslMarker
import org.jetbrains.anko.appcompat.v7.toolbar
import org.jetbrains.anko.backgroundResource
import org.jetbrains.anko.design._CoordinatorLayout
import org.jetbrains.anko.design.coordinatorLayout
import org.jetbrains.anko.design.navigationView
import org.jetbrains.anko.design.themedAppBarLayout
import org.jetbrains.anko.dip
import org.jetbrains.anko.imageResource
import org.jetbrains.anko.imageView
import org.jetbrains.anko.linearLayout
import org.jetbrains.anko.matchParent
import org.jetbrains.anko.padding
import org.jetbrains.anko.support.v4._DrawerLayout
import org.jetbrains.anko.support.v4.drawerLayout
import org.jetbrains.anko.textView
import org.jetbrains.anko.wrapContent

class MainUI : AnkoComponent<MainActivity> {

    lateinit var drawerLayout: DrawerLayout
    lateinit var navigationView: NavigationView
    lateinit var toolbar: Toolbar

    override fun createView(ui: AnkoContext<MainActivity>) = with(ui) {
        drawerLayout {
            fitsSystemWindows = true
            drawerLayout = this
            id = R.id.main_activity
            lparams(width = matchParent, height = matchParent)

            createMainLayoutWithAppBar()
            createNavigationView(ui)
        }
    }

    private fun _DrawerLayout.createMainLayoutWithAppBar() {
        coordinatorLayout {
            fitsSystemWindows = false
            addAppBar()
            addContent()

        }.lparams(
                width = matchParent,
                height = matchParent
        )
    }

    private fun @AnkoViewDslMarker _CoordinatorLayout.addContent() {
        linearLayout {
            imageView {
                imageResource = R.mipmap.ic_tactical_assistant
                padding = dip(10)
            }
            textView(R.string.main_welcome) {
                textSize = 16f
                gravity = Gravity.CENTER
            }
            textView(R.string.main_description) {
                textSize = 12f
                gravity = Gravity.CENTER
                width = dip(250)
            }
            gravity = Gravity.CENTER
            orientation = LinearLayout.VERTICAL
        }.lparams(
                width = matchParent,
                height = matchParent
        )
    }

    private fun @AnkoViewDslMarker _CoordinatorLayout.addAppBar() {
        themedAppBarLayout {
            toolbar = toolbar {
                backgroundResource = R.color.primaryColor
            }.lparams(
                    width = matchParent,
                    height = wrapContent
            )
        }.lparams(
                width = matchParent,
                height = wrapContent
        )
    }

    private fun _DrawerLayout.createNavigationView(ui: AnkoContext<MainActivity>) {
        navigationView = navigationView {
            fitsSystemWindows = true
            setNavigationItemSelectedListener(ui.owner)
            inflateHeaderView(R.layout.nav_header_main)
            inflateMenu(R.menu.activity_main_drawer)
        }.lparams(
                height = matchParent,
                gravity = GravityCompat.START
        )
    }
}