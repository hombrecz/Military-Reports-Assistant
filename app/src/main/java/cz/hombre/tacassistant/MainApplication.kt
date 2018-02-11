package cz.hombre.tacassistant

import android.app.Application
import cz.hombre.tacassistant.di.myModule
import org.koin.android.ext.android.startKoin

class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin(this, listOf(myModule))

    }
}