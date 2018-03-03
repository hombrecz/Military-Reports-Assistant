package cz.hombre.tacassistant.layout.reports

import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.widget.Toolbar
import cz.hombre.tacassistant.R
import cz.hombre.tacassistant.R.string.main_welcome
import cz.hombre.tacassistant.activity.MainActivity
import org.jetbrains.anko.*
import org.jetbrains.anko.appcompat.v7.toolbar
import org.jetbrains.anko.constraint.layout.constraintLayout
import org.jetbrains.anko.design.coordinatorLayout
import org.jetbrains.anko.design.navigationView
import org.jetbrains.anko.design.themedAppBarLayout
import org.jetbrains.anko.support.v4._DrawerLayout
import org.jetbrains.anko.support.v4.drawerLayout

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
            createAppBar(ui)
            createNavigationView(ui)
        }
    }

    fun _DrawerLayout.createAppBar(ui: AnkoContext<MainActivity>) {
        coordinatorLayout {
            fitsSystemWindows = false

            themedAppBarLayout(R.style.AppTheme_AppBarOverlay) {
                toolbar = toolbar {
                    popupTheme = R.style.AppTheme_PopupOverlay
                    backgroundResource = R.color.primaryColor
                }.lparams(
                        width = matchParent,
                        height = wrapContent
                )
            }.lparams(
                    width = matchParent,
                    height = wrapContent
            )

            constraintLayout {
                textView(main_welcome)
            }
        }.lparams(
                width = matchParent,
                height = matchParent
        )
    }

    fun _DrawerLayout.createNavigationView(ui: AnkoContext<MainActivity>) {
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