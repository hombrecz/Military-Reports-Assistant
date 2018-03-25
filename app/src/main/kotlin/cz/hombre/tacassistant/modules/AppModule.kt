package cz.hombre.tacassistant.modules

import cz.hombre.tacassistant.services.*
import cz.hombre.tacassistant.services.impl.*
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module.Module
import org.koin.dsl.module.applicationContext

val myModule: Module = applicationContext {
    bean { PreferencesServiceImpl(androidApplication().baseContext) as PreferencesService }
    bean { DateTimeServiceImpl(get()) as DateTimeService }
    bean { LocationServiceImpl(androidApplication().baseContext, get()) as LocationService }
    bean { EncodingServiceImpl(get()) as EncodingService }
    bean { LocaleServiceImpl(get()) as LocaleService }
    bean {DatabaseServiceImpl(androidApplication().baseContext) as DatabaseService}
}