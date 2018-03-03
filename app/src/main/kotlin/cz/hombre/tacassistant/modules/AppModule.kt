package cz.hombre.tacassistant.modules

import cz.hombre.tacassistant.services.*
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module.Module
import org.koin.dsl.module.applicationContext

val myModule: Module = applicationContext {
    provide { DateTimeServiceImpl() as DateTimeService }
    provide { LocationServiceImpl(androidApplication().baseContext, get()) as LocationService }
    provide { PreferencesServiceImpl(androidApplication().baseContext) as PreferencesService }
    provide { EncodingServiceImpl(get()) as EncodingService }
}