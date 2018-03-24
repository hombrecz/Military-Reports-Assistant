package cz.hombre.tacassistant.modules

import cz.hombre.tacassistant.services.*
import cz.hombre.tacassistant.services.impl.*
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module.Module
import org.koin.dsl.module.applicationContext

val myModule: Module = applicationContext {
    provide { PreferencesServiceImpl(androidApplication().baseContext) as PreferencesService }
    provide { DateTimeServiceImpl(get()) as DateTimeService }
    provide { LocationServiceImpl(androidApplication().baseContext, get()) as LocationService }
    provide { EncodingServiceImpl(get()) as EncodingService }
    provide { LocaleServiceImpl(get()) as LocaleService }
}