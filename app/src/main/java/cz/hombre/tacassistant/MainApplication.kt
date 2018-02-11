package cz.hombre.tacassistant

import android.app.Application
import cz.hombre.tacassistant.di.myModule
import org.koin.standalone.StandAloneContext

class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        // start Koin context
        StandAloneContext.startKoin(listOf(myModule))
    }
}